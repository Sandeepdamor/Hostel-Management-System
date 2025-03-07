package com.dollop.app.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Grievance;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.service.IStudentService;
import com.dollop.app.service.impl.StudentServiceImpl;
import com.dollop.app.validations.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({ "/student" })
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStudentService studentService;

	public StudentController() {
		super();
		this.studentService = new StudentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("STUDENT ACTION => " + action);
		switch (action) {
		case "dashboard": {
			dashboard(request, response);
			break;
		}
		case "logout": {
			logOut(request, response);
			break;
		}
		case "profile": {
			showProfile(request, response);
			break;
		}
		case "updateUser": {
			updateUser(request, response);
			break;
		}
		case "updateProfile": {
			updateProfile(request, response);
			break;
		}
		case "room": {
			showRoom(request, response);
			break;
		}
		case "paymentHistory": {
			paymentHistory(request, response);
			break;
		}
		case "pendingFees": {
			System.out.println("hello pendingFees user");
			pendingFees(request, response);
			break;
		}
		case "grievance": {
			grievancePage(request, response);
			break;
		}
		case "addGrievances": {
			addGrievances(request, response);
			break;
		}
		case "showPendingGrievances": {
			showPendingGrievances(request, response);
			break;
		}
		case "showAllGrievances": {
			showAllGrievances(request, response);
			break;
		}
		case "roomchange":{
			changeRoomRequest(request,response);
			break;
		}
		case "processPayment":{
			payPayment(request,response);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	private void payPayment(HttpServletRequest request, HttpServletResponse response) {
		Integer feeId = Integer.parseInt(request.getParameter("feeId"));
	    Double double1= Double.parseDouble( request.getParameter("amount"));
	    
		Integer studentId = Integer.parseInt(request.getParameter("studentId"));
		String paymentMode = request.getParameter("paymentMode");
		System.out.println(feeId+"  "+double1+" "+paymentMode);
	    // Fetch fee record
	    Fee fee = studentService.getFeeById(feeId);
	    System.out.println(fee);
	    if (fee != null && "Unpaid".equalsIgnoreCase(fee.getStatus())) {
	        fee.setStatus("Paid"); // Update status
	        fee.setPaymentDate(new Timestamp(System.currentTimeMillis()));
	        fee.setPaymentMode(paymentMode);
	       System.out.println("UPDATE FEES OR NOT "+studentService.updateFee(fee));// Save update
	    }
	    System.out.println(studentService.changeStudentFeeStatus(true, studentId));
//	    adminService.updateFeeStatus(studentId, "Paid",paymentMode);
	    
	    // Redirect back to manage payments
	    try {
	        response.sendRedirect("student?action=pendingFees&success=Payment successful");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}

	private void changeRoomRequest(HttpServletRequest request, HttpServletResponse response) {
		Integer roomId = Integer.parseInt(request.getParameter("currentRoomId"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer newRoomNumber = Integer.parseInt(request.getParameter("newRoomNumber"));
		Student student = studentService.getStudentById(userId);
        Room newRoom = studentService.getRoomByRoomNumber(newRoomNumber);
        Room oldRoom = studentService.getRoomByRoomId(roomId);
		
        // Check if the new room is already occupied
        if ("Occupied".equals(newRoom.getStatus())) {
            redirectTo(response, "templates/user/user-room.jsp?error=true");
            return;
        }

        // Check if the student has pending fees
        if (!student.getFeesPaid()) {
            redirectTo(response, "templates/user/user-room.jsp?pendingFee=true");
            return;
        }

        // Update student's room assignment
        boolean isUpdated = studentService.updateStudentRoom(userId, newRoom.getId());
        if (isUpdated) {
            // Update old room status if it's now vacant
            int oldRoomSize = studentService.getStudentsByRoomId(oldRoom.getId()).size();
            if (oldRoomSize == 0) {
                studentService.changeRoomStatus(oldRoom.getId(), "Vacant");
            }

            // Update new room status if it's now full
            int newRoomSize = studentService.getStudentsByRoomId(newRoom.getId()).size();
            if (newRoomSize == newRoom.getCapacity()) {
                studentService.changeRoomStatus(newRoom.getId(), "Occupied");
            }

            redirectTo(response, "templates/user/user-room.jsp?success=true");
        } else {
            redirectTo(response, "templates/user/user-room.jsp?notsuccess=true");
        }

		
	}

	private void grievancePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student user = (Student) session.getAttribute("user");
		Integer studentId = user.getId();
		System.out.println("=======--->" + user);
		request.setAttribute("student", user);
		request.getRequestDispatcher("templates/user/user-add-grivance.jsp").forward(request, response);
		System.out.println("=========>" + studentId);

	}

	private void showAllGrievances(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student user = (Student) session.getAttribute("user");
		Integer studentId = user.getId();
		System.out.println("===========>" + studentId);
		List<Grievance> AllGrievances = studentService.getMyGrievances(studentId);
		request.setAttribute("allGrievances", AllGrievances);
		System.out.println(AllGrievances);
		request.getRequestDispatcher("templates/user/user-grivances.jsp").forward(request, response);

	}

	private void showPendingGrievances(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student user = (Student) session.getAttribute("user");
		Integer studentId = user.getId();
		System.out.println("===========>" + studentId);

		List<Grievance> AllGrievances = studentService.getMyGrievances(studentId);
		request.setAttribute("allGrievances", AllGrievances);
		System.out.println(AllGrievances);
		request.getRequestDispatcher("templates/user/user-grivances.jsp").forward(request, response);

	}

	private void addGrievances(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student student = (Student) session.getAttribute("user");
		String description = request.getParameter("description");
		String response1 = request.getParameter("response");

		Grievance grievance = new Grievance();
		grievance.setStudentId(student.getId());
		grievance.setDescription(description);
		grievance.setStatus("Pending");
		grievance.setResponse(response1);
		System.out.println("----------------->" + grievance);
		boolean isAdded = false;
		if(description!=null && !description.isEmpty())
		{
			isAdded = studentService.submitGrievance(grievance);			
		}
		if (isAdded) {
			System.out.println("=>>>>>>>>>>>>>>>" + isAdded);
			showAllGrievances(request, response);

		} else {
			
			Student user = (Student) session.getAttribute("user");
			request.setAttribute("student", user);
			request.setAttribute("error", "Description is Required");
			System.out.println("===========>" + user);
			System.out.println("=>>>>>>>>>>>>>>>" + isAdded);
			
			request.getRequestDispatcher("templates/user/user-add-grivance.jsp").forward(request, response);
		}
	}

	private void pendingFees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student user = (Student) session.getAttribute("user");
		Integer studentId = user.getId();
		System.out.println(studentId);
		List<Fee> pendingPayments = studentService.getPendingFeesByStudentId(studentId);
		System.out.println(pendingPayments);
		request.setAttribute("pendingFees", pendingPayments);
		request.getRequestDispatcher("templates/user/user-pending-payments.jsp").forward(request, response);

	}

	private void paymentHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Student user = (Student) session.getAttribute("user");
		Integer studentId = user.getId();
		System.out.println(studentId);
		List<Fee> payments = studentService.paymentHistory(studentId);
		System.out.println(payments);
		request.setAttribute("paymentHistory", payments);
		request.getRequestDispatcher("templates/user/user-payment-history.jsp").forward(request, response);

	}

	private void showRoom(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);

		Student user = (Student) session.getAttribute("user");
		Integer userId = user.getId();
		Room assignedRoom = studentService.getAssignedRoom(userId);
		System.out.println(assignedRoom);
		request.setAttribute("assigendRoom", assignedRoom);

		System.out.println("....................." + session.getAttribute("user"));

		try {
			request.getRequestDispatcher("templates/user/user-room.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----------> "+request.getParameter("userPassword"));
		Student student = new Student();
		student.setId(Integer.parseInt(request.getParameter("userId")));
		student.setName(request.getParameter("userName"));
		student.setContact(request.getParameter("userPhone"));
		
		if(request.getParameter("userPassword")!=null && !request.getParameter("userPassword").isEmpty())
			student.setPassword(request.getParameter("userPassword"));
		else
			student.setPassword(null);
		
		student.setEmail(request.getParameter("userEmail"));
		System.out.println(student);
		// Validate user input
		List<String> errors = updateProfileValidate(student);
		if (!errors.isEmpty()) {
			HttpSession session = request.getSession(false);
			student.setPassword(request.getParameter("userPassword"));
			session.setAttribute("user", student);
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("/templates/user/user-update-form.jsp").forward(request, response);
		    return; // Ensure no further response modification occurs
		}

		
		student.setPassword(Validation.encryptPassword(request.getParameter("userPassword")));
		if(studentService.updateUserProfile(student))
		{
			Student studentProfile = studentService.getStudentProfile(Integer.parseInt(request.getParameter("userId")));
			HttpSession session = request.getSession(false);
			session.setAttribute("user", studentProfile);
			redirectTo(response, "templates/user/user-update-form.jsp?success=true");
		}
		else {
			redirectTo(response, "templates/user/user-update-form.jsp?success=false");
		}
		

	}
	
	private void redirectTo(HttpServletResponse response, String url) {
	    try {
	    	System.out.println(url);
	        response.sendRedirect(url);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	private List<String> updateProfileValidate(Student student) {
	    List<String> errors = new ArrayList<>();

	    // Validate Name
	    if (isEmpty(student.getName())) {
	        errors.add("Please Enter Name");
	    }

	    // Validate Email
	    if (isEmpty(student.getEmail())) {
	        errors.add("Please Enter Email");
	    } else if (!Validation.emailValidation(student.getEmail())) {
	        errors.add("Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)");
	    } else {
	        // Fetch student once to avoid multiple DB calls
	        Student existingStudent = studentService.getStudentById(student.getId());
	        
	        if (existingStudent != null && !existingStudent.getEmail().equals(student.getEmail()) 
	            && studentService.isEmailExists(student.getEmail())) {
	            errors.add("Email Already Exists");
	        }
	    }
	    
	    String newPassword = student.getPassword();
	    System.out.println("NEW PASSWORD => "+newPassword);

	    if(newPassword!=null && !newPassword.isEmpty())
	    {
	        if (!Validation.passwordValidation(newPassword)) {
	            errors.add("Password Format is Wrong, Please Enter Strong Password");
	        }
	    }

	    System.out.println("UPDATE STUDENT DATA => Student New Email => " + student.getEmail());

	    // Validate Contact
	    if (isEmpty(student.getContact())) {
	        errors.add("Please Enter Contact");
	    } else if (!Validation.mobileNumberValidation(student.getContact())) {
	        errors.add("Mobile Number Format is Wrong, Please Enter Right Mobile Number");
	    }

	    return errors;
	}

	private Boolean isEmpty(String str)
	{
		return str == null || str.trim().isEmpty();
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//
//		session.getAttribute("user");
//		System.out.println("...........update.........." + session.getAttribute("user"));
		request.setAttribute("user", studentService.getStudentById(Integer.parseInt(request.getParameter("id"))));
		request.getRequestDispatcher("templates/user/user-update-form.jsp").forward(request, response);

	}

	private void showProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.getAttribute("user");
		System.out.println("....................." + session.getAttribute("user"));

		request.getRequestDispatcher("templates/user/user-profile.jsp").forward(request, response);
	}

	private void dashboard(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            Student user = (Student) session.getAttribute("user");
            String name = user.getName();
            Integer roomId = user.getRoomId();
            Integer assignedRoom = studentService.getAssignedRoom(user.getId()).getRoomNumber();
            Integer totalGrviance = studentService.countTotalGrivaanceById(user.getId());
            Integer resolvedGrivances = studentService.countPendingGrivanceById(user.getId());
            Double totalPayment = studentService.countTotalPaymentOfStudent(user.getId());
            System.out.println("RESOLVED => "+resolvedGrivances);
            request.setAttribute("name", name);
            request.setAttribute("assignedRoom", assignedRoom);
            request.setAttribute("totalGrviance", totalGrviance);
            request.setAttribute("resolvedGrivances", resolvedGrivances);
            request.setAttribute("totalPayment", totalPayment);
            
            
            request.getRequestDispatcher("templates/user/user-dashboard.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		System.out.println("LOGOUT");
		session.invalidate();
		try {
			response.sendRedirect("./");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
