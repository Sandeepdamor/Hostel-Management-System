<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.dollop.app.bean.Fee"%>

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
<title>Pending Payments</title>
<style>
.pending-payments-container {
	padding: 20px;
	margin-left: 250px;
	background: white;
	min-height: 100vh;
	font-family: 'Poppins', sans-serif;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
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
	background: #c0392b;
	color: white;
}

.pay-btn {
	background: #27ae60;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
}

.pay-btn:hover {
	background: #219150;
}
</style>
</head>
<body>

	<%@ include file="./user-sidebar.jsp"%>
	<div class="pending-payments-container">
		<%@ include file="./user-navbar.jsp"%>
		<h2>Pending Payments</h2>

		<table>
			<thead>
				<tr>
					<th>Payment ID</th>
					<th>Student ID</th>
					<th>Amount</th>
					<th>Payment Mode</th>
					<th>Payment Date</th>
					<th>Status</th>
					<th>Month</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Fee> pendingFees = (List<Fee>) request.getAttribute("pendingFees");
				if (pendingFees != null && !pendingFees.isEmpty()) {
					for (Fee fee : pendingFees) {
				%>
				<tr>
					<td><%=fee.getId()%></td>
					<td><%=fee.getStudentId()%></td>
					<td>$<%=fee.getAmount()%></td>
					<td><%=fee.getPaymentMode()%></td>
					<td><%=fee.getPaymentDate() != null ? fee.getPaymentDate() : "--"%></td>
					<td><%=fee.getStatus()%></td>
					<td><%=fee.getMonth()%></td>
					<td>
					 <%
					if ("Unpaid".equalsIgnoreCase(fee.getStatus())) {
					%>
						<button class="btn btn-warning btn-pay text-dark fw-bold"
							data-bs-toggle="modal" data-bs-target="#paymentModal"
							data-id="<%=fee.getId()%>" data-amount="<%=fee.getAmount()%>"
							data-studentid="<%=fee.getStudentId()%>"
							data-paymentmode="<%=fee.getPaymentMode()%>">Pay Now</button> <%
 						} else {
 						%>
							<span class="text-success">Paid</span> <%
						 } %>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No pending payments found.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>


	<!-- Payment Modal -->
	<div class="modal fade" id="paymentModal" tabindex="-1"
		aria-labelledby="paymentModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="paymentModalLabel">Make Payment</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="paymentForm">
						<input type="hidden" name="action" value="processPayment">
						<input type="hidden" id="feeId" name="feeId">

						<div class="mb-3">
							<label for="studentId" class="form-label">Student ID</label> <input
								type="text" class="form-control" id="studentId" name="studentId"
								readonly>
						</div>

						<div class="mb-3">
							<label for="amount" class="form-label">Amount</label> <input
								type="number" class="form-control" id="amount" name="amount"
								readonly>
						</div>

						<div class="mb-3">
							<label for="paymentMode" class="form-label">Payment Mode</label>
							<select class="form-control" id="paymentMode" name="paymentMode"
								required>
								<option value="online">Online</option>
								<option value="cash">Cash</option>
							</select>
						</div>

						<button type="submit" class="btn btn-primary">Pay Now</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- JavaScript for Modal & AJAX -->
	<script>
$(document).ready(function () {
    // Open modal and populate fields
    $('#paymentModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var feeId = button.data('id');
        var amount = button.data('amount');
        var studentId = button.data('studentid'); // Get Student ID
        var paymentMode = button.data('paymentmode'); // Get Payment Mode

        console.log("Button HTML:", button[0].outerHTML); // Log the button HTML to verify attributes
        console.log("Fee ID:", feeId);
        console.log("Amount:", amount);
        console.log("Student ID:", studentId);
        console.log("Payment Mode:", paymentMode);
        
        var modal = $(this);
        modal.find('#feeId').val(feeId);
        modal.find('#amount').val(amount);
        modal.find('#studentId').val(studentId);
        modal.find('#paymentMode').val(paymentMode);
    });

    // Handle payment submission via AJAX
    $('#paymentForm').on('submit', function (e) {
        e.preventDefault();

        $.ajax({
            url: '/HostelManagementSystem/student?action=processPayment',
            type: 'POST',
            data: $(this).serialize(),
            success: function (data) {
                console.log('Server Response:', data);
                alert('Payment successful!');
                $('#paymentModal').modal('hide');
                setTimeout(() => location.reload(), 500); // Reload page to reflect payment
            },
            error: function (xhr, status, error) {
                console.error('AJAX Error:', xhr.responseText);
                alert('Payment failed. Please try again.');
            }
        });
    });
});
</script>

</body>
</html>
