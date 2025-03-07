package com.dollop.app.dao;

import java.util.List;

import com.dollop.app.bean.Admin;

public interface IAdminDao {
	public Integer save(Admin admin);
	public Admin login(String userName, String password);
	public Admin getAdmin(Integer id);
	public List<Admin> getAdmins();
	public boolean isAdminAvailable();
	
}
