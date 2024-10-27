<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<link rel="stylesheet" type="text/css" href="./css/AdminUnderwriterlogin.css">
</head>
<body>

	<div class="wrapper">
		<div class="title">Admin Login</div>
		<form action="admin" method="post">
			<div class="field">
				<input type="text" name="username" required> <label>Username</label>
			</div>
			<div class="field">
				<input type="password" name="password" required> <label>Password</label>
			</div>
			<div class="field">
				<input type="submit" value="Login">
			</div>
			
		</form>
	</div>


</body>
</html>