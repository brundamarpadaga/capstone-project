package com.example.analyticsdashboard.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "subscribers")
public class Subscriber {

	@MongoId
	@Field("subscriberID")
	private ObjectId subscriberID;
	
	private String name;
	private String phoneNumber;
	private String location;
	
	@Field("planName")
	private String planName;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planId) {
		this.planName = planId;
	}

	public String getSubscriberID() {
		return subscriberID.toString();
	}

	public String getName() {
		return name;
	}
	

	public void setSubscriberID(String subscriberID) {
		this.subscriberID = new ObjectId(subscriberID);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	
}
