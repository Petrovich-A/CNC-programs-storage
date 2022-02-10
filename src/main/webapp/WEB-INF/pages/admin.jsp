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
<head>
<meta charset="UTF-8">
<title>Admin page</title>
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
		<section class="users">
			<h2>List of users</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${allUsers.size() == 0 || allUsers.size() == null}">
						<p class="mb-1">
							<c:out value="No news are avaliable" />
						</p>
						<hr class="mb-1">
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
				<div class="">
					<button type="submit" name="commandName"
						value="go_to_update_user_page" class="btn btn-big btn-primary">Update</button>
					<button type="submit" name="commandName" value="user_delete"
						class="btn btn-big btn-primary">Delete</button>
					<button type="submit" name="commandName" value="go_to_user_info"
						class="btn btn-big btn-primary">User info</button>
				</div>
			</form>
		</section>
		<section class="cncPrograms">
			<h2>List of cncPrograms</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${allUsers.size() == 0 || allUsers.size() == null}">
						<p class="mb-1">
							<c:out value="No users are avaliable" />
						</p>
						<hr class="mb-1">
					</c:when>
					<c:otherwise>
						<table class="">
							<thead>
								<tr>
									<th></th>
									<th>number</th>
									<th>operationNumber</th>
									<th>fileExtension</th>
									<th>isActive</th>
									<th>date</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="cncProgram" items="${cn}">
									<tr>
										<td><input class="" type="radio" name="id"
											required="required" value="${user.getId()}"></td>
										<td>${cncProgram.getOperationNumber()}</td>
										<td>${cncProgram.getFileExtension()}</td>
										<td>${cncProgram.isActive()}</td>
										<td>${cncProgram.getDate()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="">
					<button type="submit" name="command" value="go_to_"
						class="btn btn-big btn-primary">Update</button>
				</div>
			</form>
		</section>
	</main>
	<ctg:footer />
</body>
</html>