package com.dollop.app.service.impl;

import java.util.List;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Grievance;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.dao.IFeeDao;
import com.dollop.app.dao.IGrievanceDao;
import com.dollop.app.dao.IRoomDao;
import com.dollop.app.dao.IStudentDao;
import com.dollop.app.dao.impl.FeeDaoImpl;
import com.dollop.app.dao.impl.GrievancesDaoImpl;
import com.dollop.app.dao.impl.RoomDaoImpl;
import com.dollop.app.dao.impl.StudentDaoImpl;
import com.dollop.app.service.IStudentService;

public class StudentServiceImpl implements IStudentService {

	private IGrievanceDao grievanceDao = null;
	private IStudentDao studentDao = null;
	private IRoomDao roomDao =null;
	private IFeeDao feeDao =null;
	
	public StudentServiceImpl() {
		super();
		this.grievanceDao = new GrievancesDaoImpl();
		this.studentDao = new StudentDaoImpl();
		this.roomDao=new RoomDaoImpl();
		this.feeDao=new FeeDaoImpl();
	}

	@Override
	public Student login(String userName, String password) {
		return studentDao.login(userName, password);
	}

	@Override
	public Student getStudentProfile(int studentId) {
		return studentDao.getStudent(studentId);
	}

	@Override
	public Room getAssignedRoom(int studentId) {
		return roomDao.getStudentRoom(studentId);
	}
	@Override
	public List<Fee> getFeeStatus(int studentId) {
		return feeDao.getFeesByStudentId(studentId);
	}

	@Override
	public Boolean submitGrievance(Grievance grievance) {
		return grievanceDao.addGrievance(grievance);		
	}

	@Override
	public List<Grievance> getMyGrievances(int studentId) {
		return grievanceDao.getGrievancesByStudentI(studentId);
	}

	@Override
	public List<Fee> paymentHistory(int studentId) {
		return feeDao.paymentHistory(studentId);
	}
	@Override
	public Boolean updateUserProfile(Student student) {
		return studentDao.update(student,student.getId());
	}

	@Override
	public List<Fee> getPendingFeesByStudentId(Integer studentId) {
		return feeDao.getPendingFeesByStudentId(studentId);
	}

	@Override
	public boolean isEmailExists(String email) {
		return studentDao.isEmailExists(email);
	}

	@Override
	public Student getStudentById(Integer id) {
		return studentDao.getStudent(id);
	}

	@Override
	public Double countTotalPaymentOfStudent(Integer studentId) {
		return feeDao.countTotalPaymentOfStudent(studentId);
	}

	public Integer countTotalGrivaanceById(Integer studentId) {
		return grievanceDao.countGrievanceByStudentId(studentId);
	}

	public Integer countPendingGrivanceById(Integer studentId) {
		return grievanceDao.getPendingGrivanceById(studentId);
	}

	@Override
	public Room getRoomByRoomId(Integer roomId) {
		return roomDao.getRoomByRoomId(roomId);
	}

	@Override
	public Room getRoomByRoomNumber(Integer newRoomNumber) {
		return roomDao.getRoomByRoomNumber(newRoomNumber);
	}

	@Override
	public Boolean updateStudentRoom(Integer userId, Integer roomId) {
		return studentDao.updateStudentRoom(userId, roomId);
	}

	@Override
	public List<Student> getStudentsByRoomId(Integer roomId) {
		return studentDao.getStudentsByRoomId(roomId);
	}

	@Override
	public Boolean changeRoomStatus(Integer roomId, String newStatus) {
		return roomDao.changeRoomStatus(roomId, newStatus);
	}

	@Override
	public Fee getFeeById(Integer feeId) {
		return feeDao.getFeeById(feeId);
	}

	@Override
	public Boolean updateFee(Fee fee) {
		return feeDao.updateFee(fee);
	}

	@Override
	public Boolean changeStudentFeeStatus(boolean fee_paid, Integer studentId) {
		return studentDao.changeStudentFeeStatus(fee_paid, studentId);
	}
	
	

}
