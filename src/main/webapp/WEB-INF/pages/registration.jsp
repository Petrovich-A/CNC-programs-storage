<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.main.navigate.registration"
	var="registration" />
<fmt:message bundle="${loc}" key="local.main.navigate.authorization"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.main.navigate.admin_page"
	var="admin_page" />
<fmt:message bundle="${loc}" key="local.main.navigate.log_out"
	var="log_out" />
<fmt:message bundle="${loc}" key="local.registration.h1.welcome"
	var="welcome" />
<fmt:message bundle="${loc}"
	key="local.registration.form.personnel_number" var="personnel_number" />
<fmt:message bundle="${loc}"
	key="local.registration.form.personnel_number_title"
	var="personnel_number_title" />
<fmt:message bundle="${loc}"
	key="local.registration.form.personnel_number_description"
	var="personnel_number_description" />
<fmt:message bundle="${loc}" key="local.registration.form.employee_name"
	var="employee_name" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_name_title"
	var="employee_name_title" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_name_description"
	var="employee_name_description" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_surname" var="employee_surname" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_surname_title"
	var="employee_surname_title" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_surname_description"
	var="employee_surname_description" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_patronymic"
	var="employee_patronymic" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_patronymic_title"
	var="employee_patronymic_title" />
<fmt:message bundle="${loc}"
	key="local.registration.form.employee_patronymic_description"
	var="employee_patronymic_description" />
<fmt:message bundle="${loc}" key="local.registration.form.position"
	var="position" />
<fmt:message bundle="${loc}"
	key="local.registration.form.position.engineer" var="engineer" />
<fmt:message bundle="${loc}"
	key="local.registration.form.position.programmer" var="programmer" />
<fmt:message bundle="${loc}" key="local.registration.form.email"
	var="email" />
<fmt:message bundle="${loc}" key="local.registration.form.password"
	var="password" />
<fmt:message bundle="${loc}"
	key="local.registration.form.password_title" var="password_title" />
<fmt:message bundle="${loc}" key="local.registration.form.password_description" var="password_description" />
<fmt:message bundle="${loc}" key="local.registration.form.confirm_password" var="confirm_password" />
<fmt:message bundle="${loc}" key="local.button.submit" var="submit" />
<fmt:message bundle="${loc}" key="local.button.reset" var="reset" />
<fmt:message bundle="${loc}" key="local.registration.h4.have_account" var="have_account" />
<fmt:message bundle="${loc}" key="local.registration.h4.log_in" var="log_in" />
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
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_authorization_page">${authorization}</a></li>
			</div>
			<li class="search-icon">
				<form role="search" action="Controller" method="post">
					<input type="hidden" name="commandName"
						value="search" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section id="">
			<h1>${welcome}</h1>
			<hr>
			<h3>${registration_message}</h3>
			<hr>
			<form action="Controller" method="post"
				onsubmit="return matchPassword()">
				<table class="programInputTable" cellpadding="5px"
					cellspacing="12px">
					<tr>
						<td align="right">${personnel_number}</td>
						<td><input type="text" id="personnelNumber"
							name="personnelNumber" required pattern="[0-9]{5}"
							title="${personnel_number_title}" /></td>
						<td>${personnel_number_description}</td>
					</tr>
					<tr>
						<td align="right">${employee_name}</td>
						<td><input type="text" name="employeeName" required
							pattern="[a-z,A-Z,??-??,??-??]{3,40}" title="${employee_name_title}" /></td>
						<td>${employee_name_description}</td>
					</tr>
					<tr>
						<td align="right">${employee_surname}</td>
						<td><input type="text" name="employeeSurname" required
							pattern="[a-z,A-Z,??-??,??-??]{3,40}"
							title="${employee_surname_title}" /></td>
						<td>${employee_surname_description}</td>
					</tr>
					<tr>
						<td align="right">${employee_patronymic}</td>
						<td><input type="text" name="employeePatronymic" required
							pattern="[a-z,A-Z,??-??,??-??]{3,40}"
							title="${employee_surname_title}" /></td>
						<td>${employee_surname_description}</td>
					</tr>
					<tr>
						<td align="right">${position}</td>
						<td><select name="employeePosition" required>
								<option value="engineering_technician" selected>${engineer}</option>
								<option value="cnc_programmer">${programmer}</option>
						</select></td>
					</tr>
					<tr>
						<td align="right">${email}</td>
						<td><input type="email" name="email" required
							pattern="^\S+@\S+\.\S+$" /></td>
					</tr>
					<tr>
						<td align="right">${password}</td>
						<td><input type="password" id="passwordInput" name="password"
							required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}+$"
							title="${password_title}" /></td>
						<td>${password_description}</td>
					</tr>
					<tr>
						<td align="right">${confirm_password}</td>
						<td><input type="password" id="passwordConfirm"
							name="passwordConfirm" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=,;:_*()]).{8,40}+$" /></td>
					<tr>
				</table>

				<div class="button">
					<input type="hidden" name="commandName" value="registration" />
					<button type="submit" class="submit_button"
						onclick="confirmMatchPassword()">${submit}</button>
					<button type="reset" value="Reset">${reset}</button>
				</div>
			</form>
			<hr>
			<h4>${have_account} <a
					href="Controller?commandName=go_to_authorization_page">${log_in}</a>
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