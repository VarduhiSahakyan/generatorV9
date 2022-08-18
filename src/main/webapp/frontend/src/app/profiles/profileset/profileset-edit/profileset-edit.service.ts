import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfilesetEditService {

  constructor(private http: HttpClient) { }

  
  getById(id: any){
    return this.http.get("/profilesets/" + id);
  }

  update(data:any,id:any){
    return this.http.put("/profilesets/" + id,data,{ responseType: 'text'}); 
  }

  downloadSqlFile(filename: String) {
    return this.http.get("/profilesets/script/download/" + filename, {responseType: 'blob'});
  }

}
