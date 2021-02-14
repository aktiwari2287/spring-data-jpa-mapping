package com.anand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Library {
	@Id
	@GeneratedValue
	@Column(name="lib_id")
	private Long lib_id;
	private String name;

	
	public Long getLib_id() {
		return lib_id;
	}

	public void setLib_id(Long lib_id) {
		this.lib_id = lib_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Library(Long lib_id, String name) {
		super();
		this.lib_id = lib_id;
		this.name = name;
	}
	public Library(String name) {
		this.name = name;
	}
	public Library() {
		super();
	}
	

}
