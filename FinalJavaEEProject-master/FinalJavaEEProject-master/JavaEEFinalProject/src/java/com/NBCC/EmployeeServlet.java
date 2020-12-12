/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.NBCC;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sql.ISqlRepo;
import sql.SqlFactory;


/**
 *
 * @author Jacob
 * Modified
 * 26-Nov-2020
 * Josh Morris
 */
public class EmployeeServlet extends HttpServlet {

    private ISqlRepo sqlRepo;
    
    @Override
    public void init() throws ServletException{
        this.sqlRepo = SqlFactory.createRepo();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Employee> tmp_Employees = (ArrayList<Employee>)session.getAttribute("Employees");
        ArrayList<Skill> skills = (ArrayList<Skill>)session.getAttribute("skills");
        Employee curr = (Employee)session.getAttribute("currentEmployee");
        
         
        if (!(request.getParameter("delete") == null)){
            int index = Utility.findEmployee(tmp_Employees, curr);
            tmp_Employees.remove(index);
            //remove from sql here
            session.setAttribute("Employees", tmp_Employees);
            session.setAttribute("currentEmployee", null);
            if (tmp_Employees.isEmpty()){
            RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
            rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("showEmployee.jsp");
            rd.forward(request, response);
        }
        if (!(request.getParameter("update") == null)){
            
            Employee tmp_Employee = new Employee();
            tmp_Employee.setFirstName(request.getParameter("txtFirstName"));
            tmp_Employee.setLastName(request.getParameter("txtLastName"));
            tmp_Employee.setSIN(request.getParameter("txtSin"));
            
            if (!request.getParameter("txtPayRate").isEmpty())
            tmp_Employee.setPayRate(Double.valueOf(request.getParameter("txtPayRate")));
            
            
            
            for (int i = 0; i < skills.size(); ++i){
                if (!(request.getParameter(skills.get(i).getName()) == null)){
                    tmp_Employee.addSkill(skills.get(i));
                }
            }
            
            if (tmp_Employee.validCheck() && !tmp_Employees.isEmpty()){
               if (!tmp_Employees.isEmpty()){
               int index = Utility.findEmployee(tmp_Employees, curr);
               tmp_Employees.set(index, tmp_Employee);
               }else{
                   tmp_Employees.add(curr);
               }
               session.setAttribute("Employees", tmp_Employees);
               RequestDispatcher rd = request.getRequestDispatcher("showEmployee.jsp");
               rd.forward(request, response);
            }else {
                session.setAttribute("currentEmployee", tmp_Employee);
            }
                RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
                rd.forward(request, response);
        }
        if (!(request.getParameter("submit") == null)) {
            
            if (tmp_Employees == null){
                tmp_Employees = new ArrayList<Employee>();
                session.setAttribute("Employees", tmp_Employees);
            }
            session.setAttribute("currentEmployee", null);
            Employee employee = new Employee();
            employee.setFirstName(request.getParameter("txtFirstName"));
            employee.setLastName(request.getParameter("txtLastName"));
            employee.setSIN(request.getParameter("txtSin"));
            for (int i = 0; i < skills.size(); ++i){
                if (!(request.getParameter(skills.get(i).getName()) == null)){
                    employee.addSkill(skills.get(i));
                }
            }
            if (!request.getParameter("txtPayRate").isEmpty())
            employee.setPayRate(Double.valueOf(request.getParameter("txtPayRate")));
            
            
            

            RequestDispatcher rd;
            if (employee.validCheck()){
                //SQL //insert the employee into the database
                
                //sqlRepo.InsertToEmployee(employee);
                //getID from SQL
                tmp_Employees.add(employee);
                session.setAttribute("Employees", tmp_Employees);
                rd = request.getRequestDispatcher("showEmployee.jsp");
            }
            else {
                rd = request.getRequestDispatcher("createEmployee.jsp");
                session.setAttribute("currentEmployee", employee);
            }
            rd.forward(request, response);
            
        }else if (!(request.getParameter("new") == null)) {
            session.setAttribute("currentEmployee", null);
            RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
            rd.forward(request, response);
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
