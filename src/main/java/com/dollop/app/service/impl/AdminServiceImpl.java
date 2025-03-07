
package com.dollop.app.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.dollop.app.bean.Admin;
import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Grievance;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.bean.Visitor;
import com.dollop.app.dao.IAdminDao;
import com.dollop.app.dao.IFeeDao;
import com.dollop.app.dao.IGrievanceDao;
import com.dollop.app.dao.IRoomDao;
import com.dollop.app.dao.IStudentDao;
import com.dollop.app.dao.IVisitorDao;
import com.dollop.app.dao.impl.AdminDaoImpl;
import com.dollop.app.dao.impl.FeeDaoImpl;
import com.dollop.app.dao.impl.GrievancesDaoImpl;
import com.dollop.app.dao.impl.RoomDaoImpl;
import com.dollop.app.dao.impl.StudentDaoImpl;
import com.dollop.app.dao.impl.VisitorDaoImpl;
import com.dollop.app.service.IAdminService;

public class AdminServiceImpl implements IAdminService {

	private IAdminDao adminDao;
	private IFeeDao feeDao;
	private IGrievanceDao grievanceDao ;
	private IRoomDao roomDao;
	private IStudentDao studentDao;
	private IVisitorDao visitorDao;
	
	public AdminServiceImpl() {
		adminDao = new AdminDaoImpl();	
		feeDao = new FeeDaoImpl();
		grievanceDao = new GrievancesDaoImpl();
		roomDao = new RoomDaoImpl();
		studentDao = new StudentDaoImpl();
		visitorDao = new VisitorDaoImpl();
		
	}

	//************* Admin Methods *************
	@Override
	public Admin login(String username, String password) {
		return adminDao.login(username, password);
	}
	@Override
	public boolean isAdminAvailable() {
		return adminDao.isAdminAvailable();
	}

	@Override
	public void saveAdmins(Admin admin) {
		adminDao.save(admin);
	}
	
	
	//************* Student Methods *************
	@Override
	public Boolean addStudent(Student student) {
		return studentDao.save(student);
	}

	@Override
	public Boolean updateStudent(Student updatedStudent,Integer id) {
		return studentDao.update(updatedStudent,id);
	}

	@Override
	public Boolean deleteStudent(Integer studentId) {
		return studentDao.deleteStudent(studentId);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentDao.getStudents();
	}

	@Override
	public Student getStudentById(Integer studentId) {
		return studentDao.getStudent(studentId);
	}
	@Override
	public Boolean isEmailExists(String email) {
		return studentDao.isEmailExists(email);
	}
	@Override
	public Student getStudentByEmail(String email) {
		return studentDao.getStudentByEmail(email);
	}
	@Override
	public List<Student> getStudentsByRoomId(Integer roomId) {
		return studentDao.getStudentsByRoomId(roomId);
	}
	@Override
	public List<Student> searchStudent(String search) {
		return studentDao.searchStudent(search);
	}
	@Override
	public Boolean changeStudentIsDeleted(Integer id) {
		return studentDao.changeStudentIsDeleted(id);
	}
	@Override
	public Boolean changeStudentFeeStatus(Boolean fee_paid, Integer id) {
		return studentDao.changeStudentFeeStatus(fee_paid, id);
	}
	@Override
	public Integer countTotalStudent() {
		return studentDao.countTotalStudent();
	}
	

	
	
	//************* Room Methods *************
	@Override
	public Boolean allocateRoom(Integer studentId, Integer roomId) {
		return roomDao.allocateRoom(studentId, roomId);
	}

	@Override
	public Boolean vacateRoom(Integer studentId) {
		return roomDao.vacateRoom(studentId);
	}

	@Override
	public String getRoomStatus(Integer roomId) {
		return roomDao.getRoomStatus(roomId);
	}
	@Override
	public Boolean isRoomExists(Integer roomNumber) {
		return roomDao.isRoomExists(roomNumber);
	}
	@Override
	public List<Room> getAllRooms() {
		return roomDao.viewAllRooms();
	}
	@Override
	public Boolean addRoom(Room room) {
		return roomDao.addRoom(room);
	}
	@Override
	public Room getRoomByRoomNumber(Integer roomNumber) {
		return roomDao.getRoomByRoomNumber(roomNumber);
	}
	@Override
	public Room getRoomByRoomId(Integer roomId) {
		return roomDao.getRoomByRoomId(roomId);
	}
	@Override
	public Boolean changeRoomStatus(Integer roomId, String newStatus) {
		return roomDao.changeRoomStatus(roomId, newStatus);
	}
	@Override
	public List<Room> searchRoom(String search) {
		return roomDao.searchRoom(search);
	}
	@Override
	public Boolean updateRoom(Room room, Integer roomId) {
		return roomDao.updateRoom(room, roomId);
	}
	@Override
	public Integer countTotalRoom() {
		return roomDao.countTotalRoom();
	}
	@Override
	public Integer countOccupiedRoom() {
		return roomDao.countOccupiedRoom();
	}
	@Override
	public Integer countVacantRoom() {
		return roomDao.countVacantRoom();
	}
	@Override
	public Integer countSingleRoom() {
		return roomDao.countSingleRoom();
	}
	@Override
	public Integer countDoubleRoom() {
		return roomDao.countDoubleRoom();
	}

