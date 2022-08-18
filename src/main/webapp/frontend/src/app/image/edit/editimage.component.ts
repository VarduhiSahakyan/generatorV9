import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, Validators} from "@angular/forms";
import {ImageeditService} from "./imageedit.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-editimage',
  templateUrl: './editimage.component.html',
  styleUrls: ['./editimage.component.css']
})
export class EditimageComponent implements OnInit {

  id: any;
  imageForm: any;
  filename: string = "";
  image: any;

  constructor(
    private service: ImageeditService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
    this.getById(this.id);
    this.initializeForm();
  }


  updateImageData() {
    this.id = this.route.snapshot.paramMap.get("id");
    let imageData = this.imageForm.value;
    this.service.edit(imageData).subscribe(response => {
      this.filename = response;
    });
  }

  getById(id: any) {
    this.service.getImageById(id).subscribe(response => {
      this.image = response;
    });
  }

  initializeForm() {
    this.imageForm = this.formBuilder.group({
      id: [0, Validators.required],
      createdBy: ['', Validators.required],
      creationDate: ['', Validators.required],
      description: ['', Validators.required],
      lastUpdateBy: ['', Validators.required],
      lastUpdateDate: ['', Validators.required],
      name: ['', Validators.required],
      updateState: ['', Validators.required],
      binaryData: ['', Validators.required],
      relativePath: ['', Validators.required]
    });
  }

  downloadFile() {
    this.service.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }
}
