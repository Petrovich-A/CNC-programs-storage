<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,800" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/CSS/main.css"/>"/>
    <meta charset="UTF-8">
    <title>Main page</title>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="properties.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message" var="message"/>
    <fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.main.navigate.home" var="home"/>
    <fmt:message bundle="${loc}" key="local.main.navigate.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.main.navigate.logIn" var="logIn"/>
</head>
<body>
<div class="header">
    <div class="inner_header">
        <div class="logo_container">
            <h1>
                CNC <span>PROGRAMS STORAGE</span>
            </h1>
        </div>
        <ul class="navigation">
            <li><a href="Controller?commandName=go_to_main_page">${home}</a></li>
            <li><a href="Controller?commandName=go_to_registration_page">${registration}</a></li>
            <li><a href="Controller?commandName=go_to_log_in_page">${logIn}</a></li>
        </ul>
    </div>
</div>
<main>
    <div id="" class="local-buttons">
        <form class="" action="Controller" method="post">
            <input type="hidden" name="commandName" value="change_local"/> <input
                type="hidden" name="local" value="en"/> <input type="submit"
                                                               value="${en_button}" class=""/>
        </form>
        <form>
            <input type="hidden" name="commandName" value="change_local">
            <input type="hidden" name="local" value="ru"> <input
                type="submit" value="${ru_button}" class="">
        </form>
    </div>
    <p>
        <c:out value="${message}" default="test using jstl"/>
    </p>
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
<ctg:footer/>
</body>
</html>