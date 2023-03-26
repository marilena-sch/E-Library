/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marilena_sch
 */
public class StayLoggedIn extends HttpServlet {

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
        Cookie[] cookies;
        //USERNAME
        String username = "";
        //COOKIE FLAG TO SHOW IF WE HAVE FOUND THE COOKIE WE WANT

        //REQUEST-GET COOKIES IN CURRENT DOMAIN
        cookies = request.getCookies();
        //CHECK IF THERE ARE COOKIES
        //IF THERE ARE COOKIES
        if (cookies != null) {
            System.out.println("COOKIES FOUND IN COOKIE ARRAY!");
            for (Cookie cookie : cookies) {
                //IF WE HAVE A USERNAME COOKIE
                if (cookie.getName().equals("usercookies")) {

                    System.out.println("COOKIE VALUE IS" + cookie.getValue());
                    System.out.println("USERNAME COOKIE FOUND!");
                    //USERNAME
                    username = cookie.getValue();
                    break;
                }
            }
            System.out.println("CURRENT USER IS " + username);
            try (PrintWriter out = response.getWriter()) {
                out.print("<div id=\"sidebar\">" + "\n"
                        + "<h2>Welcome back " + username + "</h2>" + "\n"
                        + "<button onclick='getUserData()'  id='logout-btn'\">See Data</button>" + "\n"
                        + "<br>"
                        + "<button onclick='getBookList()'  id='logout-btn'\">See All Books</button>" + "\n"
                        + "<br>"
                        + "<button onclick='LogoutButton()'  id='logout-btn'\">Logout</button>" + "\n"
                        + "<br>"
                        + "</div>" + "\n"
                );

            }
        } //ELSE USER ISNT LOGGED IN
        else {
            System.out.println("THERE IS NO ONE LOGGED IN!");
            try (PrintWriter out = response.getWriter()) {
                out.println("Nobody is currently logged in!");
            }
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
