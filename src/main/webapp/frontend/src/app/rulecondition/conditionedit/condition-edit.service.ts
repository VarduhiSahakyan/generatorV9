import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ConditionEditService {

  constructor(private http: HttpClient) { }

  editCondition(condition: any){
    return this.http.put('/condition/', condition, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/condition/script/download/' + filename, {responseType: 'blob'});
  }

  getConditionById(id: any){
    return this.http.get('/condition/' + id);
  }
}
