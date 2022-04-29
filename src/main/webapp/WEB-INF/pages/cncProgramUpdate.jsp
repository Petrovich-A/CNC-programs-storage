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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>CNC program update page</title>
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
<fmt:message bundle="${loc}" key="local.main.form.number" var="number" />
<fmt:message bundle="${loc}" key="local.main.form.operation_number"
	var="operation_number" />
<fmt:message bundle="${loc}" key="local.main.form.detail" var="detail" />
<fmt:message bundle="${loc}" key="local.main.form.comment" var="comment" />
<fmt:message bundle="${loc}" key="local.main.form.cnc_machine"
	var="cnc_machine" />

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
						value="search" /> <input type="search"
						name="searchInput" placeholder="${search_placeholder}" required>
					<label class="icon"> <span class="fas fa-search"> </span></label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
		<section class="">
			<h2>CNC program update:</h2>
			<h3>${cnc_program_update_message}</h3>
			<form action="Controller" method="POST">
				<c:choose>
					<c:when test="${cncProgram == null}">
						<p class="mb-1">
							<c:out value="CNC program isn't avaliable" />
						</p>
						<hr class="mb-1">
					</c:when>
					<c:otherwise>
						<table class="">
							<tbody>
								<tr>
									<td align="right">CNC program id</td>
									<td>${cncProgram.getId()}</td>
								</tr>
								<tr>
									<td align="right" width="100">${number}</td>
									<td><input type="text" name="number" required
										pattern="[0-9,-,_]{3,20}"
										title="Program number have to contain digitals and symbols '-', '_'. e.g. 150_82002067"
										value="${cncProgram.getNumber()}" /></td>
								</tr>
								<tr>
									<td align="right">${operation_number}</td>
									<td><input type="text" name="operationNumber" required
										pattern="[0-9]{2,3}"
										title="Operation number should only contain digitals. e.g. 120"
										value="${cncProgram.getOperationNumber()}" /></td>
								</tr>
								<tr>
									<td align="right">${detail}</td>
									<td>${cncProgram.getDetail().getName()}</td>
								</tr>
								<tr>
									<td align="right">${comment}</td>
									<td><input type="text" name="comment"
										pattern="[a-z,A-Z,а-я,А-Я,0-9]{0,100}"
										value="${cncProgram.getComment()}" /></td>
								</tr>
								<tr>
									<td align="right">active</td>
									<td><select name="isActive" required>
											<option value="active">active</option>
											<option value="inactive">inactive</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">${cnc_machine}</td>
									<td>${cncProgram.getCncMachine().getModel()}</td>
								</tr>
							</tbody>
						</table>
					</c:otherwise>
				</c:choose>
				<div class="">
					<button type="submit" name="commandName" value="cnc_program_update">Update</button>
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