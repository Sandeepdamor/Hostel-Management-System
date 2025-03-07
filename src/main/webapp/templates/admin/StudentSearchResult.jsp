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
                <a style="text-decoration: none; color: red;" href="admin?action=manageuser"><button class="btn btn-add">Close Search Result</button></a>
            </div>
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Contact</th>
                        <th>Joining Date</th>
                        <th>Room Id</th>
                        <th>Fees Paid</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <%List<Student> students = (List<Student>)request.getAttribute("searchdata"); 
                if(students.isEmpty()){%>
                	<tr>
                        <td colspan="7">Search Result Not Found Found</td>
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
                        <td><%=student.getRoomId()%></td>
                        <td><%=student.getFeesPaid()?"Paid":"Pending.."%></td>
                        <td>
                            <a href="admin?action=updatestudent&id=<%=student.getId()%>"><button class="btn btn-edit">Edit</button></a>
                            <button class="btn btn-delete" data-student-id="<%=student.getId()%>">Delete</button>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

<!-- JavaScript for showing popup based on response -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
    
 <script>
document.addEventListener('DOMContentLoaded', function() {
    // Attach event listener to all delete buttons
    document.querySelectorAll('.btn-delete').forEach(function(button) {
        button.addEventListener('click', function() {
            const studentId = this.getAttribute('data-student-id');
            console.log("<-------> " + studentId);
            Swal.fire({
                title: 'Are you sure?',
                text: "Do you really want to delete this student?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Redirect to the servlet to handle deletion and fee status check
                    window.location.href = 'admin?action=deleteStudent&id=' + studentId;
                }
            });
        });
    });
});
</script>
    <% Boolean feePending = (Boolean) request.getAttribute("feePending");
   Integer studentId = (Integer) request.getAttribute("studentId");
   if (feePending != null && feePending && studentId != null) { %>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            Swal.fire({
                title: "Pending Fee",
                text: "This student's fee is pending. Please choose a payment method.",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Online Pay",
                cancelButtonText: "Cash Pay"
            }).then((result) => {
                if (result.isConfirmed) {
                    // Redirect to payment servlet with Online Pay mode
                    window.location.href = `admin?action=payment&paymentMode=Online&studentId=<%=studentId%>`;
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    // Redirect to payment servlet with Cash Pay mode
                    window.location.href = `admin?action=payment&paymentMode=Cash&studentId=<%=studentId %>`;
                }
            });
        });
    </script>
<% } %>
<% Boolean paymentSuccess = (Boolean) request.getAttribute("paymentSuccess");
Integer stdId = (Integer) request.getAttribute("studentId");
  if (paymentSuccess != null && paymentSuccess) { %>
  <script>
    Swal.fire({
        title: "Payment Successful",
        text: "The payment has been processed successfully.",
        icon: "success",
        confirmButtonText: "OK"
    }).then((result) => {
        if (result.isConfirmed) {
            console.log("User clicked OK. Redirecting...");
            window.location.href = 'admin?action=deleteStudent&id=<%=stdId%>&paymentDone=done';
        }
    });
</script>

<% } %>

<% Boolean paymentNotSuccess = (Boolean) request.getAttribute("paymentNotSuccess");
   if (paymentNotSuccess != null && paymentNotSuccess) { %>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        Swal.fire({
            title: "Payment Not Successful",
            text: "The payment has not been processed successfully.",
            icon: "error",
            confirmButtonText: "OK"
        }).then((result) => {
            if (result.isConfirmed) {
                console.log("User clicked OK. Redirecting...");
                window.location.href = 'admin?action=manageuser';
            }
        });
    });
</script>

<% } %>
<% Boolean deleteSuccess = (Boolean) request.getAttribute("deleteSuccess");
   if (deleteSuccess != null && deleteSuccess) { %>
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        Swal.fire({
            title: "Delete Successful",
            text: "The Student has been deleted successfully.",
            icon: "success",
            confirmButtonText: "OK"
        }).then((result) => {
            if (result.isConfirmed) {
                console.log("User clicked OK. Redirecting...");
                window.location.href = 'admin?action=manageuser';
            }
        });
    });
</script>

<% } %>

</body>
</html>
