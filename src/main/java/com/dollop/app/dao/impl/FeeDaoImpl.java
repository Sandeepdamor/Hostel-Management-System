package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.dao.IFeeDao;
import com.dollop.app.utils.DbConnection;

public class FeeDaoImpl implements IFeeDao {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = null;

	public FeeDaoImpl() {
		super();
		con = DbConnection.getConnection();
		System.out.println(con);
	}

	@Override
	public boolean addFeeRecord(Fee fee) {
		System.out.println(fee+" <-------");
		sql = "INSERT INTO fees (student_id, amount, payment_date, status, payment_mode) VALUES (?, ?, ?, ?, ?)";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, fee.getStudentId());
			pstmt.setDouble(2, fee.getAmount());
			pstmt.setTimestamp(3, fee.getPaymentDate());
			pstmt.setString(4, fee.getStatus());
			pstmt.setString(5, fee.getPaymentMode());
			result =  pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(fee);
		System.out.println("ADDFEERECORD  FEE DAO IMPL =>"+result);
		return result;
	}

	@Override
	public boolean updateFeeStatus(int studentId, String status,String paymentMode) {
		sql = "UPDATE fees SET status = ? , payment_mode = ?, payment_date = ? WHERE student_id = ?";
		System.out.println("UPDATE STUDENT STATUS => "+status);
		System.out.println("UPDATE STUDENT STATUS  STUDENT ID=> "+studentId);
		System.out.println("UPDATE STUDENT STATUS  PAYMETN MODE => "+paymentMode);
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, status);
			System.out.println(status);
			pstmt.setString(2, paymentMode.toLowerCase());
			System.out.println(paymentMode.toLowerCase());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setInt(4, studentId);
			System.out.println(studentId);
			System.out.println(sql);
			result =  pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("UPDATE FEE STATUS OR NOT => "+result);
		return result;
	}

	@Override
	public boolean payFees(int studentId, double amount, String paymentMode, String paymentId) {
		sql = "UPDATE fees SET amount = ?, status = 'Paid', payment_mode = ?, payment_id = ? WHERE student_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, amount);
			pstmt.setString(2, paymentMode);
			pstmt.setString(3, paymentId);
			pstmt.setInt(4, studentId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void payFee(int feeId, String paymentMode) {
        String sql = "UPDATE fees SET status = 'Paid', payment_date = CURRENT_TIMESTAMP, payment_mode = ? WHERE id = ?";

        try (
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, paymentMode);
            stmt.setInt(2, feeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Fee> getFeesByStudentId(int studentId) {
		List<Fee> fees = new ArrayList<>();
		sql = "SELECT * FROM fees WHERE student_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				fees.add(mapResultSetToFee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fees;
	}

	@Override
	public List<Fee> getAllFees() {
		List<Fee> fees = new ArrayList<>();
		sql = "SELECT * FROM fees";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				fees.add(mapResultSetToFee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fees;
	}

	@Override
	public List<Fee> getPendingFees() {
		List<Fee> fees = new ArrayList<>();
		sql = "SELECT * FROM fees WHERE status = 'Pending'";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				fees.add(mapResultSetToFee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fees;
	}

//	@Override
//	public String generatePaymentReceipt(Integer studentId, Integer paymentId) {
//		sql = "SELECT * FROM fees WHERE student_id = ? AND payment_id = ?";
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, studentId);
//			pstmt.setInt(2, paymentId);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return "Receipt: Student ID - " + studentId + ", Amount - " + rs.getDouble("amount")
//						+ ", Payment Date - " + rs.getTimestamp("payment_date") + ", Payment Mode - "
//						+ rs.getString("payment_mode") + ", Payment ID - " + paymentId;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return "No receipt found.";
//	}

	@Override
	public List<Fee> getFeesByPaymentMode(String paymentMode) {
		List<Fee> fees = new ArrayList<>();
		sql = "SELECT * FROM fees WHERE payment_mode = ?";
		try {
			pstmt.setString(1, paymentMode);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				fees.add(mapResultSetToFee(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fees;
	}

	private Fee mapResultSetToFee(ResultSet rs) {
		try {
			return new Fee(rs.getInt("id"), rs.getInt("student_id"), rs.getDouble("amount"),
					rs.getTimestamp("payment_date"), rs.getString("status"), rs.getString("payment_mode"),rs.getTimestamp("due_date"),rs.getString("month"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public boolean updateFee(Fee fee) {
	    String sql = "UPDATE fees SET student_id = ?, amount = ?, payment_date = ?, status = ?, payment_mode = ? WHERE id = ?";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, fee.getStudentId());
	        pstmt.setDouble(2, fee.getAmount());
	        pstmt.setTimestamp(3, (fee.getPaymentDate() != null) ? fee.getPaymentDate() : new Timestamp(System.currentTimeMillis()));
	        pstmt.setString(4, fee.getStatus());
	        pstmt.setString(5, fee.getPaymentMode());
	        pstmt.setInt(6, fee.getId());

	        int rowsUpdated = pstmt.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	@Override
	public Fee getFeeById(Integer id) {
		sql = "SELECT * FROM fees WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToFee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<Fee> paymentHistory(int studentId) {
        List<Fee> payments = new ArrayList<>();
        sql = "SELECT * FROM fees WHERE student_id = ? AND status = 'Paid' ORDER BY payment_date DESC";

        try{
        	pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	payments.add(mapResultSetToFee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

	@Override
	public List<Fee> getPendingFeesByStudentId(int studentId) {
        List<Fee> pendingFees = new ArrayList<>();
        sql = "SELECT * FROM fees WHERE student_id = ? AND status = 'Unpaid' ORDER BY payment_date DESC";

        try  {
        	pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                	pendingFees.add(mapResultSetToFee(rs));
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingFees;
    }

	@Override
	public Double countTotalRevenue() {
		sql = "SELECT SUM(amount) FROM fees WHERE status = 'Paid'";
		Double totalRevenue = 0.0;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				totalRevenue = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRevenue;
	}
	
	@Override
	public Double countTotalPaymentOfStudent(Integer studentId) {
		sql = "SELECT SUM(amount) FROM fees WHERE student_id = ? AND status = 'Unpaid' ORDER BY payment_date DESC";
		Double totalRevenue = 0.0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				totalRevenue = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRevenue;
	}
	
	@Override
	public Integer countPendingPayment() {
		sql = "SELECT COUNT(*) FROM fees WHERE status = 'Unpaid'" ;
		Integer count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			 if (rs.next()) {
				 count = rs.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Count Pending Payments => "+count);
		return count;
	}

	@Override
	public List<Fee> searchPayment(String searchQuery) {
	    String sql = "SELECT * FROM fees WHERE "
	            + "LOWER(CAST(id AS CHAR)) LIKE LOWER(?) OR "
	            + "LOWER(CAST(student_id AS CHAR)) LIKE LOWER(?) OR "
	            + "amount LIKE ? OR "
	            + "LOWER(status) LIKE LOWER(?) OR "
	            + "LOWER(payment_mode) LIKE LOWER(?)";
	    
	    List<Fee> payments = new ArrayList<>();
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        String searchParam = "%" + searchQuery + "%";
	        
	        // Set parameters for all fields
	        for (int i = 1; i <= 5; i++) {
	            pstmt.setString(i, searchParam);
	        }
	        
	        ResultSet resultSet = pstmt.executeQuery();
	        
	        while (resultSet.next()) {
	            Fee fee = new Fee();
	            fee.setId(resultSet.getInt("id"));
	            fee.setStudentId(resultSet.getInt("student_id"));
	            fee.setAmount(resultSet.getDouble("amount"));
	            fee.setPaymentDate(resultSet.getTimestamp("payment_date"));
	            fee.setStatus(resultSet.getString("status"));
	            fee.setPaymentMode(resultSet.getString("payment_mode"));
	            System.out.println(fee);
	            payments.add(fee);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return payments;
	}
	
	
	
	//New Methods ************************
	
	@Override
	public List<Fee> getFeesByStudent(int studentId) {
        List<Fee> fees = new ArrayList<>();
        String sql = "SELECT * FROM fees WHERE student_id = ?";

        try (
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	fees.add(mapResultSetToFee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fees;
    }
	
	
	
	@Override
	public List<Fee> getMonthlyFeeReport(String month) {
	    List<Fee> fees = new ArrayList<>();
	    String sql = "SELECT * FROM fees WHERE month = ?";
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, month);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	fees.add(mapResultSetToFee(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return fees;
	}
	@Override
	public Double countTotalRevenueOfMonth(String month) {
		sql = "SELECT SUM(amount) FROM fees WHERE status = 'Paid' AND month = ?";
		Double totalRevenue = 0.0;
		System.out.println(month+" <=== Month");
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, month);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				totalRevenue = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRevenue;
	}
	@Override
	public Double countPendingFeesOfMonth(String month) {
		sql = "SELECT SUM(amount) FROM fees WHERE status = 'Unpaid' AND month = ?";
		Double totalRevenue = 0.0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, month);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				totalRevenue = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalRevenue;
	}
	
	
	
    @Override
	public List<Student> getActiveStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT * FROM students WHERE is_deleted = false";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setContact(rs.getString("contact"));
                student.setIsDeleted(rs.getBoolean("is_deleted"));
                student.setJoining_date(rs.getTimestamp("joining_date"));
                student.setFeesPaid(rs.getBoolean("fee_paid"));
                student.setRoomId(rs.getInt("room_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    @Override
    public boolean isFeeGenerated(int studentId, String month) {
    	 try {
             String query = "SELECT COUNT(*) FROM fees WHERE student_id = ? AND month = ?";
             PreparedStatement ps = con.prepareStatement(query);
             ps.setInt(1, studentId);
             ps.setString(2, month);
             ResultSet rs = ps.executeQuery();
             if (rs.next() && rs.getInt(1) > 0) {
                 return true; // Fee already exists
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
    }

    @Override
    public void generateMonthlyFee(int studentId, double amount, String month) {
        try {
            String query = "INSERT INTO fees (student_id, amount, status, payment_mode, due_date, month) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setDouble(2, amount);
            ps.setString(3, "Unpaid");
            ps.setString(4, "unpaid");

            LocalDate dueDate = YearMonth.now().plusMonths(1).atDay(10);
            System.out.println("DUE DATE => "+dueDate);
            ps.setTimestamp(5, Timestamp.valueOf(dueDate.atStartOfDay()));
            String currentMonth = YearMonth.now().format(DateTimeFormatter.ofPattern("yyyy MM"));
            ps.setString(6, currentMonth);

            System.out.println(ps.executeUpdate()+" <--------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

