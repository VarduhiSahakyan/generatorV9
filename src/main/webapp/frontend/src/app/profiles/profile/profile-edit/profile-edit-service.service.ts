import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfileEditServiceService {

  constructor(private http: HttpClient) { }

  getProfileById(id:any){
   return  this.http.get("/profiles/" + id);
  }

  edit(profile: any,id:any){
    return this.http.put("/profiles/" + id, profile,{ responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/profiles/script/download/' + filename, {responseType: 'blob'});
  }

}
