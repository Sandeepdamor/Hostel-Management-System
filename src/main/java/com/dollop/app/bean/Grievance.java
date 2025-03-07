package com.dollop.app.bean;

public class Grievance {
	private Integer id;
	private String description;
	private String status;
	private String response;
	private Integer studentId;
	
	public Grievance() {
		super();
	}

	public Grievance(Integer id, String description, String status, String response, Integer studentId) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.response = response;
		this.studentId = studentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Grievance [id=" + id + ", description=" + description + ", status=" + status + ", response=" + response
				+ ", studentId=" + studentId + "]";
	}

}
