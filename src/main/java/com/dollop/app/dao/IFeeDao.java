package com.dollop.app.dao;

import java.util.List;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Student;

public interface IFeeDao {
	boolean addFeeRecord(Fee fee);
    boolean updateFeeStatus(int studentId, String status,String paymentMode);
    boolean payFees(int studentId, double amount, String paymentMode, String paymentId);
    List<Fee> getFeesByStudentId(int studentId);
    List<Fee> getAllFees();
    List<Fee> getPendingFees();
//    String generatePaymentReceipt(Integer studentId, Integer paymentId);
    List<Fee> getFeesByPaymentMode(String paymentMode);//online and offline mode list
	boolean updateFee(Fee fee);
	Fee getFeeById(Integer id);
	List<Fee> paymentHistory(int studentId);
	List<Fee> getPendingFeesByStudentId(int studentId);
	public Double countTotalRevenue();
	public Integer countPendingPayment();
	List<Fee> searchPayment(String searchQuery);
	
	//New ****************


	
	List<Fee> getFeesByStudent(int studentId);
	void payFee(int feeId, String paymentMode);
	List<Fee> getMonthlyFeeReport(String month);
	Double countTotalPaymentOfStudent(Integer studentId);
	List<Student> getActiveStudents();
	boolean isFeeGenerated(int studentId, String month);
	void generateMonthlyFee(int studentId, double amount, String month);
	Double countTotalRevenueOfMonth(String month);
	Double countPendingFeesOfMonth(String month);
	
}
