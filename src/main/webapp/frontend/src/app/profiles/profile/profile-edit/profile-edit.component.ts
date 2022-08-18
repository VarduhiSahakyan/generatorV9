import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { saveAs } from 'file-saver';
import { AuthentmeanService } from 'src/app/authentmean/authentmean/authentmean.service';
import { SubIssuerService } from 'src/app/subissuer/subIssuer.service';
import { ProfileEditServiceService } from './profile-edit-service.service';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  id: any;
  profile:any;

  subIssuers:any = [];
  authMeans:any =[];

  fileName: string ="";

  profileEditForm: any;

  myDate = new Date();

  constructor(
    private service: ProfileEditServiceService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private authentMeansService: AuthentmeanService,
    private subIssuerService: SubIssuerService,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.getOneProfile(this.id);
    this.initializeForm();
  }

  initializeForm() {
    this.profileEditForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      creationDate:['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy:  ['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required],
      maxAttempts:['',Validators.required],
      dataEntryFormat: ['', Validators.required],
      dataEntryAllowedPattern: ['', Validators.required]
    });
  }

  getOneProfile(id: any) {
    this.service.getProfileById(id).subscribe(response => {
      this.profile = response;
      console.log(response);
    });
  }

  onEdit(profile: any) {
    this.service.edit(profile.value,this.id).subscribe(response => {
       this.fileName = response;
    })
  }


  downloadFile(){
    this.service.downloadSqlFile(this.fileName).subscribe(file => saveAs(file,this.fileName));
  }

}
