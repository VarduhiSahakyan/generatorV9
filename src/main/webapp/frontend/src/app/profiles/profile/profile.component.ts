import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';
import { AuthentmeanService } from 'src/app/authentmean/authentmean/authentmean.service';
import { IssuerService } from 'src/app/issuer/issuer.service';
import { SubIssuerService } from 'src/app/subissuer/subIssuer.service';
import { ProfileService } from './profile.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  issuers:any = [];
  subIssuers:any = [];
  authMeans:any =[];
  profiles:any = [];
  fileName: string = "";
  profileForm: any;
  myDate = new Date();
  validMessage: string = "";

  constructor(
    private issuerService: IssuerService,
    private authentMeansService: AuthentmeanService,
    private subIssuerService: SubIssuerService,
    private profileService: ProfileService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.index();
    this.initializeForm();
  }

  onSubmit(){
    let profileData = this.profileForm.value;
    if (this.profileForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.profileService.create(profileData).subscribe(
        response => {
          this.profileForm.reset();
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

  initializeForm(){
    this.profileForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      creationDate:['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy:  ['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required],
      maxAttempts:['',Validators.required],
      dataEntryFormat: ['', Validators.required],
      dataEntryAllowedPattern: ['', Validators.required],
      authentMeans: ['',Validators.required],
      subissuer:['',Validators.required]
    });
  }

  index(){
    this.issuerService.getAllIssuer().subscribe(response => {this.issuers = response});
    this.authentMeansService.getAllMeans().subscribe(response => {this.authMeans = response});
    this.subIssuerService.getAllSubIssuer().subscribe(response => {this.subIssuers = response});
    this.profileService.getAllProfiles().subscribe(response => {this.profiles = response});
  }



  downloadFile(){
    this.profileService.downloadSqlFile(this.fileName).subscribe(file => saveAs(file, this.fileName));
  }

}
