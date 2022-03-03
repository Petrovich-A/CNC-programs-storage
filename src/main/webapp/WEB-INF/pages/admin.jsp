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
				<li><a href="Controller?commandName=go_to_admin_users_page">USERS</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section class="cncPrograms">
			<h2>List of cncPrograms:</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when
						test="${allCncPrograms.size() == 0 || allCncPrograms.size() == null}">
						<p class="mb-1">
							<c:out value="No CNC programs are avaliable" />
						</p>
						<hr class="mb-1">
					</c:when>
					<c:otherwise>
						<table class="">
							<thead>
								<tr>
									<th>id</th>
									<th>number</th>
									<th>operation Number</th>
									<th>Creation Date</th>
									<th>Comment</th>
									<th>is active</th>
									<th>Login Personnel Number</th>
									<th>detail id</th>
									<th>detail name</th>
									<th>cncMachine Model</th>
									<th>cncMachine Code Equipment</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="cncProgram" items="${allCncPrograms}">
									<tr>
										<td><input class="" type="radio" name="id" required
											value="${cncProgram.getId()}"></td>
										<td>${cncProgram.getNumber()}</td>
										<td>${cncProgram.getOperationNumber()}</td>
										<td>${cncProgram.getCreationDate()}</td>
										<td>${cncProgram.getComment()}</td>
										<td><c:choose>
												<c:when test="${cncProgram.isActive()}">active</c:when>
												<c:otherwise>archive</c:otherwise>
											</c:choose></td>
										<td>${cncProgram.getLoginPersonnelNumber()}</td>
										<td>${cncProgram.getDetail().getId()}</td>
										<td>${cncProgram.getDetail().getName()}</td>
										<td>${cncProgram.getCncMachine().getModel()}</td>
										<td>${cncProgram.getCncMachine().getCodeEquipment()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="">
					<button type="submit" name="commandName" value="go_to_??"
						class="btn btn-big btn-primary">Update</button>
				</div>
			</form>

			<%--For displaying Previous link except for the 1st page --%>
			<c:if test="${currentPage != 1}">
				<td><a
					href="Controller?commandName=go_to_admin_page&page=${currentPage - 1}">Previous</a></td>
			</c:if>

			<!--For displaying Page numbers. The when condition does not display a link for the current page -->
			<table border="1" cellpadding="5" cellspacing="5">
				<tr>
					<c:forEach begin="1" end="${numberOfPages}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<td>${i}</td>
							</c:when>
							<c:otherwise>
								<td><a
									href="Controller?commandName=go_to_admin_page&page=${i}">${i}</a></td>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
			</table>

			<%--For displaying Next link --%>
			<c:if test="${currentPage lt numberOfPages}">
				<td><a
					href="Controller?commandName=go_to_admin_page&page=${currentPage + 1}">Next</a></td>
			</c:if>

		</section>
	</main>
	<ctg:footer />
</body>
</html>