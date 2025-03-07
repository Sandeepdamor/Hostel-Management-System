package com.dollop.app.service;

import java.util.List;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Grievance;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;

public interface IStudentService {
	public Student login(String userName, String password);

	Student getStudentProfile(int studentId);

	Room getAssignedRoom(int studentId);

	List<Fee> getFeeStatus(int studentId);

	Boolean submitGrievance(Grievance grievance);

	List<Grievance> getMyGrievances(int studentId);

	List<Fee> paymentHistory(int studentId);

	Boolean updateUserProfile(Student student);

	public List<Fee> getPendingFeesByStudentId(Integer studentId);

	public boolean isEmailExists(String email);

	public Student getStudentById(Integer id);
	Double countTotalPaymentOfStudent(Integer studentId);

	public Integer countTotalGrivaanceById(Integer id);

	public Integer countPendingGrivanceById(Integer id);

	public Room getRoomByRoomId(Integer roomId);

	public Room getRoomByRoomNumber(Integer newRoomNumber);
	public Boolean updateStudentRoom(Integer userId, Integer roomId);
	public List<Student> getStudentsByRoomId(Integer roomId);
	public Boolean changeRoomStatus(Integer roomId, String newStatus);

	public Fee getFeeById(Integer feeId);

	public Boolean updateFee(Fee fee);

	public Boolean changeStudentFeeStatus(boolean fee_paid, Integer studentId);
}
