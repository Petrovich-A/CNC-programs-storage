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
<fmt:message bundle="${loc}" key="local.authorization.h1.please"
	var="please" />
<fmt:message bundle="${loc}"
	key="local.authorization.form.personnel_number" var="personnel_number" />
<fmt:message bundle="${loc}"
	key="local.authorization.form.personnel_number_title"
	var="personnel_number_title" />
<fmt:message bundle="${loc}"
	key="local.authorization.form.personnel_number_placeholder"
	var="personnel_number_placeholder" />
<fmt:message bundle="${loc}" key="local.authorization.form.password"
	var="password" />
<fmt:message bundle="${loc}"
	key="local.authorization.form.password_title" var="password_title" />
<fmt:message bundle="${loc}"
	key="local.authorization.form.password_placeholder"
	var="password_placeholder" />
<fmt:message bundle="${loc}" key="local.button.submit"
	var="submit" />
<fmt:message bundle="${loc}" key="local.button.reset"
	var="reset" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>authorization page</title>
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_registration_page">${registration}</a></li>
			</div>
			<li class="search-icon">
				<form role="search" action="Controller" method="post">
					<input type="hidden" name="commandName"
						value="search_by_cnc_program_name" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section id="news">
			<h1>${please}</h1>
			<h2>${authorization_message}</h2>
			<form action="Controller" method="post">
				<table class="authorizationInputTable">
					<tr>
						<td align="right">${personnel_number}</td>
						<td><input type="text" name="loginPersonnelNumber" required
							pattern="[0-9]{5}" placeholder="${personnel_number_placeholder}"
							title="${personnel_number_title}" /></td>
					</tr>
					<tr>
						<td align="right">${password}</td>
						<td><input type="password" id="password-input"
							name="password" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$"
							placeholder="${password_placeholder}" title="${password_title}" />
						</td>
					</tr>
				</table>
				<div class="button">
					<input type="hidden" name="commandName" value="authorization" />
					<button type="submit">${submit}</button>
					<button type="reset">${reset}</button>
				</div>
			</form>
		</section>
	</main>
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>