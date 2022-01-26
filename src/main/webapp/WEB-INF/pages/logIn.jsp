<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<meta charset="UTF-8">
<title>Log in page</title>
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
				<li><a href="Controller?commandName=go_to_registration_page">Registration</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section id="news">
			<h1>Log in page</h1>
			<h2>${message}</h2>
			<form action="Controller" method="POST">
				<p>
					Personnel number <input type="text" name="loginPersonnelNumber"
						required pattern="^\\d{5}+$" />
				</p>
				<p>
					Password <input type="password" name="password" required
						pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
				</p>
				<div class="button">
					<input type="hidden" name="commandName" value="logIn" />
					<button type="submit" class="">submit</button>
				</div>
			</form>
		</section>
	</main>
	<ctg:footer />
</body>
</html>