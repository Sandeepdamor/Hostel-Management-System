package com.dollop.app.filter;

import java.io.IOException;

import com.dollop.app.Exception.UnauthorizedAccessException;

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

@WebFilter("/admin")
public class AdminFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

    public AdminFilter() {
        super();
        System.out.println("ADMIN FILTER");
    }

	public void destroy() {
		System.out.println("ADMIN FILTER DESTROY");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
        	System.out.println("ADMIN FILTER Redirect to UnAuthorized Exception");
        	throw new UnauthorizedAccessException("Unauthorized access detected");
//            res.sendRedirect("templates/Login.jsp?error=Unauthorized Access");
        } else {
        	System.out.println("ADMIN FILTER Continue...");        	
            chain.doFilter(request, response);
        }
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("ADMIN FILTER INIT");
	}

}