	@Override
	public Integer countTripleRoom() {
		return roomDao.countTripleRoom();
	}
	

	
	//************* Fee Methods *************
	@Override
	public List<Fee> getAllFees() {
		return feeDao.getAllFees();
	}
	
	@Override
	public Fee recordPayment(Integer studentId, Double amount, String paymentMode) {
		return null;
	}
	@Override
	public Boolean updateFee(Fee fee) {
		return feeDao.updateFee(fee);	
	}

	@Override
	public List<Fee> getPendingFees(Integer studentId) {
		return feeDao.getPendingFees();
	}

	@Override
	public Boolean makePayment(Fee fee) {
		return feeDao.addFeeRecord(fee);
	}
	@Override
	public boolean updateFeeStatus(int studentId, String status,String paymentMode) {
		return feeDao.updateFeeStatus(studentId, status,paymentMode);
	}
	@Override
	public Double countTotalRevenue() {
		return feeDao.countTotalRevenue();
	}
	@Override
	public Integer countPendingPayment() {
		return feeDao.countPendingPayment();
	}
	@Override
	public List<Fee> searchPayment(String searchQuery) {
		return feeDao.searchPayment(searchQuery);
	}
	
	
	//************* Grievances Methods *************
		@Override
		public List<Grievance> viewComplaints() {
			return grievanceDao.getAllGrievances();
		}


		@Override
		public Boolean updateComplaintStatus(Integer grievanceId, String status, String response) {
			return grievanceDao.updateGrievanceStatus(grievanceId, status, response);
		}
		@Override
		public List<Grievance> searchFeedback(String searchQuery) {
			return grievanceDao.searchFeedback(searchQuery);
		}

		
		
		
		

		@Override
		public Boolean logVisitorEntry(Visitor visitor) {
			return visitorDao.addVisitor(visitor);
		}

		@Override
		public List<Visitor> getAllVisitors() {
			return visitorDao.getAllVisitor();
		}

		@Override
		public Visitor getVisitorById(Integer visitorId) {
			return visitorDao.getVisitorById(visitorId);
		}

		@Override
		public Boolean deleteVisitor(Integer visitorId) {
			return visitorDao.deleteVisitor(visitorId);
		}

		@Override
		public Visitor getVisitorByDate(String name, Timestamp dateTime) {
			return visitorDao.getVisitorByDate(name,dateTime);
		}

		@Override
		public boolean saveVisitor(Visitor visitor) {
			return visitorDao.addVisitor(visitor);
		}

		@Override
		public boolean updateVisitor(Visitor v) {
			return visitorDao.update(v);
		}

		@Override
		public Fee getFeeById(int feeId) {
			return feeDao.getFeeById(feeId);
		}

		@Override
		public List<Visitor> searchVisitor(String searchQuery) {
			return visitorDao.searchVisitor(searchQuery);
		}

		
		
		
		//NEW METHODS
		@Override
		public int addStudent(Student student, boolean hasPaidFirstFee, double feeAmount, String paymentMode) {
			
			return studentDao.addStudent(student, hasPaidFirstFee, feeAmount, paymentMode);
		}

//	


		@Override
		public List<Fee> getMonthlyFeeReport(String month) {
			return feeDao.getMonthlyFeeReport(month);
		}

		@Override
		public List<Student> getActiveStudents() {
			return feeDao.getActiveStudents();
		}

		@Override
		public boolean isFeeGenerated(Integer id, String monthName) {
			return feeDao.isFeeGenerated(id, monthName);
		}

		@Override
		public void generateMonthlyFee(Integer id, double amount, String monthName) {
			feeDao.generateMonthlyFee(id, amount, monthName);
			
		}

		@Override
		public List<Student> getStudentsHistory() {
			return studentDao.getStudentsHistory();
		}

		@Override
		public Integer addStudentAgain(Student student, Boolean feesPaid, double feeAmount, String paymentType) {
			return studentDao.addStudentAgain(student,feesPaid,feeAmount,paymentType);
		}

		@Override
		public Double countTotalRevenueOfMonth(String month) {
			return feeDao.countTotalRevenueOfMonth(month);
		}

		@Override
		public Double countPendingFeesOfMonth(String month) {
			return feeDao.countPendingFeesOfMonth(month);
		}


		

				
		
}
