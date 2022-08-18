import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DeleteService {

  constructor(private http: HttpClient) { }

  delete(id: any){
    return this.http.delete("/issuers/"+id,{ responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/issuers/script/download/' + filename, {responseType: 'blob'});
  }
}
