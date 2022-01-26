<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<meta charset="UTF-8">
<title>Registration page</title>
</head>
<script type="text/javascript">
	function matchPassword() {
		var password = document.getElementById("password").value;
		var passwordConfirm = document.getElementById("passwordConfirm").value;
		if (password != passwordConfirm) {
			alert("Passwords did not match");
		} else {
			alert("Password created successfully");
		}
	}
	function verifyRegistrationData() {
		var loginPersonnelNumber = document
				.getElementById("loginPersonnelNumber").value;
		if (pw.loginPersonnelNumber == 5) {
			document.getElementById("loginPersonnelNumber").innerHTML = "**loginPersonnelNumber length must be atleast 5 characters";
			return false;
		}
	}
</script>
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
			<h1>Please fill the registration form</h1>
			<form action="Controller" method="post"
				onsubmit="return matchPassword()">
				<h3>
					Personnel number <input type="text" id="loginPersonnelNumber"
						name="loginPersonnelNumber" required pattern="^\\d{5}+$" />
				</h3>
				<p>Digits (0-5)</p>
				<h3>
					Password <input type="password" id="password" name="password"
						required
						pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
				</h3>
				<h3>
					Confirm password <input type="password" id="passwordConfirm"
						name="passwordConfirm" required
						pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
				</h3>
				<p>Latin symbols (A-z), cyrillic symbols(А-я) plus digits (0-9)
					plus *@#$%^&-+=().</p>
				<h3>
					employeeName <input type="text" name="employeeName" required
						pattern="^{8,40}+$" />
				</h3>
				<p>cyrillic symbols(А-я)</p>
				<h3>
					employeeSurname <input type="text" name="employeeSurname" required
						pattern="^(?={8,40}+$" />
				</h3>
				<p>cyrillic symbols(А-я)</p>
				<h3>
					employeePatronimic <input type="text" name="employeePatronymic"
						required pattern="^(?={8,40}+$" />
				</h3>
				<p>cyrillic symbols(А-я)</p>
				<h3>position</h3>
				<select name="employeePosition" required>
					<option value="engineeringTechnologist" selected>engineering
						technologist</option>
					<option value="cncProgrammer">CNC programmer</option>
				</select>
				<h3>
					email <input type="email" name="email" required
						pattern="^\S+@\S+\.\S+$" />
				</h3>
				<div class="button">
					<input type="hidden" name="commandName" value="registration" />
					<button type="submit" class="submit_button"
						onclick="matchPassword()">Submit</button>
					<button type="reset" value="Reset">Reset</button>
				</div>
			</form>
		</section>
	</main>
	<ctg:footer />
</body>
</html>