import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RuleDeleteService {

  constructor(private http: HttpClient) { }

  deleteRule( id: number, profile_id: number){
    return this.http.delete('/rule/' + id + '/' + profile_id, {responseType: 'text'});

  }

  downloadSqlFile(filename: String) {
    return this.http.get('/rule/script/download/' + filename, {responseType: 'blob'});
  }

  getById(id: number){
    return this.http.get('/rule/' + id);
  }
}
