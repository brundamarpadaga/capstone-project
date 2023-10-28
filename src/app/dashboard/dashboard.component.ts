import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnalyticsService } from '../analytics.service';
import { HttpClient } from '@angular/common/http';


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
  
  
 

  constructor(private http: HttpClient,private router: Router,private analyticsService: AnalyticsService) {}

  ngOnInit(): void {
    this.analyticsService.getAverageCallDuration().subscribe((duration) => {
      this.averageDuration = duration;
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
