package com.dollop.app.dao;

import java.util.List;

import com.dollop.app.bean.Student;

public interface IStudentDao {
	public Student login(String userName, String password);
	public Boolean save(Student student);
	public Boolean update(Student student,Integer id);
	public Boolean updatePassword(Integer id,String password);
	public Boolean deleteStudent(Integer id);
	public Boolean isEmailExists(String email);
	public Student getStudentByEmail(String email);
	public Student getStudent(Integer id);
	public List<Student> getStudents();
	public List<Student> getStudentsByRoomId(Integer roomId);
	public List<Student> searchStudent(String search);
	public Boolean changeStudentIsDeleted(Integer id);
	public Boolean changeStudentFeeStatus(Boolean fee_paid,Integer id);
	public Integer countTotalStudent();
//	public Boolean updateByUser(Student student);
	
	//NEW MWTHOD**************
	int addStudent(Student student, boolean hasPaidFirstFee, double feeAmount, String paymentMode);
	public List<Student> getStudentsHistory();
	public Integer addStudentAgain(Student student, Boolean feesPaid, double feeAmount, String paymentType);
	public Boolean updateStudentRoom(Integer userId, Integer roomId);
}
