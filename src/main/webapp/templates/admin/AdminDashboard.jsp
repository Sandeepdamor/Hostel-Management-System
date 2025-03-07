<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<!-- FullCalendar CSS -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css'
	rel='stylesheet' />
<!-- Bootstrap JS Bundle -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Font Awesome -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<!-- FullCalendar JS -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js'></script>
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
	width:  100%;
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
	font-size: 1.8rem;
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
	<%@ include file="./Admin-sidebar.jsp"%>
	
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>
		<div class="container mt-4">
			<div class="row g-4">
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-building card-icon"></i>
						<h5 class="card-title">Total Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("totalRoom")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-users card-icon"></i>
						<h5 class="card-title">Total Users</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("totalStudent")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-dollar-sign card-icon"></i>
						<h5 class="card-title">Total Revenue</h5>
						<p class="card-text">$<%=(Double) request.getAttribute("totalRevenue")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-bed card-icon"></i>
						<h5 class="card-title">Occupied Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("occupiedRoom")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-door-open card-icon"></i>
						<h5 class="card-title">Vacant Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("vacantRoom")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-exclamation-triangle card-icon text-warning"></i>
						<h5 class="card-title">Pending Payments</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("pendingPayment")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card ">
						<i class="fas fa-user card-icon"></i>
						<h5 class="card-title">Single Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("singleRoom")%></p>
					</div>
				</div>
				<div class="col-md-4 ">
					<div class="card">
						<i class="fas fa-user-friends card-icon"></i>
						<h5 class="card-title">Double Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("doubleRoom")%></p>
					</div>
				</div>
				<div class="col-md-4 ">
					<div class="card">
						<i class="fas fa-users card-icon"></i>
						<h5 class="card-title">Triple Rooms</h5>
						<p class="card-text"><%=(Integer) request.getAttribute("tripleRoom")%></p>
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
	<footer class="footer"> © 2025 Admin Dashboard | Designed by
		YourName </footer>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {
				initialView : 'dayGridMonth',
				headerToolbar : {
					left : 'prev,next today',
					center : 'title',
					right : 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				events : [ {
					title : 'Payment Due',
					date : '2025-02-20',
					color : '#e74c3c'
				}, {
					title : 'Room Check',
					date : '2025-02-22',
					color : '#3498db'
				}, {
					title : 'Maintenance',
					date : '2025-02-25',
					color : '#f1c40f'
				} ],
				eventClick : function(info) {
					alert('Event: ' + info.event.title + '\nDate: '
							+ info.event.start.toLocaleDateString());
				}
			});
			calendar.render();
		});
	</script>
</body>
</html>
