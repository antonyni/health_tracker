<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Foods Store Application</title>
</head>
<body>
	<center>
		<h1>Health Tracker Management</h1>
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
		<c:if test="${food != null}">
			<form action="updateFood" method="post">
        </c:if>
        <c:if test="${food == null}">
			<form action="insertFood" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${food != null}">
            			Edit Food
            		</c:if>
            		<c:if test="${food == null}">
            			Add New Food
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${food != null}">
        			<input type="hidden" name="id" value="<c:out value='${food.id}' />" />
        		</c:if>            
            <tr>
                <th>date: </th>
                <td>
                	<input type="text" name="date" size="45"
                			value="<c:out value='${food.date}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>healthy: </th>
                <td>
                	<input type="text" name="healthy" size="45"
                			value="<c:out value='${food.healthy}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>name: </th>
                <td>
                	<input type="text" name="name" size="45"
                			value="<c:out value='${food.name}' />"
                	/>
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value="Save" />
            	</td>
            </tr>
        </table>
        </form>
    </div>	
</body>
</html>
