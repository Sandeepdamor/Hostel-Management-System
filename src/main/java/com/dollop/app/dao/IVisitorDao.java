package com.dollop.app.dao;

import java.sql.Timestamp;
import java.util.List;

import com.dollop.app.bean.Visitor;

public interface IVisitorDao {
	public boolean addVisitor(Visitor visitor);
	public List<Visitor> getAllVisitor();
	public Visitor getVisitorById(Integer visitorId);
	public boolean deleteVisitor(Integer visitorId);
	Visitor getVisitorByDate(String name, Timestamp dateTime);
	boolean update(Visitor v);
	public List<Visitor> searchVisitor(String searchQuery);
}
