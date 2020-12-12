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
public class TaskServlet extends HttpServlet {

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
            out.println("<title>Servlet TaskServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaskServlet at " + request.getContextPath() + "</h1>");
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
        ArrayList<Task> tasks = (ArrayList<Task>)session.getAttribute("Tasks");
        Task curr = (Task)session.getAttribute("currentTask");
        
        if (!(request.getParameter("new") == null)) {
            session.setAttribute("currentTask", null);
            RequestDispatcher rd = request.getRequestDispatcher("createTask.jsp");
            rd.forward(request, response);
        }
        
        if (!(request.getParameter("delete") == null)){
            int index = Utility.findTask(tasks, curr);
            tasks.remove(index);
            //remove from sql here
            session.setAttribute("Tasks", tasks);
            session.setAttribute("currentTask", null);
            if (tasks.isEmpty()){
           RequestDispatcher rd = request.getRequestDispatcher("createTask.jsp");
            rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("showTask.jsp");
            rd.forward(request, response);
        }
        if (!(request.getParameter("update") == null)){
            Task tmp_Task = new Task();
            
            tmp_Task.setName(request.getParameter("txtName"));
            tmp_Task.setDescription(request.getParameter("txtDescirption"));
            tmp_Task.setLength(request.getParameter("txtLength"));
            
            if (tmp_Task.validCheck() && !tasks.isEmpty()){
               int index = Utility.findTask(tasks, curr);
               tasks.set(index, tmp_Task);
               RequestDispatcher rd = request.getRequestDispatcher("showTask.jsp");
               rd.forward(request, response);
               
            }else {
                session.setAttribute("currentTask", tmp_Task);
            }
                RequestDispatcher rd = request.getRequestDispatcher("createTask.jsp");
                rd.forward(request, response);
        }
        if (!(request.getParameter("submit") == null)) {
            
            if (tasks == null){
                tasks = new ArrayList<Task>();
                session.setAttribute("Tasks", tasks);
            }
            session.setAttribute("currentTask", null);
            Task task = new Task();
            
            task.setName(request.getParameter("txtName"));
            task.setDescription(request.getParameter("txtDescirption"));
            task.setLength(request.getParameter("txtLength"));
            /*
            ArrayList<Team> teams = (ArrayList<Team>)session.getAttribute("Teams");
            for (int i = 0; i < teams.size(); ++i){
                if (!(request.getParameter(teams.get(i).getName()) == null)){
                    task.addTeam(teams.get(i));
                }
            }
            */
            if (task.validCheck()){
                //SQL 
                sqlRepo.InsertToTask(task);
                //getID from SQL
                tasks.add(task);
                session.setAttribute("Tasks", tasks);
            }else {
                session.setAttribute("currentTask", task);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("showTask.jsp");
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
