package com.stavroula.postexample.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY,
			cascade =  CascadeType.ALL,
			mappedBy = "chat")
	private TripRequest tripRequest;
	
	@OneToMany(mappedBy = "chat", 
			cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<Message> messages = new HashSet<Message>();
	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TripRequest getTripRequest() {
		return tripRequest;
	}

	public void setTripRequest(TripRequest tripRequest) {
		this.tripRequest = tripRequest;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	
}
