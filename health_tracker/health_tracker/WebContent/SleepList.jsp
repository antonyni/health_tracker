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
        <table border="1" cellpadding="6">
            <caption><h2>List of Sleeps</h2></caption>
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Mood</th>
                <th>Rest</th>
                <th>Hours</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="sleep" items="${listSleep}">
                <tr>
                    <td><c:out value="${sleep.id}" /></td>
                    <td><c:out value="${sleep.date}" /></td>
                    <td><c:out value="${sleep.mood}" /></td>
                    <td><c:out value="${sleep.rest}" /></td>
                     <td><c:out value="${sleep.how_long_hours}" /></td>
                    <td>
                    	<a href="editSleep?id=<c:out value='${sleep.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="deleteSleep?id=<c:out value='${sleep.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
