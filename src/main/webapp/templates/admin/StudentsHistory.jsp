<!DOCTYPE html>
<%@page import="com.dollop.app.bean.Student"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            background: #2c3e50;
            padding-top: 20px;
        }
        .sidebar a {
            display: block;
            padding: 15px;
            color: white;
            text-decoration: none;
            transition: background 0.3s;
        }
        .sidebar a:hover {
            background: #34495e;
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
    <%@ include file="./Admin-sidebar.jsp" %>
    <div class="content">
        <%@ include file="./Admin-navbar.jsp" %>
        <div class="container">
            <div class="d-flex justify-content-between align-items-center mb-3">
           <!--  <form action="admin" method="post">
           		<div class="input-group"> 
    				<input type="text" class="form-control search-bar" placeholder="Search students..." name="query">
    				<button class="btn btn-warning" style="width: 100px; height: 38px;" type="submit" name="action" value="searchstudent">Search</button>
				</div>
			</form> -->
            </div>
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Contact</th>
                        <th>Joining Date</th>
                       	<th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <%List<Student> students = (List<Student>)request.getAttribute("students"); 
                if(students.isEmpty()){%>
                	<tr>
                        <td colspan="7">No Students Found</td>
                    </tr>
                <%}
                for(Student student:students){
                %>
                    <tr>
                        <td><%=student.getId()%></td>
                        <td><%=student.getName()%></td>
                        <td><%=student.getEmail()%></td>
                        <td><%=student.getContact()%></td>
                        <td><%=student.getJoining_date()%></td>
                       <td>
                            <a href="admin?action=addStudentAgain&id=<%=student.getId()%>"><button class="btn btn-edit">Add Again</button></a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
