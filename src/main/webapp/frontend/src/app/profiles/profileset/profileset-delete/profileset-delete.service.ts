import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfilesetDeleteService {

  constructor(private http: HttpClient) { }

  remove(id:any){
      return this.http.delete("/profilesets/" + id, { responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get("/profilesets/script/download/" + filename, {responseType: 'blob'});
  }
}
