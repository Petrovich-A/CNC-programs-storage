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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>User update page</title>
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
						value="search_by_cnc_program_name" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="user">
			<h2>User info:</h2>
			<c:choose>
				<c:when test="${userForUpdate == null}">
					<p class="mb-1">
						<c:out value="No user avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<form action="Controller" method="POST">
						<table class="">
							<tbody>
								<tr>
									<td align="right">loginPersonnelNumber</td>
									<td>${userForUpdate.getLoginPersonnelNumber()}</td>
								</tr>
								<tr>
									<td align="right">EmployeeName</td>
									<td><input name="employeeName" required
										pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
										value="${userForUpdate.getEmployeeName()}"></td>
								</tr>
								<tr>
									<td align="right">EmployeeSurname</td>
									<td><input name="employeeSurname" required
										pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
										value="${userForUpdate.getEmployeeSurname()}"></td>
								</tr>
								<tr>
									<td align="right">EmployeePatronymic</td>
									<td><input name="employeePatronymic" required
										pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
										value="${userForUpdate.getEmployeePatronymic()}"></td>
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
									<td><input type="email" name="email"
										value="${userForUpdate.getEmail()}" required
										pattern="^\S+@\S+\.\S+$"></td>
								</tr>

								<tr>
									<td align="right">user role</td>
									<td><select name="userRole" required>
											<option value="GUEST" selected>GUEST</option>
											<option value="USER">USER</option>
											<option value="ADMINISTRATOR">ADMINISTRATOR</option>
									</select></td>
								</tr>
							</tbody>
						</table>
						<div class="button">
							<button type="submit" name="commandName" value="user_update">Update</button>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</section>
	</main>
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>