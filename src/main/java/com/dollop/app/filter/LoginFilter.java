package com.dollop.app.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter("/login")
public class LoginFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;


	public LoginFilter() {
        super();
        System.out.println("LOGIN FILTER");
    }

	
	public void destroy() {
		System.out.println("LOGIN FILTER DESTROY");
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        String path = req.getRequestURI();
        System.out.println("PATH => "+path);
        
        if(session!=null && session.getAttribute("user")!=null) {
        	System.out.println("User already logged in.");
        	System.out.println("REQ URI => "+req.getRequestURI()+" CTX PATH => "+req.getContextPath() + "/login");
        	if(req.getRequestURI().equals(req.getContextPath() + "/login") && "admin".equals(session.getAttribute("role"))) {
        		System.out.println("LOGIN FILTER Redirect to Admin Dashboard");
        		res.sendRedirect("admin?action=dashboard");
        	}else if (req.getRequestURI().equals(req.getContextPath() + "/login") && "student".equals(session.getAttribute("role"))){
        		System.out.println("LOGIN FILTER Redirect to Student Dashboard");
        		res.sendRedirect("student?action=dashboard");
        	}
        	else {
        		System.out.println("LOGIN FILTER INSIDE SESSION LAST ELSE");
        		chain.doFilter(request, response);
        	}
        }
        else if (path.endsWith("login.jsp") || path.endsWith("/login")) {
        	System.out.println("CONTINUE...");
            chain.doFilter(request, response);
            return;
        }
        else {
            System.out.println("Redirecting to login.jsp...");
            res.sendRedirect("templates/Login.jsp");
        }
        
       
        
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LOGIN FILTER INIT");
	}

}
