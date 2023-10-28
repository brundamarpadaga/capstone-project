package com.example.analyticsdashboard.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.analyticsdashboard.entity.Subscriber;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber,String> {
	public Subscriber findBySubscriberID(String id);

	public Subscriber findByPhoneNumber(String phoneNumber);
	
	

}
