package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Student;
import com.dollop.app.bean.Visitor;
import com.dollop.app.dao.IVisitorDao;
import com.dollop.app.utils.DbConnection;

public class VisitorDaoImpl implements IVisitorDao {

	private PreparedStatement preparedStatement = null;
	private Connection connection = null;
	private String sql = null;
	private ResultSet resultSet = null;

	public VisitorDaoImpl() {
		connection = DbConnection.getConnection();
	}

	@Override
	public boolean addVisitor(Visitor visitor) {
		sql = "INSERT INTO visitors (name, purpose, contact, entry_time, exit_time) VALUES (?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, visitor.getName());
			preparedStatement.setString(2, visitor.getPurpose());
			preparedStatement.setString(3, visitor.getNumber());
			preparedStatement.setTimestamp(4, visitor.getEntryTime());
			preparedStatement.setTimestamp(5, visitor.getExitTime());
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Visitor> getAllVisitor() {
		sql = "SELECT * FROM visitors";
		ArrayList<Visitor> arrayList = new ArrayList<Visitor>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Visitor visitor = new Visitor();
				visitor.setId(resultSet.getInt("id"));
				visitor.setName(resultSet.getString("name"));
				visitor.setNumber(resultSet.getString("contact"));
				visitor.setPurpose(resultSet.getString("purpose"));
				visitor.setEntryTime(resultSet.getTimestamp("entry_time"));
				visitor.setExitTime(resultSet.getTimestamp("exit_time"));
				arrayList.add(visitor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	@Override
	public Visitor getVisitorById(Integer visitorId) {

		sql = "SELECT * FROM visitors WHERE id = ?";
		Visitor visitor = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, visitorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				visitor = new Visitor();
				visitor.setId(resultSet.getInt("id"));
				visitor.setName(resultSet.getString("name"));
				visitor.setNumber(resultSet.getString("contact"));
				visitor.setPurpose(resultSet.getString("purpose"));
				visitor.setEntryTime(resultSet.getTimestamp("entry_time"));
				visitor.setExitTime(resultSet.getTimestamp("exit_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		 return visitor;
		
	}

	

	@Override
	public boolean deleteVisitor(Integer visitorId) {
		sql = "DELETE FROM visitors WHERE id = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, visitorId);
			int affectedRows = preparedStatement.executeUpdate();
			return affectedRows > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Visitor getVisitorByDate(String name, Timestamp dateTime) {
		// sql = "SELECT * FROM visitors WHERE name = ? entry_time = ?";
		String sql = "SELECT * FROM visitors WHERE name = ? AND entry_time BETWEEN ? - INTERVAL 1 SECOND AND ? + INTERVAL 1 SECOND";
		Visitor visitor = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, dateTime); // Format: "YYYY-MM-DD"
			preparedStatement.setTimestamp(3, dateTime); // Format: "YYYY-MM-DD"
			System.out.println("Executing Query: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				visitor = new Visitor(rs.getInt("id"), rs.getString("name"), rs.getString("contact"),
						rs.getString("purpose"), rs.getTimestamp("entry_time"), rs.getTimestamp("exit_time"));
			}
			System.out.println(visitor);
			return visitor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return visitor;
	}

	@Override
	public boolean update(Visitor v) {
	     sql = "UPDATE visitors SET name = ?, purpose = ?, contact = ?, entry_time = ?, exit_time = ? WHERE id = ?";
	    try {
	    	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, v.getName());
	        preparedStatement.setString(2, v.getPurpose());
	        preparedStatement.setString(3, v.getNumber());
	        preparedStatement.setTimestamp(4, v.getEntryTime());
	        preparedStatement.setTimestamp(5, v.getExitTime());
	        preparedStatement.setInt(6, v.getId());
	        int affectedRows = preparedStatement.executeUpdate();
	        return affectedRows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<Visitor> searchVisitor(String searchQuery) {
		String sql = "SELECT * FROM visitors WHERE "
		           + "LOWER(name) LIKE LOWER(?) OR "
		           + "LOWER(purpose) LIKE LOWER(?) OR "
		           + "contact LIKE ? OR "
		           + "CAST(id AS CHAR) LIKE ? OR "
		           + "CAST(entry_time AS CHAR) LIKE ? OR "
		           + "CAST(exit_time AS CHAR) LIKE ?";

	    
	    List<Visitor> visitors = new ArrayList<>();
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        String searchParam = "%" + searchQuery + "%";

	        // Set parameters for all string fields
	        for (int i = 1; i <= 6; i++) {
	            preparedStatement.setString(i, searchParam);
	        }

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                visitors.add(new Visitor(
	                    resultSet.getInt("id"),
	                    resultSet.getString("name"),
	                    resultSet.getString("contact"),
	                    resultSet.getString("purpose"), 
	                    resultSet.getTimestamp("entry_time"), 
	                    resultSet.getTimestamp("exit_time")
	                ));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return visitors;
	}


}
