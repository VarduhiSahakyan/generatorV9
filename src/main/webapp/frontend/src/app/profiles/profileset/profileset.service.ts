import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProfilesetService {

  constructor(private http: HttpClient) { }

  getAllProfileSets(){
    return this.http.get("/profilesets");
  }

  create(data:any){
    return this.http.post("/profilesets", data, { responseType: 'text'} );
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/profilesets/script/download/' + filename, {responseType: 'blob'});
  }


}
