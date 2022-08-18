import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class IssuerService {

  constructor(private http: HttpClient) { }

  getAllIssuer(){
    return  this.http.get('/issuers');
  }

  sendIssuerData(issuerData:any){
    return this.http.post('/issuers', issuerData, { responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/issuers/script/download/' + filename, {responseType: 'blob'});
  }
}
