import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Subissuer} from "./subissuer";

@Injectable({
  providedIn: 'root'
})
export class SubIssuerService {

  constructor(private http: HttpClient) {
  }

  getAllSubIssuer() {
    return this.http.get('/subissuer');
  }

  sendData(subIssuerData: Subissuer) {
    return this.http.post('/subissuer', subIssuerData, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/subissuer/script/download/' + filename, {responseType: 'blob'});
  }

  getSubissuerProperties(){
    return this.http.get('/subissuer/properties');
  }
}

