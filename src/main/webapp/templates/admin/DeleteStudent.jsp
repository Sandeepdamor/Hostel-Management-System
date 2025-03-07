<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fee Payment Popup</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <button id="deleteButton" class="btn btn-danger">Delete</button>

    <script>
        document.getElementById('deleteButton').addEventListener('click', function() {
            Swal.fire({
                title: 'Are you sure?',
                text: "Do you really want to delete this item?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                	window.location.href = 'paymentPage.jsp?studentId=<%= 2 %>';
                    /* showFeePendingPopup(); */
                }
            });
        });

        
        function showFeePendingPopup() {
            Swal.fire({
                title: "Pending Fee",
                text: "Your fee is pending. Please make a payment.",
                icon: "warning",
                showCancelButton: true,
                confirmButtonText: "Pay Fee",
                cancelButtonText: "Cancel"
            }).then((result) => {
                if (result.isConfirmed) {
                    showPaymentOptions();
                }
            });
        }

        function showPaymentOptions() {
            Swal.fire({
                title: "Select Payment Method",
                text: "Choose how you want to pay your fee.",
                icon: "info",
                showCancelButton: true,
                confirmButtonText: "Online Pay",
                cancelButtonText: "Cash Pay"
            }).then((result) => {
                if (result.isConfirmed) {
                    // Redirect to servlet with Online Pay mode
                    window.location.href = "PaymentServlet?paymentMode=Online";
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    // Redirect to servlet with Cash Pay mode
                    window.location.href = "PaymentServlet?paymentMode=Cash";
                }
            });
        }
    </script>
</body>
</html>
