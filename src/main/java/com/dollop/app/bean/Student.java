package com.dollop.app.bean;

import java.sql.Timestamp;

public class Student {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String contact;
	private Boolean isDeleted;
	private Timestamp joining_date;
	private Boolean feesPaid;
	private Integer roomId;
	
	public Student() {
		super();
	}

	
	public Student(Integer id, String name, String email, String password, String contact, Boolean isDeleted,
			Timestamp joining_date, Boolean feesPaid, Integer roomId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.isDeleted = isDeleted;
		this.joining_date = joining_date;
		this.feesPaid = feesPaid;
		this.roomId = roomId;
	}


	public Student(String name, String email, String password, String contact, Boolean feesPaid, Integer roomId) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.feesPaid = feesPaid;
		this.roomId = roomId;
	}

	public Student(String name, String email, String password, String contact) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
	}
	
	

	public Student(String name, String email, String contact) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Timestamp getJoining_date() {
		return joining_date;
	}


	public void setJoining_date(Timestamp joining_date) {
		this.joining_date = joining_date;
	}


	public Boolean getFeesPaid() {
		return feesPaid;
	}


	public void setFeesPaid(Boolean feesPaid) {
		this.feesPaid = feesPaid;
	}


	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", contact="
				+ contact + ", isDeleted=" + isDeleted + ", joining_date=" + joining_date + ", feesPaid=" + feesPaid
				+ ", roomId=" + roomId + "]";
	}
	

	
}
