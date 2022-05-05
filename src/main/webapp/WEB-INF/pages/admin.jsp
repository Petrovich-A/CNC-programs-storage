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
<title>Admin page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.main.navigate.users" var="users" />
<fmt:message bundle="${loc}" key="local.navigate.details" var="details" />
<fmt:message bundle="${loc}" key="local.admin.message.list_programs"
	var="list_programs" />
<fmt:message bundle="${loc}" key="local.admin.table.program_id"
	var="program_id" />
<fmt:message bundle="${loc}" key="local.admin.table.number_program"
	var="number_program" />
<fmt:message bundle="${loc}" key="local.admin.table.operation_number"
	var="operation_number" />
<fmt:message bundle="${loc}" key="local.admin.table.creation_date"
	var="creation_date" />
<fmt:message bundle="${loc}" key="local.admin.table.comment"
	var="comment" />
<fmt:message bundle="${loc}" key="local.admin.table.active" var="active" />
<fmt:message bundle="${loc}" key="local.admin.table.personnel_number"
	var="personnel_number" />
<fmt:message bundle="${loc}" key="local.admin.table.detail_id"
	var="detail_id" />
<fmt:message bundle="${loc}" key="local.admin.table.detail" var="detail" />
<fmt:message bundle="${loc}" key="local.admin.table.cnc_machine"
	var="cnc_machine" />
<fmt:message bundle="${loc}" key="local.admin.table.model_cnc_machine"
	var="model_cnc_machine" />
<fmt:message bundle="${loc}" key="local.button.update" var="update" />
<fmt:message bundle="${loc}" key="local.navigate.cnc_machines"
	var="cnc_machines" />

</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_admin_users_page">${users}</a></li>
				<li><a href="Controller?commandName=go_to_details_page">${details}</a></li>
				<li><a href="Controller?commandName=go_to_cnc_machines_page">${cnc_machines}</a></li>
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
		<section class="cncPrograms">
			<h2>${list_programs}</h2>
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
									<th>${program_id}</th>
									<th>${number_program}</th>
									<th>${operation_number}</th>
									<th>${creation_date}</th>
									<th>${comment}</th>
									<th>${active}</th>
									<th>${personnel_number}</th>
									<th>${detail_id}</th>
									<th>${detail}</th>
									<th>${cnc_machine}</th>
									<th>${model_cnc_machine}</th>
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
					<button type="submit" name="commandName"
						value="go_to_cnc_program_update">${update}</button>
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
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>