<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Health Tracker</title>
</head>
<body>
	<center>
		<h1>Health Management</h1>
        <h2>
        	<a href="newFood">Add New Food</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listFood">List All Foods</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="newExercise">Add New Exercise</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listExercise">List All Exercises</a>
        	        	&nbsp;&nbsp;&nbsp;
        	<a href="newSleep">Add New Sleep</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="listSleep">List All Sleeps</a>
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Foods</h2></caption>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Healthy</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="food" items="${listFood}">
                <tr>
                    <td><c:out value="${food.id}" /></td>
                    <td><c:out value="${food.date}" /></td>
                    <td><c:out value="${food.healthy}" /></td>
                    <td><c:out value="${food.name}" /></td>
                    <td>
                    	<a href="editFood?id=<c:out value='${food.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="deleteFood?id=<c:out value='${food.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
