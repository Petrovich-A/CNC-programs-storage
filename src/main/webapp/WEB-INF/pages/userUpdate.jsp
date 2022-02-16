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
<title>User update page</title>
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
		<section class="user">
			<h2>User info:</h2>
			<c:choose>
				<c:when test="${user == null}">
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
									<td>loginPersonnelNumber</td>
									<td><input name="loginPersonnelNumber"
										value="${user.getLoginPersonnelNumber()}" pattern="^{3,20}+$"></td>
								</tr>
								<tr>
									<td>EmployeeName</td>
									<td>${user.getEmployeeName()}</td>
								</tr>
								<tr>
									<td>EmployeeSurname</td>
									<td>${user.getEmployeeSurname()}</td>
								</tr>
								<tr>
									<td>EmployeePatronymic</td>
									<td>${user.getEmployeePatronymic()}</td>
								</tr>
								<tr>
									<td>employeePosition</td>
									<td>${user.getEmployeePosition()}</td>
								</tr>
								<tr>
									<td>email</td>
									<td>${user.getEmail()}</td>
								</tr>
								<tr>
									<td>CreationDate</td>
									<td>${user.getCreationDate()}</td>
								</tr>
								<tr>
									<td>role name</td>
									<td>${user.getUserRole()}</td>
								</tr>
								<tr>
									<td>position name</td>
									<td>${user.getEmployeePosition()}</td>
								</tr>
							</tbody>
						</table>
						<div class="button">
							<input type="hidden" name="commandName" value="user_update" />
							<button type="submit" class="submit_button">Update</button>
							<button type="reset" value="Reset">Reset</button>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</section>
	</main>
	<ctg:footer />
</body>
</html>