import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ConditionDeleteService {

  constructor(private http: HttpClient) { }

  deleteCondition( id: number){
    return this.http.delete('/condition/' + id, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/condition/script/download/' + filename, {responseType: 'blob'});
  }

  getById(id: number){
    return this.http.get('/condition/' + id);
  }
}
