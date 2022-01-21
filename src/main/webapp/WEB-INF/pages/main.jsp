<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="60">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	href="<c:url value="/CSS/main.css"/>" />
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
<fmt:message bundle="${loc}" key="local.main.navigate.logIn" var="logIn" />
</head>
<body>
	<nav>
		<ul>
			<li class="logo">
				<h1>
					CNC<span>PROGRAMS STORAGE</span>
				</h1>
			</li>
			<div class="navigation">
				<li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
				<li><a href="Controller?commandName=go_to_registration_page">${registration}</a></li>
				<li><a href="Controller?commandName=go_to_log_in_page">${logIn}</a></li>
			</div>
			<li class="search">
				<form role="search" action="Controller" method="post">
					<input type="hidden" name="commandName" value="to do" /> <input
						type="search" placeholder="${search_placeholder}"> <label
						class="icon"> <span class="fa-search"> </span>
						<button>${search_button}</button>
					</label>
				</form>
			</li>
		</ul>
	</nav>
	<main>
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
		<p>
			<c:out value="${message}" default="test using jstl" />
		</p>
		<h1>Main</h1>
		<div class="program_input">
			<form action="Controller" method="post"></form>
			<input type="hidden" name="commandName" value=""
				placeholder="add programm">
			<p>number</p>
			<input type="text" name="number" required pattern="^{3,20}+$" />
			<p>operationNumber</p>
			<input type="text" name="operation" required pattern="^\\d{2,7}+$" />
			<p>fileExtension</p>
			<input type="text" name="fileExtension" required pattern="^{2,5}+$" />
			<p>comment</p>
			<input type="text" name="comment" pattern="^{100}+$" />
			<p>programText</p>
			<input type="text" name="programText" placeholder="" required
				pattern="*" />
			<textarea rows="2" name="text program" class="form-control"
				placeholder="program..."></textarea>
		</div>

		<div class="previous_program">
			<h1>name</h1>
			<h1>detail</h1>
		</div>
	</main>
	<!-- Here is the main footer that is used across all the pages of website with using customTag writing -->
	<footer>
		<div class="footer">
			<ctg:footer />
		</div>
	</footer>
</body>
</html>