<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/CSS/style.css"/>" />
<head>
<meta charset="UTF-8">
<title>authorization page</title>
</head>
<body>
	<nav>
		<ul>
			<li class="logo">CNC <span>PROGRAMS STORAGE</span></li>
			<div class="items">
				<li><a href="Controller?commandName=go_to_main_page">HOME</a></li>
				<li><a href="Controller?commandName=go_to_registration_page">REGISTRATION</a></li>
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
		<section id="news">
			<h1>Please, enter password and login for authorization</h1>
			<h2>${authorization_message}</h2>
			<form action="Controller" method="post">
				<table class="authorizationInputTable">
					<tr>
						<td align="right">personnel number</td>
						<td><input type="text" name="loginPersonnelNumber" required
							pattern="[0-9]{5}" placeholder="Input your loginPersonnelNumber"
							title="loginPersonnelNumber should only contain digitals. e.g. 43510" />
						</td>
					</tr>
					<tr>
						<td align="right">password</td>
						<td><input type="password" id="password-input"
							name="password" required
							pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$"
							placeholder="Input your current password"
							title="password should contain at least once lowcase and uppercase letters, at least once specail symbol, at least once digital." />
						</td>
					</tr>
				</table>

				<div class="button">
					<input type="hidden" name="commandName" value="authorization" />
					<button type="submit">submit</button>
					<button type="reset">reset</button>
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