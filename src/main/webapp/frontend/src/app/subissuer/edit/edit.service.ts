import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EditService {

  constructor(private http: HttpClient) {
  }

  edit(subissuer: any) {
    return this.http.put('/subissuer/', subissuer, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/subissuer/script/download/' + filename, {responseType: 'blob'});
  }

  getByCodeSubissuer(code: any) {
    return this.http.get('/subissuer/' + code);
  }

}
