

<!DOCTYPE html>
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
.card {
    border: none;
    border-radius: 15px;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s;
    background: white;
    text-align: center;
    padding: 20px;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.card-icon {
    font-size: 3rem;
    margin-bottom: 10px;
    color: #2980b9;
}

.card-title {
    color: #2c3e50;
    font-weight: bold;
}

.card-text {
    font-size: 1.8rem;
    color: #7f8c8d;
}
</style>
</head>
<body>
	<%@ include file="./Admin-sidebar.jsp"%>
	<div class="content">
		<%@ include file="./Admin-navbar.jsp"%>
		
		<div class="container mt-4">
			<div class="row g-4">
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-dollar-sign card-icon"></i>
						<h5 class="card-title">Total Revenue Of This Month</h5>
						<p class="card-text"><%=(Double) request.getAttribute("TotalPaidFee")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-exclamation-triangle card-icon text-warning"></i>
						<h5 class="card-title">Pending Fee Of This Month</h5>
						<p class="card-text"><%=(Double) request.getAttribute("TotalUnPaidFee")%></p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<i class="fas fa-building card-icon"></i>
						<h5 class="card-title">Month</h5>
						<p class="card-text"><%=request.getAttribute("Month")!=null ? request.getAttribute("Month") : "--"%></p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2>Monthly Fee Report</h2>
    <form action="admin" method="get">
        <label>Select Month:</label>
        <input type="month" name="month" required>
        <button type="submit" name="action" value="MonthlyReport" >Generate Report</button>
    </form>
			</div>
			<table class="table table-bordered text-center">
				<thead>
					<tr>
            <th>Student ID</th>
            <th>Amount</th>
            <th>Payment Date</th>
            <th>Status</th>
            <th>Payment Mode</th>
        </tr>
				</thead>
				<tbody>
					<% for (Fee fee : (List<Fee>) request.getAttribute("monthlyFees")) { %>
        <tr>
            <td><%= fee.getStudentId() %></td>
            <td><%= fee.getAmount() %></td>
            <td><%= fee.getPaymentDate()!=null ? fee.getPaymentDate() : "--" %></td>
            <td><%= fee.getStatus() %></td>
            <td><%= fee.getPaymentMode().equals("unpaid") ? "--" : fee.getPaymentMode() %></td>
        </tr>
        <% } %>
			
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
