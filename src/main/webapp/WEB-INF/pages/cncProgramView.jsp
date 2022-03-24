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
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
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
						<thead>
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
								<td>login</td>
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
								<td>CNC program</td>
								<td style="white-space: pre-line;">${cncProgram.getProgramText()}</td>
							</tr>
						</thead>
					</table>
				</c:otherwise>
			</c:choose>
			<div class="button">
				<button type="submit" name="commandName" value="go_to_update!!">update
					???</button>
			</div>
		</section>
	</main>
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>