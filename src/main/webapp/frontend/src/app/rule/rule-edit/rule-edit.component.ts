import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {RuleEditService} from "./rule-edit.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-rule-edit',
  templateUrl: './rule-edit.component.html',
  styleUrls: ['./rule-edit.component.css']
})
export class RuleEditComponent implements OnInit {

  id: any;
  ruleForm: any;
  filename: string = "";
  rule: any;

  constructor(private service: RuleEditService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.getById(this.id);
    this.initializeForm();

  }

  updateRuleData() {
    this.id = this.route.snapshot.paramMap.get("id");
    let ruleData = this.ruleForm.value;
    this.service.editRule(ruleData).subscribe(response => {
      this.filename = response;
    });
  }

  getById(id: any){
    this.service.getRuleById(id).subscribe(response =>{
      this.rule = response
    })
  }

  initializeForm() {
    this.ruleForm = this.formBuilder.group({
      id: [0, Validators.required],
      createdBy: ['', Validators.required],
      creationDate: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      lastUpdateDate: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
      orderRule: [0, Validators.required]
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
