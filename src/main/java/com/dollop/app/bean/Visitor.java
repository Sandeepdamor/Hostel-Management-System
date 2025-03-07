package com.dollop.app.bean;

import java.sql.Timestamp;

public class Visitor {
	private Integer id;
	private String name;
	private String number;
	private String purpose;
	private Timestamp entryTime;
	private Timestamp exitTime;
	
	public Visitor( String name, String number, String purpose, Timestamp entryTime) {
		super();
		
		this.name = name;
		this.number = number;
		this.purpose = purpose;
		this.entryTime = entryTime;
		
	}
	
	public Visitor(Integer id, String name, String number, String purpose, Timestamp entryTime, Timestamp exitTime) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.purpose = purpose;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
	}

	public Visitor() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Timestamp getExitTime() {
		return exitTime;
	}

	public void setExitTime(Timestamp exitTime) {
		this.exitTime = exitTime;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", number=" + number + ", purpose=" + purpose + ", entryTime="
				+ entryTime + ", exitTime=" + exitTime + "]";
	}
}
