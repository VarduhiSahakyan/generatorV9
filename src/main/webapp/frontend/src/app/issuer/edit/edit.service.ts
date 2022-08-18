import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class EditService {

  constructor(private http: HttpClient) { }

  findById(id:any){
    return this.http.get("/issuers/"+ id);
  }

  edit(issuer: any,id:any){
    return this.http.put("/issuers/"+ id,issuer,{ responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/issuers/script/download/' + filename, {responseType: 'blob'});
  }  

}
