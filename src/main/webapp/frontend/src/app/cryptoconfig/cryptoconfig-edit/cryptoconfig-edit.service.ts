import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CryptoconfigEditService {

  constructor(private http: HttpClient) { }

  getCryptoConfig(id: number) {
    return this.http.get("/crypto/" + id);
  };

  updateCryptoConfig(cryptoConfigData: any,id:any) {
    return this.http.put("/crypto/" + id, cryptoConfigData, {responseType: 'text'});
  }

  downloadFile(filename: String) {
    return this.http.get("/crypto/download/" + filename, {responseType: 'blob'});
  }
}
