import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DeleteService {


  constructor(private http: HttpClient) {
  }

  deleteAuthentMeansService(id: any) {
    return this.http.delete('/authent/' + id, {responseType: 'text'});
  }


  downloadFile(filename: String) {
    return this.http.get('/authent/script/download/' + filename, {responseType: 'blob'});
  }

  getAuthentMeansById(id: any) {
    return this.http.get('/authent/' + id);
  }
}
