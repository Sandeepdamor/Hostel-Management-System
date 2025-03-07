<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hostel Management System</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	/*   background: url('./static/assets/Landing.jpg') no-repeat center center/cover; */
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

.hero {
	background: url('./static/assets/Landing3.jpg') no-repeat center center/cover;
	height: 115vh;
	display: flex;
	align-items: center;
	text-align: left;
	margin-top: 69px;
	padding-left: 5%;
	align-items: center;
	text-align: left;
	margin-top: 69px;
}

.hero p {
	color: midnightblue;
	font-size: 28px;
	margin-bottom: 10px;
}

.hero-content {
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}

.hero h1 {
	font-size: 89px;
	font-weight: 600;
	margin-bottom: 10px;
	color: midnightblue;
	border-bottom: 3px solid midnightblue;
	padding-bottom: 10px;
	display: inline-block;
	margin-top: -288px;
}

.hero ul {
	margin-top: 20px;
	padding-left: 20px;
	list-style-type: none;
}

.hero ul li {
	margin-bottom: 10px;
	font-size: 22px;
	color: midnightblue;
	font-weight: bold;
	display: flex;
	align-items: center;
}

.hero ul li::before {
	content: '\2022'; /* Bullet point */
	color: midnightblue;
	font-weight: bold;
	display: inline-block;
	width: 1em;
	margin-left: -1em;
	margin-right: 10px;
	font-size: 30px;
}

.btn-primary {
	background-color: #ffcc00;
	color: black;
	padding: 12px 24px;
	border-radius: 8px;
	margin-top: 20px;
	transition: transform 0.3s ease-in-out, background 0.3s;
}

.btn-primary:hover {
	background-color: #e6b800;
	transform: scale(1.05);
}

.features {
	padding: 60px 20px;
	text-align: center;
}

.feature-card {
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s;
	animation: fadeInUp 1s;
}

@
keyframes fadeInUp {from { transform:translateY(30px);
	opacity: 0;
}

to {
	transform: translateY(0);
	opacity: 1;
}

}
.feature-card:hover {
	transform: scale(1.05);
}

.feature-card img {
	width: 177px;
	height: 166px;
	max-height: 200px;
	margin-bottom: 15px;
	border-radius: 10px;
}

.gallery {
	padding: 50px 20px;
	text-align: center;
}

.gallery img {
	width: 100%;
	max-width: 300px;
	margin: 10px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
	transition: transform 0.3s;
}

.gallery img:hover {
	transform: scale(1.1);
}

.footer {
	background: rgba(0, 0, 0, 0.9);
	color: white;
	text-align: center;
	padding: 20px 0;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg">
		<div class="container">
			<a class="navbar-brand" href="#"> <img
				src="./static/assets/Logo.png" alt="Maple Logo" class="logo-img">
				Maple
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link text-white" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="#features">Features</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="#contact">Contact Us</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="../../HostelManagementSystem/templates/VisitUsForm.jsp">Visit</a></li>
					<li class="nav-item"><a class="nav-link text-white"
						href="login">Login</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- <div class="hero-section"> -->
	<div class="hero">
		<div class="hero-content">
			<h1>Maple Hostel Services</h1>
			<ul>
				<li>Discover world-class facilities and comfortable
					accommodation.</li>
				<li>Fully furnished rooms with modern amenities.</li>
				<li>24/7 security ensuring a safe stay.</li>
				<li>High-speed WiFi and study-friendly spaces.</li>
			</ul>
			<a href="./templates/VisitUsForm.jsp" class="btn btn-primary">Take
				a tour</a>
		</div>
	</div>
	<!-- </div> -->


	<section class="features" id="features">
		<h2>Key Features</h2>
		<div class="container">
			<div class="row text-center">
				<div class="col-md-4">
					<div class="feature-card">
						<img src="./static/assets/fees.jpg" alt="Feature 1">
						<h4>Online Fee Payment</h4>
						<p>Pay hostel fees securely online.</p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="feature-card">
						<img src="./static/assets/room.jpg" alt="Feature 2">
						<h4>Automated Room Allocation</h4>
						<p>Get a room assigned instantly based on availability.</p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="feature-card">
						<img src="./static/assets/247.jpg" alt="Feature 3">
						<h4>24/7 Security</h4>
						<p>Ensuring your safety with round-the-clock security.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="gallery" id="gallery">
		<h2>Our Facilities</h2>
		<img src="./static/assets/Hostel-room.jpg" alt="Hostel Room"> <img
			src="./static/assets/Dining-area.jpg" alt="Cafeteria"> <img
			src="./static/assets/Study-area.jpg" alt="Study Area">
	</section>

	<section class="contact" id="contact">
		<div class="container text-center">
			<h2>Contact Us</h2>
			<p>Email: support@hostelms.com | Phone: +123 456 7890</p>
		</div>
	</section>

	<footer class="footer">
		<p>&copy; 2025 Hostel Management System | Designed by YourName</p>
	</footer>
</body>
</html>
