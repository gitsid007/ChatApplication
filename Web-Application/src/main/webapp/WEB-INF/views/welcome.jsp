<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>
<%@ include file="common/navbar.jspf"%>

<div class="container">
<caption><label>Welcome    ${name}<br/>
Language:</label><spring:message code="welcome.caption"/></caption><br/>
Click here to manage todo's list: <a href="/list">Manage Todo's List</a>
</div>

<%@ include file="common/footer.jspf"%>

</body>
</html>