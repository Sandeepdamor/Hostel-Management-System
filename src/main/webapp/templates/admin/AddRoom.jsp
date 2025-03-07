<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Room</title>
<link rel="stylesheet" href="styles.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- External CSS for styling -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/js/all.min.js"></script>
<style>
body {
    font-family: 'Arial', sans-serif;
    background-color: #f4f4f4;
    overflow-x: hidden;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.content {
    margin-left: 250px;
    padding: 20px;
    flex-grow: 1;
    transition: all 0.3s;
}

.footer {
    background: #2c3e50;
    color: white;
    text-align: center;
    padding: 10px;
    position: relative;
    width: 100%;
    bottom: 0;
}

.card {
    transition: transform 0.3s;
}

.card:hover {
    transform: scale(1.05);
}

.container {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 600px;
    margin: auto;
}

.input-group {
    margin-bottom: 15px;
}

.input-group label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

.input-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
}
/* Style for the select dropdown */
.input-group select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    background-color: #f9f9f9;
    color: #333;
    transition: background-color 0.3s ease, border-color 0.3s ease;
}

/* Change the background color and border on focus */
.input-group select:focus {
    background-color: #fff;
    border-color: #6a11cb;
    outline: none;
}

.input-group select option {
    padding: 10px;
    background-color: #fff;
    color: #333;
}

/* Error message styling */
.error {
	color: red;
	font-size: 10px;
	font-weight: bold;
	margin-top: 5px;
	display: block;
}
.submit-btn {
    background: #6a11cb;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;
}

.submit-btn:hover {
    background: #2575fc;
}

.cancel-btn {
    background: #f44336;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;
    margin-left: 10px;
}

.cancel-btn:hover {
    background: #d32f2f;
}

@media ( max-width : 768px) {
    .content {
        margin-left: 0;
    }
    .footer {
        position: relative;
    }
}
</style>
</head>
<body>
	<%@ include file="./Admin-sidebar.jsp"%>

	<!-- Content Section -->
	<div class="content">
		<!-- Include the Navbar here -->
		<%@ include file="./Admin-navbar.jsp"%>

		<!-- Add New Student Form -->
		<div class="container mt-4">
			<!-- <div class="container"> -->
			<h2>Add New Room</h2>
			<form id="addRoomForm" action="../../../HostelManagementSystem/admin" method="post">
				<%
				List<String> errors = (List<String>) request.getAttribute("errors");
				%>

				<!-- Room Number Field -->
				<div class="input-group">
					<label for="roomNumber">Room Number</label>
					<input type="text" id="roomNumber" name="roomNumber"
						value="<%=request.getParameter("roomNumber") != null ? request.getParameter("roomNumber") : ""%>"
						placeholder="Enter Room Number">
					<div>
						<%
						if (errors != null && errors.contains("Room Number is Required")) {
						%>
						<span class="error">Room Number is Required</span>
						<%
						}
						%>
					</div>
					<div>
						<%
						if (errors != null && errors.contains("Room Number Already Exists")) {
						%>
						<span class="error">Room Number Already Exists</span>
						<%
						}
						%>
					</div>
					<div>
						<%
						if (errors != null && errors.contains("Only Room Numbers are allowed in Room Number.")) {
						%>
						<span class="error">Only Room Numbers are allowed in Room Number.</span>
						<%
						}
						%>
					</div>
				</div>

				<!-- Capacity Field -->
				<div class="input-group">
					<label for="capacity">Capacity</label>
					<select id="capacity" name="capacity">
						<option value="">Please Select</option>
						<option value="1"
							<%="1".equals(request.getParameter("capacity")) ? "selected" : ""%>>Single</option>
						<option value="2"
							<%="2".equals(request.getParameter("capacity")) ? "selected" : ""%>>Double</option>
						<option value="3"
							<%="3".equals(request.getParameter("capacity")) ? "selected" : ""%>>Triple</option>
					</select>
					<div>
						<%
						if (errors != null && errors.contains("Capacity is Required")) {
						%>
						<span class="error">Capacity is Required</span>
						<%
						}
						%>
					</div>
				</div>

				<!-- Payment Field -->
				 <div class="input-group">
                <label for="payment">Price per Month</label>
                <div>
            		<%if(errors!=null && errors.contains("Payment is Required")) {%>
            		<span class="error">Payment is Required</span>
            		<% } %>
            		<%if(errors!=null && errors.contains("Only numbers are allowed in Payment.")) {%>
            		<span class="error">Only numbers are allowed in Payment.</span>
            		<% } %>
            	</div>
                <input type="number" id="payment" name="payment" value="<%=request.getParameter("payment")!= null ? request.getParameter("payment") : ""%>" placeholder="Enter price per month">
            </div>

				

				<!-- Submit Button -->
				<button type="submit" class="submit-btn" name="action"
					value="addroom">Add Room</button>
			</form>
		</div>
	</div>

	<!-- JavaScript for showing popup based on response -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
    document.addEventListener("DOMContentLoaded", function() {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get("success") !== null) { // Ensures 'success' exists
            Swal.fire({
                title: "Success!",
                text: "Room Added Successfully!",
                icon: "success",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                    console.log("User clicked OK. Redirecting...");
                    window.location.href = '../../admin?action=managerooms';
                }
            });
        }
    });
</script>

</body>
</html>
