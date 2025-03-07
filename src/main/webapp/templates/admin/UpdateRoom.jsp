<%@page import="com.dollop.app.bean.Room"%>
<%@page import="com.dollop.app.bean.Student"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Student</title>
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
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.content {
    margin-left: 250px;
    padding: 20px;
    flex-grow: 1;
    transition: all 0.3s;
}

.footer {
    background: #2c3e50;
    color: white;
    text-align: center;
    padding: 10px;
    position: relative;
    width: 100%;
    bottom: 0;
}

.card {
    transition: transform 0.3s;
}

.card:hover {
    transform: scale(1.05);
}

.container {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 600px;
    margin: auto;
}

.input-group {
    margin-bottom: 15px;
}

.input-group label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

.input-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
}
/* Style for the select dropdown */
.input-group select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 14px;
    background-color: #f9f9f9;
    color: #333;
    transition: background-color 0.3s ease, border-color 0.3s ease;
}

/* Change the background color and border on focus */
.input-group select:focus {
    background-color: #fff;
    border-color: #6a11cb;
    outline: none;
}

.input-group select option {
    padding: 10px;
    background-color: #fff;
    color: #333;
}

/* Error message styling */
.error {
	color: red;
	font-size: 10px;
	font-weight: bold;
	margin-top: 5px;
	display: block;
}
.submit-btn {
    background: #6a11cb;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;
}

.submit-btn:hover {
    background: #2575fc;
}

.cancel-btn {
    background: #f44336;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;
    margin-left: 10px;
}

.cancel-btn:hover {
    background: #d32f2f;
}

@media ( max-width : 768px) {
    .content {
        margin-left: 0;
    }
    .footer {
        position: relative;
    }
}
</style>
</head>
<body>

    <!-- Include the sidebar here -->
    <%@ include file="./Admin-sidebar.jsp"%>

    <!-- Content Section -->
    <div class="content">
        <!-- Include the Navbar here -->
        <%@ include file="./Admin-navbar.jsp"%>

        <!-- Add New Student Form -->
        <div class="container mt-4">
            <h2 class="text-center">Update Room</h2>
            <form id="updateRoomForm" action="../../../HostelManagementSystem/admin" method="post">

                <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                Room room = (Room) request.getAttribute("room");
                %>
                <!-- Full Name Input -->
                <div class="input-group">
                    <label for="roomNumber">Room Number</label>
                    
                    <input type="number" id="roomNumber" name="roomNumber"
                        value="<%=room!=null?room.getRoomNumber():""%>">
                    <div>
                        <% if (errors != null && errors.contains("Please Enter Room Number")) { %>
                        	<span class="error">Please Enter Room Number</span>
                        <% } %>
                    </div>
                    <div>
                        <% if (errors != null && errors.contains("Only Room Numbers are allowed in Room Number.")) { %>
                        	<span class="error">Only Room Numbers are allowed in Room Number.</span>
                        <% } %>
                    </div>
                    <div>
                        <% if (errors != null && errors.contains("Room Number Already Exists")) { %>
                        	<span class="error">Room Number Already Exists</span>
                        <% } %>
                    </div>
                </div>
				
				<!-- Capacity Field -->
				<div class="input-group">
    				<label for="capacity">Capacity</label>
    				<select id="capacity" name="capacity">
        				<option value="">
            				<%= (room != null) ? room.getCapacity()==1 ? "Single" : room.getCapacity()==2 ? "Double" : room.getCapacity()==3 ? "Triple" : "Please Select" : "Please Select" %>
        				</option>
        				<option value="1" <%= (room != null && room.getCapacity() == 1) ? "selected" : "" %>>Single</option>
        				<option value="2" <%= (room != null && room.getCapacity() == 2) ? "selected" : "" %>>Double</option>
        				<option value="3" <%= (room != null && room.getCapacity() == 3) ? "selected" : "" %>>Triple</option>
    				</select>
    				<div>
        				<% if (errors != null && errors.contains("Please Enter Capacity")) { %>
            				<span class="error">Please Enter Capacity</span>
        				<% } %>
    				</div>
    				<div>
        				<% if (errors != null && errors.contains("Room is Occupied You Cann't Decrease the Room Capacity, You Can Increase OR Don't Change The Capacity")) { %>
            				<span class="error">Room is Occupied You Cann't Decrease the Room Capacity, You Can Increase OR Don't Change The Capacity</span>
        				<% } %>
    				</div>
				</div>

                <!-- Phone Number Input -->
                <div class="input-group">
                    <label for="phone">Room Charges</label>
                    
                    <input type="number" id="payment" name="payment"
                        value="<%=room!=null?room.getPayment():""%>">
                    <div>
        				<% if (errors != null && errors.contains("Please Enter Payment")) { %>
            				<span class="error">Please Enter Payment</span>
        				<% } %>
    				</div>
    				<div>
        				<% if (errors != null && errors.contains("Only numbers are allowed in Payment.")) { %>
            				<span class="error">Only numbers are allowed in Payment.</span>
        				<% } %>
    				</div>
                </div>
				<input type="hidden" name="id" value="<%=(String)request.getAttribute("id")!=null?(String)request.getAttribute("id"):""%>">
                <!-- Submit Button -->
                <button type="submit" name="action" value="updateroominfo" class="submit-btn">Update Room</button>
                <a href="admin?action=managerooms"><button type="button" class="cancel-btn">Cancel</button></a>
            </form>
        </div>
    </div>

    <!-- Footer Section -->
    <footer class="footer"> &copy; 2025 Admin Dashboard | Designed
        by YourName </footer>

    <!-- JavaScript for showing popup based on response -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
		document.addEventListener("DOMContentLoaded", function() {
			const urlParams = new URLSearchParams(window.location.search);
			if (urlParams.has("success")) {
				Swal.fire({
					title : "Success!",
					text : "Student Updated Successfully!",
					icon : "success",
					confirmButtonText : "OK"
				}).then(() => {
                    window.location.href = "../../../HostelManagementSystem/admin?action=managerooms"; // Change to your actual user list page URL
                });
			}
			/* else if (!urlParams.has("success")){
				Swal.fire({
					title : "Error!",
					text : "Student Not Updated Successfully!",
					icon : "error",
					confirmButtonText : "OK"
				}).then(() => {
                    window.location.href = "../../../HostelManagementSystem/admin?action=managerooms"; // Change to your actual user list page URL
                });
			} */
		});
	</script>
</body>
</html>