import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Mood } from '../models/mood';

@Injectable({
  providedIn: 'root'
})

export class MoodService {

  private baseUrl = 'https://localhost:8443';

  constructor(private http: HttpClient) { }

  moodImage: Array<any> = [
    { id: 1, img: '../../assets/img/logo.png', name: 'glücklich', color: "#F9E959" },
    { id: 2, img: '../../assets/img/good.png', name: 'gut', color: "#1EB605" },
    { id: 3, img: '../../assets/img/sad.png', name: 'traurig', color: "#6D88EB" },
    { id: 4, img: '../../assets/img/bad.png', name: 'schlecht', color: "#E94040" },

  ]

  getMood(): Observable<Mood[]> {
    return this.http.get<Mood[]>(this.baseUrl + '/auth/users/mood/all');
  }

  getMoodById(moodData: any): Observable<Mood> {
    return this.http.get<Mood>(this.baseUrl + '/auth/users/moods/' + moodData.id);
  }

  createMood(moodData: Mood): Observable<Mood> {
    return this.http.post<Mood>(this.baseUrl + '/auth/users/mood/', moodData);
  }

  deleteMood(id: any): Observable<any> {
    return this.http.delete(this.baseUrl + '/auth/users/mood/' + id);
  }
}

