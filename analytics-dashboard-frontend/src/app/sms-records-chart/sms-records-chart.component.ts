import { AfterViewInit, Component,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AnalyticsService } from '../analytics.service';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-sms-records-chart',
  templateUrl: './sms-records-chart.component.html',
  styleUrls: ['./sms-records-chart.component.css']
})
export class SmsRecordsChartComponent implements AfterViewInit {

  chart!: Chart;

  public barChartLabels = Array.from({ length: 24 }, (_, i) => i.toString());
  public barChartType = 'bar';
  public barChartLegend = true;
  public barChartData: { head: string, data: number[], label: string }[] = [
    { head: '', data: [], label: 'SMS Records Count' }
  ];

  constructor(private http: HttpClient, private analyticsService: AnalyticsService) {}

  ngAfterViewInit() {
    this.createChart();
  }

  createChart() {
    const canvas = document.getElementById('smsRecordsChart') as HTMLCanvasElement;
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
    this.analyticsService.getSmsRecordsHourlyCount().subscribe(hourlyCounts => {
      this.barChartData[0].head = 'at Time';
      this.barChartData[0].data = hourlyCounts;

      // Update the chart data and refresh the chart
      this.chart.data.labels = this.barChartLabels;
      this.chart.data.datasets[0].data = this.barChartData[0].data;
      this.chart.update();
    });
  }

 

  public barChartOptions = {
    scaleShowVerticalLines: false,
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
          text: 'Number of SMS',
        },
      },
    },
  };

}
