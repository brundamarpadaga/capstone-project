import { Component } from '@angular/core';
import { SmsRecordsService } from '../sms-records.service';
import { SmsRecord } from '../sms-record';

@Component({
  selector: 'app-sms-records',
  templateUrl: './sms-records.component.html',
  styleUrls: ['./sms-records.component.css']
})
export class SmsRecordsComponent {
  smsRecords: SmsRecord[] = [];
  filteredSmsRecords: SmsRecord[] = [];
  searchSubscriberId: string = '';

  constructor(private smsRecordService: SmsRecordsService) { }

  ngOnInit(): void {
    this.getSmsRecords();
  }


  getSmsRecords(){
    this.smsRecordService.getAllSmsRecordsDTO().subscribe((data) => {
      this.smsRecords = data;
    });

  }

  searchSmsRecords() {
    if (this.searchSubscriberId) {
      this.smsRecordService.getAllSmsRecordsBySubscriber(this.searchSubscriberId)
        .subscribe(data => {
          this.smsRecords = data;
        });
    } else {
      this.smsRecordService.getAllSmsRecords().subscribe(records => {
        this.smsRecords = records;
      });
    }
  }

}
