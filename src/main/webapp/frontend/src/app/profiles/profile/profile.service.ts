import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { distinct } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  getAllProfiles(){
    return this.http.get("/profiles");
  }

  create(data:any){
    return this.http.post("/profiles", data, { responseType: 'text'} );
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/profiles/script/download/' + filename, {responseType: 'blob'});
  }

}
