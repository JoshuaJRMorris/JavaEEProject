<%-- 
    Document   : createJob
    Created on : 24-Nov-2020, 7:41:35 AM
    Author     : Jacob
--%>
<%@page import="com.NBCC.Team"%>
<%@page import="com.NBCC.Task"%>
<%@page import="com.NBCC.Job" %>
<%@page import="java.util.ArrayList"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Job currJob = (Job)session.getAttribute("currentJob");
    ArrayList<Task> tasks = (ArrayList)session.getAttribute("Tasks");
    ArrayList<Team> teams = (ArrayList)session.getAttribute("Teams");
    if (tasks == null){
        tasks = new ArrayList<Task>();
    }
    if (teams == null){
        teams = new ArrayList<Team>();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nbcc:Header></nbcc:Header>
        <h1>Create Job</h1>
        <% if (currJob == null) {%>
        <form action="JobServlet" method="POST">
            <table>
                <tr>
                    <td><label>Client Name</label></td>
                    <td><input type="text" id="txtClientName" name="txtClientName" value=""/></td>
                </tr>
                <tr>
                    <td><label>Description</label></td>
                    <td><input type="text" id="txtDescription" name="txtDescription" value=""/></td>
                </tr>
            </table>
            <h5>Task<h5>
            <% for (int i = 0; i < tasks.size(); ++i) { %>
            <input type="checkbox" name="<%=tasks.get(i).getName()%>" value="<%=tasks.get(i).getName()%>">
            <label for="<%=tasks.get(i).getName()%>"> <%=tasks.get(i).getName()%></label><br>
            <% } if (tasks.isEmpty()) {%> 
            <a href="createTask.jsp">Create Task</a>
            <% } %>
            <br />
            <h5>Team<h5>
            <% for (int i = 0; i < teams.size(); ++i) { %>
            <input type="radio" name="Team" value="<%=teams.get(i).getName()%>">
            <label for="<%=teams.get(i).getName()%>"> <%=teams.get(i).getName()%></label><br>
            <% } %> 
            <% if (teams.isEmpty()) {%> 
            <a href="createTeam.jsp">Create Team</a>
            <% } %>
            <br />
            <input type="submit" name="submit" value="submit">
            <% } else { %>
            <form action="JobServlet" method="POST">
            <table>
                <tr>
                    <td><label>Client Name</label></td>
                    <td><input type="text" id="txtClientName" name="txtClientName" value="<%=currJob.getClientName()%>"</td>
                </tr>
                <tr>
                    <td><label>Description</label></td>
                    <td><input type="text" id="txtDescription" name="txtDescription" value="<%=currJob.getDescription()%>"></td>
                </tr>
            </table>
            <h5>Task<h5>
            <% for (int i = 0; i < tasks.size(); ++i) { 
                if (currJob.taskIsInJob(tasks.get(i))) { %>
            <input type="checkbox" name="<%=tasks.get(i).getName()%>" value="<%=tasks.get(i).getName()%>" checked="true">
            <% } else { %>
            <input type="checkbox" name="<%=tasks.get(i).getName()%>" value="<%=tasks.get(i).getName()%>">
            <% } %>
            
            <label for="<%=tasks.get(i).getName()%>"> <%=tasks.get(i).getName()%></label><br>
            <% } if (tasks.isEmpty()) {%> 
            <a href="createTask.jsp">Create Task</a>
            <% } %>
            <br />
            <h5>Team<h5>
            <% for (int i = 0; i < teams.size(); ++i) { 
            if (teams.get(i).equals(currJob.getTeam())) { %>
            <input type="radio" name="Team" value="<%=teams.get(i).getName()%>" checked="true">
            <% } else { %>
            <input type="radio" name="Team" value="<%=teams.get(i).getName()%>">
            <% } %>
            <label for="<%=teams.get(i).getName()%>"> <%=teams.get(i).getName()%></label><br>
            <% } %> 
            <% if (teams.isEmpty()) {%> 
            <a href="createTeam.jsp">Create Team</a>
            <% } %>
            <br />
            <input type="submit" name="new" value="new">
            <input type="submit" name="update" value="update">
            <input type="submit" name="delete" value="delete"> 
            <% } %>
            <% if (!(currJob == null)){
                for (int i = 0; i < currJob.getErrors().size(); ++i) { %>
            <h5><%=currJob.getErrors().get(i) %></h5>
            <%  }
            } %>
            
        </form>
    </body>
</html>
