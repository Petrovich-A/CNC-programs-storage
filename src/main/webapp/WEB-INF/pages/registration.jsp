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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/main.css"/>" />
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
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
			</div>
			<li class="search-icon">
				<form role="search" action="Controller" method="post">
					<input type="hidden" name="commandName" value="to do" /> <input
						type="search" placeholder="search..."> <label class="icon">
						<span class="fas fa-search"> </span>
					</label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section id="">
			<h1>Welcome to CNC Program Storage account!</h1>
			<br>
			<h3>Please complete the registration form below</h3>
			<br>
			<form action="Controller" method="post"
				onsubmit="return matchPassword()">
				<table class="programInputTable" cellpadding="5px"
					cellspacing="12px">
					<tr>
						<td align="right">personnel number</td>
						<td><input type="text" id="loginPersonnelNumber"
							name="loginPersonnelNumber" required pattern="^\\d{5}+$" /></td>
						<td>Login personnel number should have (0-9) digits 5
							characters long.</td>
					</tr>
					<tr>
						<td align="right">employeeName</td>
						<td><input type="text" name="employeeName" required
							pattern="^{8,40}+$" /></td>
						<td>Cyrillic symbols(А-я)</td>
					</tr>
					<tr>
						<td align="right">employeeSurname</td>
						<td><input type="text" name="employeeSurname" required
							pattern="^(?={8,40}+$" /></td>
						<td>Cyrillic symbols(А-я)</td>
					</tr>
					<tr>
						<td align="right">employeePatronimic</td>
						<td><input type="text" name="employeePatronymic" required
							pattern="^(?={8,40}+$" /></td>
						<td>Cyrillic symbols(А-я)</td>
					</tr>
					<tr>
						<td align="right">position</td>
						<td><select name="employeePosition" required>
								<option value="engineering_technician" selected>engineering
									technologist</option>
								<option value="cnc_programmer">CNC programmer</option>
						</select></td>
					</tr>
					<tr>
						<td align="right">email</td>
						<td><input type="email" name="email" required
							pattern="^\S+@\S+\.\S+$" /></td>
					</tr>
					<tr>
						<td align="right">password</td>
						<td><input type="password" id="password-input"
							name="password" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
						</td>
						<td>Password should contain latin symbols (A-z), cyrillic
							symbols (А-я), digits (0-9) 8 to 40 characters long</td>
					</tr>
					<tr>
						<td align="right">confirm password</td>
						<td><input type="password" id="passwordConfirm"
							name="passwordConfirm" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" /></td>
					<tr>
				</table>

				<div class="button">
					<input type="hidden" name="commandName" value="registration" />
					<button type="submit" class="submit_button"
						onclick="matchPassword()">Submit</button>
					<button type="reset" value="Reset">Reset</button>
				</div>
			</form>
		</section>
	</main>
	<!-- Here is the main footer that is used across all the pages of website with using customTag writing -->
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>