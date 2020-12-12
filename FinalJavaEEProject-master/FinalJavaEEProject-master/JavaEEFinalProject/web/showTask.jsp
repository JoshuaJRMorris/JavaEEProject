<%-- 
    Document   : showTask
    Created on : 18-Nov-2020, 11:02:00 AM
    Author     : Jacob
--%>

<%@page import="com.NBCC.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<% 
    ArrayList<Task> tasks = (ArrayList)session.getAttribute("Tasks");
    if (tasks == null || tasks.isEmpty()){
        response.sendRedirect("createTask.jsp");
    }
    else {
        int index = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nbcc:Header></nbcc:Header>
        <h1>Tasks</h1>
        <% for (index = 0; index < tasks.size(); ++index) { %>
        <form method="POST" action="TaskShowServlet">
            <div>
                <input type="submit" name="submit" value="<%=tasks.get(index).getName()%>">
            </div>
        </form>
        <% } %>
    </body>
</html>
<% }%>