package com.dollop.app.service;

import java.sql.Timestamp;
import java.util.List;

import com.dollop.app.bean.Admin;
import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Grievance;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.bean.Visitor;

public interface IAdminService {
	//Admin Methods
	public Admin login(String username, String password);
	
	//Student Methods
	public Boolean addStudent(Student student);
	public Boolean updateStudent(Student updatedStudent,Integer id);
	public Boolean deleteStudent(Integer studentId);
	public List<Student> getAllStudents();
	public Student getStudentById(Integer studentId);
	public Student getStudentByEmail(String email);
	public Boolean isEmailExists(String email);
	public List<Student> getStudentsByRoomId(Integer roomId);
	public List<Student> searchStudent(String search);
	public Boolean changeStudentIsDeleted(Integer id);
	public Boolean changeStudentFeeStatus(Boolean fee_paid,Integer id);
	public Integer countTotalStudent();
	
	//Room's Methods
	public Boolean allocateRoom(Integer studentId, Integer roomId);
	public Boolean vacateRoom(Integer studentId);
	public String getRoomStatus(Integer roomId);
	public Boolean isRoomExists(Integer roomNumber);
	public List<Room> getAllRooms();
	public Boolean addRoom(Room room);
	public Room getRoomByRoomNumber(Integer roomNumber);
	public Room getRoomByRoomId(Integer roomId);
	public Boolean changeRoomStatus(Integer roomId, String newStatus);
	public List<Room> searchRoom(String search);
	public Boolean updateRoom(Room room, Integer roomId);
	public Integer countTotalRoom();
	public Integer countOccupiedRoom();
	public Integer countVacantRoom();
	public Integer countSingleRoom();
	
	
	//Fees Methods
	public List<Fee> getAllFees();
	public Fee recordPayment(Integer studentId, Double amount, String paymentMode);
	public List<Fee> getPendingFees(Integer studentId);
	public Boolean makePayment(Fee fee);
	boolean updateFeeStatus(int studentId, String status,String paymentMode);
	public Double countTotalRevenue();
	public Integer countPendingPayment();
	public List<Fee> searchPayment(String searchQuery);
	
	
	public List<Grievance> viewComplaints();
	public List<Grievance> searchFeedback(String searchQuery);
	
	//Visitor's Methods
	
	public Boolean updateComplaintStatus(Integer grievanceId, String status, String response);
	public Boolean logVisitorEntry(Visitor visitor);
	public List<Visitor> getAllVisitors();
	public Visitor getVisitorById(Integer visitorId);
	public Boolean deleteVisitor(Integer visitorId);

	boolean saveVisitor(Visitor visitor);

	boolean updateVisitor(Visitor v);

	Visitor getVisitorByDate(String name, Timestamp dateTime);

	public Boolean updateFee(Fee fee);

	public Fee getFeeById(int feeId);

	public boolean isAdminAvailable();

	public void saveAdmins(Admin admin);

	public Integer countDoubleRoom();

	public Integer countTripleRoom();

	public List<Visitor> searchVisitor(String searchQuery);

	//NEW METHODS 
	public int addStudent(Student student, boolean hasPaidFirstFee, double feeAmount, String paymentMode);
	List<Fee> getMonthlyFeeReport(String month);

	public List<Student> getActiveStudents();

	public boolean isFeeGenerated(Integer id, String monthName);

	public void generateMonthlyFee(Integer id, double amount, String monthName);

	public List<Student> getStudentsHistory();

	public Integer addStudentAgain(Student student, Boolean feesPaid, double feeAmount, String paymentType);
	public Double countTotalRevenueOfMonth(String month);
	public Double countPendingFeesOfMonth(String month);


}
