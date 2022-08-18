import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";
import {AuthentmeanService} from "./authentmean.service";


@Component({
  selector: 'app-authentmean',
  templateUrl: './authentmean.component.html',
  styleUrls: ['./authentmean.component.css'],

})
export class AuthentmeanComponent implements OnInit {

  means: any;
  meanForm: any;
  filename: string = "";
  validMessage: string = "";

  constructor(private meanService: AuthentmeanService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllMeans();
  }

  sendMeanData() {
    let meanData = this.meanForm.value;
    if (this.meanForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.meanService.sendData(meanData).subscribe(
        response => {
          this.meanForm.reset();
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
    this.meanForm = this.formBuilder.group({
      createdBy: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy:['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
    });
  }

  getAllMeans() {
    this.meanService.getAllMeans().subscribe(means => {
      this.means = means;
    });
  }

  downloadFile() {
    this.meanService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
