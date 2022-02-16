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
										value="${user.getLoginPersonnelNumber()}" pattern="^\\d{5}+$"></td>
								</tr>
								<tr>
									<td>EmployeeName</td>
									<td><input name="employeeName"
										value="${user.getEmployeeName()}" pattern="^{8,40}+$"></td>
								</tr>
								<tr>
									<td>EmployeeSurname</td>
									<td><input name="employeeSurname"
										value="${user.getEmployeeSurname()}" pattern="^(?={8,40}+$"></td>
								</tr>
								<tr>
									<td>EmployeePatronymic</td>
									<td><input name="employeePatronymic"
										value="${user.getEmployeePatronymic()}" pattern="^(?={8,40}+$"></td>
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
										value="${user.getEmail()}" pattern="^\S+@\S+\.\S+$"></td>
								</tr>

								<tr>
									<td align="right">user role</td>
									<td><select name="userRole" required>
											<option value="GUEST" selected>GUEST</option>
											<option value="USER">USER</option>
											<option value="ADMINISTRATOR">ADMINISTRATOR</option>
									</select></td>
								</tr>

								<tr>
									<td align="right">password</td>
									<td><input type="password" id="password-input"
										name="password" required
										pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
									</td>
									<td>Password should contain latin symbols (A-z), cyrillic
										symbols (А-я), digits (0-9) 8 to 40 characters long</td>
								</tr>
								<tr>
									<td align="right">confirm password</td>
									<td><input type="password" id="passwordConfirm"
										name="passwordConfirm" required
										pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" /></td>
								<tr>
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