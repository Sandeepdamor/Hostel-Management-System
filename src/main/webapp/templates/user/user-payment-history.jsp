<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dollop.app.bean.Fee"%>
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
<title>Payment History</title>
<style>
.payments-container {
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
	margin-top: 40px;
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

.download-btn {
	background: #ff8800;
	color: white;
	padding: 8px 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.3s;
}

.download-btn:hover {
	background: #e67300;
}
</style>
</head>
<body>
	<%@ include file="./user-sidebar.jsp"%>

	<div class="payments-container">
		<%@ include file="./user-navbar.jsp"%>
		<h2>Payment History - Maple Hostel Services</h2>

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
					<th>Invoice</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Fee> fees = (List<Fee>) request.getAttribute("paymentHistory");
				if (fees != null && !fees.isEmpty()) {
					for (Fee fee : fees) {
				%>
				<tr>
					<td><%=fee.getId()%></td>
					<td><%=fee.getStudentId()%></td>
					<td><%=fee.getAmount()%></td>
					<td><%=fee.getPaymentMode()%></td>
					<td><%=fee.getPaymentDate()%></td>
					<td><%=fee.getStatus()%></td>
					<td><%=fee.getMonth()%></td>
					<td>
						<button class="download-btn"
							onclick="downloadInvoice('<%=fee.getId()%>', '<%=fee.getPaymentDate()%>', '<%=fee.getAmount()%>', '<%=fee.getPaymentMode()%>')">
							Download</button>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5">No payment history found.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.3/html2pdf.bundle.min.js"></script>


<script>





function downloadInvoice(paymentId, date, amount, paymentMode) {
    console.log("Debugging Invoice Data:", paymentId, date, amount, paymentMode); // Debugging

    if (!paymentId || !date || !amount || !paymentMode) {
        alert("Invoice data is missing! Please try again.");
        return;
    }

    const currentDate = new Date().toLocaleDateString();
    const parsedAmount = parseFloat(amount).toFixed(2); // Ensure correct format

    const invoiceContent = `
        <div id="invoice" style="text-align: center; padding: 20px; font-family: Arial, sans-serif; border: 1px solid #ddd; width: 80%; margin: auto;">
            <h1 style="color: #2c3e50;">Invoice</h1>
            <p><strong>Maple Hostel Services</strong></p>
            <p>123 Hostel Lane, City, Country</p>
            <p style="margin-bottom: 20px;">Date: ${currentDate}</p>

            <table border="1" style="width: 100%; border-collapse: collapse; text-align: center; margin-top: 10px;">
                <thead>
                    <tr style="background: #f4f4f4;">
                        <th style="padding: 10px;">Payment ID</th>
                        <th style="padding: 10px;">Date</th>
                        <th style="padding: 10px;">Amount</th>
                        <th style="padding: 10px;">Payment Mode</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="padding: 10px;">`+paymentId+`</td>
                        <td style="padding: 10px;">`+date+`</td>
                        <td style="padding: 10px;">`+parsedAmount+`</td>
                        <td style="padding: 10px;">`+paymentMode+`</td>
                    </tr>
                </tbody>
            </table>

            <div style="margin-top: 30px; text-align: left; padding: 10px;">
                <h3 style="color: #2c3e50;">Terms & Conditions</h3>
                <p style="font-size: 14px;">
                    1. This invoice is generated electronically and does not require a signature.<br>
                    2. Please retain this invoice for your records.<br>
                    3. Payments made are non-refundable except in cases of error.<br>
                    4. If you have any questions, please contact our support team.<br>
                </p>
            </div>

            <p style="margin-top: 30px; font-size: 16px; font-weight: bold; color: #2c3e50;">
                Thank you for choosing Maple Hostel Services!
            </p>
        </div>
    `;

    console.log("Generated Invoice Content:", invoiceContent); // Debugging

    const invoiceElement = document.createElement("div");
    invoiceElement.innerHTML = invoiceContent;
    document.body.appendChild(invoiceElement);

    setTimeout(() => {
        html2pdf().from(invoiceElement).save(`invoice_${paymentId}.pdf`).then(() => {
            console.log("PDF successfully generated for:", paymentId);
            document.body.removeChild(invoiceElement);
        });
    }, 2000); // Ensuring proper rendering before PDF capture
}
















/* 

function downloadInvoice(paymentId, date, amount, paymentMode) {
    console.log("Debugging Invoice Data:", paymentId, date, amount, paymentMode); // Debugging

    if (!paymentId || !date || !amount || !paymentMode) {
        alert("Invoice data is missing! Please try again.");
        return;
    }

    const currentDate = new Date().toLocaleDateString();
    const parsedAmount = parseFloat(amount).toFixed(2); // Ensure correct format

    const invoiceContent = `
        <div id="invoice" style="text-align: center; padding: 20px; font-family: Arial, sans-serif;">
            <h1>Invoice</h1>
            <p><strong>Maple Hostel Services</strong></p>
            <p>123 Hostel Lane, City, Country</p>
            <p>Date: ${currentDate}</p>
            <table border="1" style="width: 100%; border-collapse: collapse; text-align: center; margin-top: 10px;">
                <thead>
                    <tr style="background: #f4f4f4;">
                        <th>Payment ID</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Payment Mode</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>`+paymentId+`</td>
                        <td>`+date+`</td>
                        <td>`+parsedAmount+`</td>
                        <td>`+paymentMode+`</td>
                    </tr>
                </tbody>
            </table>
            
        </div>
    `;

    console.log("Generated Invoice Content:", invoiceContent); // Debugging

    const invoiceElement = document.createElement("div");
    invoiceElement.innerHTML = invoiceContent;
    document.body.appendChild(invoiceElement);

    setTimeout(() => {
        html2pdf().from(invoiceElement).save(`invoice_${paymentId}.pdf`).then(() => {
            console.log("PDF successfully generated for:", paymentId);
            document.body.removeChild(invoiceElement);
        });
    }, 2000); // Ensuring proper rendering before PDF capture
} */


</script>
</html>
