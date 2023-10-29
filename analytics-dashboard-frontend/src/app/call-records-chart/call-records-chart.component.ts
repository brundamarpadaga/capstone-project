import { AfterViewInit, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Chart } from 'chart.js/auto';
import { AnalyticsService } from '../analytics.service';
import { AbsoluteSourceSpan } from '@angular/compiler';

@Component({
  selector: 'app-call-records-chart',
  templateUrl: './call-records-chart.component.html',
  styleUrls: ['./call-records-chart.component.css']
})
export class CallRecordsChartComponent implements AfterViewInit {

  chart!: Chart;

  barChartLabels = Array.from({ length: 24 }, (_, i) => i.toString());
  barChartType = 'bar';
  barChartData: { head: string, data: number[], label: string }[] = [
    { head: '', data: [], label: 'Call Records Count' }
  ];

  constructor(private http: HttpClient, private analyticsService: AnalyticsService) {}

  ngAfterViewInit() {
    this.createChart();
  }

  createChart() {
    const canvas = document.getElementById('callRecordsChart') as HTMLCanvasElement;
    this.chart = new Chart(canvas, {
      type: 'bar',
      data: {
        labels: this.barChartLabels,
        datasets: [
          {
            label: this.barChartData[0].label,
            data: this.barChartData[0].data,
            borderWidth: 1,
          },
        ],
      },
      options: this.barChartOptions,
    });

    this.getData();
  }

  getData() {
    this.analyticsService.getCallRecordsHourlyCount().subscribe(hourlyCounts => {
      this.barChartData[0].head = 'at Time';
      this.barChartData[0].data = hourlyCounts;

      // Update the chart data
      this.chart.data.labels = this.barChartLabels;
      this.chart.data.datasets[0].data = this.barChartData[0].data;
      this.chart.update();
    });
  }

 

  barChartOptions = {
    scaleShowVerticalLines: true,
    maintainAspectRatio: true,
    responsive: true,
    scales: {
      x: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'At Hour',
        },
      },
      y: {
        beginAtZero: true,
        title: {
          display: true,
          text: 'Number of Calls',
        },
      },
    },
  };
  

}