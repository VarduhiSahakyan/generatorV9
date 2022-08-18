import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class AuthentmeanService {
  constructor(private http: HttpClient) {
  }

  getAllMeans() {
    return this.http.get('/authent');
  }

  sendData(meansData: any) {
    return this.http.post('/authent', meansData, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/authent/script/download/' + filename, {responseType: 'blob'});
  }
}
