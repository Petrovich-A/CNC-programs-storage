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
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
				<li><a href="Controller?commandName=go_to_users_program">CNC
						PROGRAMS</a></li>
				<c:choose>
					<c:when test="${sessionScope.user != null}">
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
						value="search_by_cnc_program_name" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="user">
			<h2>User information:</h2>
			<c:choose>
				<c:when test="${user == null}">
					<p class="mb-1">
						<c:out value="User isn't avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<table class="">
						<tbody>
							<tr>
								<td>login Personnel Number</td>
								<td>${user.getLoginPersonnelNumber()}</td>
							</tr>
							<tr>
								<td>Employee Name</td>
								<td>${user.getEmployeeName()}</td>
							</tr>
							<tr>
								<td>Employee Surname</td>
								<td>${user.getEmployeeSurname()}</td>
							</tr>
							<tr>
								<td>Employee Patronymic</td>
								<td>${user.getEmployeePatronymic()}</td>
							</tr>
							<tr>
								<td>employee Position</td>
								<td>${user.getEmployeePosition()}</td>
							</tr>
							<tr>
								<td>email</td>
								<td>${user.getEmail()}</td>
							</tr>
							<tr>
								<td>Creation Date</td>
								<td>${user.getCreationDate()}</td>
							</tr>
							<tr>
								<td>role name</td>
								<td>${user.getUserRole()}</td>
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