<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<%@ page import="by.petrovich.storage.entity.User"%>
<%@ page import="by.petrovich.storage.entity.CncProgram"%>
<%@ page import="by.petrovich.storage.entity.UserRole"%>
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
<title>Admin details page</title>
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
<fmt:message bundle="${loc}" key="local.button.update" var="update" />
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<c:if test="${sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
					<li><a href="Controller?commandName=go_to_admin_page">${admin_page}</a></li>
				</c:if>
			</div>
			<li class="search-icon">
				<form role="search" action="Controller" method="post">
					<input type="hidden" name="commandName"
						value="search" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="users">
			<h2>List of details:</h2>
			<hr>
			<h2>${admin_details_message}</h2>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${details.size() == 0 || details.size() == null}">
						<p class="">
							<c:out value="No details are avaliable" />
						</p>
						<hr class="">
					</c:when>
					<c:otherwise>
						<table class="">
							<thead>
								<tr>
									<th></th>
									<c:if
										test="${sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
										<th>id</th>
									</c:if>
									<th>name</th>
								<tr />
							</thead>
							<tbody>
								<c:forEach var="detail" items="${details}">
									<tr>
										<c:if
											test="${sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
											<td><input class="" type="radio" name="detail_id"
												required="required" value="${detail.getId()}"></td>
											<td>${detail.getId()}</td>
										</c:if>
										<td><a
											href="Controller?commandName=go_to_details_cnc_programs_page&detail_name=${detail.getName()}">${detail.getName()}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="button">
					<c:if test="${sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
						<button type="submit" name="commandName" value="go_to_detail_update_page">${update}</button>
					</c:if>
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