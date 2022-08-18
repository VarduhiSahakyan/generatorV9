import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';
import { SubIssuerService } from 'src/app/subissuer/subIssuer.service';
import {ProfilesetService} from "./profileset.service";

@Component({
  selector: 'app-profileset',
  templateUrl: './profileset.component.html',
  styleUrls: ['./profileset.component.css']
})
export class ProfilesetComponent implements OnInit {

  profileSets : any = [];
  subIssuers: any = [];
  profileSetForm:any;
  fileName: string = "";
  validMessage: string = "";


  constructor(
    private profileSetService: ProfilesetService,
    private subIssuerService: SubIssuerService,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    this.index();
    this.initializeForm();
  }

  onSubmit(){
    let setData = this.profileSetForm.value;
    if (this.profileSetForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.profileSetService.create(setData).subscribe(
        response => {
          this.profileSetForm.reset();
          this.fileName = response;
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

  index(){
    this.profileSetService.getAllProfileSets().subscribe(response => {this.profileSets = response});
    this.subIssuerService.getAllSubIssuer().subscribe(response => {this.subIssuers = response});
  }

  initializeForm(){
    this.profileSetForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy:  ['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required],
      subissuer:['',Validators.required]
    });
  }



  downloadFile(){
    this.profileSetService.downloadSqlFile(this.fileName).subscribe(file => saveAs(file,this.fileName));
  }
}
