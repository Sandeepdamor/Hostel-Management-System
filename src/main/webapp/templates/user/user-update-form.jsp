<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="com.dollop.app.bean.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            overflow-x: hidden;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
            transition: all 0.3s;
        }
        .footer {
            background: #2c3e50;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .card {
            transition: transform 0.3s;
        }
        .card:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <%@ include file="./user-sidebar.jsp" %>
    
    <%
        // Retrieve user object from session
        Student user = (Student) session.getAttribute("user");
        
        // Redirect to login if session expired
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <div class="content">
        <%@ include file="./user-navbar.jsp" %>
        
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3>Edit User Profile</h3>
                        </div>
                        <div class="card-body">
                            <form id="updateForm" action="updateProfile" method="post">
                                <div class="mb-3">
                                    <label for="userName" class="form-label">Name</label>
                                    <input type="text" class="form-control" id="userName" name="userName" value="<%= user.getName() %>" required>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="userEmail" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="userEmail" name="userEmail" value="<%= user.getEmail() %>" readonly>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="userPhone" class="form-label">Phone Number</label>
                                    <input type="text" class="form-control" id="userPhone" name="userPhone" value="<%= user.getContact() %>" required>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="userPassword" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="userPassword" name="userPassword" value="<%= user.getPassword() %>" required>
                                </div>
                                
                                <div class="d-flex justify-content-between">
                                    <a href="javascript:history.back()" class="btn btn-secondary">Cancel</a>
                                    <button type="button" class="btn btn-primary" onclick="document.getElementById('updateForm').submit();">Update Profile</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>&copy; 2025 Hostel Management System</p>
    </div>
</body>
</html>
 --%>



<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
	overflow-x: hidden;
}

.content {
	margin-left: 250px;
	padding: 20px;
	transition: all 0.3s;
}

.footer {
	background: #2c3e50;
	color: white;
	text-align: center;
	padding: 10px;
	position: fixed;
	bottom: 0;
	width: 100%;
}

.card {
	transition: transform 0.3s;
}

.card:hover {
	transform: scale(1.05);
}
/* Error message styling */
.error {
	color: red;
	font-size: 10px;
	font-weight: bold;
	margin-top: 5px;
	display: block;
}
</style>
</head>
<body>
	<%@ include file="./user-sidebar.jsp"%>

	<%
	// Retrieve user object from session
	Student user = (Student) session.getAttribute("user");
	List<String> errors = (List<String>) request.getAttribute("errors");
	// Redirect to login if session expired
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>

	<div class="content">
		<%@ include file="./user-navbar.jsp"%>

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header text-center">
							<h3>Edit User Profile</h3>
						</div>
						<div class="card-body">
							<form action="./student" method="post">
								<div class="mb-3">
									 <input
										type="hidden" class="form-control" id="userId" name="userId"
										value="<%=user.getId()%>" readonly>
								</div>
								<div class="mb-3">
									<label for="userName" class="form-label">Name</label> <input
										type="text" class="form-control" id="userName" name="userName"
										value="<%=user.getName()%>">
									<div>
                        				<% if (errors != null && errors.contains("Please Enter Name")) { %>
                        					<span class="error">Please Enter Name</span>
                        				<% } %>
                    				</div>
       
								</div>

								<div class="mb-3">
									<label for="userEmail" class="form-label">Email</label> <input
										type="email" class="form-control" id="userEmail"
										name="userEmail" value="<%=user.getEmail()%>">
										<div>
                        				<% if (errors != null && errors.contains("Please Enter Email")) { %>
                        					<span class="error">Please Enter Email</span>
                        				<% } %>
                    				</div>
                    				<div>
                        				<% if (errors != null && errors.contains("Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)")) { %>
                        					<span class="error">Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)</span>
                        				<% } %>
                    				</div>
                    				<div>
                        				<% if (errors != null && errors.contains("Email Already Exists")) { %>
                        					<span class="error">Email Already Exists</span>
                        				<% } %>
                    				</div>
								</div>

								<div class="mb-3">
									<label for="userPhone" class="form-label">Phone Number</label>
									<input type="text" class="form-control" id="userPhone"
										name="userPhone" value="<%=user.getContact()%>">
										<div>
                        				<% if (errors != null && errors.contains("Please Enter Contact")) { %>
                        					<span class="error">Please Enter Contact</span>
                        				<% } %>
                    				</div>
                    				<div>
                        				<% if (errors != null && errors.contains("Mobile Number Format is Wrong, Please Enter Right Mobile Number")) { %>
                        					<span class="error">Mobile Number Format is Wrong, Please Enter Right Mobile Number</span>
                        				<% } %>
                    				</div>
								</div>

								<div class="mb-3">
    <label for="userPassword" class="form-label">Password</label>
    <input type="password" class="form-control" id="userPassword"
           name="userPassword" placeholder="Enter new password (leave blank to keep old)">
    
    <div>
        <% if (errors != null && errors.contains("Please Enter Password")) { %>
            <span class="error">Please Enter Password</span>
        <% } %>
    </div>
    
    <div>
        <% if (errors != null && errors.contains("Password Format is Wrong, Please Enter Strong Password")) { %>
            <span class="error">Password Format is Wrong, Please Enter Strong Password</span>
        <% } %>
    </div>
</div>


								<div class="d-flex justify-content-between">
									<a href="student?action=profile" class="btn btn-secondary">Cancel</a>
									<button type="submit" name="action" value="updateProfile"
										class="btn btn-primary">Update Profile</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <!-- JavaScript for showing popup based on response -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
		document.addEventListener("DOMContentLoaded", function() {
			const urlParams = new URLSearchParams(window.location.search);
			if (urlParams.has("success")) {
				Swal.fire({
					title : "Success!",
					text : "User Updated Successfully!",
					icon : "success",
					confirmButtonText : "OK"
				}).then(() => {
                    window.location.href = "../../../HostelManagementSystem/student?action=profile"; // Change to your actual user list page URL
                });
			}
		});
	</script>

	<!-- <div class="footer">
		<p>&copy; 2025 Hostel Management System</p>
	</div> -->
</body>
</html>
