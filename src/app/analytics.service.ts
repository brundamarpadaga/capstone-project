import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubscriberAnalyticsDto } from './subscriber-analytics-dto';

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  private baseUrl = 'http://localhost:8080'; // Replace with your API endpoint

  constructor(private http: HttpClient) { }

  

  getCallRecordsHourlyCount(): Observable<number[]> {
    return this.http.get<number[]>(`${this.baseUrl}/api/analytics/call-records-hourly-count`);
  }

  getAverageCallDuration(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/analytics/average-call-duration`);
  }

  getSmsRecordsHourlyCount(): Observable<number[]> {
    return this.http.get<number[]>(`${this.baseUrl}/api/analytics/sms-records-hourly-count`);
  }

  getSubscriberAnalytics(): Observable<SubscriberAnalyticsDto> {
    return this.http.get<SubscriberAnalyticsDto>(`${this.baseUrl}/api/analytics/subscriber-analytics`);
  }

  getActiveCallCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/analytics/active-call-count`);
  }

  getInactiveSubscriberUsageCount(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/api/analytics/inactive-plan-count`);
  }

}
