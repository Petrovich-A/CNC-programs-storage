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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>Admin CNC machines page</title>
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
		<section class="users">
			<h2>List of CNC machines:</h2>
			<hr>
			<h2>${admin_cnc_machines_message}</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when
						test="${cncMachines.size() == 0 || cncMachines.size() == null}">
						<p class="">
							<c:out value="No CNC machines are avaliable" />
						</p>
						<hr class="">
					</c:when>
					<c:otherwise>
						<table class="">
							<thead>
								<tr>
									<th></th>
									<th>id</th>
									<th>model</th>
									<th>code Equipment</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="cncMachine" items="${cncMachines}">
									<tr>
										<td><input class="" type="radio" name="cnc_machine_id"
											required="required" value="${cncMachine.getId()}"></td>
										<td>${cncMachine.getId()}</td>
										<td>${cncMachine.getModel()}</td>
										<td>${cncMachine.getCodeEquipment()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="button">
					<button type="submit" name="commandName" value="go_to_cnc_machine_update_page">Update</button>
				</div>
			</form>
		</section>
	</main>
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>