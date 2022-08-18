import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import * as saveAs from 'file-saver';
import { Profileset } from '../profileset';
import { ProfilesetEditService } from './profileset-edit.service';

@Component({
  selector: 'app-profileset-edit',
  templateUrl: './profileset-edit.component.html',
  styleUrls: ['./profileset-edit.component.css']
})
export class ProfilesetEditComponent implements OnInit {

  id:any;
  profileset!: any;
  profileSetEditForm!: FormGroup;

  fileName: string = "";

  myDate = new Date();

  constructor(
    private profileSetEditService: ProfilesetEditService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.show(this.id);
    this.initializeForm();
  }

  initializeForm(){
    this.profileSetEditForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      creationDate:['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy:  ['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required]
    });
  }

  show(id:any){
     this.profileSetEditService.getById(id).subscribe(response => {
      this.profileset = response
    });
  }

  onSubmit(data:any){
    this.profileSetEditService.update(data.value,this.id).subscribe(response => {
        this.fileName = response
    });
  }

  downloadFile(){
    this.profileSetEditService.downloadSqlFile(this.fileName).subscribe(file => saveAs(file,this.fileName));
  }


}
