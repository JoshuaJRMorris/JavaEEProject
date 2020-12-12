<%-- 
    Document   : showJob
    Created on : 30-Nov-2020, 9:59:49 AM
    Author     : Jacob
--%>

<%@page import="com.NBCC.Job"%>
<%@page import="java.util.ArrayList"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ArrayList<Job> jobs = (ArrayList)session.getAttribute("Jobs");
    if (jobs == null || jobs.isEmpty()){
        response.sendRedirect("createJob.jsp");
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
        <h1>Jobs</h1>
        <% for (index = 0; index < jobs.size(); ++index) { %>
        <form method="POST" action="JobShowServlet">
            <div>
                <input type="submit" name="submit" value="<%=jobs.get(index).getClientName()%>">
            </div>
        </form>
        <% } %>
    </body>
</html>
<% }%>