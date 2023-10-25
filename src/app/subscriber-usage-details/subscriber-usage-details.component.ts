import { Component } from '@angular/core';
import { SubscriberUsage } from '../subscriber-usage';
import { ActivatedRoute } from '@angular/router';
import { UsageService } from '../usage.service';


@Component({
  selector: 'app-subscriber-usage-details',
  templateUrl: './subscriber-usage-details.component.html',
  styleUrls: ['./subscriber-usage-details.component.css']
})
export class SubscriberUsageDetailsComponent {
  subscriberId !: string;
  subscriberUsage !: SubscriberUsage;

  constructor(private route: ActivatedRoute, private usageService: UsageService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.subscriberId = params['subscriberId'];
      this.loadSubscriberUsage();
    });
  }

  loadSubscriberUsage() {
    this.usageService.getSubscriberUsage(this.subscriberId).subscribe((usage) => {
      this.subscriberUsage = usage;
    });
  }

}
