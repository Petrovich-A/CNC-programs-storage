<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="customtags" prefix="ctg"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<meta charset="UTF-8">
<title>Registration page</title>
</head>
<body>
	<div class="header">
		<div class="">
			<div class="">
				<h1>
					CNC <span>programs storage</span>
				</h1>
			</div>
			<ul class="navigation">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section id="news">
			<h1>Please registr</h1>
			<form action="Controller" method="POST">
				<p>
					Personnel number <input type="text" name="loginPersonnelNumber"
						required pattern="^\\d{5}+$" />
				</p>
				<p>
					Password <input type="password" name="password" required
						pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
				</p>
				<p>
					employeeName <input type="text" name="employeeName" required
						pattern="^{8,40}+$" />
				</p>
				<p>
					employeeSurname <input type="text" name="employeeSurname" required
						pattern="^(?={8,40}+$" />
				</p>
				<p>
					employeePatronimic <input type="text" name="employeePatronymic"
						required pattern="^(?={8,40}+$" />
				</p>
				<p>
					position <input type="text" name="position" required
						pattern="^(?={8,40}+$" />
				</p>

				<p>
					email <input type="email" name="email" required
						pattern="^\S+@\S+\.\S+$" />
				</p>
				<div class="button">
					<input type="hidden" name="commandName" value="registration" />
					<button type="submit" class="">submit</button>
				</div>
			</form>
		</section>
	</main>
	<ctg:footer />
</body>
</html>