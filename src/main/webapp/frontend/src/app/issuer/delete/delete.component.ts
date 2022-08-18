import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DeleteService} from "./delete.service";
import {FormGroup} from "@angular/forms";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  id: any;
  filename: string = "";

  constructor(
    private deleteService: DeleteService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  onDelete(){
    this.deleteService.delete(this.id).subscribe(resposne => {
         this.filename = resposne;
    });
  }

  downloadFile() {
    this.deleteService.downloadSqlFile(this.filename).subscribe(file => saveAs(file, this.filename));
  }

}
