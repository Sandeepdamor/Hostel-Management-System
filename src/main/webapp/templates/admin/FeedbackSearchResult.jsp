<!DOCTYPE html>
<%@page import="com.dollop.app.bean.Grievance"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Feedback</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

.btn-update {
	background-color: #f1c40f;
	color: black;
	border-radius: 5px;
	padding: 5px 12px;
	font-weight: bold;
}

.btn-update:hover {
	background-color: #d4ac0d;
	color: white;
}
</style>
</head>
<body>
	<%@ include file="./Admin-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>
		<div class="container">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<a style="text-decoration: none; color: red;" href="admin?action=managfeedback"><button class="btn btn-add">Close Search Result</button></a>
			</div>
			<table class="table table-bordered text-center">
				<thead>
					<tr>
						<th>Feedback ID</th>
						<th>Student Id</th>
						<th>Description</th>
						<th>Status</th>
						<th>Response</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Grievance> feedbacks = (List<Grievance>) request.getAttribute("searchdata");
					%>
					<%
					   if (feedbacks == null || feedbacks.isEmpty()) {
					%>
					<tr>
						<td colspan="6">No Feedback Found</td>
					</tr>
					<%
					}
					   else {
					%>
					<%
					for (Grievance feedback : feedbacks) {
					%>
					<tr>
						<td><%=feedback.getId()%></td>
						<td><%=feedback.getStudentId()%></td>
						<td><%=feedback.getDescription()%></td>
						<td><%=feedback.getStatus()%></td>
						<td><%=feedback.getResponse()%></td>
						<td>
							<% if (!"Resolved".equalsIgnoreCase(feedback.getStatus())) { %>
								<button class="btn btn-warning btn-update text-dark fw-bold" data-bs-toggle="modal"
									data-bs-target="#responseModal"
									data-id="<%=feedback.getId()%>"
									data-student-id="<%=feedback.getStudentId()%>"
									data-response="<%=feedback.getResponse()%>">
									Update
								</button>
							<% } else { %>
								<span class="badge bg-success p-2">Resolved</span>
							<% } %>
						</td>
					</tr>
					<%
					}
					%>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Modal for Updating Response -->
	<div class="modal fade" id="responseModal" tabindex="-1"
		aria-labelledby="responseModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="responseModalLabel">Update Feedback Response</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="responseForm">
						<input type="hidden" name="action" value="adminresponse">
						<input type="hidden" id="feedbackId" name="feedbackId">
						<input type="hidden" name="studentId" id="studentId">
						<div class="mb-3">
							<label for="responseText" class="form-label">Response</label>
							<textarea class="form-control" id="responseText" name="response" rows="4"></textarea>
						</div>
						<button type="submit" class="btn btn-primary">Submit Response</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- JavaScript for handling modal and AJAX -->
	<script>
    $(document).ready(function () {
        // Open modal and populate fields
        $('#responseModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var feedbackId = button.data('id');
            var studentId = button.data('student-id');
            var responseText = button.data('response') || ''; // Default empty if null
            
            var modal = $(this);
            modal.find('#feedbackId').val(feedbackId);
            modal.find('#responseText').val(responseText);
            modal.find('#studentId').val(studentId);
        });

        // Handle form submission via AJAX
        $('#responseForm').on('submit', function (e) {
            e.preventDefault();     
            $.ajax({
                url: '/HostelManagementSystem/admin?action=updateFeedback',
                type: 'POST',
                data: $(this).serialize(), // Ensures all form fields are included
                success: function (data) {
                    console.log('Server Response:', data);
                    alert('Response updated successfully!');
                    $('#responseModal').modal('hide');
                    setTimeout(() => location.reload(), 500); // Reload page after update
                },
                error: function (xhr, status, error) {
                    console.error('AJAX Error:', xhr.responseText);
                    alert('Error updating response. Please try again.');
                }
            });
        });
    });
    </script>
</body>
</html>
