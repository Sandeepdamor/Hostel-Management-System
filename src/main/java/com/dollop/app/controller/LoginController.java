package com.dollop.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.dollop.app.bean.Admin;
import com.dollop.app.bean.Student;
import com.dollop.app.dao.IStudentDao;
import com.dollop.app.service.IAdminService;
import com.dollop.app.service.IStudentService;
import com.dollop.app.service.impl.AdminServiceImpl;
import com.dollop.app.service.impl.StudentServiceImpl;
import com.dollop.app.validations.Validation;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAdminService adminService = null;
    private IStudentService studentService = null;
    
    public LoginController() {
        super();
        System.out.println("LOGIN CONTROLLER");
        this.adminService = new AdminServiceImpl();
        this.studentService = new StudentServiceImpl();
        createAdmin();
        
    }
    private void createAdmin() {
    	System.out.println("CREATE ADMIN=============");
		if(!adminService.isAdminAvailable())
		{
			adminService.saveAdmins(new Admin(null,"admin", "admin123@gmail.com", Validation.encryptPassword("admin123")));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("LOGIN SERVLETS => "+userName+", "+password);
		HttpSession session = request.getSession();
		Admin admin = null;
		Student student = null;
		if(password!=null)
			password = Validation.encryptPassword(password);
		if((admin = adminService.login(userName, password)) != null) {
			//cookie setting for 1 day
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60 * 60 * 24); // Cookie persists for 1 day
			response.addCookie(cookie);
			System.out.println("ADMIN LOGIN => "+admin);
			session.setAttribute("user", admin);
			session.setAttribute("role", "admin");
			response.sendRedirect("admin?action=dashboard");
		}
		else if((student = studentService.login(userName, password)) != null) {
			//cookie setting for 1 day
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60 * 60 * 24); // Cookie persists for 1 day
			response.addCookie(cookie);
			System.out.println("STUDENT LOGIN => "+student);
			session.setAttribute("user", student);
			session.setAttribute("role", "student");
			response.sendRedirect("student?action=dashboard");
		}else {
			System.out.println("LOGIN SERVLETS Redirect to Login.jsp...");
			System.out.println(userName+", "+password);
			String error = "";
			if(userName!=null || password!=null)
				error = "Invalid User or Password..!!";
			response.sendRedirect("templates/Login.jsp?error="+error);
		}
		
	}

}
