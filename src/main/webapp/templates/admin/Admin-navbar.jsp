<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>

<style>
.navbar {
	background: #2c3e50;
	color: white;
	padding: 13px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-radius: 25px;
}

.navbar .profile-section {
	display: flex;
	align-items: center;
	gap: 10px;
}

.navbar .profile-section a {
	color: white;
	text-decoration: none;
	display: flex;
	align-items: center;
	transition: color 0.3s ease-in-out;
}

.navbar .profile-section a:hover {
	color: #f1c40f;
}

.navbar .profile-section i {
	font-size: 20px;
	margin-right: 5px;
}
</style>

<nav class="navbar">
	<h4>Admin Dashboard</h4>
	<div class="profile-section">
		<a href="admin?action=adminProfile">
			<i class="fas fa-user-circle"></i> <span>Admin</span>
		</a>
		<button class="btn btn-light btn-sm ms-3" data-bs-toggle="modal" data-bs-target="#logoutModal">Logout</button>
	</div>
</nav>

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
				<a href="admin?action=logout" class="btn btn-danger">Logout</a>
			</div>
		</div>
	</div>
</div>
