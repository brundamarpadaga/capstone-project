package com.example.analyticsdashboard.dto;

public class SubscriberAnalyticsDTO {
    private int totalSubscribers;
    private int prepaidSubscribers;
    private int postpaidSubscribers;
	public int getTotalSubscribers() {
		return totalSubscribers;
	}
	public void setTotalSubscribers(int totalSubscribers) {
		this.totalSubscribers = totalSubscribers;
	}
	public int getPrepaidSubscribers() {
		return prepaidSubscribers;
	}
	public void setPrepaidSubscribers(int prepaidSubscribers) {
		this.prepaidSubscribers = prepaidSubscribers;
	}
	public int getPostpaidSubscribers() {
		return postpaidSubscribers;
	}
	public void setPostpaidSubscribers(int postpaidSubscribers) {
		this.postpaidSubscribers = postpaidSubscribers;
	}

    
}
