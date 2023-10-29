import { Component } from '@angular/core';
import { SubscribersService } from '../subscribers.service';
import { SmsRecordsService } from '../sms-records.service';
import { Subscriber } from '../subscriber';
@Component({
  selector: 'app-add-sms-record',
  templateUrl: './add-sms-record.component.html',
  styleUrls: ['./add-sms-record.component.css']
})
export class AddSmsRecordComponent  {

  subscribers: Subscriber[] = [];
  selectedSubscriberId!: string;
  phoneNumber: string = '';
  successMessage: string | null = null;

  constructor(private smsRecordService: SmsRecordsService, private subscriberService: SubscribersService) { }
  ngOnInit(): void {
    this.getSubscribers();
  }

  getSubscribers() {
    this.subscriberService.getAllSubscribers().subscribe(subscribers => {
      this.subscribers = subscribers;
    });
  }
 
  sendSmsRecord() {
    if (this.selectedSubscriberId && this.phoneNumber) {
      this.smsRecordService.addSmsRecord(this.selectedSubscriberId, this.phoneNumber).subscribe((res) => {
        setTimeout(() => {
          this.successMessage = null;
        }, 3000); 
        this.successMessage = 'SMS sent' ;
      console.log(res['statusReport']);
      });
    }
  }

}
