<div class="sidebar">
	<div class="sidebar-header">
		<img src="static/assets/Logo.png" alt="User Logo" class="logo-img">
	</div>
	<ul class="sidebar-menu">
		<li><a
			href="../../../HostelManagementSystem/admin?action=dashboard"><i
				class="fas fa-tachometer-alt"></i> <span>Dashboard</span></a></li>
		<li><a
			href="../../../HostelManagementSystem/admin?action=manageuser"><i
				class="fas fa-users"></i> <span>Manage Users</span></a></li>
		<li><a
			href="../../../HostelManagementSystem/admin?action=managerooms"><i
				class="fas fa-bed"></i> <span>Rooms</span></a></li>
		<li><a
			href="../../../HostelManagementSystem/admin?action=managepayments"><i
				class="fas fa-dollar-sign"></i> <span>Payments</span></a></li>
		<li><a
			href="../../../HostelManagementSystem/admin?action=managfeedback"><i
				class="fas fa-comments"></i> <span>Grievance</span></a></li>
		<li><a
			href="../../../HostelManagementSystem/admin?action=managvisitors"><i
				class="fas fa-id-badge"></i> <span>Visitors</span></a></li>
		<!-- <a href="./Settings.jsp"><i class="fas fa-cogs"></i> <span>Settings</span></a>-->
		<li><a
			href="../../../HostelManagementSystem/admin?action=studentshistory"><i
				class="fas fa-users"></i> <span>Students History</span></a></li>
		<li><a href="../../../HostelManagementSystem/admin?action=logout"
			data-bs-toggle="modal" data-bs-target="#logoutModal" class="logout"><i
				class="fas fa-sign-out-alt"></i> <span>Logout</span></a></li>
	</ul>
</div>
<div class="modal fade" id="logoutModal" tabindex="-1"
	aria-labelledby="logoutModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="logoutModalLabel">Confirm Logout</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">Are you sure you want to logout?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Cancel</button>
				<a href="admin?action=logout" class="btn btn-danger">Logout</a>
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













