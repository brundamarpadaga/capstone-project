import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SmsRecordsChartComponent } from './sms-records-chart.component';

describe('SmsRecordsChartComponent', () => {
  let component: SmsRecordsChartComponent;
  let fixture: ComponentFixture<SmsRecordsChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SmsRecordsChartComponent]
    });
    fixture = TestBed.createComponent(SmsRecordsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
