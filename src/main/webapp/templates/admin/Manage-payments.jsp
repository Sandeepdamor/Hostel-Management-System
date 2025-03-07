

<!DOCTYPE html>
<%@page import="com.dollop.app.service.impl.AdminServiceImpl"%>
<%@page import="com.dollop.app.service.IAdminService"%>
<%@page import="com.dollop.app.dao.impl.FeeDaoImpl"%>
<%@page import="com.dollop.app.dao.IFeeDao"%>
<%@page import="com.dollop.app.bean.Fee"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Payments</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

.btn-pay {
	background-color: #27ae60;
	color: white;
	border-radius: 5px;
	padding: 5px 12px;
}

.btn-pay:hover {
	background-color: #219150;
}
</style>
</head>
<body>
	<%@ include file="./Admin-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>
		<div class="container">
		<%IFeeDao feeDao = new FeeDaoImpl(); 
		IAdminService adminService = new AdminServiceImpl();
		%>
			<div class="d-flex justify-content-between align-items-center mb-3">
				<form action="admin" method="post">
           			<div class="input-group"> 
    					<input type="text" class="form-control search-bar" placeholder="Search payment..." name="query">
    					<button class="btn btn-warning" style="width: 100px; height: 38px;" type="submit" name="action" value="searchpayment">Search</button>
					</div>
				</form>
				<a href="admin?action=MonthlyReport"><button class="btn btn-warning">Get Monthly Report</button></a>
			</div>
			<table class="table table-bordered text-center">
				<thead>
					<tr>
						<th>ID</th>
						<th>Student ID</th>
						<th>Amount</th>
						<th>Payment Date</th>
						<th>Payment Mode</th>
						<th>Status</th>
						<th>Month</th>
						<th>Due Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Fee> payments = (List<Fee>) request.getAttribute("payments");
					%>
					<%
					if (payments == null || payments.isEmpty()) {
					%>
					<tr>
						<td colspan="7">No Payment Records Found</td>
					</tr>
					<%
					} else {
					%>
					<%
					for (Fee fee : payments) {
					%>
					<tr>
						<td><%=fee.getId()%></td>
						<td><%=fee.getStudentId()%></td>
						<td><%=fee.getAmount()%></td>
						<td><%=fee.getPaymentDate()!=null ? fee.getPaymentDate() : "--"%></td>
						<td><%=fee.getPaymentMode().equals("unpaid") ? "--" : fee.getPaymentMode()%></td>
						<td><%=fee.getStatus()%></td>
						<td><%=fee.getMonth()%></td>
						
						<%-- <td><%=fee.getDueDate()%></td> --%>
						<td><%=fee.getStatus().equals("Paid") ? "--" : fee.getDueDate()%></td>
						<td>
							<% if ("Unpaid".equalsIgnoreCase(fee.getStatus())) { %>
                            <button class="btn btn-warning btn-pay text-dark fw-bold" data-bs-toggle="modal"
    data-bs-target="#paymentModal"
    data-id="<%=fee.getId()%>"
    data-amount="<%=fee.getAmount()%>"
    data-studentid="<%=fee.getStudentId()%>"
    data-paymentmode="<%=fee.getPaymentMode()%>">Pay Now</button>

                                <% } else { %>
                              <span class="badge bg-success p-2">Paid</span>
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

	<!-- Payment Modal -->
<div class="modal fade" id="paymentModal" tabindex="-1" aria-labelledby="paymentModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="paymentModalLabel">Make Payment</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="paymentForm">
					<input type="hidden" name="action" value="processPayment">
					<input type="hidden" id="feeId" name="feeId">

					<div class="mb-3">
						<label for="studentId" class="form-label">Student ID</label>
						<input type="text" class="form-control" id="studentId" name="studentId" readonly>
					</div>

					<div class="mb-3">
						<label for="amount" class="form-label">Amount</label>
						<input type="number" class="form-control" id="amount" name="amount" readonly>
					</div>

					<div class="mb-3">
						<label for="paymentMode" class="form-label">Payment Mode</label>
						<select class="form-control" id="paymentMode" name="paymentMode" required>
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
            url: '/HostelManagementSystem/admin?action=processPayment',
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
