<%@page import="com.dollop.app.bean.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.*"%>
<!-- Import User Model -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<style>
/* Global Styling */
body {
	font-family: 'Poppins', sans-serif;
	background: #ffffff;
	color: #333;
	margin: 0;
	padding: 0;
}

/* Content Area */
.content {
	margin-left: 250px;
	padding: 30px;
	transition: margin-left 0.3s ease-in-out;
}

/* Footer */
.footer {
	background: #2c3e50;
	color: white;
	text-align: center;
	padding: 12px;
	position: fixed;
	bottom: 0;
	width: 100%;
	font-size: 14px;
	box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
}

.footer {
	background: #1e272e;
	color: white;
	text-align: center;
	padding: 10px;
	position: fixed bottom: 0;
	width:  100%;
}

/* Profile Image Styling */
.profile-img {
	width: 221px;
	height: 282px;
	border-radius: 17%;
	object-fit: cover;
	border: 5px solid #007bff;
	transition: transform 0.3s ease-in-out;
}

.profile-img:hover {
	transform: scale(1.05);
}

/* Profile Card */
.card {
	background: #ffffff;
	border-radius: 12px;
	border: 1px solid #e0e0e0;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	transition: all 0.3s ease-in-out;
}

.card:hover {
	transform: translateY(-3px);
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}

/* Button Styling */
.btn-primary {
	background: #007bff;
	border: none;
	transition: all 0.3s ease-in-out;
	padding: 10px 20px;
	border-radius: 6px;
	font-size: 16px;
	font-weight: 500;
}

.btn-primary:hover {
	background: #0056b3;
	transform: scale(1.04);
	box-shadow: 0 4px 10px rgba(0, 91, 187, 0.2);
}

/* Buttons */
.btn-primary {
	background: #007bff;
	border: none;
	transition: all 0.3s ease-in-out;
	padding: 10px 20px;
	border-radius: 6px;
	font-size: 16px;
	font-weight: 500;
}

.btn-primary:hover {
	background: #0056b3;
	transform: scale(1.04);
	box-shadow: 0 4px 10px rgba(0, 91, 187, 0.2);
}

/* Smooth fade-in effect */
@
keyframes fadeIn {from { opacity:0;
	transform: translateY(10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.card-body p {
	animation: fadeIn 0.5s ease-in-out;
}
</style>
</head>

<%@ include file="./Admin-sidebar.jsp"%>
<body>

	<%
	// Retrieve user object from session
	Admin user = (Admin) session.getAttribute("user");

	// Check if user is null (if session expired or not logged in)
	if (user == null) {
		response.sendRedirect("login.jsp"); // Redirect to login page
		return;
	}
	%>

	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card p-4">
						<div class="row align-items-center">

							<!-- User Image Section -->
							<div class="col-md-4 text-center">
								<img src="./static/assets/adminProfile.jpg" alt="User Profile"
									class="profile-img">
							</div>

							<!-- User Details Section -->
							<div class="col-md-8">
								<div class="card-body">
									<h3 class="text-center mb-3">Admin Profile</h3>
									<p>
										<strong>Name:</strong>
										<%=user.getName()%></p>
									<p>
										<strong>Email:</strong>
										<%=user.getEmail()%></p>
								</div>
							</div>

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
