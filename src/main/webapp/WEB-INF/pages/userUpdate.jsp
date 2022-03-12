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
									<td>loginPersonnelNumber</td>
									<td>${userForUpdate.getLoginPersonnelNumber()}</td>
								</tr>
								<tr>
									<td>EmployeeName</td>
									<td><input name="employeeName" required
										pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
										value="${userForUpdate.getEmployeeName()}"></td>
								</tr>
								<tr>
									<td>EmployeeSurname</td>
									<td><input name="employeeSurname" required
										pattern="[a-z,A-Z,а-я,А-Я]{3,40}"
										value="${userForUpdate.getEmployeeSurname()}"></td>
								</tr>
								<tr>
									<td>EmployeePatronymic</td>
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
									<td>email</td>
									<td><input type="email" name="email"
										value="${userForUpdate.getEmail()}" required pattern="^\S+@\S+\.\S+$"></td>
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
	<ctg:footer />
</body>
</html>