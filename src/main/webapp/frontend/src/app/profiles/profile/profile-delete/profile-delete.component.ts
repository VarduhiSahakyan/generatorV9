import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { saveAs } from 'file-saver';
import { ProfileDeleteServiceService } from './profile-delete-service.service';

@Component({
  selector: 'app-profile-delete',
  templateUrl: './profile-delete.component.html',
  styleUrls: ['./profile-delete.component.css']
})
export class ProfileDeleteComponent implements OnInit {

  profile_id: any;
  fileName: string = "";

  constructor(
    private service: ProfileDeleteServiceService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.profile_id = this.route.snapshot.paramMap.get('profile_id');
  }

  onDelete() {
    this.service.delete(this.profile_id).subscribe(response => {
      this.fileName = response;
    })
  }

  downloadFile() {
    this.service.downloadSqlFile(this.fileName).subscribe(
      file => saveAs(file, this.fileName)
      );
  }
}
