package com.dollop.app.dao;

import java.util.List;

import com.dollop.app.bean.Grievance;

public interface IGrievanceDao {
	
	public Boolean addGrievance(Grievance grievance);	//Add Complain
	
	public Grievance getGrievanceByGrievanceId(Integer grievanceId);
	
	List<Grievance> getAllGrievances();		//Get All Complains for Admin
	
	//Get All Complains For Particular Student (Student Show their Own Complains List)
	List<Grievance> getGrievancesByStudentI(Integer studentId); 
	
	//When Admin Resolve the complain then change the grievance status
	boolean updateGrievanceStatus(int grievanceId, String status, String response);	
	
    boolean deleteGrievance(int grievanceId);

	public List<Grievance> searchFeedback(String searchQuery);

	public Integer countGrievanceByStudentId(Integer studentId);

	public Integer getPendingGrivanceById(Integer studentId);
   

}
