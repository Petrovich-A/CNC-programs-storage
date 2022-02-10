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
<head>
<meta charset="UTF-8">
<title>User info page</title>
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
					<input type="hidden" name="commandName" value="to do" /> <input
						type="search" placeholder="${search_placeholder}"> <label
						class="icon"> <span class="fas fa-search"> </span>
					</label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="users">
			<h2>List of users</h2>
			<c:choose>
				<c:when test="${user.size() == 0 || user.size() == null}">
					<p class="mb-1">
						<c:out value="No users are avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<table class="">
						<tr>
							<th>loginPersonnelNumber</th>
							<th>EmployeeName</th>
							<th>EmployeeSurname</th>
							<th>EmployeePatronymic</th>
							<th>employeePosition</th>
							<th>email</th>
							<th>CreationDate</th>
							<th>role name</th>
							<th>position name</th>
						<tr />
						<tbody>
							<tr>
								<td>${user.getLoginPersonnelNumber()}></td>
								<td>${user.getEmployeeName()}</td>
								<td>${user.getEmployeeSurname()}</td>
								<td>${user.getEmployeePatronymic()}</td>
								<td>${user.getEmployeePosition()}</td>
								<td>${user.getEmail()}</td>
								<td>${user.getCreationDate()}</td>
								<td>${user.getUserRole()}</td>
								<td>${user.getEmployeePosition()}</td>
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