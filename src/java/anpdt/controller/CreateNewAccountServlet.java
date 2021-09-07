/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.controller;

import anpdt.registration.RegistrationDAO;
import anpdt.registration.RegistrationDTO;
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
import sun.security.pkcs11.wrapper.Functions;

/**
 *
 * @author ASUS
 */
public class CreateNewAccountServlet extends HttpServlet {

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
        String confirm = request.getParameter("txtConfirm");
        String lastname = request.getParameter("txtLastname");

        ServletContext context = request.getServletContext();
        Map<String,String> roadmap = (Map<String,String>) context.getAttribute("ROADMAP");
        RegistrationInsertErrors errors = new RegistrationInsertErrors();
        boolean foundErr = false;
        String url = roadmap.get("createNewAccountPage");
        try{
            if(username.trim().length() < 6 || username.trim().length() > 12){
                foundErr = true;
                errors.setUsernameLengthErr("Username is required 6 to 12 chars");
            }
            if(password.trim().length() < 6 || password.trim().length() > 20){
                foundErr = true;
                errors.setPasswordLengthErr("Password is required 6 to 20 chars");
            }else if(!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMathced("Cofirm must be matched");
            }
            if(lastname.trim().length() < 2 || lastname.trim().length() > 50){
                foundErr = true;
                errors.setLastnameLengthErr("Lastname is reuired 2 to 50 chars");
            }
            if(foundErr){
                request.setAttribute("INSERT_ERROR", errors);
            }else{
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, lastname, false);
                boolean result = dao.createAccount(dto);
                if(result){
                    url = roadmap.get("loginPage");
                }
            }
        }catch(SQLException ex){
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL "+ex.getMessage());
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is existed !!!");
                request.setAttribute("INSERT_ERROR", errors);
            }
        }catch(NamingException ex){
            log("CreateNewAccountServlet _ Naming "+ex.getMessage());
        }finally{
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
