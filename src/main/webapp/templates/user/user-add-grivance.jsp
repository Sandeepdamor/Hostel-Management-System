<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Submit Grievance</title>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script> -->
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
	margin: 0;
	padding: 0;
	display: flex;
}

.content {
	margin-left: 250px;
	padding: 20px;
	transition: all 0.3s;
	width: 100%;
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

.container {
	    max-width: 474px;
    margin: auto;
    background: white;
    padding: 34px;
    border-radius: 38px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 118px;

}

h3 {
	text-align: center;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
}

textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	resize: none;
	font-size: 14px;
}

.btn-container {
	display: flex;
	justify-content: space-between;
	margin-top: 15px;
}

.btn {
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.btn-secondary {
	background: #aaa;
	color: white;
	text-decoration: none;
}

.btn-primary {
	background: #007bff;
	color: white;
}

.btn:hover {
	opacity: 0.8;
}
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
	<%
	Student user = (Student) request.getAttribute("student");
	if (user == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	%>

	<%@ include file="./user-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./user-navbar.jsp"%>

		<div class="container">
			<h3>Submit Grievance</h3>
			<form action="./student" method="post">
				<div class="form-group">
					<label for="id">Student Id</label>
					<textarea id="id" name="id" readonly><%=user.getId()%></textarea>
				</div>

				<div class="form-group">
					<label for="description">Description</label>
					<textarea id="description" name="description"></textarea>
					 <div>
                        <%
                        if ( (String) request.getAttribute("error")!=null) {
                        %>
                        <span class="error">Description is Required</span>
                        <%
                        }
                        %>
                    </div>
				</div>

				<div class="btn-container">
					<a href="user-grivances.jsp" class="btn btn-secondary">Cancel</a>
					<button type="submit" name="action" value="addGrievances" class="btn btn-primary">Submit</button>
				</div>
			</form>
		</div>
	</div>

	<script>
	function handleFormSubmit(event) {
	    event.preventDefault(); // Prevent default form submission

	    Swal.fire({
	        title: "Success!",
	        text: "Grievance submitted successfully!",
	        icon: "success",
	        confirmButtonColor: "#ff8800",
	        confirmButtonText: "OK"
	    }).then((result) => {
	        if (result.isConfirmed) {
	            // Redirect to the servlet with the desired action
	            window.location.href = "./student?action=addGrievances";
	        }
	    });

	    return false; // Stop the default form submission
	}
	</script>
</body>
</html>