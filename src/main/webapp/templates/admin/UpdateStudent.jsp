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
            <h2 class="text-center">Update Student</h2>
            <form id="updateStudentForm" action="../../../HostelManagementSystem/admin" method="post">

                <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                Student student = (Student) request.getAttribute("student");
                %>
                <!-- Full Name Input -->
                <div class="input-group">
                    <label for="name">Full Name</label>
                    
                    <input type="text" id="name" name="name"
                        value="<%=student!=null?student.getName():""%>">
                    <div>
                        <%
                        if (errors != null && errors.contains("Please Enter Name")) {
                        %>
                        <span class="error">Please Enter Name</span>
                        <%
                        }
                        %>
                    </div>
                </div>

                <!-- Email Input -->
                <div class="input-group">
                    <label for="email">Email</label>
                    
                    <input type="email" id="email" name="email"
                        value="<%=student!=null?student.getEmail():""%>">
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

                <!-- Phone Number Input -->
                <div class="input-group">
                    <label for="phone">Phone Number</label>
                    
                    <input type="tel" id="phone" name="contact"
                        value="<%=student!=null?student.getContact():""%>">
                    <div>
                        <%
                        if (errors != null && errors.contains("Mobile Number Format is Wrong, Please Enter Right Mobile Number")) {
                        %>
                        <span class="error">Mobile Number Format is Wrong, Please Enter Right Mobile Number</span>
                        <%
                        }
                        %>
                    </div>
                </div>
				<input type="hidden" name="id" value="<%=(String)request.getAttribute("id")!=null?(String)request.getAttribute("id"):""%>">
                <!-- Submit Button -->
                <button type="submit" name="action" value="updatestudentinfo" class="submit-btn">Update Student</button>
                <a href="admin?action=manageuser"><button type="button" class="cancel-btn">Cancel</button></a>
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
                    window.location.href = "../../../HostelManagementSystem/admin?action=manageuser"; // Change to your actual user list page URL
                });
			}
		});
	</script>
</body>
</html>