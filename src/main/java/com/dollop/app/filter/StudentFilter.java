package com.dollop.app.filter;

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

import java.io.IOException;

import com.dollop.app.Exception.UnauthorizedAccessException;

@WebFilter("/student")
public class StudentFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	public StudentFilter() {
        super();
       System.out.println("STUDENT FILTER");
    }

	
	public void destroy() {
		System.out.println("STUDENT FILTER DESTROY");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session == null || !"student".equals(session.getAttribute("role"))) {
        	System.out.println("STUDENT FILTER Throw Unauthorized Access Exception");
        	throw new UnauthorizedAccessException("Unauthorized access detected");
//            res.sendRedirect("templates/Login.jsp?error=Unauthorized Access");
        } else {
        	System.out.println("STUDENT FILTER Continue...");        	
            chain.doFilter(request, response);
        }
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("STUDENT FILTER INIT");
	}

}
