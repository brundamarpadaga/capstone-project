import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-call-records-chart',
  templateUrl: './call-records-chart.component.html',
  styleUrls: ['./call-records-chart.component.css']
})
export class CallRecordsChartComponent {

  private chart: any; // Chart.js chart instance
  private chartData: number[] = [];
  private chartLabels: string[] = [];
  private baseUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Make a GET request to your Spring Boot endpoint
    this.http
      .get<number[]>(`${this.baseUrl}/api/call-records-hourly-count`)
      .subscribe((hourlyCounts) => {
        this.chartData = hourlyCounts;

        // Generate labels for the last 24 hours
        const now = new Date();
        for (let i = 0; i < 24; i++) {
          const hour = now.getHours() - i;
          this.chartLabels.unshift(`${hour < 10 ? '0' : ''}${hour}:00`);
        }

        this.createChart();
      });
  }

  createChart(): void {
    const canvas = document.getElementById('callRecordsChart') as HTMLCanvasElement;
    if (canvas) {
      const ctx = canvas.getContext('2d');
      if (ctx) {
        this.chart = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: this.chartLabels,
            datasets: [
              {
                label: 'Call Records Count',
                data: this.chartData,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
              }
            ]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      }
    }
  }

}