import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EditService} from "./edit.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import * as saveAs from 'file-saver';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  code:any;

  // @ts-ignore
  issuerEditForm: FormGroup;
  filename: string = "";

  issuer: any;
  myDate = new Date();

  constructor(
    private service: EditService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get("id");
    this.initializeForm();
    this.find(this.code);
  }

  initializeForm(){
    this.issuerEditForm = this.formBuilder.group({
      code: ['', Validators.required],
      createdBy: ['', Validators.required],
      creationDate:['', Validators.required],
      name: ['', Validators.required],
      updateState:['',Validators.required],
      label:['',Validators.required],
      availaibleAuthentMeans: ['', Validators.required]
    });
  }

  find(id:any){
     this.service.findById(id).subscribe(resposne => {this.issuer = resposne});
  }

  onEdit(data:any){
     this.service.edit(data.value,this.code).subscribe(response => {
         this.filename = response;
     });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
