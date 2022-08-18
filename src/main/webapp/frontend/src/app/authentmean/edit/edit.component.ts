import {Component, OnInit} from '@angular/core';
import {ImageeditService} from "../../image/edit/imageedit.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {EditService} from "./edit.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class AuthentMeanEditComponent implements OnInit {

  id: any;
  meanForm: any;
  filename: string = "";
  mean: any;

  constructor(
    private service: EditService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.getMeanById(this.id);
    this.initializeForm();
  }


  updateMeanData() {
    this.id = this.route.snapshot.paramMap.get("id");
    let meanData = this.meanForm.value;
    this.service.edit(meanData).subscribe(response => {
      this.filename = response;
    });
  }

  getMeanById(id: any) {
    this.service.getMeanById(id).subscribe(response => {
      this.mean = response;
    });
  }

  initializeForm() {
    this.meanForm = this.formBuilder.group({
      id: ['', Validators.required],
      createdBy: ['', Validators.required],
      creationDate: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required]
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
