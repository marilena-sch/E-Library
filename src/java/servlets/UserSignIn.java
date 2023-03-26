/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.Librarian;
import mainClasses.Student;

/**
 *
 * @author marilena_sch
 */
public class UserSignIn extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        //
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
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("user_username");
        String password = request.getParameter("user_password");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            EditStudentsTable eut = new EditStudentsTable();
            Student su = eut.databaseToStudent(username, password);
            EditLibrarianTable elt = new EditLibrarianTable();
            Librarian li = elt.databaseToLibrarian(username, password);
            HttpSession session = request.getSession();

            if (su == null) {
                if (li == null) {
                    response.setStatus(403);
                } else {
                    session.setAttribute("lusername", username);
                    session.setAttribute("lpassword", password);

                    Cookie cookies = new Cookie("usercookies", username);
                    int e_time = 41506000;
                    cookies.setMaxAge(e_time);
                    response.addCookie(cookies);

                    response.getWriter().write("lib");
                    String json = elt.librarianToJSON(li);
                    out.println(json);
                    response.setStatus(200);
                }
            } else {
                session.setAttribute("lusername", username);
                session.setAttribute("lpassword", password);

                Cookie cookies = new Cookie("usercookies", username);
                int e_time = 41506000;
                cookies.setMaxAge(e_time);
                response.addCookie(cookies);

//                response.getWriter().write("stud");
                String json = eut.studentToJSON(su);
                out.println(json);
                response.setStatus(200);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSignIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserSignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
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
//        processRequest(request, response);
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
