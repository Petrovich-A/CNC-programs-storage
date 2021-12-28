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
<title>Main page</title>
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
				<li><a href="Controller?commandName=go_to_registration">Registration</a></li>
			</ul>
		</div>
	</div>
	<main>
		<div class="main">
			<!--  	<section id="news">
				<c:forEach var="news" items="${listNews}" begin="0" end="2">
					<c:url var="readLink" value="/news/read">
						<c:param name="newsId" value="${news.id}" />
					</c:url>
					<h1>
						<c:out value="${news.title}" />
					</h1>
					<h2>
						<c:out value="${news.brief}" />
					</h2>
					<a href="${readLink}">Read</a>
					<hr>
				</c:forEach>
			</section>  -->
		</div>
	</main>
	<footer>EPAM web app task | Java Web Development Training | ©
		Petrovich A.V., 2021-2022 </footer>
</body>
</html>