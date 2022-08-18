import { Component, OnInit } from '@angular/core';
import {RuleDeleteService} from "./rule-delete.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-delete',
  templateUrl: './rule-delete.component.html',
  styleUrls: ['./rule-delete.component.css']
})
export class RuleDeleteComponent implements OnInit {

  id: any ;
  rule: any;
  filename: string = "";
  ruleForm: any;
  profile_id: any;


  constructor(
    private service: RuleDeleteService,
    private router: ActivatedRoute,
    private formBuilder: FormBuilder,

  ) { }

  ngOnInit(): void {
    this.id = this.router.snapshot.paramMap.get("id");
    this.profile_id = this.router.snapshot.paramMap.get("profile_id");
    this.getById(this.id);
    this.initializeForm();
  }

  deleteRule(){
    console.log(this.rule);
    this.service.deleteRule( this.id, this.profile_id).subscribe(response =>{
      this.filename = response
    })
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

  getById(id: any){
    this.service.getById(id).subscribe(response =>{
      this.rule = response
    })
  }

  initializeForm() {
    this.ruleForm = this.formBuilder.group({
      id: ['', Validators.required]
    });
  }

}
