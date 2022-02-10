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
<meta http-equiv="refresh" content="60">
<meta http-equiv="Content-Type" conАtent="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,800"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/main.css"/>" />
<meta charset="UTF-8">
<title>Main page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.main.navigate.registration"
	var="registration" />
</head>
<fmt:message bundle="${loc}" key="local.main.navigate.authorization"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.main.navigate.admin_page"
	var="admin_page" />
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_registration_page">${registration}</a></li>
				<li><a href="Controller?commandName=go_to_authorization_page">${authorization}</a></li>
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
	<div class="local-buttons">
		<form action="Controller" method="post">
			<input type="hidden" name="commandName" value="change_local" /> <input
				type="hidden" name="local" value="en" /> <input type="submit"
				value="${en_button}" class="" />
		</form>
		<form>
			<input type="hidden" name="commandName" value="change_local">
			<input type="hidden" name="local" value="ru"> <input
				type="submit" value="${ru_button}" class="">
		</form>
	</div>
	<main>
		<p>
			<c:out value="${message}" default="test using jstl" />
		</p>
		<div class="programInput">
			<form action="Controller" method="post">
				<table class="programInputTable" cellspacing="0" cellpadding="4">
					<tr>
						<td align="right" width="100">number</td>
						<td><input type="text" name="number" maxlength="50" size="20"
							required pattern="^{3,20}+$" /></td>
					</tr>
					<tr>
						<td align="right">operationNumber</td>
						<td><input type="text" name="operationNumber" maxlength="50"
							size="20" pattern="^\\d{2,7}+$" /></td>
					</tr>
					<c:forEach var="fileExtension" items="${fileExtension}">
						<tr>
							<td align="right">file Extension</td>
							<td><select name="fileExtension" required>
									<option value="">${fileExtension}</option>
							</select></td>
						</tr>
					</c:forEach>
					<tr>
						<td align="right">comment</td>
						<td><input type="text" name="comment" maxlength="50"
							size="20" pattern="^{100}+$" /></td>
					</tr>
					<tr>
						<td align="right">programText</td>
						<td><textarea name="programText" cols="60" rows="20"
								class="form-control" placeholder="program text..."></textarea></td>
					</tr>
				</table>

				<div class="button">
					<input type="hidden" name="commandName" value="cnc_program_save" />
					<button type="submit" class="submit_button">Save</button>
					<button type="reset" value="Reset">Reset</button>
				</div>
			</form>
		</div>

		<!-- List of programs for pagination -->
		<section class="listUsers">
			<h2>Previous program</h2>
			<c:choose>
				<c:when test="${allUsers == null}">
					<p>
						<c:out value="No user are avaliable" />
					</p>
					<hr>
				</c:when>
				<c:otherwise>
					<c:forEach var="user" items="${allUsers}">
						<table class="table">
							<col width="50">
							<col width="30">
							<col width="50">
							<thead>
								<tr>
									<th>loginPersonnelNumber</th>
									<th>employeeName</th>
									<th>email</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${user.getLoginPersonnelNumber()}</td>
									<td>${user.getEmployeeName()}</td>
									<td>${user.getEmail()}</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
					<form action="yourservlet" method="post">
						<input type="hidden" name="firstrow" value="${firstrow}">
						<input type="hidden" name="rowcount" value="${rowcount}">
						<input type="submit" name="page" value="next"> <input
							type="submit" name="page" value="previous">
					</form>
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