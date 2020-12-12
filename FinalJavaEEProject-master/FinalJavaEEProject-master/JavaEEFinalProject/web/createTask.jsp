<%-- 
    Document   : createTask
    Created on : 18-Nov-2020, 5:15:44 AM
    Author     : Jacob
--%>

<%@page import="com.NBCC.Team"%>
<%@page import="com.NBCC.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<%
Task currTask = (Task)session.getAttribute("currentTask");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nbcc:Header></nbcc:Header>
        <% if (currTask == null) { %>
        <h1>Create Task</h1>
        <form action="TaskServlet" method="POST">
            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" id="txtName" name="txtName" value=""></td>
                </tr>
                <tr>
                    <td><label>Description</label></td>
                    <td><input type="text" id="txtDescription" name="txtDescirption" value=""></td>
                </tr>
                <tr>
                    <td><label>Length</label></td>
                    <td><input type="text" id="txtLength" name="txtLength" value=""></td>
                </tr>
            </table>
            <input type="submit" name="submit" value="submit">
        </form>
        <% } else { %>
        <h1>Task</h1>
        <form action="TaskServlet" method="POST">
            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" id="txtName" name="txtName" value="<%= currTask.getName()%>" ></td>
                </tr>
                <tr>
                    <td><label>Description</label></td>
                    <td><input type="text" id="txtDescription" name="txtDescirption" value="<%= currTask.getDescription()%>" ></td>
                </tr>
                <tr>
                    <td><label>Length</label></td>
                    <td><input type="text" id="txtLength" name="txtLength" value=<%= currTask.getLength() %> ></td>
                </tr>
            </table>
            <input type="submit" name="new" value="new">
            <input type="submit" name="update" value="update">
            <input type="submit" name="delete" value="delete"> 
        </form>
        <% } %>
            <% if (!(currTask == null)){
                for (int i = 0; i < currTask.getErrors().size(); ++i) { %>
            <h5><%=currTask.getErrors().get(i) %></h5>
            <%  }
            } %>
    </body>
</html>
