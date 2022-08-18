import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EditService {

  constructor(private  http: HttpClient) {
  }

  edit(mean: any) {
    return this.http.put('/authent/', mean, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/authent/script/download/' + filename, {responseType: 'blob'});
  }

  getMeanById(id: any) {
    return this.http.get('/authent/' + id);
  }
}
