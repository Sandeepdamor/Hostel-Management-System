<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
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
        .btn-save {
            background-color: #1abc9c;
            color: white;
            border-radius: 5px;
        }
        .btn-save:hover {
            background-color: #16a085;
        }
    </style>
</head>
<body>
    <%@ include file="./Admin-sidebar.jsp" %>
    <div class="content">
        <%@ include file="./Admin-navbar.jsp" %>
        <div class="container">
            <h2 class="mb-4">Settings</h2>
            <form>
                <div class="mb-3">
                    <label for="adminName" class="form-label">Admin Name</label>
                    <input type="text" class="form-control" id="adminName" placeholder="Enter your name">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter your email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">New Password</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter new password">
                </div>
                <div class="mb-3">
                    <label for="notifications" class="form-label">Enable Notifications</label>
                    <select class="form-control" id="notifications">
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-save">Save Changes</button>
            </form>
        </div>
    </div>
</body>
</html>
