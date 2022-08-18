import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ImageeditService {

  constructor(private  http: HttpClient) {
  }

  edit(image: any) {
    return this.http.put('/image/', image, {responseType: 'text'});
  }

  downloadSqlFile(filename: String) {
    return this.http.get('/image/script/download/' + filename, {responseType: 'blob'});
  }

  getImageById(id: any) {
    return this.http.get('/image/' + id);
  }
}
