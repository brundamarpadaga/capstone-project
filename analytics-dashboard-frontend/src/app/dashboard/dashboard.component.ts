import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnalyticsService } from '../analytics.service';
import { HttpClient } from '@angular/common/http';
import { SubscriberAnalyticsDto } from '../subscriber-analytics-dto';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  public barChartData : { head : string,data: number[], label: string }[]= [
    {head:"", data: [], label: 'Call Records Count' }
  ];

  averageDuration!: number;
  subscriberData !: SubscriberAnalyticsDto; 
  activeCallCount !: number;
  inactiveSubscriberUsageCount !: number;
  
  
 

  constructor(private http: HttpClient,private router: Router,private analyticsService: AnalyticsService) {}

  ngOnInit(): void {
    this.analyticsService.getAverageCallDuration().subscribe((duration) => {
      this.averageDuration = duration;
    });

    this.analyticsService.getSubscriberAnalytics().subscribe((subscriberData) => {
      this.subscriberData = subscriberData;
    });

    this.analyticsService.getActiveCallCount().subscribe((count) => {
      this.activeCallCount = count;
    });

    this.analyticsService.getInactiveSubscriberUsageCount().subscribe((count) => {
      this.inactiveSubscriberUsageCount = count;
    });
  }

  goToSubscribersPage() {
    this.router.navigate(['/subscribers']);
  }
  goToPlansPage() {
    this.router.navigate(['/plans']);
  }
  goToCallRecordsPage() {
    this.router.navigate(['/call-records']);
  }




  
  
}
