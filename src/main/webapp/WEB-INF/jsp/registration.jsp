<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<meta charset="UTF-8">
<title>Registration page</title>
</head>
<body>
	<div class="header">
		<div class="inner_header">
			<div class="logo_container">
				<h1>
					CNC <span>programs storage</span>
				</h1>
			</div>
			<ul class="navigation">
				<li><a href="Controller?commandName=go_to_main">HOME</a></li>
			</ul>
		</div>
	</div>
	<main>
		<section id="news">
			<h1>Please registr</h1>
			<form action="Controller" method="POST">
				<input type="text" name="name" placeholder="login_personnel_number"
					required pattern="^[a-zA-Z0-9]\\w{5,30}+$" /> <br /> <input
					type="password" name="password" class="form-control"
					placeholder="password" required
					pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,40}+$" />
				<br /> <input type="email" name="eMail" class="form-control"
					placeholder="${user_email}" required pattern="^\S+@\S+\.\S+$" /> <br />
				<h2>Title</h2>
				<textarea name="title" rows="2" cols="60" required form="txtarea">${news.title}
							</textarea>
				<br />
				<h2>Brief</h2>
				<textarea name="brief" rows="3" cols="60" required form="txtarea">${news.brief}
							</textarea>
				<br />

				<div class="button">
					<input type="submit" value="Submit" class="create" />
				</div>
			</form>
		</section>
	</main>
	<footer>EPAM web app task | Java Web Development Training | ©
		Petrovich A.V., 2021-2022 </footer>
</body>
</html>