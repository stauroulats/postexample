package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	private Integer priority;
	@Enumerated(EnumType.STRING)
	private PhotoType type;
	
	@ManyToOne
	private Car car;
	
	
	public Photo() {
		super();
	}
	
	public Photo(String url,Integer priority,PhotoType type, Car car) {
		super();
		this.url = url;
		this.priority = priority;
		this.type = type;
		this.car = car;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public PhotoType getType() {
		return type;
	}

	public void setType(PhotoType type) {
		this.type = type;
	}
	
	

}
