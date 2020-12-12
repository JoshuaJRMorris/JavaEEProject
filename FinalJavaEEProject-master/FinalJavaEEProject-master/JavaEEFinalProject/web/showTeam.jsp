<%-- 
    Document   : showTeam
    Created on : 3-Dec-2020, 6:32:08 AM
    Author     : Jacob
--%>

<%@page import="com.NBCC.Team"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<% 
    ArrayList<Team> teams = (ArrayList)session.getAttribute("Teams");
    if (teams == null || teams.isEmpty()){
        response.sendRedirect("createTeam.jsp");
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
        <h1>Teams</h1>
        <% for (index = 0; index < teams.size(); ++index) { %>
        <form method="POST" action="TeamShowServlet">
            <div>
                <input type="submit" name="submit" value="<%=teams.get(index).getName()%>">
            </div>
        </form>
        <% } %>
    </body>
</html>
<%}%>
