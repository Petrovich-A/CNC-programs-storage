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
<title>Search CNC Program page</title>
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
		<h2>${search_cnc_program_message}</h2>
		<section class="CNCProgramView">
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${cncProgram != null}">
						<h3>CNC program:</h3>
						<table class="">
							<tr>
								<td>program number</td>
								<td>${cncProgram.getNumber()}</td>
							<tr />
							<tr>
								<td>operation Number</td>
								<td>${cncProgram.getOperationNumber()}</td>
							<tr />
							<tr>
								<td>program text</td>
								<td>${cncProgram.getProgramText()}</td>
							<tr />
							<tr>
								<td>Creation Date</td>
								<td>${cncProgram.getCreationDate()}</td>
							<tr />
							<tr>
								<td>Comment</td>
								<td>${cncProgram.getComment()}</td>
							<tr />
							<tr>
								<td>is active</td>
								<td><c:choose>
										<c:when test="${cncProgram.isActive()}">active</c:when>
										<c:otherwise>archive</c:otherwise>
									</c:choose></td>
							<tr />
							<tr>
								<td>Login Personnel Number</td>
								<td>${cncProgram.getLoginPersonnelNumber()}</td>
							<tr />
							<tr>
								<td>detail name</td>
								<td>${cncProgram.getDetail().getName()}</td>
							<tr />
							<tr>
								<td>cncMachine Model</td>
								<td>${cncProgram.getCncMachine().getModel()}</td>
							<tr />
						</table>
					</c:when>
				</c:choose>
			</form>
		</section>
	</main>
	<ctg:footer />
</body>
</html>