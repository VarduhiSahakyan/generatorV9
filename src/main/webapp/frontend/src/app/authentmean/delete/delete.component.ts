import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {DeleteService} from "./delete.service";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class AuthentMeanDeleteComponent implements OnInit {

  id: any;
  authentMeans: any;
  filename: string = "";
  authentForm: any;

  constructor(
    private service: DeleteService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.id = this.router.snapshot.paramMap.get("id");
    this.initializeForm();
    this.getById(this.id);
  }

  deleteAuthentMeans() {
    console.log(this.authentForm);
    this.service.deleteAuthentMeansService(this.id).subscribe(response => {
      this.filename = response;
    });
  }

  downloadFile() {
    this.service.downloadFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }


  getById(id: any) {
    this.service.getAuthentMeansById(id)
      .subscribe(response => {
        this.authentMeans = response;
      });
  }

  initializeForm() {
    this.authentForm = this.formBuilder.group({
      name: ['', Validators.required],
      createdBy: ['', Validators.required],
      id: [0, Validators.required]
    });
  }

}
