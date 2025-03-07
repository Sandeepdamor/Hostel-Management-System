package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Grievance;
import com.dollop.app.dao.IGrievanceDao;
import com.dollop.app.utils.DbConnection;

public class GrievancesDaoImpl implements IGrievanceDao {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	String sql = null;

	public GrievancesDaoImpl() {
		super();
		con = DbConnection.getConnection();
		System.out.println(con);
	}

	@Override
	public Boolean addGrievance(Grievance grievance) {
	    String sql = "INSERT INTO grievances (description, status, response, student_id) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        pstmt.setString(1, grievance.getDescription());
	        pstmt.setString(2, grievance.getStatus());
	        pstmt.setString(3, grievance.getResponse());
	        pstmt.setInt(4, grievance.getStudentId());

	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	@Override
	public Grievance getGrievanceByGrievanceId(Integer grievanceId) {
		sql = "SELECT * FROM grievances WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grievanceId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Grievance(rs.getInt("id"), rs.getString("description"), rs.getString("status"),
						rs.getString("response"), rs.getInt("student_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Grievance> getAllGrievances() {
		List<Grievance> list = new ArrayList<>();
		sql = "SELECT * FROM grievances";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Grievance(rs.getInt("id"), rs.getString("description"), rs.getString("status"),
						rs.getString("response"), rs.getInt("student_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public boolean updateGrievanceStatus(int grievanceId, String status, String response) {
		sql = "UPDATE grievances SET status = ?, response = ? WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setString(2, response);
			pstmt.setInt(3, grievanceId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteGrievance(int grievanceId) {
		sql = "DELETE FROM grievances WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grievanceId);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Grievance> getGrievancesByStudentI(Integer studentId) {
		List<Grievance> list = new ArrayList<>();
		sql = "SELECT * FROM grievances WHERE student_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Grievance(rs.getInt("id"), rs.getString("description"), rs.getString("status"),
						rs.getString("response"), rs.getInt("student_id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Grievance> searchFeedback(String searchQuery) {
	    String sql = "SELECT * FROM grievances WHERE "
	               + "LOWER(CAST(id AS CHAR)) LIKE LOWER(?) OR "
	               + "LOWER(description) LIKE LOWER(?) OR "
	               + "LOWER(status) LIKE LOWER(?) OR "
	               + "LOWER(response) LIKE LOWER(?) OR "
	               + "student_id LIKE ?";
	    
	    List<Grievance> grievances = new ArrayList<>();
	    
	    try {
	        pstmt = con.prepareStatement(sql);
	        
	        // Append `%` for partial matching
	        String searchParam = "%" + searchQuery.toLowerCase() + "%";
	        
	        // Set parameters for all fields
	        for (int i = 1; i <= 5; i++) {
	            pstmt.setString(i, searchParam);
	        }
	        
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Grievance grievance = new Grievance();
	            grievance.setId(rs.getInt("id"));
	            grievance.setDescription(rs.getString("description"));
	            grievance.setStatus(rs.getString("status"));
	            grievance.setResponse(rs.getString("response"));
	            grievance.setStudentId(rs.getInt("student_id"));
	            grievances.add(grievance);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return grievances;
	}

	@Override
	//count total givance where  
		public Integer countGrievanceByStudentId(Integer studentId) {
				sql = "SELECT COUNT(*) FROM grievances WHERE student_id = ?";
	        try {
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, studentId);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
		}

	@Override
	public Integer getPendingGrivanceById(Integer studentId) {
	    String sql = "SELECT COUNT(*) FROM grievances WHERE student_id = ? AND status = 'Pending'";
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, studentId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Consider using a logger instead
	    }
	    
	    return 0; // Return 0 if no records found or an error occurs
	}


}
