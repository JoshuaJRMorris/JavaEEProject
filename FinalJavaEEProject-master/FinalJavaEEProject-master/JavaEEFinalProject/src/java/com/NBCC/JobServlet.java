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
public class JobServlet extends HttpServlet {

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
            out.println("<title>Servlet JobServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JobServlet at " + request.getContextPath() + "</h1>");
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
            ArrayList<Job> jobs = (ArrayList<Job>)session.getAttribute("Jobs");
            ArrayList<Task> tasks = (ArrayList<Task>)session.getAttribute("Tasks");
            ArrayList<Team> teams = (ArrayList<Team>)session.getAttribute("Teams");
            Job curr = (Job)session.getAttribute("currentJob");
            
         if (!(request.getParameter("new") == null)) {
            session.setAttribute("currentJob", null);
            RequestDispatcher rd = request.getRequestDispatcher("createJob.jsp");
            rd.forward(request, response);
        }
        
        if (!(request.getParameter("delete") == null)){
            int index = Utility.findJob(jobs, curr);
            jobs.remove(index);
            //remove from sql here
            session.setAttribute("Jobs", jobs);
            session.setAttribute("currentJob", null);
            if (jobs.isEmpty()){
           RequestDispatcher rd = request.getRequestDispatcher("createJob.jsp");
            rd.forward(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("showJob.jsp");
            rd.forward(request, response);
        }
        if (!(request.getParameter("update") == null)){
            Job tmp_Job = new Job();
            
            tmp_Job.setClientName(request.getParameter("txtClientName"));
            tmp_Job.setDescription(request.getParameter("txtDescirption"));
            
            for (int i = 0; i < tasks.size(); ++i){
                if (!(request.getParameter(tasks.get(i).getName()) == null)){
                    tmp_Job.addTask(tasks.get(i));
                }
            }
            if (!(request.getParameter("Team") == null)){
                for (Team tmp_Curr : teams){
                    if (tmp_Curr.getName().equals(request.getParameter("Team"))){
                        tmp_Job.setTeam(tmp_Curr);
                        break;
                    }
                }
            }
            
            if (tmp_Job.validCheck()){
               if (!jobs.isEmpty()){
               int index = Utility.findJob(jobs, curr);
               jobs.set(index, tmp_Job);
               }else{
                   jobs.add(curr);
               }
               RequestDispatcher rd = request.getRequestDispatcher("showJob.jsp");
               rd.forward(request, response);
               
            }else {
                session.setAttribute("currentJob", tmp_Job);
            }
                RequestDispatcher rd = request.getRequestDispatcher("createJob.jsp");
                rd.forward(request, response);
        }
            
        if (!(request.getParameter("submit") == null)) {
            
            if (jobs == null){
                jobs = new ArrayList<Job>();
                session.setAttribute("Jobs", jobs);
            }
            session.setAttribute("currentJob", null);
            Job job = new Job();
            
            job.setClientName(request.getParameter("txtClientName"));
            job.setDescription(request.getParameter("txtDescription"));
            
            for (int i = 0; i < tasks.size(); ++i){
                if (!(request.getParameter(tasks.get(i).getName()) == null)){
                    job.addTask(tasks.get(i));
                }
            }
            if (!(request.getParameter("Team") == null)){
                for (Team tmp_Curr : teams){
                    if (tmp_Curr.getName().equals(request.getParameter("Team"))){
                        job.setTeam(tmp_Curr);
                        break;
                    }
                }
            }
            if (job.validCheck()){
                //SQL 
                sqlRepo.InsertToJob(job);
                //getID from SQL
                jobs.add(job);
                session.setAttribute("Jobs", jobs);
            }else {
                session.setAttribute("currentJob", job);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("createJob.jsp");
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
