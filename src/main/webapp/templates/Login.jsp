<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
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

.container {
	max-width: 493px;
	margin-top: 204px;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.btn-submit {
	background-color: #ffcc00;
	color: black;
	border-radius: 5px;
	width: 100%;
}

.btn-submit:hover {
	background-color: #e6b800;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg">

		<a class="navbar-brand" href="#"> <img
			src="../static/assets/Logo.png" alt="Maple Logo" class="logo-img">
			Maple
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link text-white" href="../">Home</a></li>

				<li class="nav-item"><a class="nav-link text-white"
					href="login">Login</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<h2 class="text-center">Login</h2>
		<form action="../login" method="post">
		<%String error = (String) request.getParameter("error");
		if(error!=null && error.contains("Invalid User or Password..!!")){%>
		<h6 style="color: red;"><%=error%></h6><% } %>
			<div class="mb-3">
				<label for="username" class="form-label">Email</label> <input
					type="email" class="form-control" id="username" name="username"
					required>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label> <input
					type="password" class="form-control" id="password" name="password"
					required>
			</div>
			<button type="submit" class="btn btn-submit">Login</button>
		</form>
	</div>
</body>
</html>