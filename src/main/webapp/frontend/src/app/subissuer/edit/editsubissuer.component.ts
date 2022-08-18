import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {EditService} from "./edit.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditsubissuerComponent implements OnInit {

  code: any;
  subIssuerForm: any;
  filename: string = "";
  subIssuer: any;

  constructor(
    private service: EditService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get("code");
    this.getByCode(this.code);
    this.initializeForm();
  }

  updateSubIssuerData() {
    console.log(this.subIssuerForm);
    let subIssuerData = this.subIssuerForm.value;
    this.service.edit(subIssuerData).subscribe(response => {
      this.filename = response;
    });
  }

  getByCode(code: any) {
    this.service.getByCodeSubissuer(code).subscribe(resposne => {
      this.subIssuer = resposne;
    });
  }

  initializeForm() {
    this.subIssuerForm = this.formBuilder.group({
      acsId: ['', Validators.required],
      authenticationTimeOut: [0, [Validators.required, Validators.pattern("^[0-9]*$")]],
      defaultLanguage: ['', Validators.required],
      code: ['', [Validators.required, Validators.pattern("^[0-9]*$")]],
      codeSvi: ['', Validators.required],
      currencyCode: ['', Validators.required],
      name: ['', Validators.required],
      label: ['', Validators.required],
      authentMeans: ['', Validators.required],
      personnalDataStorage: [0, Validators.required],
      resetBackupsIfSuccess: [0, Validators.required],
      resetChoicesIfSuccess: [0, Validators.required],
      manageBackupsCombinedAmounts: [0, Validators.required],
      manageChoicesCombinedAmounts: [0, Validators.required],
      hubMaintenanceModeEnabled: [0, Validators.required]
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
