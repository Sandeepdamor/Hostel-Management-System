<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hostel Facilities & Reviews</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f4f4f4;
}

.hero {
	text-align: center;
	padding: 80px 20px;
	background: url('assets/hostel-bg.jpg') no-repeat center center/cover;
	color: white;
	position: relative;
}

.hero::before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 219px;
	background: rgba(0, 0, 0, 0.9);
}

.hero h1 {
	position: relative;
	z-index: 2;
	font-size: 48px;
	font-weight: 600;
}

.hero p {
	position: relative;
	z-index: 2;
	font-size: 24px;
}

.facilities, .reviews, .gallery {
	padding: 40px 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	margin-bottom: 30px;
}

.review-card {
	background: #fff;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	margin-bottom: 15px;
}

.btn-book {
	background-color: #ffcc00;
	color: black;
	border-radius: 5px;
	width: 100%;
}

.btn-book:hover {
	background-color: #e6b800;
}

.gallery img {
	width: 100%;
	height: auto;
	border-radius: 8px;
	transition: transform 0.3s;
}

.gallery img:hover {
	transform: scale(1.05);
}
</style>
</head>
<body>
	<div class="hero">
		<h1>Welcome to Mapple Hostel Services</h1>
		<p>Discover world-class facilities and comfortable accommodation.</p>
	</div>
	<div class="container">
		<div class="facilities">
			<h2>Our Facilities</h2>
			<ul>
				<li>Fully furnished rooms with attached bathrooms</li>
				<li>24/7 Security & CCTV Surveillance</li>
				<li>High-Speed WiFi & Study Areas</li>
				<li>Delicious & Hygienic Meals</li>
				<li>Laundry & Cleaning Services</li>
				<li>Sports & Recreation Facilities</li>
			</ul>
		</div>
           
		<div class="gallery">
			<h2>Gallery</h2>
			<div class="row">
				<div class="col-md-4">
					<img src="./static/assets/Hostel-room.jpg" alt="Hostel Room">
				</div>
				<div class="col-md-4">
					<img src="./static/assets/Dining-area.jpg" alt="Dining Area">
				</div>
				<div class="col-md-4">
					<img src="./static/assets/Study-area.jpg" alt="Study Room">
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-md-4">
					<img src="./static/assets/gym.jpg" alt="Gym">
				</div>
				<div class="col-md-4">
					<img src="./static/assets/terrace.jpg" alt="Lounge">
				</div>
				<div class="col-md-4">
					<img src="./static/assets/Outdoor.jpg" alt="Outdoor Area">
				</div>
			</div>
		</div>

		<div class="reviews">
			<h2>What Our Residents Say</h2>
			<div class="review-card">
				<p>
					<strong>Aman Verma:</strong> "The best hostel experience! Clean
					rooms and friendly staff."
				</p>
			</div>
			<div class="review-card">
				<p>
					<strong>Priya Sharma:</strong> "Great food and amazing atmosphere!
					Highly recommended."
				</p>
			</div>
			<div class="review-card">
				<p>
					<strong>Rahul Gupta:</strong> "Affordable and top-notch facilities.
					Loved my stay here!"
				</p>
			</div>
		</div>

		<div class="text-center">
		<form action="./admin2">
		  <input type="hidden" name="visitorId" value="<%= request.getAttribute("visitorId") %>">
		    <input type="submit" name = "action" value ="visitorExit" class="btn btn-book">
		</form>
		<%--  <input type="hidden" name="visitorId" value="<%= request.getAttribute("visitorId") %>">
			<a href="./admin2?action=visitorExit" class="btn btn-book">Book Your Stay Now</a> --%>
		</div>
	</div>
</body>
</html>
