<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<%@ page import="by.petrovich.storage.entity.User"%>
<%@ page import="by.petrovich.storage.entity.CncProgram"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>Admin users page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.main.navigate.admin_page"
	var="admin_page" />
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_admin_page">${admin_page}</a></li>
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
		<section class="users">
			<h2>List of users:</h2>
			<h2>${admin_users_message}</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${allUsers.size() == 0 || allUsers.size() == null}">
						<p class="">
							<c:out value="No users are avaliable" />
						</p>
						<hr class="">
					</c:when>
					<c:otherwise>
						<table class="">
							<thead>
								<tr>
									<th>loginPersonnelNumber</th>
									<th>EmployeeName</th>
									<th>getEmployeeSurname</th>
									<th>getEmployeePatronymic</th>
									<th>getEmail</th>
									<th>time create</th>
									<th>role name</th>
									<th>position name</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="user" items="${allUsers}">
									<tr>
										<td><input class="" type="radio"
											name="loginPersonnelNumber" required="required"
											value="${user.getLoginPersonnelNumber()}">${user.getLoginPersonnelNumber()}</td>
										<td>${user.getEmployeeName()}</td>
										<td>${user.getEmployeeSurname()}</td>
										<td>${user.getEmployeePatronymic()}</td>
										<td>${user.getEmail()}</td>
										<td>${user.getCreationDate()}</td>
										<td>${user.getUserRole()}</td>
										<td>${user.getEmployeePosition()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="button">
					<button type="submit" name="commandName"
						value="go_to_update_user_page">Update</button>
					<button type="submit" name="commandName" value="go_to_user_info_page">User
						info</button>
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