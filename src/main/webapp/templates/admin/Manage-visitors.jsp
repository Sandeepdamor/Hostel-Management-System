<!DOCTYPE html>
<%@page import="com.dollop.app.bean.Visitor"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Visitors</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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

.btn-delete {
    background: linear-gradient(45deg, #ff416c, #ff4b2b);
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    transition: all 0.3s ease-in-out;
}

.btn-delete:hover {
    background: linear-gradient(45deg, #ff4b2b, #ff416c);
}
</style>

<script>
function confirmDelete(form) {
    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        cancelButtonColor: "#3085d6",
        confirmButtonText: "Yes, delete it!"
    }).then((result) => {
        if (result.isConfirmed) {
            form.submit(); // Submit the form if user confirms
        }
    });

    return false; // Prevent immediate form submission
}
</script>

</head>
<body>
    <%@ include file="./Admin-sidebar.jsp"%>
    <div class="content">
        <%@ include file="./Admin-navbar.jsp"%>
        <div class="container">
            <h2 class="mb-4">Manage Visitors</h2>
            <div class="d-flex justify-content-between align-items-center mb-3">
               <form action="admin" method="post">
           			<div class="input-group"> 
    					<input type="text" class="form-control search-bar" placeholder="Search Feedback..." name="query">
    					<button class="btn btn-warning" style="width: 100px; height: 38px;" type="submit" name="action" value="searchvisitor">Search</button>
					</div>
				</form>
            </div>
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>Visitor ID</th>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Purpose</th>
                        <th>Entry Time</th>
                        <th>Exit Time</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<Visitor> visitors = (List<Visitor>) request.getAttribute("visitor");
                    if (visitors == null || visitors.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="7">No visitors Found</td>
                    </tr>
                    <%
                    } else {
                    for (Visitor visitor : visitors) {
                    %>
                    <tr>
                        <td><%=visitor.getId()%></td>
                        <td><%=visitor.getName()%></td>
                        <td><%=visitor.getNumber()%></td>
                        <td><%=visitor.getPurpose()%></td>
                        <td><%=visitor.getEntryTime()%></td>
                        <td><%=visitor.getExitTime()%></td>
                        <td>
                            <form action="admin" method="post" onsubmit="return confirmDelete(this);" style="display: inline;">
                                <input type="hidden" name="id" value="<%=visitor.getId()%>">
                                <input type="hidden" name="action" value="deleteVisitor">

                                <button type="submit" class="btn btn-delete">
                                    <i class="fas fa-trash"></i> Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                    }
                    }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
