<%-- 
    Document   : CreateTeam
    Created on : 16-Nov-2020, 7:12:57 AM
    Author     : Jacob
--%>

<%@page import="com.NBCC.Team"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.NBCC.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<%
Team currTeam = (Team)session.getAttribute("currentTeam");
ArrayList<Employee> employees = (ArrayList)session.getAttribute("Employees");
    if (employees == null || employees.isEmpty()){
        employees = new ArrayList<Employee>();
        response.sendRedirect("createEmployee.jsp");
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
        <%if (currTeam == null) {%>
        <h1>Create Team</h1>
        <form action="TeamServlet" method="POST">
            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" id="txtName" name="txtName" value=""/></td>
                </tr>
            </table>
            <% if (!employees.isEmpty()) { %>
            <h5>Employees<h5>
            <% for (int i = 0; i < employees.size(); ++i) {
                if (!employees.get(i).isInTeam()) { %>
            <input type="checkbox" name="<%=employees.get(i).getFullName()%>" value="<%=employees.get(i).getFullName()%>">
            <label for="<%=employees.get(i).getFullName()%>"> <%=employees.get(i).getFullName()%></label><br>
            <%  }
            }
            }%> <br />
            <input type="submit" name="submit" value="submit">
        </form>
        <%} else {%>
        <h1>Team<h1>
        <form action="TeamServlet" method="POST">
            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" id="txtName" name="txtName" value="<%= currTeam.getName() %>"/></td>
                </tr>
            </table>
            <% if (!employees.isEmpty()) { %>
            <h5>Employees<h5>
            <% for (int i = 0; i < employees.size(); ++i) { 
                if (currTeam.employeeIsInTeam(employees.get(i))) { %>
            <input type="checkbox" name="<%=employees.get(i).getFullName()%>" value="<%=employees.get(i).getFullName()%>" checked="true">
            <% } else { %>
            <input type="checkbox" name="<%=employees.get(i).getFullName()%>" value="<%=employees.get(i).getFullName()%>">
            <% } %>
            
            <label for="<%=employees.get(i).getFullName()%>"> <%=employees.get(i).getFullName()%></label><br>
            <% } 
            }%> <br />
            
            <input type="submit" name="submit" value="new">
            <input type="submit" name="delete" value="delete"> 
            <%if (!currTeam.isOnCall()) {%>
            <input type="submit" name="PlaceOnCall" value="Place on Call">
            <%}else {%>
            <input type="submit" name="TakeOffOnCall" value="Place on Call">
            <br />
            <h5>Team is On Call<h5>
            <%}%>
            
        <% } %>
        
            <% if (!(currTeam == null)){
                for (int i = 0; i < currTeam.getErrors().size(); ++i) { %>
            <h5><%=currTeam.getErrors().get(i) %></h5>
            <%}
             }%>
    </body>
</html>
