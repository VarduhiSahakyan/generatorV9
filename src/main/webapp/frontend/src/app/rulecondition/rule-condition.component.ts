import {Component, OnInit} from '@angular/core';
import {RuleConditionService} from "./rule-condition.service";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {RuleService} from "../rule/rule.service";

@Component({
  selector: 'app-rulecondition',
  templateUrl: './rule-condition.component.html',
  styleUrls: ['./rule-condition.component.css']
})
export class RuleConditionComponent implements OnInit {

  conditions: any;
  conditionForm: any;
  rules: any;
  filename: string = "";
  validMessage: string = "";

  constructor(private conditionService: RuleConditionService,
              private formBuilder: FormBuilder,
              private ruleService: RuleService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllConditions();
    this.index();
  }

  sendRuleConditionData(){
    let conditionData = this.conditionForm.value;
    if (this.conditionForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.conditionService.sendData(conditionData).subscribe(
        response => {
          this.conditionForm.reset();
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

  initializeForm() {
    this.conditionForm = this.formBuilder.group({
      description: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
      rule_id: ['', Validators.required]
    });
  }

  getAllConditions(){
    this.conditionService.getAllConditions().subscribe(condition =>{
      this.conditions = condition;
    });
  }

  downloadFile() {
    this.conditionService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

  index(){
    this.ruleService.getAllRules().subscribe(response => {this.rules = response});
  }
}
