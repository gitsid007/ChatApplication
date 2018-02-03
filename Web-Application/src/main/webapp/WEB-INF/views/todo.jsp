<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add/Edit List</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">

</head>
<body>

<%@ include file="common/navbar.jspf"%>

<div class="container">

<h1>Add List Here</h1>

<form:form method="post" commandName="todo">
<form:hidden path="id"/>
<fieldset class="form-group">
<form:label path="desc">Remainder</form:label>
<form:input path="desc" type="text" class="form-control" required="required"></form:input>
<form:errors path="desc" cssClass="text-warning" />
</fieldset>

<fieldset class="form-group">
<form:label path="targetDate">Date</form:label>
<form:input path="targetDate" type="text" class="form-control" required="required"></form:input>
<form:errors path="targetDate" cssClass="text-warning" />
</fieldset>

<input class="btn btn-success" type="submit" value="Submit"/>
</form:form> 

</div>

<%@ include file ="common/footer.jspf" %>

</body>
</html>