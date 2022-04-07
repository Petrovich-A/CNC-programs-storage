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
<title>CNC machine update page</title>
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
					<input type="hidden" name="commandName" value="" /> <input
						type="search" placeholder="search..."> <label class="icon">
						<span class="fas fa-search"> </span>
					</label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="cnc_machine">
			<h2>CNC machine update:</h2>
			<hr>
			<h2>${cnc_machine_update_message}</h2>
			<c:choose>
				<c:when test="${cncMachine == null}">
					<p class="mb-1">
						<c:out value="No CNC machine avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<form action="Controller" method="POST">
						<table class="">
							<tbody>
								<tr>
									<td align="right">id</td>
									<td>${cncMachine.getId()}</td>
								</tr>
								<tr>
									<td align="right">model</td>
									<td><input name="model" required
										pattern="[a-z,A-Z,а-я,А-Я,0-9,-]{2,20}"
										value="${cncMachine.getModel()}"
										title="CNC machine model should contain digitals, uppercase/lowcase letters and symbol '-'. e.g. LM70-AT"></td>
								</tr>
								<tr>
									<td align="right">${code_equipment}</td>
									<td><input type="text" name="codeEquipment" required
										pattern="[0-9]{2,5}" value="${cncMachine.getCodeEquipment()}"
										title="Code equipment should only contain digitals. e.g. 117" /></td>
								</tr>
						</table>
						<div class="button">
							<button type="submit" name="commandName" value="cnc_machine_update">Update</button>
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