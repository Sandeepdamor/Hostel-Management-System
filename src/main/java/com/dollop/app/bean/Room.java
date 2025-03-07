package com.dollop.app.bean;

public class Room {
	private Integer id;
	private Integer roomNumber;
	private Integer capacity;
	private Double payment;
	private String status;
	
	public Room() {
		super();
	}

	public Room(Integer id, Integer roomNumber, Integer capacity, Double payment, String status) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.payment = payment;
		this.status = status;
	}
	

	public Room(Integer roomNumber, Integer capacity, Double payment, String status) {
		super();
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.payment = payment;
		this.status = status;
	}

	public Room(Integer roomNumber, Integer capacity, Double payment) {
		super();
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.payment = payment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomNumber=" + roomNumber + ", capacity=" + capacity + ", payment=" + payment
				+ ", status=" + status + "]";
	}

	
}
