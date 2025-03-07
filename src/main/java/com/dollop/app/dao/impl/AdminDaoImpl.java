package com.dollop.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Admin;
import com.dollop.app.dao.IAdminDao;
import com.dollop.app.utils.DbConnection;

public class AdminDaoImpl implements IAdminDao {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private String sql = null;
	private ResultSet rs = null;
	

	public AdminDaoImpl() {
		super();
		this.con = DbConnection.getConnection();
	}

	@Override
	public Integer save(Admin admin) {
		sql = "INSERT INTO admins(name, email, password) VALUES(?,?,?)";
		int row = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getName());
			pstmt.setString(2, admin.getEmail());
			pstmt.setString(3, admin.getPassword());
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public Admin login(String userName, String password) {
		sql = "SELECT * FROM admins WHERE email = ? AND password = ?";
		Admin admin = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				admin = new Admin(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public Admin getAdmin(Integer id) {
		sql = "SELECT * FROM admins WHERE id = ?";
		Admin admin = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				admin = new Admin(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> getAdmins() {
		sql = "SELECT * FROM admins";
		List<Admin> admins = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				admins.add(new Admin(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}

	@Override
	public boolean isAdminAvailable() {
		sql = "SELECT COUNT(*) FROM admins";
		Boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);   //lga bhi skte hai nhi bhi lga skte h
			rs = pstmt.executeQuery();
			//System.out.println(rs.getInt(1)+" ********************");
			if(rs.next())
				result = rs.getInt(1)>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
