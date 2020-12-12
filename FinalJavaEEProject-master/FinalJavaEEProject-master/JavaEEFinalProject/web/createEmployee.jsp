<%-- 
    Document   : createEmployee
    Created on : 11-Nov-2020, 8:54:44 AM
    Author     : Jacob

    Modified
    26-Nov-2020
    Josh Morris
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.NBCC.Utility"%>
<%@page import="com.NBCC.Employee"%>
<%@page import="com.NBCC.Skill"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="nbcc" %>
<%
    //temp load skills should be replaced by sql
    session.setAttribute("skills", Utility.loadTmpSkills());
    ArrayList<Skill> skills = (ArrayList<Skill>)session.getAttribute("skills");
    Employee currentEmployee = (Employee)session.getAttribute("currentEmployee");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nbcc:Header></nbcc:Header>
        <% if (currentEmployee == null) { %>
        <h1>Create Employee</h1>
        <form action="EmployeeCreater" method="POST">
            <table>
                <tr>
                    <th>Name</th>
                </tr>
                <tr>
                    <td><label>First Name</label></td>
                    <td><input type="text" id="txtFirstName" name="txtFirstName" value=""/></td>
                </tr>
                <tr>
                    <td><label>Last Name</label></td>
                    <td><input type="text" id="txtLastName" name="txtLastName" value=""/></td>
                </tr>
                <tr>
                    <td><label>SIN</label></td>
                    <td><input type="text" id="txtSIN" name="txtSin" value="" /></td>
                </tr>
                <tr>
                    <td><label>Pay Rate</label></td>
                    <td><input type="text" id="txtPayRate" name="txtPayRate" value="" /></td>
                </tr>
            </table>
             <% if (!skills.isEmpty()) { %>
            <h5>Skills<h5>
            <% for (int i = 0; i < skills.size(); ++i) { %>
            <input type="checkbox" name="<%=skills.get(i).getName()%>" value="<%=skills.get(i).getName()%>">
            <label for="<%=skills.get(i).getName()%>"> <%=skills.get(i).getName()%></label><br>
            <%
            }
            }%> <br />
            <input type="submit" name="submit" value="submit">   
           <!-- <button name="btnSubmit" class="btn btn-primary">Submit</button> -->
        </form>
        
        <% }  else { %>
        <% if (currentEmployee.getErrors().isEmpty()) { %>
        <h1><%= currentEmployee.getFullName() %></h1>       
        <% } else { %>
        <h1>Create Employee</h1>
        <% } %>
        <form action="EmployeeCreater" method="POST">
            <table>
                <tr>
                    <th>Name</th>
                </tr>
                <tr>
                    <td><label>First Name</label></td>
                    <td><input type="text" id="txtFirstName" name="txtFirstName" value=<%= currentEmployee.getFirstName() %>></td>
                </tr>
                <tr>
                    <td><label>Last Name</label></td>
                    <td><input type="text" id="txtLastName" name="txtLastName" value=<%= currentEmployee.getLastName() %>></td>
                </tr>
                <tr>
                    <td><label>SIN</label></td>
                    <td><input type="text" id="txtSIN" name="txtSin" value=<%= currentEmployee.getSIN() %> /></td>
                </tr>
                <tr>
                    <td><label>Pay Rate</label></td>
                    <td><input type="text" id="txtPayRate" name="txtPayRate" value=<%= Double.toString(currentEmployee.getPayRate()) %> ></td>
                </tr>
                <% if (currentEmployee.getErrors().isEmpty()) { %>
                <tr>
                    <td>Date of Creation: </td>
                    <td><%= currentEmployee.getDateOfCreation().toString() %></td>
                </tr>
                <% } %>
            </table>
            <% if (!skills.isEmpty()) { %>
            <h5>Skills<h5>
            <% for (int i = 0; i < skills.size(); ++i) { 
                if (currentEmployee.hasSkill(skills.get(i))) { %>
            <input type="checkbox" name="<%=skills.get(i).getName()%>" value="<%=skills.get(i).getName()%>" checked="true">
            <% } else { %>
            <input type="checkbox" name="<%=skills.get(i).getName()%>" value="<%=skills.get(i).getName()%>">
            <% } %>
            
            <label for="<%=skills.get(i).getName()%>"> <%=skills.get(i).getName()%></label><br>
            <% } 
            }%> <br />
            
            <% if (currentEmployee.getErrors().isEmpty()) { %>            
            <input type="submit" name="new" value="new">
            <input type="submit" name="update" value="update">
            <input type="submit" name="delete" value="delete"> 
            <% } else { %>           
            <input type="submit" name="submit" value="submit">
            
            <% } %>
           <!-- <button name="btnSubmit" class="btn btn-primary">Submit</button> -->
        </form>          
        <%
            for (int i= 0; i < currentEmployee.getErrors().size(); ++i) {
        %>
            <h5><%=currentEmployee.getErrors().get(i) %></h5>
        <% 
            }
        %>       
        <% } %>       
    </body>
</html>
