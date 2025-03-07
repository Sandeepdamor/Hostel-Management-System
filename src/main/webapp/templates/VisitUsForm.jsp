<!DOCTYPE html>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Visit Us</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
}

.container {
	max-width: 500px;
	    margin-top: 160px;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.navbar {
	background: rgba(0, 0, 0, 0.9);
	padding: 10px 0;
	position: fixed;
	width: 100%;
	top: 0;
	left: 0;
	z-index: 1000;
	height: 70px;
}

.navbar-brand {
	font-size: 24px;
	font-weight: bold;
	color: #ffcc00;
}

.logo-img {
	height: 50px; /* Adjust size as needed */
	width: 50px;
	border-radius: 50%; /* Makes the image circular */
	margin-right: 10px; /* Adds spacing between logo and text */
}

.btn-submit {
	background-color: #ffcc00;
	color: black;
	border-radius: 5px;
	width: 100%;
}

/* Error message styling */
.error {
	color: red;
	font-size: 10px;
	font-weight: bold;
	margin-top: 5px;
	display: block;
}
.btn-submit:hover {
	background-color: #e6b800;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg">
		<!-- <div class="container"> -->
			<a class="navbar-brand" href="#"> <img
				src="../static/assets/Logo.png" alt="Maple Logo" class="logo-img">
				Maple
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link text-white" href="../">Home</a></li>
					<!-- <li class="nav-item"><a class="nav-link text-white"
						href="#features">Features</a></li> -->
					<!-- <li class="nav-item"><a class="nav-link text-white"
						href="#contact">Contact Us</a></li> -->
					<li class="nav-item"><a class="nav-link text-white"
						href="./templates/VisitUsForm.jsp">Visit</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="../HostelManagementSystem/login">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h2 class="text-center">Visit Us</h2>
		<form action="../../HostelManagementSystem/admin2" method="post">
		<%List<String> errors = (List<String>) request.getAttribute("errors"); %>
			<div class="mb-3">
				<label for="name" class="form-label">Name</label> <input type="text"
					class="form-control" id="name" name="name" value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>" placeholder="Enter full name">
					<div>
                        <% if (errors != null && errors.contains("Name is Required, Please Enter Name")) { %>
                        	<span class="error">Name is Required, Please Enter Name</span>
                        <% } %>
                    </div>
			</div>
			<div class="mb-3">
				<label for="number" class="form-label">Phone Number</label> <input
					type="tel" class="form-control" id="number" name="number" value="<%=request.getParameter("number") != null ? request.getParameter("number") : ""%>" placeholder="Enter Contact Number">
					<div>
                        <% if (errors != null && errors.contains("Number is Required, Please Enter Number")) { %>
                        	<span class="error">Number is Required, Please Enter Number</span>
                        <% } %>
                    </div>
                    <div>
                        <% if (errors != null && errors.contains("Mobile Number Format is Wrong, Please Enter Right Mobile Number")) { %>
                        	<span class="error">Mobile Number Format is Wrong, Please Enter Right Mobile Number</span>
                        <% } %>
                    </div>
			</div>
			<div class="mb-3">
				<label for="purpose" class="form-label">Purpose</label>
				<textarea class="form-control" id="purpose" name="purpose" rows="3" >
				<%=request.getParameter("purpose") != null ? request.getParameter("purpose") : ""%>
				</textarea>
					<div>
                        <% if (errors != null && errors.contains("Purpose is Required, Please Enter Purpose")) { %>
                        	<span class="error">Purpose is Required, Please Enter Purpose</span>
                        <% } %>
                    </div>
			</div>
			<button type="submit" value="visitorEntry" name="action"
				class="btn btn-submit">Submit</button>
		</form>
	</div>
</body>
<script>
	window.onbeforeunload = function() {
		// Capture visitorId from the hidden input
		let visitorId = document.querySelector('input[name="visitorId"]').value;

		if (visitorId) {
			navigator.sendBeacon('./admin2', new URLSearchParams({
				action : 'visitorExit',
				visitorId : visitorId
			}));
		}
	};
</script>
</html>
