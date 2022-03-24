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
	href="<c:url value="/CSS/style.css"/>" />
<title>Registration page</title>
</head>
<script type="text/javascript">
	function confirmMatchPassword() {
		var password = document.getElementById("passwordInput");
		var passwordConfirm = document.getElementById("passwordConfirm");
		if (password != passwordConfirm) {
			alert("Passwords did not match");
		} else {
			alert("Passwords is norm");
		}
	}
</script>

<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
				<li><a href="Controller?commandName=go_to_authorization_page">LOG
						IN</a></li>
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
			<hr>
			<h3>${registration_message}</h3>
			<hr>
			<form action="Controller" method="post"
				onsubmit="return matchPassword()">
				<table class="programInputTable" cellpadding="5px"
					cellspacing="12px">
					<tr>
						<td align="right">personnel number</td>
						<td><input type="text" id="loginPersonnelNumber"
							name="loginPersonnelNumber" required pattern="[0-9]{5}"
							title="loginPersonnelNumber should only contain digitals. e.g. 43510" /></td>
						<td>Login personnel number should have (0-9) numeric digits 5
							characters long.</td>
					</tr>
					<tr>
						<td align="right">employeeName</td>
						<td><input type="text" name="employeeName" required
							pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
							title="employeeName should contain lowcase and uppercase letters. e.g. Алексей" /></td>
						<td>Cyrillic symbols(А-я)</td>
					</tr>
					<tr>
						<td align="right">employeeSurname</td>
						<td><input type="text" name="employeeSurname" required
							pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
							title="employeeSurname should contain lowcase and uppercase letters. e.g. Муравьев" /></td>
						<td>Cyrillic symbols(А-я)</td>
					</tr>
					<tr>
						<td align="right">employeePatronimic</td>
						<td><input type="text" name="employeePatronymic" required
							pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
							title="employeePatronimic should contain lowcase and uppercase letters. e.g. Арнольдович" /></td>
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
						<td><input type="password" id="passwordInput" name="password"
							required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}+$"
							title="passwords have to contain at least 1 lowcase and uppercase letter,
							at least once digital, including at least 1 specail symbol(!?()@#$%^&+=,;:_*) 
							at least 8 to 40 characters long." />
						</td>
						<td>Password have to contain latin symbols (A-z), cyrillic
							symbols (А-я), numeric digits (0-9) from 8 to 40 characters long.</td>
					</tr>
					<tr>
						<td align="right">confirm password</td>
						<td><input type="password" id="passwordConfirm"
							name="passwordConfirm" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}+$" /></td>
					<tr>
				</table>

				<div class="button">
					<input type="hidden" name="commandName" value="registration" />
					<button type="submit" class="submit_button"
						onclick="confirmMatchPassword()">Submit</button>
					<button type="reset" value="Reset">Reset</button>
				</div>
			</form>
			<hr>
			<h4>
				Have an account already? <a
					href="Controller?commandName=go_to_authorization_page">log in</a>
			</h4>

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