import { Component, OnInit } from '@angular/core';
import {IssuerService} from "./issuer.service";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-issuer',
  templateUrl: './issuer.component.html',
  styleUrls: ['./issuer.component.css']
})
export class IssuerComponent implements OnInit {

  issuers: any = [];
  filename: string ="";
  issuer: string = '';
  id!: string | null;
  issuerForm: any;
  validMessage: string = "";


  constructor(
    private issuerService: IssuerService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.getAllIssuer();
    this.initializeForm();
  }

  onSubmit(){
    let issuerData = this.issuerForm.value;
    if (this.issuerForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.issuerService.sendIssuerData(issuerData).subscribe(
        response => {
          this.issuerForm.reset();
          this.filename = response;
          return true;
        },
        error => {
          return (error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

  initializeForm(){
    this.issuerForm = this.formBuilder.group({
      code: ['', Validators.required],
      createdBy: ['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required],
      label:['',Validators.required],
      availaibleAuthentMeans: ['', Validators.required]
    });
  }

  getAllIssuer(){
    this.issuerService.getAllIssuer().subscribe(issuers => {
       this.issuers = issuers;
    });
  }

  downloadFile() {
    this.issuerService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
