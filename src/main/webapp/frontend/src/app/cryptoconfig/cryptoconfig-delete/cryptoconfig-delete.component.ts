import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { saveAs } from 'file-saver';
import { CryptoconfigDeleteService } from './cryptoconfig-delete.service';

@Component({
  selector: 'app-cryptoconfig-delete',
  templateUrl: './cryptoconfig-delete.component.html',
  styleUrls: ['./cryptoconfig-delete.component.css']
})
export class CryptoconfigDeleteComponent implements OnInit {

  filename:string ="";
  id:any;

  constructor(
    private service: CryptoconfigDeleteService,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get("id");
  }

  onDelete(){
    this.service.deleteById(this.id).subscribe(response => {
        this.filename = response;
    })
  }

  downloadFile(){
    this.service.downloadFile(this.filename).subscribe(file => saveAs(file,this.filename));
  }

}
