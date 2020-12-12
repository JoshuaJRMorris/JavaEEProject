<%-- 
    Document   : showEmployee
    Created on : 11-Nov-2020, 8:55:12 AM
    Author     : Jacob
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.NBCC.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<% 
    ArrayList<Employee> employees = (ArrayList)session.getAttribute("Employees");
    if (employees == null || employees.isEmpty()){
        response.sendRedirect("createEmployee.jsp");
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
        <h1>Employees</h1>
        <% for (index = 0; index < employees.size(); ++index) { %>
        <form method="POST" action="EmployeeShow">
            <div>
                <input type="submit" name="submit" value="<%=employees.get(index).getFirstName()%> <%=employees.get(index).getLastName()%>">
            </div>
        </form>
        <% } %>
    </body>
</html>
<% }%>
