import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RuleCondition} from "./ruleCondition";

@Injectable({
  providedIn: 'root'
})
export class RuleConditionService {

  constructor(private http: HttpClient) { }

  getAllConditions(){
    return this.http.get('/condition')
  }

  sendData(conditionData: RuleCondition){
    return this.http.post('/condition', conditionData, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/condition/script/download/' + filename, {responseType: 'blob'});
  }
}
