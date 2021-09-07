/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.controller;

import anpdt.registration.RegistrationDAO;
import anpdt.registration.RegistrationInsertErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String isAdmin = request.getParameter("chkAdmin");
        String lastSearchValue = request.getParameter("lastSearchValue");
        boolean adminCheckbox = false;
        RegistrationInsertErrors errors = new RegistrationInsertErrors();
        HttpSession session = request.getSession(true);
        
        String url = "SearchLastnameServlet?txtSearchValue="+lastSearchValue;

        try {

            if (password.trim().length() < 6 || password.trim().length() > 20) {
                errors.setPasswordLengthErr("Password is required 6 to 20 chars"); 
                errors.setFlagUsername(username);
                session.setAttribute("UPDATE_ERROR", errors);
            } else {
                if(session.getAttribute("UPDATE_ERROR")!= null){
                session.removeAttribute("UPDATE_ERROR");
                }
                if (isAdmin != null) {
                    adminCheckbox = true;
                }
                RegistrationDAO dao = new RegistrationDAO();
                dao.updateAccount(username, password, adminCheckbox);
            }
        } catch (SQLException ex) {
            log("UpdateServlet _ SQL"+ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateServlet _ Naming"+ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
