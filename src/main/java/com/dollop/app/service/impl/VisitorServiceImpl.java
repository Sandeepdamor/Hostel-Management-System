package com.dollop.app.service.impl;

import com.dollop.app.bean.Visitor;
import com.dollop.app.dao.IVisitorDao;
import com.dollop.app.dao.impl.VisitorDaoImpl;
import com.dollop.app.service.IVisitorService;

public class VisitorServiceImpl implements IVisitorService {

	private IVisitorDao visitorDao;

	VisitorServiceImpl() {
		visitorDao = new VisitorDaoImpl();

	}

	@Override
	public boolean saveVisitor(Visitor visitors) {
		
			return visitorDao.addVisitor(visitors);
	
	}

}
