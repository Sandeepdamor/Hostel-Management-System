<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Admin Profile</title>
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
</style>
</head>
<%@ include file="./Admin-sidebar.jsp"%>
<body>
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>

		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card">
						<div class="card-header text-center">
							<h3>Edit Admin Profile</h3>
						</div>
						<div class="card-body">
							<form action="updateProfile" method="post">
								<div class="mb-3">
									<label for="adminName" class="form-label">Name</label> <input
										type="text" class="form-control" id="adminName"
										name="adminName" value="Admin Name" required>
								</div>
								<div class="mb-3">
									<label for="adminEmail" class="form-label">Email</label> <input
										type="email" class="form-control" id="adminEmail"
										name="adminEmail" value="admin@example.com" required>
								</div>
								<div class="mb-3">
									<label for="adminPhone" class="form-label">Phone Number</label>
									<input type="text" class="form-control" id="adminPhone"
										name="adminPhone" value="1234567890" required>
								</div>
								<div class="mb-3">
									<label for="adminDept" class="form-label">Department</label> <input
										type="text" class="form-control" id="adminDept"
										name="adminDept" value="Administration" required>
								</div>
								<div class="mb-3">
									<label for="adminAddress" class="form-label">Address</label> <input
										type="text" class="form-control" id="adminAddress"
										name="adminAddress" value="Admin Address" required>
								</div>

								<div class="d-flex justify-content-between">
									<!-- Cancel button that goes back to the profile details -->
									<a href="javascript:history.back()" class="btn btn-secondary">Cancel</a>
									<button type="submit" class="btn btn-primary">Update
										Profile</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<div class="footer">
		<p>&copy; 2025 Hostel Management System</p>
	</div>
</body>
</html>
