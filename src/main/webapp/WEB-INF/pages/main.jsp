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
<!-- <meta http-equiv="refresh" content="60"> -->
<meta http-equiv="Content-Type" conАtent="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,800"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<meta charset="UTF-8">
<title>Main page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="properties.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.search"
	var="search_button" />
<fmt:message bundle="${loc}" key="local.search_placeholder"
	var="search_placeholder" />
<fmt:message bundle="${loc}" key="local.main.navigate.home" var="home" />
<fmt:message bundle="${loc}" key="local.main.navigate.registration"
	var="registration" />
<fmt:message bundle="${loc}" key="local.main.navigate.authorization"
	var="authorization" />
<fmt:message bundle="${loc}" key="local.main.navigate.admin_page"
	var="admin_page" />
<fmt:message bundle="${loc}" key="local.main.navigate.log_out"
	var="log_out" />
<fmt:message bundle="${loc}" key="local.main.message.to_save_program"
	var="save_program_message" />
<fmt:message bundle="${loc}" key="local.main.form.number" var="number" />
<fmt:message bundle="${loc}" key="local.main.form.operation_number"
	var="operation_number" />
<fmt:message bundle="${loc}" key="local.main.form.program_text"
	var="program_text" />
<fmt:message bundle="${loc}" key="local.main.form.comment" var="comment" />
<fmt:message bundle="${loc}" key="local.main.form.detail" var="detail" />
<fmt:message bundle="${loc}" key="local.main.form.cnc_machine"
	var="cnc_machine" />
<fmt:message bundle="${loc}" key="local.main.form.code_equipment"
	var="code_equipment" />
<fmt:message bundle="${loc}" key="local.main.button.save" var="save" />
<fmt:message bundle="${loc}" key="local.main.button.reset" var="reset" />
<fmt:message bundle="${loc}" key="local.main.message.program_list"
	var="program_list" />
<fmt:message bundle="${loc}" key="local.main.table.personnel_number"
	var="personnel_number" />
<fmt:message bundle="${loc}" key="local.main.table.creation_date"
	var="creation_date" />
<fmt:message bundle="${loc}" key="local.main.table.cnc_machine_model"
	var="cnc_machine_model" />
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_registration_page">${registration}</a></li>
				<c:if
					test="${sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
					<li><a href="Controller?commandName=go_to_admin_page">${admin_page}</a></li>
				</c:if>
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<li><a
							href="Controller?commandName=go_to_user_info&loginPersonnelNumber=${user.getLoginPersonnelNumber()}">${user.getLoginPersonnelNumber()}</a></li>
						<li><a href="Controller?commandName=log_out">${log_out}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="Controller?commandName=go_to_authorization_page">${authorization}</a></li>
					</c:otherwise>
				</c:choose>
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
	<div class="local-buttons">
		<form action="Controller" method="post">
			<input type="hidden" name="commandName" value="change_local" /> <input
				type="hidden" name="local" value="en" /> <input type="submit"
				value="${en_button}" class="" />
		</form>
		<form>
			<input type="hidden" name="commandName" value="change_local">
			<input type="hidden" name="local" value="ru"> <input
				type="submit" value="${ru_button}" class="">
		</form>
	</div>
	<main>
		<div class="programInput">
			<h2>${to_save_program}</h2>
			<hr>
			<h2>${main_message}</h2>
			<hr>
			<h2>${save_program_message}</h2>
			<form action="Controller" method="post">
				<table class="programInputTable" border="0" cellspacing="0"
					cellpadding="4">
					<tr>
						<td align="right" width="100">${number}</td>
						<td><input type="text" name="number" required
							pattern="[0-9]{3,20}"
							title="Program number should only contain digitals. e.g. 24500411" /></td>
					</tr>
					<tr>
						<td align="right">${operation_number}</td>
						<td><input type="text" name="operationNumber" required
							pattern="[0-9]{2,3}"
							title="Operation number should only contain digitals. e.g. 120" /></td>
					</tr>
					<tr>
						<td align="right">${detail}</td>
						<td><input type="text" name="detail" required
							pattern="[0-9,A-Z,-]{3,20}"
							title="Detail number should contain digitals, uppercase letters and symbol '-'. e.g. 7555H-1712412" /></td>
					</tr>
					<tr>
						<td align="right">${program_text}</td>
						<td><textarea name="programText" cols="60" rows="20" required
								class="form-control" placeholder="program text..."></textarea></td>
					</tr>
					<tr>
						<td align="right">${comment}</td>
						<td><input type="text" name="comment"
							pattern="[a-z,A-Z,а-я,А-Я,0-9]{0,100}" /></td>
					</tr>
					<tr>
						<td align="right">${cnc_machine}</td>
						<td><input type="text" name="cncMachine" required
							pattern="[a-z,A-Z,а-я,А-Я,0-9,-]{2,20}"
							title="CNC machine number should contain digitals, uppercase/lowcase letters and symbol '-'. e.g. LM70-AT" /></td>
					</tr>
					<tr>
						<td align="right">${code_equipment}</td>
						<td><input type="text" name="codeEquipment" required
							pattern="[0-9]{2,5}"
							title="Code equipment should only contain digitals. e.g. 117" /></td>
					</tr>
				</table>
				<div class="button">
					<c:if
						test="${sessionScope.user.getUserRole() eq UserRole.USER || sessionScope.user.getUserRole() eq UserRole.ADMINISTRATOR}">
						<input type="hidden" name="commandName" value="cnc_program_save" />
						<button type="submit" class="submit_button">${save}</button>
						<button type="reset" value="Reset">${reset}</button>
					</c:if>
				</div>
			</form>
		</div>

		<!-- List of programs for pagination -->
		<section class="listUsers">
			<h2>${program_list}</h2>
			<hr>
			<c:choose>
				<c:when
					test="${allCncPrograms.size() == 0 || allCncPrograms.size() == null}">
					<p>
						<c:out value="No CNC programs are avaliable" />
					</p>
					<hr>
				</c:when>
				<c:otherwise>
					<table class="table">
						<col width="50">
						<col width="30">
						<col width="50">
						<thead>
							<tr>
								<th>${detail}</th>
								<th>${number}</th>
								<th>${cnc_machine_model}</th>
								<th>${personnel_number}</th>
								<th>${creation_date}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cncProgram" items="${allCncPrograms}" begin="0"
								end="6">
								<tr>
									<td>${cncProgram.getDetail().getName()}</td>
									<td>${cncProgram.getNumber()}</td>
									<td>${cncProgram.getCncMachine().getModel()}</td>
									<td>${cncProgram.getLoginPersonnelNumber()}</td>
									<td>${cncProgram.getCreationDate()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</section>
	</main>
	<!-- Here is the main footer that is used across all the pages of website with using customTag writing -->
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>