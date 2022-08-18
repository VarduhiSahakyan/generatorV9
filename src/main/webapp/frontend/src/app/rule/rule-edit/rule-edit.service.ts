import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RuleEditService {

  constructor(private http: HttpClient) { }

  editRule(rule: any){
    return this.http.put('/rule/', rule, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/rule/script/download/' + filename, {responseType: 'blob'});
  }

  getRuleById(id: any){
    return this.http.get('/rule/' + id);
  }
}
