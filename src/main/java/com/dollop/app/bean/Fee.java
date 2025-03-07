package com.dollop.app.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Fee {
	private int id;
    private int studentId;
    private double amount;
    private Timestamp paymentDate;
    private String status;
    private String paymentMode;
    private Timestamp dueDate;
    private String month;
    
	public Fee() {
		super();
	}
	
	
	public Fee(int id, int studentId, double amount, Timestamp paymentDate, String status, String paymentMode,
			Timestamp dueDate, String month) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.status = status;
		this.paymentMode = paymentMode;
		this.dueDate = dueDate;
		this.month = month;
	}


	
	
	public Timestamp getDueDate() {
		return dueDate;
	}


	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "Fee [id=" + id + ", studentId=" + studentId + ", amount=" + amount + ", paymentDate=" + paymentDate
				+ ", status=" + status + ", paymentMode=" + paymentMode + "]";
	}
}
