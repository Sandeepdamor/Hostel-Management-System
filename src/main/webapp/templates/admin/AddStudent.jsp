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
            <h2 class="text-center">Add New Student</h2>
            <form id="addStudentForm" action="../../../HostelManagementSystem/admin" method="post">

                <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                %>
                <div>
                        <% if (errors != null && errors.contains("Already Registered Student, Please Check Student History and Click ADD AGAIN Button")) { %>
                        	<h3><span class="error">Already Registered Student, Please Check Student History and Click "ADD AGAIN" Button To Add Student</span></h3>
                        <% } %>
                    </div>
                <!-- Full Name Input -->
                <div class="input-group">
                    <label for="name">Full Name</label>
                    
                    <input type="text" id="name" name="name"
                        value="<%=request.getParameter("name") != null ? request.getParameter("name") : ""%>"
                        placeholder="Enter student's full name">
                    <div>
                        <%
                        if (errors != null && errors.contains("Name is Required..")) {
                        %>
                        <span class="error">Name is Required..</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Email Input -->
                <div class="input-group">
                    <label for="email">Email</label>
                    
                    <input type="email" id="email" name="email"
                        value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>"
                        placeholder="Enter student's email">
                    <div>
                        <%
                        if (errors != null && errors.contains("Email Format is Wrong, Please Enter Right Email Format (xyz@gmail.com)")) {
                        %>
                        <span class="error">Email Format is Wrong, Please Enter
                            Right Email Format (xyz@gmail.com)</span>
                        <%
                        }
                        %>
                    </div>
                    <div>
                        <%
                        if (errors != null && errors.contains("Email is Already Exists")) {
                        %>
                        <span class="error">Email is Already Exists</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Password Input -->
                <div class="input-group">
                    <label for="password">Password</label>
                    
                    <input type="password" id="password" name="password"
                        value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>"
                        placeholder="Enter Password">
                     <div>
                        <%
                        if (errors != null && errors.contains("Password Format is Wrong, Please Enter Strong Password")) {
                        %>
                        <span class="error">Password Format is Wrong, Please Enter
                            Strong Password</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Phone Number Input -->
                <div class="input-group">
                    <label for="phone">Phone Number</label>
                    
                    <input type="tel" id="phone" name="contact"
                        value="<%=request.getParameter("contact") != null ? request.getParameter("contact") : ""%>"
                        placeholder="Enter phone number">
                    <div>
                        <%
                        if (errors != null && errors.contains("Mobile Number Format is Wrong, Please Enter Right Mobile Number")) {
                        %>
                        <span class="error">Mobile Number Format is Wrong, Please
                            Enter Right Mobile Number</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Room Number Input -->
                <div class="input-group">
                    <label for="roomNumber">Room Number</label>
                    
                    <input type="number" id="roomNumber" name="roomNumber"
                        value="<%=request.getParameter("roomNumber") != null ? request.getParameter("roomNumber") : ""%>"
                        placeholder="Enter assigned room Number">
                    <div>
                        <%
                        if (errors != null && errors.contains("Only Room Numbers are allowed in Room Number.")) {
                        %>
                        <span class="error">Only Room Numbers are allowed in Room
                            Number.</span>
                        <%
                        }
                        %>
                    </div>
                    <div>
                        <%
                        if (errors != null && errors.contains("Room Not Found, Please Check Entered Room Number")) {
                        %>
                        <span class="error">Room Not Found, Please Check Entered
                            Room Number</span>
                        <%
                        }
                        %>
                    </div>
                    <div>
                        <%
                        if (errors != null && errors.contains("Room is already full. Please select a different room.")) {
                        %>
                        <span class="error">Room is already full. Please select a different room.</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Fee Select Dropdown -->
                <div class="input-group">
                    <label for="payment_type">Fee</label>
                    
                    <%
                    // Retrieve the selected capacity from the request
                    String selectedCapacity = request.getParameter("payment_type");
                    %>

                    <select id="payment_type" name="payment_type">
                        <option value=""
                            <%=(selectedCapacity == null || selectedCapacity.isEmpty()) ? "selected" : ""%>>Please
                            Select</option>
                        <option value="online"
                            <%="online".equals(selectedCapacity) ? "selected" : ""%>>Online</option>
                        <option value="cash"
                            <%="cash".equals(selectedCapacity) ? "selected" : ""%>>Cash</option>
                        <option value="unpaid"
                            <%="unpaid".equals(selectedCapacity) ? "selected" : ""%>>Unpaid</option>
                    </select>
                    <div>
                        <%
                        if (errors != null && errors.contains("Fee Mode is Required, Please Select Fee Mode")) {
                        %>
                        <span class="error">Fee Mode is Required, Please Select Fee
                            Mode</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Submit Button -->
                <button type="submit" name="action" value="addstudent" class="submit-btn">Add Student</button>
                <button type="submit" class="cancel-btn" name="action" value="manageuser">Cancel</button>
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
        if (urlParams.get("success") !== null) { // Ensure the parameter is explicitly checked
            Swal.fire({
                title: "Success!",
                text: "Student Added Successfully!",
                icon: "success",
                confirmButtonText: "OK"
            }).then((result) => { // Removed extra dot before .then()
                if (result.isConfirmed) {
                    console.log("User clicked OK. Redirecting...");
                    window.location.href = '../../admin?action=manageuser';
                }
            });
        }
    });
</script>

</body>
</html>