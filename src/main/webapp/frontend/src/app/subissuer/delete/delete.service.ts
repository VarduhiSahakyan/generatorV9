import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DeleteService {

  constructor(private http: HttpClient) {
  }

  deleteSubissuer(code: any, issuer_id: number, crypto_id: number) {
    return this.http.delete("/subissuer/" + code + '/' + issuer_id + '/' + crypto_id, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/subissuer/script/download/' + filename, {responseType: 'blob'});
  }

  getByCodeSubissuer(code: any) {
    return this.http.get('/subissuer/' + code);
  }

}
