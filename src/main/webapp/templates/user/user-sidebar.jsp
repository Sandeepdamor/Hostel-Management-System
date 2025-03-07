<div class="sidebar">
	<div class="sidebar-header">
		<img src="static/assets/Logo.png" alt="User Logo" class="logo-img">
	</div>
	<ul class="sidebar-menu">
		<li><a href="./student?action=dashboard"><i
				class="fas fa-home"></i> <span>Dashboard</span></a></li>
		<li><a href="./student?action=profile"><i class="fas fa-user"></i>
				<span>Profile</span></a></li>
		<li><a href="./student?action=room"><i class="fas fa-bed"></i>
				<span>My Room</span></a></li>
		<li><a href="./student?action=paymentHistory"><i
				class="fas fa-wallet"></i> <span>Payment History</span></a></li>
		<li><a href="./student?action=pendingFees"><i
				class="fas fa-wallet"></i> <span>Pending Payments</span></a></li>
		<li><a href="./student?action=showAllGrievances"><i class="fas fa-comments"></i>
				<span>Grievances</span></a></li>
		<!-- <li><a href="./UserSettings.jsp"><i class="fas fa-cogs"></i>
				<span>Settings</span></a></li> -->
		<li><a href="../../../HostelManagementSystem/student?action=logout" data-bs-toggle="modal" data-bs-target="#logoutModal" class="logout"><i class="fas fa-sign-out-alt"></i> <span>Logout</span></a>
</li>
	</ul>
</div>
<!-- Logout Confirmation Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="logoutModalLabel">Confirm Logout</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				Are you sure you want to logout?
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				<a href="student?action=logout" class="btn btn-danger">Logout</a>
			</div>
		</div>
	</div>
</div>


<style>
.sidebar {
	width: 260px;
	height: 100vh;
	background: #1e272e;
	position: fixed;
	top: 0;
	left: 0;
	padding-top: 30px;
	box-shadow: 2px 0 10px rgba(0, 0, 0, 0.2);
	transition: all 0.3s;
}

.sidebar-header {
	text-align: center;
	margin-bottom: 20px;
}

.logo-img {
	height: 70px;
	width: 70px;
	border-radius: 50%;
	border: 3px solid #4cd137;
}

.sidebar-menu {
	list-style: none;
	padding: 0;
}

.sidebar-menu li {
	margin: 10px 0;
}

.sidebar-menu a {
	color: white;
	text-decoration: none;
	display: flex;
	align-items: center;
	padding: 12px 20px;
	border-radius: 5px;
	transition: background 0.3s ease-in-out;
	font-size: 18px;
	gap: 15px; /* Adds proper spacing between icon and text */
}

.sidebar-menu a i {
	font-size: 18px;
	color: #4cd137;
}

.sidebar-menu a:hover {
	background: #4cd137;
	color: white;
	transform: scale(1.05);
}

.sidebar-menu a:hover i {
	color: white;
}

.logout {
	margin-top: 20px;
	background: #e74c3c;
	border-radius: 5px;
	text-align: center;
}

.logout:hover {
	background: #c0392b;
}
</style>








