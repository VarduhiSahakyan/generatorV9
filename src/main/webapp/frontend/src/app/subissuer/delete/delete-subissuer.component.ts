import {Component, OnInit} from '@angular/core';
import {DeleteService} from "./delete.service";
import {ActivatedRoute} from "@angular/router";
import {saveAs} from "file-saver";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteSubissuerComponent implements OnInit {

  code: any;
  subIssuer: any;
  filename: string = "";
  subIssuerForm: any;
  issuer_id: any;
  crypto_id: any;

  constructor(
    private service: DeleteService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.code = this.router.snapshot.paramMap.get("code");
    this.issuer_id = this.router.snapshot.paramMap.get("issuer_id");
    this.crypto_id = this.router.snapshot.paramMap.get("crypto_id")
    this.initializeForm();
    this.getByCode(this.code);
  }

  delete() {
    console.log(this.subIssuerForm);
    this.service.deleteSubissuer( this.code, this.issuer_id, this.crypto_id).subscribe(response => {
      this.filename = response;
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }


  getByCode(code: any) {
    this.service.getByCodeSubissuer(code).subscribe(resposne => {
      this.subIssuer = resposne;
    });
  }

  initializeForm() {
    this.subIssuerForm = this.formBuilder.group({
      name: ['', Validators.required],
      label: ['', Validators.required],
      code: ['', Validators.required]
    });
  }
}

