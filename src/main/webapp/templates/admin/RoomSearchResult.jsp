<!DOCTYPE html>
<%@page import="com.dollop.app.bean.Room"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Rooms</title>
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
}

.content {
	margin-left: 250px;
	padding: 20px;
}

.container {
	margin-top: 20px;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.btn-add {
	background-color: #1abc9c;
	color: white;
	border-radius: 5px;
}

.btn-add:hover {
	background-color: #16a085;
}

.search-bar {
	width: 100%;
	max-width: 300px;
	display: inline-block;
	margin-bottom: 15px;
}

.table th {
	background-color: #2c3e50;
	color: white;
}

.btn-edit {
	background-color: #f39c12;
	color: white;
	border-radius: 5px;
}

.btn-delete {
	background-color: #e74c3c;
	color: white;
	border-radius: 5px;
}

.btn-edit:hover {
	background-color: #e67e22;
}

.btn-delete:hover {
	background-color: #c0392b;
}
</style>
</head>
<body>
	<%@ include file="./Admin-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>
		<div class="container">
			<div class="d-flex justify-content-between align-items-center mb-3">
				 <div class="d-flex justify-content-between align-items-center mb-3">
                <a style="text-decoration: none; color: red;" href="admin?action=managerooms"><button class="btn btn-add">Close Search Result</button></a>
            </div>
			</div>
			<table class="table table-bordered text-center">
				<thead>
					<tr>
						<th>Room ID</th>
						<th>Room Number</th>
						<th>Capacity</th>
						<th>Room Charges</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				<%List<Room> rooms = (List<Room>)request.getAttribute("searchdata"); 
                if(rooms.isEmpty()){%>
                	<tr>
                        <td colspan="5">Search Result Not Found</td>
                    </tr>
                <%}
                for(Room room:rooms){
                %>
                    <tr>
                        <td><%=room.getId()%></td>
                        <td><%=room.getRoomNumber()%></td>
                        <td><%=room.getCapacity()%></td>
                        <td><%=room.getPayment()%></td>
                        <td><%=room.getStatus()%></td>
                        <td>
                            <button class="btn btn-edit">Edit</button>
                        </td>
                    </tr>
                    <% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
