import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Files } from '../models/file.model';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private baseUrl = 'https://localhost:8443/auth/users/files';

  constructor(private http: HttpClient) { }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.baseUrl}`, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }
  getFiles(): Observable<Files[]> {
    return this.http.get<Files[]>(`${this.baseUrl}`);
  }

  deleteFile(id: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}