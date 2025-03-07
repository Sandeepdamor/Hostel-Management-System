<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Pay Fee</title></head>
<body>
    <h2>Fee Payment</h2>
    <form action="admin" method="post">
        <input type="hidden" name="feeId" value="<%= request.getParameter("feeId") %>">
        <input type="hidden" name="studentId" value="<%= request.getParameter("studentId") %>">
        <input type="hidden" name="amount" value="<%= request.getParameter("amount") %>">

        <label>Payment Mode:</label>
        <select name="paymentMode">
            <option value="Cash">Cash</option>
            <option value="Online">Online</option>
        </select>
        <br>
        <button type="submit" name="action" value="processPayment">Confirm Payment</button>
    </form>
</body>
</html>
