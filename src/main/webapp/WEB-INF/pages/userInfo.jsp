<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<%@ page import="by.petrovich.storage.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>User info page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.user.info.message.information" var="information" />
<fmt:message bundle="${loc}" key="local.registration.form.personnel_number" var="personnel_number" />
<fmt:message bundle="${loc}" key="local.registration.form.employee_name" var="employee_name" />
<fmt:message bundle="${loc}" key="local.registration.form.employee_surname" var="employee_surname" />
<fmt:message bundle="${loc}" key="local.registration.form.employee_patronymic" var="employee_patronymic" />
<fmt:message bundle="${loc}" key="local.registration.form.position" var="position" />
<fmt:message bundle="${loc}" key="local.registration.form.email" var="email" />
<fmt:message bundle="${loc}" key="local.user.info.form.creation_date" var="creation_date" />
<fmt:message bundle="${loc}" key="local.user.info.form.role" var="role" />
<fmt:message bundle="${loc}" key="local.navigate.details" var="details" />
<fmt:message bundle="${loc}" key="local.navigate.cnc_programs" var="cnc_programs" />
<fmt:message bundle="${loc}" key="local.navigate.cnc_machines" var="cnc_machines" />
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_users_program">${cnc_programs}</a></li>
				<li><a href="Controller?commandName=go_to_details">${details}</a></li>
				<li><a href="Controller?commandName=go_to_cnc_machines">${cnc_machines}</a></li>
				<c:choose>
					<c:when test="${user != null}">
						<li><a
							href="Controller?commandName=go_to_user_info&loginPersonnelNumber=${user.getLoginPersonnelNumber()}">${user.getLoginPersonnelNumber()}</a></li>
						<li><a href="Controller?commandName=log_out">${log_out}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="Controller?commandName=go_to_authorization_page">${authorization}</a></li>
					</c:otherwise>
				</c:choose>
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
		<section class="user">
			<h2>${information}</h2>
			<c:choose>
				<c:when test="${userFromDao == null}">
					<p class="mb-1">
						<c:out value="User isn't avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<table class="">
						<tbody>
							<tr>
								<td>${personnel_number}</td>
								<td>${userFromDao.getLoginPersonnelNumber()}</td>
							</tr>
							<tr>
								<td>${employee_name}</td>
								<td>${userFromDao.getEmployeeName()}</td>
							</tr>
							<tr>
								<td>${employee_surname}</td>
								<td>${userFromDaoFromDao.getEmployeeSurname()}</td>
							</tr>
							<tr>
								<td>${employee_patronymic}</td>
								<td>${userFromDao.getEmployeePatronymic()}</td>
							</tr>
							<tr>
								<td>${position}</td>
								<td>${userFromDao.getEmployeePosition()}</td>
							</tr>
							<tr>
								<td>${email}</td>
								<td>${userFromDao.getEmail()}</td>
							</tr>
							<tr>
								<td>${creation_date}</td>
								<td>${userFromDao.getCreationDate()}</td>
							</tr>
							<tr>
								<td>${role}</td>
								<td>${userFromDao.getUserRole()}</td>
							</tr>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
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