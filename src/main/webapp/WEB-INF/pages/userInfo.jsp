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
<title>Log in page</title>
</head>
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
				<li><a href="Controller?commandName=go_to_admin_page">ADMIN</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section class="users">
			<h2>User info:</h2>
			<c:choose>
				<c:when test="${user.size() == 0 || user.size() == null}">
					<p class="mb-1">
						<c:out value="No news are avaliable" />
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
							<th>date</th>
							<th>user role</th>
						<tr />
						<tbody>
							<c:forEach var="user" items="${user}">
								<tr>
									<td>${user.getLoginPersonnelNumber()}></td>
									<td>${user.getEmployeeName()}</td>
									<td>${user.getEmployeeSurname()}</td>
									<td>${user.getEmployeePatronymic()}</td>
									<td>${user.getPosition()}</td>
									<td>${user.getEmail()}</td>
									<td>${user.getDate()}</td>
									<td>${user.getRole()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</section>
	</main>
	<ctg:footer />
</body>
</html>