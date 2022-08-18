import {Component, OnInit} from '@angular/core';
import {ImageService} from "./image.service";
import {FormBuilder, Validators} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.css']
})
export class ImageComponent implements OnInit {

  images: any;
  imageForm: any;
  filename: string = "";
  validMessage: string = "";


  constructor(private imageService: ImageService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initializeForm();
    this.getAllImages();
  }

  sendImageData() {
    let imageData = this.imageForm.value;
    if (this.imageForm.valid) {
      this.validMessage = "Insert query submitted. You can insert again!";
      this.imageService.sendData(imageData).subscribe(
        response => {
          this.imageForm.reset();
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
    this.imageForm = this.formBuilder.group({
      id: [0, Validators.required],
      createdBy: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
      binaryData: ['', Validators.required],
      relativePath: ['', Validators.required]
    });
  }

  getAllImages() {
    this.imageService.getAllImage().subscribe(image => {
      this.images = image;
    });
  }

  downloadFile() {
    this.imageService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
