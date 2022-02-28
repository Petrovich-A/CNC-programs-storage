<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
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
<title>Error page</title>
</head>
<body>
	<h1>Error page</h1>
	<h3>${error_message}</h3>
	<!-- 	Request from ${pageContext.errorData.requestURI} is failed
	<br /> Servlet name: ${pageContext.errorData.servletName}
	<br /> Status code: ${pageContext.errorData.statusCode}
	<br /> Exception: ${pageContext.exception}
	<br /> Message from exception: ${pageContext.exception.message} 
 	<a href="Controller?commandName=go_to_main_page"> to main page </a>-->
</body>
</html>