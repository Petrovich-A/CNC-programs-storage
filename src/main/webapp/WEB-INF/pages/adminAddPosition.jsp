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
	<div class="header">
		<div class="">
			<div class="">
				<h1>
					CNC <span>programs storage</span>
				</h1>
			</div>
			<ul class="navigation">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section class="user">
			<h2>User information:</h2>
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
								<td>loginPersonnelNumber</td>
								<td>${userFromDao.getLoginPersonnelNumber()}</td>
							</tr>
							<tr>
								<td>EmployeeName</td>
								<td>${userFromDao.getEmployeeName()}</td>
							</tr>
							<tr>
								<td>EmployeeSurname</td>
								<td>${userFromDao.getEmployeeSurname()}</td>
							</tr>
							<tr>
								<td>EmployeePatronymic</td>
								<td>${userFromDao.getEmployeePatronymic()}</td>
							</tr>
							<tr>
								<td>employeePosition</td>
								<td>${userFromDao.getEmployeePosition()}</td>
							</tr>
							<tr>
								<td>email</td>
								<td>${userFromDao.getEmail()}</td>
							</tr>
							<tr>
								<td>CreationDate</td>
								<td>${userFromDao.getCreationDate()}</td>
							</tr>
							<tr>
								<td>role name</td>
								<td>${userFromDao.getUserRole()}</td>
							</tr>
							<tr>
								<td>position name</td>
								<td>${userFromDao.getEmployeePosition()}</td>
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