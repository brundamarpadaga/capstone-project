import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubscriberUsage } from './subscriber-usage';

@Injectable({
  providedIn: 'root'
})
export class UsageService {

  private baseUrl = 'http://localhost:8080'; // Replace with your actual API base URL

  constructor(private http: HttpClient) {}

  getSubscriberUsage(subscriberId: string): Observable<SubscriberUsage> {
    const url = `${this.baseUrl}/api/subscriber-usage/${subscriberId}`;
    return this.http.get<SubscriberUsage>(url);
  }
}
