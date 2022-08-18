import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProfileDeleteServiceService {

  constructor(private http: HttpClient) { }


  delete(id: any){
    return this.http.delete("/profiles/" + id,{ responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/profiles/script/download/' + filename, {responseType: 'blob'});
  }


}
