package com.dollop.app.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dollop.app.bean.Fee;
import com.dollop.app.bean.Room;
import com.dollop.app.bean.Student;
import com.dollop.app.bean.Visitor;
import com.dollop.app.service.IAdminService;
import com.dollop.app.service.impl.AdminServiceImpl;
import com.dollop.app.validations.Validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/admin","/admin2"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAdminService adminService = null;
	
   
    public AdminController() {
       super();
       this.adminService = new AdminServiceImpl();
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("ADMIN ACTION => "+action);
		switch (action) {
		//Sanjana 
		case "dashboard": {
			dadhboard(request,response);
			break;
		}
		case "register":{
			registerUser(request,response);
			break;
		}
		case "manageuser":{
			manageUser(request,response);
			break;
		}
		case "addnewstudent":{
			addNewStudent(request,response);
			break;
		}
		case "addstudent":{
			addStudent(request,response);
			break;
		}
		case "updatestudent":{
			updateStudent(request,response);
			break;
		}
		case "updatestudentinfo":{
			updateStudentInfo(request,response);
			break;
		}
		case "deleteStudent":{
			deleteStudent(request,response);
			break;
		}
		case "payment":{
			paymentWhenStudentDeleted(request,response);
			break;
		}
		case "managerooms":{
			manageRooms(request,response);
			break;
		}
		case "addnewroom":{
			addNewRoom(request,response);
			break;
		}
		case "addroom":{
			addRoom(request,response);
			break;
		}
		case "updateroom":{
			updateRoom(request,response);
			break;
		}
		case "updateroominfo":{
			updateRoomInfo(request,response);
			break;
		}
		case "searchstudent":{
			searchStudent(request,response);
			break;
		}
		case "searchroom":{
			searchRoom(request,response);
			break;
		}
		case "searchpayment":{
			searchPayment(request,response);
			break;
		}
		case "searchfeedback":{
			searchFeedback(request,response);
			break;
		}
		case "searchvisitor":{
			searchVisitor(request,response);
			break;
		}
		case "studentshistory":{
			StudentsHistory(request,response);  //Who Left The Hostel
			break;
		}
		
		case "logout":{
			logOut(request,response);
			break;
		}
		//Merge Code 
		case "managepayments": {
			managePayments(request, response);
			break;
		}
		case "processPayment": {
			System.out.println("process..");
		    payFee(request, response);
		    break;
		}	
		case "managfeedback": {
			manageFeedback(request, response);
			break;
		}
		case "updateFeedback": {
			updateFeedback(request, response);
			break;
		}
		case "visitorEntry": {
			visitingPage(request, response);
			break;
		}
		case "visitorExit": {
			visitorExit(request, response);
			break;
		}
		case "managvisitors": {
			manageVisitors(request, response);
			break;
		}
		case "deleteVisitor": {
			System.out.println("hello");
			deleteVisitors(request, response);
			break;
		}
		case"adminProfile":{
			adminProfile(request, response);
			break;
		}
		case "MonthlyReport":{
			monthlyReport(request,response);
			break;
		}
		case "addStudentAgain":{
			addStudentAgain(request,response);
			break;
		}
		case "addagain":{
			addAgain(request,response);
			break;
		}
//		case "adminresponse":{
//			processAdminResponse(request, response);
//			break;
//		}
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}
	
	



	private List<String> studentAddAgainValidate(Student student,String roomNumber,String paymentType) {
		List<String> errors = new ArrayList<>();
		if(isEmpty(student.getName()))	errors.add("Name is Required..");
		
		if(!Validation.mobileNumberValidation(student.getContact()))
			errors.add("Mobile Number Format is Wrong, Please Enter Right Mobile Number"); 

		if(!Validation.passwordValidation(student.getPassword()))
			errors.add("Password Format is Wrong, Please Enter Strong Password"); 
		
		if(!Validation.numberValidation(roomNumber))
			errors.add("Only Room Numbers are allowed in Room Number.");
		if(roomNumber!=null &&  !roomNumber.isEmpty() && !roomNumber.isBlank())
		{
			System.out.println("ROOM NUMBER ==> "+roomNumber);
			Integer roomNum = Integer.parseInt(roomNumber);
			System.out.println(adminService.isRoomExists(roomNum)+" <====== Room ISEXISTS "+roomNum);
			Room roomByRoomNum = adminService.getRoomByRoomNumber(roomNum);
			System.out.println("BYROOM NUMBER => "+roomByRoomNum);
			if(roomByRoomNum==null)
				errors.add("Room Not Found, Please Check Entered Room Number");
			else {
				if(!hasSpaceInRoom(roomByRoomNum.getId())) 
				errors.add("Room is already full. Please select a different room.");
			}
				
		}
		if(isEmpty(paymentType))
			errors.add("Fee Mode is Required, Please Select Fee Mode");
		return errors;
	}
	
	private void addAgain(HttpServletRequest request, HttpServletResponse response) {
		Student student = new Student(
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("contact")
				);
		System.out.println("STUDENT ADD AGAIN STUDNT => "+student);
		String roomNumberStr = request.getParameter("roomNumber");
		System.out.println("ROOM NUMBER  ADD STUDENT=> "+roomNumberStr);
		String paymentType = request.getParameter("payment_type");
		List<String> errors = studentAddAgainValidate(student,roomNumberStr,paymentType);
		
		if(!errors.isEmpty()) {
			request.setAttribute("student", student);
			request.setAttribute("errors", errors);
			System.out.println("ERROR IS OCCURED");
			try {
				request.getRequestDispatcher("templates/admin/AddStudentAgain.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		//Assign Room
		if(roomNumberStr!=null && !roomNumberStr.isEmpty()) {
			Integer roomNumber = Integer.parseInt(roomNumberStr);
			Room roomByRoomNumber = adminService.getRoomByRoomNumber(roomNumber);
			System.out.println("ROOM BY ROOM ID => "+roomByRoomNumber);
			student.setRoomId(roomByRoomNumber.getId());
			
		}
		// Encrypt Password 
		student.setPassword(Validation.encryptPassword(request.getParameter("password")));
		//Set Fee Status
		student.setFeesPaid(setFeePaidOrUnpaid(paymentType)); 
		student.setIsDeleted(false);
		
		
		Room roomByRoomId = adminService.getRoomByRoomId(student.getRoomId());
		double feeAmount = roomByRoomId.getPayment();
		
		
		boolean isAdded = adminService.addStudentAgain(student, student.getFeesPaid(), feeAmount, paymentType)>0;
		 
		//Continue
		System.out.println(isAdded);
		if (isAdded) {
			Room roomByRoomNumber = adminService.getRoomByRoomNumber(Integer.parseInt(roomNumberStr));
			System.out.println("ROOM BY ROOM ID => => "+roomByRoomNumber.getId());
			System.out.println("Size => => "+adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size());
			System.out.println("CAPACITY =>  => "+adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity());
			if (adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size() == adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity()) {
	            System.out.println("CHANGE ROOM STATUS VACANT TO OCCUPIED=> " + adminService.changeRoomStatus(roomByRoomNumber.getId(), "Occupied"));
	      }
//			System.out.println("STUDENT FOUND OR NOT => "+adminService.getStudentByEmail(student.getEmail()).getId());
//			AddFeeByStudentId(adminService.getStudentByEmail(student.getEmail()).getId(),paymentType);
			System.out.println("templates/admin/AddStudentAgain.jsp?success=true");
            redirectTo(response, "templates/admin/AddStudentAgain.jsp?success=true");
        } else {
        	System.out.println("templates/admin/AddStudentAgain.jsp?error=true");
            redirectTo(response, "templates/admin/AddStudentAgain.jsp?error=true");
        }
		
	}



	private void addStudentAgain(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("student", adminService.getStudentById(id));
	        try {
				request.getRequestDispatcher("templates/admin/AddStudentAgain.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		
	}



	private void StudentsHistory(HttpServletRequest request, HttpServletResponse response) {
		 request.setAttribute("students", adminService.getStudentsHistory());
	        try {
				request.getRequestDispatcher("templates/admin/StudentsHistory.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		
	}

	private void monthlyReport(HttpServletRequest request, HttpServletResponse response) {
		String month = request.getParameter("month"); // Format: YYYY-MM
        List<Fee> monthlyFees = adminService.getMonthlyFeeReport(month);
    	request.setAttribute("TotalPaidFee", adminService.countTotalRevenueOfMonth(month));
    	request.setAttribute("TotalUnPaidFee", adminService.countPendingFeesOfMonth(month));
    	request.setAttribute("Month", month);
        request.setAttribute("monthlyFees", monthlyFees);
        try {
			request.getRequestDispatcher("templates/admin/MonthlyReport.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void searchVisitor(HttpServletRequest request, HttpServletResponse response) {
		String searchQuery = request.getParameter("query");
		request.setAttribute("searchdata", adminService.searchVisitor(searchQuery));
		try {
			request.getRequestDispatcher("templates/admin/VisitorSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}



	private void searchFeedback(HttpServletRequest request, HttpServletResponse response) {
		String searchQuery = request.getParameter("query");
		request.setAttribute("searchdata", adminService.searchFeedback(searchQuery));
		try {
			request.getRequestDispatcher("templates/admin/FeedbackSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}



	private void searchPayment(HttpServletRequest request, HttpServletResponse response) {
		String searchQuery = request.getParameter("query");
		request.setAttribute("searchdata", adminService.searchPayment(searchQuery));
		try {
			request.getRequestDispatcher("templates/admin/PaymentSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}



	private void setAdminDashboardData(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("totalStudent", adminService.countTotalStudent());
		request.setAttribute("totalRoom", adminService.countTotalRoom());
		request.setAttribute("totalRevenue", adminService.countTotalRevenue());
		request.setAttribute("occupiedRoom", adminService.countOccupiedRoom());
		request.setAttribute("vacantRoom", adminService.countVacantRoom());
		request.setAttribute("pendingPayment", adminService.countPendingPayment());
		request.setAttribute("singleRoom", adminService.countSingleRoom());
		request.setAttribute("doubleRoom", adminService.countDoubleRoom());
		request.setAttribute("tripleRoom", adminService.countTripleRoom());
	}
	
	private void adminProfile(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.getRequestDispatcher("templates/admin/AdminProfile.jsp").forward(request, response);
		
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
		
	}


	private void deleteVisitors(HttpServletRequest request, HttpServletResponse response) {
	    Integer integer = Integer.parseInt(request.getParameter("id"));
	    System.out.println(integer);
	    Boolean boolean1 =adminService.deleteVisitor(integer);
	    System.out.println(boolean1);
	    if(boolean1!=null) {
	    	manageVisitors(request, response);
	    }		
	}
	
	private void manageVisitors(HttpServletRequest request, HttpServletResponse response) {
    	List<Visitor> list = adminService.getAllVisitors();
		//System.out.println(list);
		request.setAttribute("visitor", list);
		try {
			request.getRequestDispatcher("templates/admin/Manage-visitors.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
	
	private void visitorExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("exit.................");
		String vId = request.getParameter("visitorId");
		System.out.println("v===================="+vId);
		Integer int1 = Integer.parseInt(vId);
		System.out.println("int..............." +int1);
		Visitor vById = adminService.getVisitorById(int1);
		System.out.println(vById);
		Timestamp exitTime = new Timestamp(System.currentTimeMillis());
		Visitor v = new Visitor(vById.getId(),vById.getName(),vById.getNumber(),vById.getPurpose(),vById.getEntryTime(),exitTime);
		adminService.updateVisitor(v);
	
		response.sendRedirect("/HostelManagementSystem");

	}
	
	// visitor entry
		private void visitingPage(HttpServletRequest request, HttpServletResponse response) {
			try {
				String name = request.getParameter("name");
				String number = request.getParameter("number");
				String purpose = request.getParameter("purpose");

				Timestamp entryTime = new Timestamp(System.currentTimeMillis());

//		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		        String currentDate = dateFormat.format(entryTime);
				List<String> errors = addVisitorValidate(name, number, purpose);
				if(!errors.isEmpty())
				{
					System.out.println("ERROR FOUND IN VISING PAGE");
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/templates/VisitUsForm.jsp").forward(request, response);
					return;
				}
				Visitor visitor = new Visitor(name, number, purpose, entryTime);
				System.out.println(visitor);

				boolean isSaved = adminService.saveVisitor(visitor);
				System.out.println("Visitor saved: " + isSaved);

				Visitor visitor2 = adminService.getVisitorByDate(name, entryTime);

				request.setAttribute("visitorId", visitor2.getId());
				request.getRequestDispatcher("/templates/Visiting-page.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	
	private List<String> addVisitorValidate(String name,String number, String purpose)
	{
		List<String> errors = new ArrayList<>();
		if(isEmpty(name))	errors.add("Name is Required, Please Enter Name");
		if(isEmpty(purpose))	errors.add("Purpose is Required, Please Enter Purpose");
		if(isEmpty(number))	
			errors.add("Number is Required, Please Enter Number");
		else {
			if(!Validation.mobileNumberValidation(number))
				errors.add("Mobile Number Format is Wrong, Please Enter Right Mobile Number"); 
		}
		
		return errors;
	}
		
	private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer feedbackId = Integer.parseInt(request.getParameter("feedbackId"));
		String responseText = request.getParameter("response");
		Integer studentId = Integer.parseInt(request.getParameter("studentId"));

		System.out.println("Feedback ID: " + feedbackId);
		System.out.println("Response Text: " + responseText);
		System.out.println("Student Id: " + studentId);

		boolean success = adminService.updateComplaintStatus(feedbackId, "Resolved", responseText);
		System.out.println("==>" + success);
	}
	
	private void manageFeedback(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("feedbacks", adminService.viewComplaints());
		try {
			request.getRequestDispatcher("templates/admin/Manage-feedbacks.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	private void payFee(HttpServletRequest request, HttpServletResponse response) {
		Integer feeId = Integer.parseInt(request.getParameter("feeId"));
	    Double double1= Double.parseDouble( request.getParameter("amount"));
	    
		Integer studentId = Integer.parseInt(request.getParameter("studentId"));
		String paymentMode = request.getParameter("paymentMode");
		System.out.println(feeId+"  "+double1+" "+paymentMode);
	    // Fetch fee record
	    Fee fee = adminService.getFeeById(feeId);
	    System.out.println(fee);
	    if (fee != null && "Unpaid".equalsIgnoreCase(fee.getStatus())) {
	        fee.setStatus("Paid"); // Update status
	        fee.setPaymentDate(new Timestamp(System.currentTimeMillis()));
	        fee.setPaymentMode(paymentMode);
	       System.out.println("UPDATE FEES OR NOT "+adminService.updateFee(fee));// Save update
	    }
	    System.out.println(adminService.changeStudentFeeStatus(true, studentId));
//	    adminService.updateFeeStatus(studentId, "Paid",paymentMode);
	    
	    // Redirect back to manage payments
	    try {
	        response.sendRedirect("admin?action=managepayments&success=Payment successful");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

		
	}
	private void managePayments(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("payments", adminService.getAllFees());
		try {
			request.getRequestDispatcher("templates/admin/Manage-payments.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}		

	//Sanjana
	private void updateRoomInfo(HttpServletRequest request, HttpServletResponse response) {
		String roomId = request.getParameter("id");
		String roomNumber =	request.getParameter("roomNumber");
		String capacity =	request.getParameter("capacity");
		String payment = request.getParameter("payment");
				
		Integer id = Integer.parseInt(roomId);
		Room getExistsRoom = adminService.getRoomByRoomId(id);
		List<String> errors = updateRoomValidate(roomNumber,capacity,payment,id);
		System.out.println(getExistsRoom.getCapacity()>Integer.parseInt(capacity));
		System.out.println(getExistsRoom.getStatus().equals("Occupied"));
//		if(getExistsRoom.getCapacity()>Integer.parseInt(capacity) && getExistsRoom.getStatus().equals("Occupied"))
//			errors.add("Room is Occupied You Cann't Decrease the Room Capacity, You Can Increase OR Don't Change The Capacity");
		if(getExistsRoom.getCapacity()>Integer.parseInt(capacity) && adminService.getStudentsByRoomId(getExistsRoom.getId()).size() == adminService.getRoomByRoomId(getExistsRoom.getId()).getCapacity())
			errors.add("Room is Occupied You Cann't Decrease the Room Capacity, You Can Increase OR Don't Change The Capacity");
		if(!errors.isEmpty()) {
			System.out.println("WHEN ERROR FOUND IN UPDATE ROOM");
			
			request.setAttribute("room", new Room(
					Integer.parseInt(roomNumber),
					Integer.parseInt(capacity),
					Double.parseDouble(payment)
					));
			request.setAttribute("id", roomId);
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("templates/admin/UpdateRoom.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		Room room = new Room();
		System.out.println(adminService.getRoomByRoomId(id).getCapacity()+" <= Get Room Capacity");
		System.out.println(Integer.parseInt(capacity)+" <= New Room Capacity");
		System.out.println(adminService.getRoomByRoomId(id).getCapacity()<Integer.parseInt(capacity));
		
		if (adminService.getStudentsByRoomId(getExistsRoom.getId()).size() == Integer.parseInt(capacity))
			room.setStatus("Occupied");
		else if(getExistsRoom.getCapacity()<Integer.parseInt(capacity))
			room.setStatus("Vacant");
		else
			room.setStatus(getExistsRoom.getStatus());	
		
		room.setRoomNumber(Integer.parseInt(roomNumber));
		room.setCapacity(Integer.parseInt(capacity));
		room.setPayment(Double.parseDouble(payment));
		System.out.println(room.getStatus()+" FINAL ROOM STATUS");
		if(adminService.updateRoom(
				room,id)
				)
		{
			redirectTo(response, "templates/admin/UpdateRoom.jsp?success=true");
		}
		else {
			redirectTo(response, "templates/admin/UpdateRoom.jsp?success=false");
		}
		
	}
	
	private List<String> updateRoomValidate(String roomNumber, String capacity, String payment,Integer roomId) {
		List<String> errors = new ArrayList<>();
		if(isEmpty(roomNumber))	errors.add("Please Enter Room Number");
		else {
			if(!Validation.numberValidation(roomNumber))
			errors.add("Only Room Numbers are allowed in Room Number.");
		}
		if(roomNumber!=null &&  !roomNumber.isEmpty() && !roomNumber.isBlank())
		{
			System.out.println("ROOM NUMBER ==> "+roomNumber);
			Integer roomNum = Integer.parseInt(roomNumber);
			System.out.println(">>>>>> "+adminService.getRoomByRoomId(roomId).getRoomNumber());
			System.out.println(">>>>>> "+roomNum);
			System.out.println(adminService.getRoomByRoomId(roomId).getRoomNumber().equals(roomNum));
			System.out.println(adminService.isRoomExists(roomNum) +" <------");
			System.out.println(!adminService.getRoomByRoomId(roomId).getRoomNumber().equals(roomNum)+" <----- Secound");
			if(adminService.isRoomExists(roomNum) && !adminService.getRoomByRoomId(roomId).getRoomNumber().equals(roomNum))
				errors.add("Room Number Already Exists");
		}
		if(isEmpty(capacity))
			errors.add("Please Enter Capacity");
		if(isEmpty(payment))
			errors.add("Please Enter Payment");
		else {
			if(!Validation.numberValidation(payment))
				errors.add("Only numbers are allowed in Payment.");
		}
		System.out.println("Payment Update Room Validate ----> "+payment);
		return errors;
	}


	private void updateRoom(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println("ROOM ID IN UPDATEROOM PAGE => "+id);
		if("null".equals(id) || id!=null)
		{
			Room room = adminService.getRoomByRoomId(Integer.parseInt(id));
			System.out.println("room ----> "+room);
			request.setAttribute("room", room);
			request.setAttribute("id", id);
		}
		try {
			request.getRequestDispatcher("templates/admin/UpdateRoom.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		
		}
		
	}


	private void searchRoom(HttpServletRequest request, HttpServletResponse response) {
		String searchQuery = request.getParameter("query");
		request.setAttribute("searchdata", adminService.searchRoom(searchQuery));
		try {
			request.getRequestDispatcher("templates/admin/RoomSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void searchStudent(HttpServletRequest request, HttpServletResponse response) {
		String searchQuery = request.getParameter("query");
		request.setAttribute("searchdata", adminService.searchStudent(searchQuery));
		try {
			request.getRequestDispatcher("templates/admin/StudentSearchResult.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void paymentWhenStudentDeleted(HttpServletRequest request, HttpServletResponse response) {
		String paymentMode = request.getParameter("paymentMode");
		Integer studentId = Integer.parseInt(request.getParameter("studentId"));
		request.setAttribute("students", adminService.getAllStudents());
		request.setAttribute("studentId", studentId);
//		adminService.changeStudentFeeStatus(setFeePaidOrUnpaid(paymentMode), studentId);
		System.out.println("IN PAYMENT WHEN STUDENT DELETED.......");
		if(adminService.updateFeeStatus(studentId, "Paid",paymentMode)) {
			adminService.changeRoomStatus(adminService.getStudentById(studentId).getRoomId() , "Vacant");
			System.out.println("paymentSuccess");
			request.setAttribute("paymentSuccess", true);
		}else {
			System.out.println("paymentNotSuccess");
			request.setAttribute("paymentNotSuccess", true);
		}
		try {
			request.getRequestDispatcher("templates/admin/Manage-users.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("DELETE STUDENT =>>> "+request.getParameter("id"));
		Integer id = Integer.parseInt(request.getParameter("id"));
		Student studentById = adminService.getStudentById(id);
		request.setAttribute("students", adminService.getAllStudents());
		request.setAttribute("studentId", id);
		if(studentById.getFeesPaid()) {
			Boolean deleteStudent = adminService.changeStudentIsDeleted(id);
			Boolean changeRoomStatus = adminService.changeRoomStatus(studentById.getRoomId(), "Vacant");
			System.out.println("CHANGE ROOM STATUS VACANT => "+changeRoomStatus);
			System.out.println("Student Delete => "+deleteStudent);
			request.setAttribute("deleteSuccess", true);
		}else if("done".equals(request.getParameter("paymentDone"))) {
			System.out.println("PENDING...... TO PAYMENT DONE THEN DELTE STUDENT");
			Boolean deleteStudent = adminService.changeStudentIsDeleted(id);
			Boolean changeRoomStatus = adminService.changeRoomStatus(studentById.getRoomId(), "Vacant");
			System.out.println("CHANGE ROOM STATUS VACANT => "+changeRoomStatus);
			System.out.println("Student Delete => "+deleteStudent);
			request.setAttribute("deleteSuccess", true);
		}
		else {
			request.setAttribute("feePending", true);
			
		}
		try {
			request.getRequestDispatcher("templates/admin/Manage-users.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void updateStudentInfo(HttpServletRequest request, HttpServletResponse response) {
		String stdId = request.getParameter("id");
		Student student = new Student(
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("contact")
				);
		Integer id = Integer.parseInt(stdId);
		List<String> errors = updateStudentValidate(student,id);
		if(!errors.isEmpty()) {
			System.out.println("WHEN ERROR FOUND STUDENT => "+student);
			request.setAttribute("student", student);
			request.setAttribute("id", stdId);
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("templates/admin/UpdateStudent.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if(adminService.updateStudent(student,id))
		{
			redirectTo(response, "templates/admin/UpdateStudent.jsp?success=true");
		}
		else {
			redirectTo(response, "templates/admin/UpdateStudent.jsp?success=false");
		}
		
	}


	private List<String> updateStudentValidate(Student student,Integer id) {
		List<String> errors = new ArrayList<>();
		
		if(isEmpty(student.getName()))	errors.add("Please Enter Name");
		if(!Validation.emailValidation(student.getEmail()))	
			errors.add("Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)");
		System.out.println("UPDATE STUDENT DATA => GET EMAIL BY STD ID =>"+adminService.getStudentById(id).getEmail());
		System.out.println("UPDATE STUDENT DATA => Student New Email =>"+student.getEmail());
		if(adminService.isEmailExists(student.getEmail()) && !adminService.getStudentById(id).getEmail().equals(student.getEmail()))
			errors.add("Email is Already Exists");
		
		if(!Validation.mobileNumberValidation(student.getContact()))
			errors.add("Mobile Number Format is Wrong, Please Enter Right Mobile Number"); 
		for (String string : errors) {
			System.out.println("ERROR UPDATESTUDENT => "+string);
		}
		return errors;
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println("STUDENT ID IN UPDATESTUDENT PAGE => "+id);
		if("null".equals(id) || id!=null)
		{
			Student student = adminService.getStudentById(Integer.parseInt(id));
			System.out.println("student ----> "+student);
			request.setAttribute("student", student);
			request.setAttribute("id", id);
		}
		try {
			request.getRequestDispatcher("templates/admin/UpdateStudent.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		
		}
	}


	private List<String> roomValidate(String roomNumber, String capacity, String payment){
		List<String> errors = new ArrayList<>();
		if(roomNumber==null || roomNumber.isEmpty() || roomNumber.isBlank())
			errors.add("Room Number is Required");
		System.out.println(Validation.numberValidation(roomNumber)+" <== Check Room Number");
		if(!Validation.numberValidation(roomNumber))
			errors.add("Only Room Numbers are allowed in Room Number.");
		if(capacity==null || capacity.isEmpty() || capacity.isBlank())
			errors.add("Capacity is Required");
		if(payment==null || payment.isEmpty() || payment.isBlank())
			errors.add("Payment is Required");
		else {
			if(!Validation.numberValidation(payment))
				errors.add("Only numbers are allowed in Payment.");
		}
		if(roomNumber!=null &&  !roomNumber.isEmpty() && !roomNumber.isBlank() && !errors.contains("Only Room Numbers are allowed in Room Number."))
		{
			System.out.println("ROOM NUMBER ==> "+roomNumber);
			Integer roomNum = Integer.parseInt(roomNumber);
			if(adminService.isRoomExists(roomNum))
				errors.add("Room Number Already Exists");
		}
		return errors;
	}
	private void addRoom(HttpServletRequest request, HttpServletResponse response) {
		String roomNumber = request.getParameter("roomNumber");
		String capacity = request.getParameter("capacity");
		String payment = request.getParameter("payment");
		System.out.println(roomNumber+", "+capacity+", "+payment);
		List<String> errors = roomValidate(roomNumber, capacity, payment);
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("templates/admin/AddRoom.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}			
		}else {
			boolean isAdded = adminService.addRoom(
					new Room(
							Integer.parseInt(roomNumber),
							Integer.parseInt(capacity),
							Double.parseDouble(payment),
							"Vacant"
							)
					);
			
			if (isAdded) {
				System.out.println("templates/admin/AddRoom.jsp?success=true");
				System.out.println("CONTEXT PATH => "+request.getContextPath());
				System.out.println("REQ URI => "+request.getRequestURI());
	            redirectTo(response, "templates/admin/AddRoom.jsp?success=true");
	        }
		}
		
	}


	private void addNewRoom(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("templates/admin/AddRoom.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void addNewStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("templates/admin/AddStudent.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	


	private void manageRooms(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("rooms", adminService.getAllRooms());
		try {
			request.getRequestDispatcher("templates/admin/Manage-rooms.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void manageUser(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", adminService.getAllStudents());
		try {
			request.getRequestDispatcher("templates/admin/Manage-users.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		System.out.println("LOGOUT");
		session.invalidate();
		try {
			response.sendRedirect("login");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
		//OLD CODE
	
//	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
//		Student student = new Student(
//				request.getParameter("name"),
//				request.getParameter("email"),
//				request.getParameter("password"),
//				request.getParameter("contact")
//				);
//		System.out.println("STUDENT ADD STUDNT => "+student);
//		String roomNumberStr = request.getParameter("roomNumber");
//		System.out.println("ROOM NUMBER  ADD STUDENT=> "+roomNumberStr);
//		String paymentType = request.getParameter("payment_type");
//		List<String> errors = studentValidate(student,roomNumberStr,paymentType);
//		
//		if(!errors.isEmpty()) {
//			request.setAttribute("errors", errors);
//			try {
//				request.getRequestDispatcher("templates/admin/AddStudent.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			}
//			return;
//		}
//		
//		//Assign Room
//		if(roomNumberStr!=null && !roomNumberStr.isEmpty()) {
//			Integer roomNumber = Integer.parseInt(roomNumberStr);
//			Room roomByRoomNumber = adminService.getRoomByRoomNumber(roomNumber);
//			System.out.println("ROOM BY ROOM ID => "+roomByRoomNumber);
//			student.setRoomId(roomByRoomNumber.getId());
//			
//		}
//		// Encrypt Password 
//		student.setPassword(Validation.encryptPassword(request.getParameter("password")));
//		//Set Fee Status
//		student.setFeesPaid(setFeePaidOrUnpaid(paymentType)); 
//		student.setIsDeleted(false);
//		boolean isAdded = adminService.addStudent(student);
//		System.out.println(isAdded);
//		if (isAdded) {
//			Room roomByRoomNumber = adminService.getRoomByRoomNumber(Integer.parseInt(roomNumberStr));
//			System.out.println("ROOM BY ROOM ID => => "+roomByRoomNumber.getId());
//			System.out.println("Size => => "+adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size());
//			System.out.println("CAPACITY =>  => "+adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity());
//			if (adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size() == adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity()) {
//	            System.out.println("CHANGE ROOM STATUS VACANT TO OCCUPIED=> " + adminService.changeRoomStatus(roomByRoomNumber.getId(), "Occupied"));
//	      }
//			System.out.println("STUDENT FOUND OR NOT => "+adminService.getStudentByEmail(student.getEmail()).getId());
//			AddFeeByStudentId(adminService.getStudentByEmail(student.getEmail()).getId(),paymentType);
//			System.out.println("templates/admin/AddStudent.jsp?success=true");
//            redirectTo(response, "templates/admin/AddStudent.jsp?success=true");
//        } else {
//        	System.out.println("templates/admin/AddStudent.jsp?error=true");
//            redirectTo(response, "templates/admin/AddStudent.jsp?error=true");
//        }
//		
//	}
	
	
	
	//NEW 
	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		Student student = new Student(
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("contact")
				);
		System.out.println("STUDENT ADD STUDNT => "+student);
		String roomNumberStr = request.getParameter("roomNumber");
		System.out.println("ROOM NUMBER  ADD STUDENT=> "+roomNumberStr);
		String paymentType = request.getParameter("payment_type");
		List<String> errors = studentValidate(student,roomNumberStr,paymentType);
		
		if(!errors.isEmpty()) {
			if(errors.contains("Email is Already Exists")){
				Student studentByEmail = adminService.getStudentByEmail(student.getEmail());
				if(studentByEmail.getIsDeleted()) {
					errors.add("Already Registered Student, Please Check Student History and Click ADD AGAIN Button");
				}
			}
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("templates/admin/AddStudent.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		//Assign Room
		if(roomNumberStr!=null && !roomNumberStr.isEmpty()) {
			Integer roomNumber = Integer.parseInt(roomNumberStr);
			Room roomByRoomNumber = adminService.getRoomByRoomNumber(roomNumber);
			System.out.println("ROOM BY ROOM ID => "+roomByRoomNumber);
			student.setRoomId(roomByRoomNumber.getId());
			
		}
		// Encrypt Password 
		student.setPassword(Validation.encryptPassword(request.getParameter("password")));
		//Set Fee Status
		student.setFeesPaid(setFeePaidOrUnpaid(paymentType)); 
		student.setIsDeleted(false);
		
		
		Room roomByRoomId = adminService.getRoomByRoomId(student.getRoomId());
		double feeAmount = roomByRoomId.getPayment();
		
		
		boolean isAdded = adminService.addStudent(student, student.getFeesPaid(), feeAmount, paymentType)>0;
		System.out.println(isAdded);
		if (isAdded) {
			Room roomByRoomNumber = adminService.getRoomByRoomNumber(Integer.parseInt(roomNumberStr));
			System.out.println("ROOM BY ROOM ID => => "+roomByRoomNumber.getId());
			System.out.println("Size => => "+adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size());
			System.out.println("CAPACITY =>  => "+adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity());
			if (adminService.getStudentsByRoomId(roomByRoomNumber.getId()).size() == adminService.getRoomByRoomId(roomByRoomNumber.getId()).getCapacity()) {
	            System.out.println("CHANGE ROOM STATUS VACANT TO OCCUPIED=> " + adminService.changeRoomStatus(roomByRoomNumber.getId(), "Occupied"));
	      }
//			System.out.println("STUDENT FOUND OR NOT => "+adminService.getStudentByEmail(student.getEmail()).getId());
//			AddFeeByStudentId(adminService.getStudentByEmail(student.getEmail()).getId(),paymentType);
			System.out.println("templates/admin/AddStudent.jsp?success=true");
            redirectTo(response, "templates/admin/AddStudent.jsp?success=true");
        } else {
        	System.out.println("templates/admin/AddStudent.jsp?error=true");
            redirectTo(response, "templates/admin/AddStudent.jsp?error=true");
        }
		
	}

	private Boolean hasSpaceInRoom(Integer roomId) {
		Integer capacity = adminService.getRoomByRoomId(roomId).getCapacity();
		List<Student> studentsByRoomId = adminService.getStudentsByRoomId(roomId);
		if(studentsByRoomId.isEmpty())
			System.out.println("NO Student Found Size = 0");
		System.out.println("CAPACITY => "+capacity);
		System.out.println("Already Fill => "+studentsByRoomId.size());
		return studentsByRoomId.size() < capacity;
	}
//	private Boolean AddFeeByStudentId(Integer studentId, String paymentMode) {
//		Fee fee = new Fee();
//		System.out.println("STD ID => "+studentId);
//		fee.setAmount(getPaymentByRoomId(adminService.getStudentById(studentId).getRoomId()));
//		if(paymentMode.equals("unpaid")) {
//			fee.setStatus("Pending");
//		}
//		else {
//			fee.setPaymentDate(new Timestamp(System.currentTimeMillis()));
//			fee.setStatus("Paid");
//		}
//		fee.setStudentId(studentId);
//		fee.setPaymentMode(paymentMode);
//		return adminService.makePayment(fee);
//	}
	
//	private Double getPaymentByRoomId(Integer roomId) {
//		return adminService.getRoomByRoomId(roomId).getPayment();
//	}


	private Boolean setFeePaidOrUnpaid(String paymentType) {
		if(paymentType.equals("unpaid"))
			return false;
		if(paymentType.equals("cash"))
			return true;
		if(paymentType.equals("online"))
			return true;
		return false;
	}


	private List<String> studentValidate(Student student,String roomNumber,String paymentType) {
		List<String> errors = new ArrayList<>();
		if(student==null) {
			errors.add("Please Fill Fields....");
			 return errors;
		}
		if(isEmpty(student.getName()))	errors.add("Name is Required..");
		
		if(!Validation.emailValidation(student.getEmail()))	
			errors.add("Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)"); 
		
		if(adminService.isEmailExists(student.getEmail()))
		{
			errors.add("Email is Already Exists");
		}
		
		if(!Validation.mobileNumberValidation(student.getContact()))
			errors.add("Mobile Number Format is Wrong, Please Enter Right Mobile Number"); 

		if(!Validation.passwordValidation(student.getPassword()))
			errors.add("Password Format is Wrong, Please Enter Strong Password"); 
		
		if(!Validation.numberValidation(roomNumber))
			errors.add("Only Room Numbers are allowed in Room Number.");
		if(roomNumber!=null &&  !roomNumber.isEmpty() && !roomNumber.isBlank())
		{
			System.out.println("ROOM NUMBER ==> "+roomNumber);
			Integer roomNum = Integer.parseInt(roomNumber);
			System.out.println(adminService.isRoomExists(roomNum)+" <====== Room ISEXISTS "+roomNum);
//			if(!adminService.isRoomExists(roomNum))
//				errors.add("Room Not Found, Please Check Entered Room Number");
			Room roomByRoomNum = adminService.getRoomByRoomNumber(roomNum);
			System.out.println("BYROOM NUMBER => "+roomByRoomNum);
			if(roomByRoomNum==null)
				errors.add("Room Not Found, Please Check Entered Room Number");
			else {
				if(!hasSpaceInRoom(roomByRoomNum.getId())) 
				errors.add("Room is already full. Please select a different room.");
			}
				
		}
		if(isEmpty(paymentType))
			errors.add("Fee Mode is Required, Please Select Fee Mode");
		return errors;
	}
	
	private Boolean isEmpty(String str)
	{
		return str == null || str.trim().isEmpty();
	}
	private void redirectTo(HttpServletResponse response, String url) {
	    try {
	        response.sendRedirect(url);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("addStudent.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}


	private void dadhboard(HttpServletRequest request, HttpServletResponse response) {
		setAdminDashboardData(request,response);
		try {
			request.getRequestDispatcher("templates/admin/AdminDashboard.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
