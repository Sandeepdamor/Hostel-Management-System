<%@page import="com.dollop.app.bean.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.*"%>
<!-- Import User Model -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Room Details</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<style>
/* Global Styling */
body {
	font-family: 'Poppins', sans-serif;
	background: #ffffff;
	color: #333;
	margin: 0;
	padding: 0;
}

/* Content Area */
.content {
	margin-left: 250px;
	padding: 30px;
	transition: margin-left 0.3s ease-in-out;
}

/* Footer */
.footer {
	background:#1e272e;
	color: white;
	text-align: center;
	padding: 12px;
	position: fixed;
	bottom: 0;
	width: 100%;
	font-size: 14px;
	box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.1);
}

/* Profile Image Styling */
.profile-img {
	width: 221px;
	height: 282px;
	border-radius: 17%;
	object-fit: cover;
	border: 5px solid #007bff;
	transition: transform 0.3s ease-in-out;
}

.profile-img:hover {
	transform: scale(1.05);
}

/* Profile Card */
.card {
	background: #ffffff;
	border-radius: 12px;
	border: 1px solid #e0e0e0;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
	transition: all 0.3s ease-in-out;
}

.card:hover {
	transform: translateY(-3px);
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}

/* Button Styling */
.btn-primary {
	background: #007bff;
	border: none;
	transition: all 0.3s ease-in-out;
	padding: 10px 20px;
	border-radius: 6px;
	font-size: 16px;
	font-weight: 500;
}

.btn-primary:hover {
	background: #0056b3;
	transform: scale(1.04);
	box-shadow: 0 4px 10px rgba(0, 91, 187, 0.2);
}

/* Buttons */
.btn-primary {
	background: #007bff;
	border: none;
	transition: all 0.3s ease-in-out;
	padding: 10px 20px;
	border-radius: 6px;
	font-size: 16px;
	font-weight: 500;
}

.btn-primary:hover {
	background: #0056b3;
	transform: scale(1.04);
	box-shadow: 0 4px 10px rgba(0, 91, 187, 0.2);
}

/* Smooth fade-in effect */
@
keyframes fadeIn {from { opacity:0;
	transform: translateY(10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.card-body p {
	animation: fadeIn 0.5s ease-in-out;
}
</style>
</head>
<%
	// Retrieve user object from session
	Student user = (Student) session.getAttribute("user");

	// Check if user is null (if session expired or not logged in)
	if (user == null) {
		response.sendRedirect("login.jsp"); // Redirect to login page
		return;
	}
	%>
<%
Room room = (Room) request.getAttribute("assigendRoom");
if (room == null) {
%>
<p style="color: red; text-align: center; font-weight: bold;">No
	room assigned to you</p>
<%
} else {
%>


<%@ include file="./user-sidebar.jsp"%>
<body>
	<div class="content">
		<%@ include file="./user-navbar.jsp"%>
		
		<div class="container mt-5">
			<div class="row justify-content-center">
				<div class="col-md-8">
					<div class="card p-4">
						<div class="row align-items-center">

							<!-- User Image Section -->
							<div class="col-md-4 text-center">
								<img src="./static/assets/user.jpg" alt="User Profile"
									class="profile-img">
							</div>

							<!-- User Details Section -->
							<div class="col-md-8">
								<div class="card-body">
									<h3 class="text-center mb-3">Room</h3>
									<p>
										<strong>Room Id:</strong>
										<%=room.getId()%></p>
									<p>
										<strong>Room Number:</strong>
										<%=room.getRoomNumber()%></p>
									<p>
										<strong>Capacity:</strong>
										<%=room.getCapacity()%></p>
									<p>
										<strong>Status:</strong>
										<%=room.getStatus()%></p>
								</div>
								<div class="text-center">
    								<button type="button" class="btn btn-primary"
    data-room-id="<%= room.getId() %>"
    data-user-id="<%= user.getId() %>"
    onclick="confirmRoomChange(this)">Send Room Change Request</button>

    									
    							</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>	
	<%
	}
	%>

	<div class="footer">
		<p>&copy; 2025 Hostel Management System</p>
	</div>
	  <!-- JavaScript for showing popup based on response -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
    
    function confirmRoomChange(button) {
    	let currentRoomId = button.getAttribute("data-room-id");
        let userId = button.getAttribute("data-user-id");

        console.log("Current Room ID:", currentRoomId);
        console.log("User ID:", userId);

        Swal.fire({
            title: "Enter New Room Number",
            input: "text",
            inputPlaceholder: "Enter room number...",
            showCancelButton: true,
            confirmButtonText: "Send Request",
            cancelButtonText: "Cancel",
            preConfirm: (newRoomNumber) => {
                if (!newRoomNumber || isNaN(newRoomNumber)) {
                    Swal.showValidationMessage("Please enter a valid room number.");
                }
                return newRoomNumber;
            }
        }).then((result) => {
            if (result.isConfirmed) {
                let newRoomNumber = result.value;
                console.log("Requesting room change for user:", userId, "Current Room:", currentRoomId, "New Room:", newRoomNumber);
                // Redirecting to servlet with parameters
               let url = `./student?action=roomchange&userId=`+userId+`&currentRoomId=`+currentRoomId+`&newRoomNumber=`+newRoomNumber+``;
                console.log("Redirecting to:", url);
                window.location.href = url;

            }
        });
    }


    
    document.addEventListener("DOMContentLoaded", function() {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get("success") !== null) { // Check if success parameter exists
            Swal.fire({
                title: "Success!",
                text: "Room has been Changed Successfully!",
                icon: "success",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                    console.log("User clicked OK. Redirecting...");
                    window.location.href = '../../student?action=room';
                }
            });
        } else if (urlParams.get("pendingFee") !== null) { // Check if pendingFee parameter exists
            Swal.fire({
                title: "Payment Required!",
                text: "You cannot change your room without paying the pending fee.",
                icon: "warning",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                    console.log("User clicked Pay Now. Redirecting to payment page...");
                    window.location.href = '../../student?action=pendingFees'; // Redirect to Pending Fee page
                }
            });
        } else if (urlParams.get("error") !== null) { // Check if error parameter exists
            Swal.fire({
                title: "Room is Alredy Full..!",
                text: "Room change failed. Please try again.",
                icon: "error",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                	 console.log("User clicked OK. Redirecting...");
                     window.location.href = '../../student?action=room';
                }
            });
        }
        else if (urlParams.get("notsuccess") !== null) { // Check if error parameter exists
            Swal.fire({
                title: "ERROR!",
                text: "Room has not been Changed..!",
                icon: "error",
                confirmButtonText: "OK"
            }).then((result) => {
                if (result.isConfirmed) {
                	 console.log("User clicked OK. Redirecting...");
                     window.location.href = '../../student?action=room';
                }
            });
        }


    });
</script>
</body>
</html>
