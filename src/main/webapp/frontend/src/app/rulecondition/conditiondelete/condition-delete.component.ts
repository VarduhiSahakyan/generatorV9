import { Component, OnInit } from '@angular/core';
import {ConditionDeleteService} from "./condition-delete.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-conditiondelete',
  templateUrl: './condition-delete.component.html',
  styleUrls: ['./condition-delete.component.css']
})
export class ConditionDeleteComponent implements OnInit {
  id: any ;
  condition: any;
  filename: string = "";
  conditionForm: any;

  constructor(
    private service: ConditionDeleteService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.id = this.router.snapshot.paramMap.get("id");
    this.getById(this.id);
    this.initializeForm();
  }

  deleteCondition(){
    console.log(this.condition);
    this.service.deleteCondition( this.id).subscribe(response =>{
      this.filename = response
    })
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

  getById(id: any){
    this.service.getById(id).subscribe(response =>{
      this.condition = response
    })
  }

  initializeForm() {
    this.conditionForm = this.formBuilder.group({
      id: ['', Validators.required]
    });
  }

}
