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
<title>CNC program view page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<style>
.scrolling {
	padding: 5px 0px 20px 20px;
	height: 750px; /* высота нашего блока */
	width: 900px; /* ширина нашего блока */
	background: #fff; /* цвет фона, белый */
	border: 1px solid #C1C1C1; /* размер и цвет границы блока */
	overflow-x: scroll; /* прокрутка по горизонтали */
	overflow-y: scroll; /* прокрутка по вертикали */
	white-space: pre-line;
	color: black;
	font-weight: bold;
}
</style>
-->
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
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
		<section class="cncPrograms">
			<h2>CNC program:</h2>
			<c:choose>
				<c:when test="${cncProgram == null}">
					<p>
						<c:out value="No CNC program is avaliable" />
					</p>
					<hr class="mb-1">
				</c:when>
				<c:otherwise>
					<table class="">
						<tr>
							<td>number</td>
							<td>${cncProgram.getNumber()}</td>
						</tr>
						<tr>
							<td>detail name</td>
							<td>${cncProgram.getDetail().getName()}</td>
						</tr>
						<tr>
							<td>operation Number</td>
							<td>${cncProgram.getOperationNumber()}</td>
						<tr>
						<tr>
							<t d>login
							</td>
							<td>${cncProgram.getLoginPersonnelNumber()}</td>

							<tr>
								<td>Creation Date</td>
								<td>${cncProgram.getCreationDate()}</td>
							</tr>
							<tr>
								<td>Comment</td>
								<td>${cncProgram.getComment()}</td>
							</tr>
							<tr>
								<td>is active</td>
								<td><c:choose>
										<c:when test="${cncProgram.isActive()}">active</c:when>
										<c:otherwise>archive</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<td>cncMachine Model</td>
								<td>${cncProgram.getCncMachine().getModel()}</td>
							</tr>
							<tr>
								<td>CNC program:</td>
								<td>
									<!-- <td style="white-space: pre-line;">${cncProgram.getProgramText()}</td>
								<td style="white-space: pre-line;"><textarea
										name="programText" cols="60" rows="20" class="form-control"
										placeholder=${cncProgram.getProgramText()}></textarea></td> -->
								</td>
							</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</section>
		<div class="scrolling">${cncProgram.getProgramText()}</div>
	</main>
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>