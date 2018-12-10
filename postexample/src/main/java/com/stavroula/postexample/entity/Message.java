package com.stavroula.postexample.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String body;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
	private Chat chat;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	
	
	

}
