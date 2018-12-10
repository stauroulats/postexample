package com.stavroula.postexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue
	private Long id;
	private String url;
	//private Integer priority;
	@Enumerated(EnumType.STRING)
	private PhotoType type;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "photo",
            optional = true)
	private User user;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "photo",
            optional = true)
	private Car car;
	
	
	public Photo() {
		super();
	}
	
	public Photo(String url,PhotoType type, Car car) {
		super();
		this.url = url;
		//this.priority = priority;
		this.type = type;
		this.car = car;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	/*public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}*/
	public PhotoType getType() {
		return type;
	}

	public void setType(PhotoType type) {
		this.type = type;
	}
	
	

}
