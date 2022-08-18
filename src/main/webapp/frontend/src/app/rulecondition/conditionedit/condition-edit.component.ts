import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {ConditionEditService} from "./condition-edit.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-conditionedit',
  templateUrl: './condition-edit.component.html',
  styleUrls: ['./condition-edit.component.css']
})
export class ConditionEditComponent implements OnInit {

  id: any;
  conditionForm: any;
  filename: string = "";
  condition: any;

  constructor(
    private service: ConditionEditService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.getById(this.id);
    this.initializeForm();
  }

  updateCondition() {
    this.id = this.route.snapshot.paramMap.get("id");
    let conditionData = this.conditionForm.value;
    this.service.editCondition(conditionData).subscribe(response =>{
      this.filename = response;
    });
  }

  getById(id: any){
    this.service.getConditionById(id).subscribe(response =>{
      this.condition = response
    })
  }

  initializeForm() {
    this.conditionForm = this.formBuilder.group({
      id: [0, Validators.required],
      createdBy: ['', Validators.required],
      creationDate: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      lastUpdateDate: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required]
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
