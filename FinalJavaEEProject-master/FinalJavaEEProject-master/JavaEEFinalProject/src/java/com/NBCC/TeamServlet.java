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
 */
public class TeamServlet extends HttpServlet {

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
            out.println("<title>Servlet TeamServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamServlet at " + request.getContextPath() + "</h1>");
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
        if (!(request.getParameter("PlaceOnCall") == null)){
            Team curr = (Team)session.getAttribute("currentTeam");
            ArrayList<Team> tmp_Teams = (ArrayList<Team>)session.getAttribute("Teams");
            curr.setOnCall(true);
            int index = Utility.findTeam(tmp_Teams, curr);
            tmp_Teams.set(index, curr);
            session.setAttribute("Teams", tmp_Teams);
            session.setAttribute("currentTeam", curr);
            
        }
        if (!(request.getParameter("delete") == null)){
            Team curr = (Team)session.getAttribute("currentTeam");
            ArrayList<Team> teams = (ArrayList<Team>)session.getAttribute("Teams");
            ArrayList<Employee> employees = (ArrayList<Employee>)session.getAttribute("Employees");
            int index = Utility.findTeam(teams, curr);
            for (Employee tmp : employees){
                if (curr.employeeIsInTeam(tmp)){
                    tmp.setInTeam(false);
                }
            }
            teams.remove(index);
            //remove from sql here
            session.setAttribute("Employees", employees);
            session.setAttribute("Teams", teams);
            session.setAttribute("currentTeam", null);
            if (teams.isEmpty()){
            RequestDispatcher rd = request.getRequestDispatcher("createTeam.jsp");
            rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("showTeam.jsp");
            rd.forward(request, response);
        }
         if (!(request.getParameter("TakeOffOnCall") == null)){
            Team curr = (Team)session.getAttribute("currentTeam");
            ArrayList<Team> tmp_Teams = (ArrayList<Team>)session.getAttribute("Teams");
            curr.setOnCall(false);
            int index = Utility.findTeam(tmp_Teams, curr);
            tmp_Teams.set(index, curr);
            session.setAttribute("Teams", tmp_Teams);
            session.setAttribute("currentTeam", curr);
        }
        if (!(request.getParameter("submit") == null)) {
            ArrayList<Team> tmp_Teams = (ArrayList<Team>)session.getAttribute("Teams");
            if (tmp_Teams == null){
                tmp_Teams = new ArrayList<Team>();
                session.setAttribute("Teams", tmp_Teams);
            }
            session.setAttribute("currentTeam", null);
            Team team = new Team();
            ArrayList<Employee> employees = (ArrayList<Employee>)session.getAttribute("Employees");
            team.setName(request.getParameter("txtName"));
            for (int i = 0; i < employees.size(); ++i){
                if (!(request.getParameter(employees.get(i).getFullName()) == null)){
                    team.addEmployee(employees.get(i));
                }
            }
            if (team.validCheck()){
                //SQL 
                sqlRepo.InsertToTeam(team);
                //getID from SQL
                tmp_Teams.add(team);
                session.setAttribute("Teams", tmp_Teams);
            }else {
                session.setAttribute("currentTeam", team);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("createTeam.jsp");
        rd.forward(request, response);
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
