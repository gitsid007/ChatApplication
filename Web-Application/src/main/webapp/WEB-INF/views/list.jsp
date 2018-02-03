<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>To-Do List</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	    		rel="stylesheet">
</head>
<body>

<%@ include file="common/navbar.jspf"%>

<div class="container">
<br/>

<table class="table table-striped">

<caption><spring:message code="todo.caption"/></caption>
<thead>
<tr>
<th>Description</th>
<th>Target Date</th>
<th>Is Completed?</th>
<th></th>
</tr>
</thead>

<tbody>

<c:forEach items="${todos}" var="todo">
<tr>
<td>${todo.desc}</td>
<td><fmt:formatDate pattern="dd/MM/yyyy"
									value="${todo.targetDate}" /></td>
<td>${todo.done}</td>
<td>
<a href="/updatelist?id=${todo.id}" class="btn btn-success">Update</a></a>
<a href="/deletelist?id=${todo.id}" class="btn btn-danger">Delete</a></a>
</td>
</tr>
</c:forEach>


</tbody>
</table>

<div>
<a class="btn btn-success" href="/addlist">Add</a>
</div>

</div>

<%@ include file ="common/footer.jspf" %>
</body>
</html>