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
				<li><a href="Controller?commandName=">ADD EXT</a></li>
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
									<th></th>
									<th>id</th>
									<th>number</th>
									<th>operation Number</th>
									<th>Creation Date</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="cncProgram" items="${cncProgram}">
									<tr>
										<td><input class="" type="radio" name="id"
											required="required" value="${cncProgram.getId()}"></td>
										<td>${cncProgram.getNumber()}</td>
										<td>${cncProgram.getOperationNumber()}</td>
										<td>${cncProgram.getCreationDate()}</td>
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