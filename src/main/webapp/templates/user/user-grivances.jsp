<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.Grievance"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<meta charset="ISO-8859-1">
<title>Grivance</title>
<style>
.grievance-container {
	padding: 20px;
	margin-left: 250px;
	background: white;
	min-height: 100vh;
	font-family: 'Poppins', sans-serif;
}

.header {
	display: flex;
	align-items: unset;
	margin-bottom: 20px;
	justify-content: flex-end;
}

.add-grievance-btn {
	background: #ff8800;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
}

.add-grievance-btn:hover {
	background: #e67300;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
	gap: 10px;
	max-width: 500px;
	margin: 0 auto 30px auto;
	padding: 20px;
	background: #f8f9fa;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

input, textarea {
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	width: 100%;
}

.submit-btn {
	background: #ff8800;
	color: white;
	padding: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.submit-btn:hover {
	background: #e67300;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background: white;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: center;
}

th {
	background: #2c3e50;
	color: white;
}
</style>
</head>
<body>
	<%@ include file="./user-sidebar.jsp"%>


	<div class="grievance-container">
		<%@ include file="./user-navbar.jsp"%>
		<h2>My Grievances</h2>
		<div class="header">
			<!-- <button class="add-grievance-btn">New Grievance</button> -->
			<a href="./student?action=grievance" class="add-grievance-btn">New Grievance</a>
		</div>
		<table>
            <thead>
                <tr>
                    <th>Grievance ID</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Response</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Grievance> grievances = (List<Grievance>) request.getAttribute("allGrievances");
                    if (grievances != null && !grievances.isEmpty()) {
                        for (Grievance grievance : grievances) {
                %>
                <tr>
                    <td><%= grievance.getId() %></td>
                    <td><%= grievance.getDescription() %></td>
                    <td><%= grievance.getStatus() %></td>
                    <td><%= (grievance.getResponse() != null) ? grievance.getResponse() : "--" %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No grievances found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
	</div>

</body>
</html>