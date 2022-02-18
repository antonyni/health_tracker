<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Sleeps Store Application</title>
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
		<c:if test="${sleep != null}">
			<form action="updateSleep" method="post">
        </c:if>
        <c:if test="${sleep == null}">
			<form action="insertSleep" method="post">
        </c:if>
        <table border="1" cellpadding="6">
            <caption>
            	<h2>
            		<c:if test="${sleep != null}">
            			Edit Sleep
            		</c:if>
            		<c:if test="${sleep == null}">
            			Add New Sleep
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${sleep != null}">
        			<input type="hidden" name="id" value="<c:out value='${sleep.id}' />" />
        		</c:if>            
            <tr>
                <th>date: </th>
                <td>
                	<input type="text" name="date" size="45"
                			value="<c:out value='${sleep.date}' />"
                		/>
                </td>
            </tr>
            <tr>
                <th>mood: </th>
                <td>
                	<input type="text" name="mood" size="45"
                			value="<c:out value='${sleep.mood}' />"
                	/>
                </td>
            </tr>
            <tr>
                <th>rest: </th>
                <td>
                	<input type="text" name="rest" size="45"
                			value="<c:out value='${sleep.rest}' />"
                	/>
                </td>
            </tr>
            <tr>
                            <th>hours: </th>
                <td>
                	<input type="text" name="hours" size="45"
                			value="<c:out value='${sleep.how_long_hours}' />"
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
