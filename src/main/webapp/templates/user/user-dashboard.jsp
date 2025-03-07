<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css' rel='stylesheet' />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js'></script>
<meta charset="ISO-8859-1">
<title>User Dashboard</title>
<style>
body {
	font-family: 'Poppins', sans-serif;
	background: linear-gradient(135deg, #ecf0f1, #dcdde1);
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
	position: unset;
	bottom: 0;
	width: 100%;
}

.card {
	border: none;
	border-radius: 15px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s, box-shadow 0.3s;
	background: white;
	text-align: center;
	padding: 20px;
}

.card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.card-icon {
	font-size: 3rem;
	margin-bottom: 10px;
	color: #2980b9;
}

.card-title {
	color: #2c3e50;
	font-weight: bold;
}

.card-text {
	font-size: 1.5rem;
	color: #7f8c8d;
}

.calendar-card {
	min-height: 400px;
	overflow: hidden;
}

#calendar {
	margin-top: 15px;
}
</style>
</head>
<body>
	<%@ include file="./user-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./user-navbar.jsp"%>
		<div class="container mt-4">
			<div class="row g-4">
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-user card-icon"></i>
						<h5 class="card-title">My Profile</h5>
						<p class="card-text"><%=request.getAttribute("name")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-bed card-icon"></i>
						<h5 class="card-title">My Room</h5>
						<p class="card-text">Room no. <%=(Integer)request.getAttribute("assignedRoom")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-wallet card-icon"></i>
						<h5 class="card-title">Payments</h5>
						<p class="card-text">Pending: $<%=(Double)request.getAttribute("totalPayment")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-comments card-icon"></i>
						<h5 class="card-title">Grievances</h5>
						<p class="card-text">Raised:<%=(Integer) request.getAttribute("totalGrviance")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-comments card-icon"></i>
						<h5 class="card-title">Grievances</h5>
						<p class="card-text">Resolved: <%=(Integer) request.getAttribute("resolvedGrivances")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-bell card-icon"></i>
						<h5 class="card-title">Responses</h5>
						<p class="card-text">Total: <%=(Integer) request.getAttribute("resolvedGrivances")%></p>
					</div>
				</div>
				<div class="col-md-12">
					<div class="card calendar-card">
						<h5 class="card-title">Calendar</h5>
						<div id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer"> © 2025 User Dashboard | Designed by YourName </footer>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView: 'dayGridMonth',
				headerToolbar: {
					left: 'prev,next today',
					center: 'title',
					right: 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				events: [{
					title: 'Rent Due',
					date: '2025-02-20',
					color: '#e74c3c'
				}],
				eventClick: function(info) {
					alert('Event: ' + info.event.title + '\nDate: ' + info.event.start.toLocaleDateString());
				}
			});
			calendar.render();
		});
	</script>
</body>
</html>
