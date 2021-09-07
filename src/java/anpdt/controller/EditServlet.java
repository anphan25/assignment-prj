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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class EditServlet extends HttpServlet {

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
        String lastname = request.getParameter("txtLastname");
        String isAdmin = request.getParameter("chkAdmin");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String url = "";
        boolean checkAdmin = false;
        boolean foundErr = false;
        RegistrationInsertErrors errors = new RegistrationInsertErrors();
        HttpSession session = request.getSession();
        try{
            if(password.trim().length() < 6 || password.trim().length() > 20){
                errors.setPasswordLengthErr("password is required 6 to 20 chars");
                foundErr = true;
            }
            if(lastname.trim().length() < 2 || lastname.trim().length() > 50){
                errors.setLastnameLengthErr("lastname is required 2 to 50 chars");
                foundErr = true;
            }
            if(isAdmin != null){
                    checkAdmin = true;
                }
            if(foundErr){
                session.setAttribute("EDIT_ERROR", errors);
                url = "editPage?"
                        +"txtUsername="+username
                        +"&txtPassword="+password
                        +"&txtLastname="+lastname
                        +"&chkAdmin="+isAdmin
                        +"&lastSearchValue="+lastSearchValue;
            }else{
                RegistrationDAO dao = new RegistrationDAO();
                dao.editAccount(username, password, lastname, checkAdmin);
                session.removeAttribute("EDIT_ERROR");
                url = "search?txtSearchValue="+lastSearchValue;
            }
        }catch(SQLException ex){
            log("EditServlet _ SQL"+ex.getMessage());
        }catch(NamingException ex){
            log("EditServlet _ Naming"+ex.getMessage());
        }finally{
            response.sendRedirect(url);
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
