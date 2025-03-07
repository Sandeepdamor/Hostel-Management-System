package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Student;
import com.dollop.app.dao.IStudentDao;
import com.dollop.app.utils.DbConnection;

public class StudentDaoImpl implements IStudentDao {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = null;
	private ResultSet rs = null;
	
	
	public StudentDaoImpl() {
		super();
		this.con = DbConnection.getConnection();
		
	}

	@Override
	public Student login(String userName, String password) {
		sql = "SELECT * FROM students WHERE email = ? AND password = ? AND is_deleted = false";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				student = new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Boolean save(Student student) {
		sql = "INSERT INTO students(name, email, password, contact, is_deleted ,fee_paid, room_id) VALUES(?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getPassword());
			pstmt.setString(4, student.getContact());
			pstmt.setBoolean(5, student.getIsDeleted());
			pstmt.setBoolean(6, student.getFeesPaid());
			pstmt.setInt(7, student.getRoomId());
			return pstmt.executeUpdate()>0;
//			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public int addStudent(Student student, boolean hasPaidFirstFee, double feeAmount, String paymentMode) {
        String studentSql = "INSERT INTO students (name, email, password, contact, joining_date, fee_paid, room_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String feeSql = "INSERT INTO fees (student_id, amount, due_date, status, payment_mode, payment_date, month) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int studentId = -1;

        try (
             PreparedStatement studentStmt = con.prepareStatement(studentSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement feeStmt = con.prepareStatement(feeSql)) {

            // Insert student
            studentStmt.setString(1, student.getName());
            studentStmt.setString(2, student.getEmail());
            studentStmt.setString(3, student.getPassword());
            studentStmt.setString(4, student.getContact());
            studentStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // Joining Date
            studentStmt.setBoolean(6, hasPaidFirstFee);
            studentStmt.setInt(7, student.getRoomId());
            studentStmt.executeUpdate();

            // Get generated student ID
            ResultSet rs = studentStmt.getGeneratedKeys();
            if (rs.next()) {
                studentId = rs.getInt(1);
            }

            // Insert first fee record
            if (studentId > 0) {
                feeStmt.setInt(1, studentId);
                feeStmt.setDouble(2, feeAmount);

                // Set due date: First day of next month
                LocalDate dueDate = YearMonth.now().atDay(10);
                feeStmt.setTimestamp(3, Timestamp.valueOf(dueDate.atStartOfDay()));

                // Set payment status and mode
                if (hasPaidFirstFee) {
                    feeStmt.setString(4, "Paid");
                    feeStmt.setString(5, paymentMode);
                    feeStmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                } else {
                    feeStmt.setString(4, "Unpaid");
                    feeStmt.setString(5, "unpaid");
                    feeStmt.setTimestamp(6, null);
                }

                // Set Month: Format as YYYY-MM
                YearMonth feeMonth = YearMonth.now();
                feeStmt.setString(7, feeMonth.toString()); // Example: "2025-03"

                int rowsInserted = feeStmt.executeUpdate();
                System.out.println("Fee record inserted: " + rowsInserted + " for Student ID: " + studentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentId;
    }



	@Override
	public Boolean update(Student student,Integer id) {
		sql = "UPDATE students SET name = ?, email = ?, contact = ? WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setString(3, student.getContact());
			pstmt.setInt(4, id);
			result = pstmt.executeUpdate()>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	public Boolean updateByUser(Student student) {
//		sql = "UPDATE students SET name = ?, email = ?, password = ?, contact = ? WHERE id = ?";
//		Boolean result = false;
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, student.getName());
//			pstmt.setString(2, student.getEmail());
//			pstmt.setString(3, student.getPassword());
//			pstmt.setString(4, student.getContact());
//			pstmt.setInt(5, student.getId());
//			result = pstmt.executeUpdate()>0;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

	@Override
	public Boolean updatePassword(Integer id, String password) {
		sql = "UPDATE students SET password = ? WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate()>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean deleteStudent(Integer id) {
		sql = "DELETE FROM students WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Student getStudent(Integer id) {
		sql = "SELECT * FROM students WHERE id = ?";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				student = new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public List<Student> getStudents() {
		sql = "SELECT * FROM students WHERE is_deleted = false";
		List<Student> students = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				students.add(new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Student student : students) {
			System.out.println(student+" WHO IS NOT DELETED");
		}
		return students;
	}

	@Override
	public Boolean isEmailExists(String email) {
		 String sql = "SELECT COUNT(*) FROM students WHERE email=?";
		    boolean result = false;

		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, email);
		        
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            if (resultSet.next()) {
		                int count = resultSet.getInt(1);
		                result = count > 0;
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return result;
	}

	@Override
	public Student getStudentByEmail(String email) {
		sql = "SELECT * FROM students WHERE email = ?";
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				student = new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public List<Student> getStudentsByRoomId(Integer roomId) {
		sql = "SELECT * FROM students WHERE room_id = ? AND is_deleted = false";
		List<Student> students = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				students.add(new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public List<Student> searchStudent(String search) {
		sql = "SELECT * FROM students WHERE "
			    + "LOWER(name) LIKE LOWER(?) OR "
			    + "LOWER(email) LIKE LOWER(?) OR "
			    + "contact LIKE ? OR "
			    + "CAST(id AS CHAR) LIKE ? OR "
			    + "CAST(joining_date AS CHAR) LIKE ? OR "
			    + "CAST(fee_paid AS CHAR) LIKE ? OR "
			    + "CAST(room_id AS CHAR) LIKE ?";

		List<Student> students = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			// Append `%` to enable partial matching
	        String searchParam = "%" + search + "%";
	        
	        // Set parameters for all fields
	        for (int i = 1; i <= 7; i++) {
	            pstmt.setString(i, searchParam);
	        }
	        
	        rs = pstmt.executeQuery();
			while(rs.next())
			{
				students.add(new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public Boolean changeStudentIsDeleted(Integer id) {
		sql = "UPDATE students SET room_id = null, is_deleted = CASE WHEN is_deleted = TRUE THEN FALSE ELSE TRUE END WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate()>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	

	@Override
	public Boolean changeStudentFeeStatus(Boolean fee_paid,Integer id) {
		sql = "UPDATE students SET fee_paid = ? WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, fee_paid);
			pstmt.setInt(2, id);
			result = pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer countTotalStudent() {
		sql = "SELECT COUNT(*) FROM students WHERE is_deleted = false" ;
		Integer count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			 if (rs.next()) {
				 count = rs.getInt(1);
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Student> getStudentsHistory() {
		sql = "SELECT * FROM students WHERE is_deleted = true";
		List<Student> students = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				students.add(new Student(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("contact"),
						rs.getBoolean("is_deleted"),
						rs.getTimestamp("joining_date"),
						rs.getBoolean("fee_paid"),
						rs.getInt("room_id"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Student student : students) {
			System.out.println(student+" WHO Left The Hostel");
		}
		return students;
	}

	@Override
	public Integer addStudentAgain(Student student, Boolean feesPaid, double feeAmount, String paymentType) {
		 String studentSql = "Update students set name = ?, password = ?, contact = ?, joining_date = ?, is_deleted = false ,fee_paid = ?, room_id= ? WHERE email = ?";
	        String feeSql = "INSERT INTO fees (student_id, amount, due_date, status, payment_mode, payment_date, month) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        int studentId = getStudentByEmail(student.getEmail()).getId();
	        int rows = -1;
	        try (
	             PreparedStatement studentStmt = con.prepareStatement(studentSql);
	             PreparedStatement feeStmt = con.prepareStatement(feeSql)) {

	            // Insert student
	            studentStmt.setString(1, student.getName());
	            studentStmt.setString(2, student.getPassword());
	            studentStmt.setString(3, student.getContact());
	            studentStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Joining Date
	            studentStmt.setBoolean(5, feesPaid);
	            studentStmt.setInt(6, student.getRoomId());
	            studentStmt.setString(7, student.getEmail());
	            studentStmt.executeUpdate();

	            // Get generated student ID
	            
	            rows = studentStmt.executeUpdate();

	            // Insert first fee record
	            if (rows > 0) {
	                feeStmt.setInt(1, studentId);
	                feeStmt.setDouble(2, feeAmount);

	                // Set due date: First day of next month
	                LocalDate dueDate = YearMonth.now().atDay(5);
	                feeStmt.setTimestamp(3, Timestamp.valueOf(dueDate.atStartOfDay()));

	                // Set payment status and mode
	                if (student.getFeesPaid()) {
	                    feeStmt.setString(4, "Paid");
	                    feeStmt.setString(5, paymentType);
	                    feeStmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
	                } else {
	                    feeStmt.setString(4, "Unpaid");
	                    feeStmt.setString(5, "unpaid");
	                    feeStmt.setTimestamp(6, null);
	                }

	                // Set Month: Format as YYYY-MM
	                YearMonth feeMonth = YearMonth.now();
	                feeStmt.setString(7, feeMonth.toString()); // Example: "2025-03"

	                int rowsInserted = feeStmt.executeUpdate();
	                System.out.println("Fee record inserted: " + rowsInserted + " for Student ID: " + studentId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return rows;
	}

	@Override
	public Boolean updateStudentRoom(Integer userId, Integer roomId) {
		sql = "UPDATE students SET room_id = ? WHERE id = ?";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, roomId);
			pstmt.setInt(2, userId);
			result = pstmt.executeUpdate()>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
