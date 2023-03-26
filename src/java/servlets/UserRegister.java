/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marilena_sch
 */
public class UserRegister extends HttpServlet {

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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String birthdate = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String tmp_lat = request.getParameter("lat");
        String tmp_lon = request.getParameter("lon");
        String telephone = request.getParameter("telephone");
        String personalpage = request.getParameter("personalpage");
        String user_type = request.getParameter("user_type");
        double lat = 0;
        double lon = 0;

        if (tmp_lat != null && tmp_lon != null) {
            lat = Double.parseDouble(tmp_lat);
            lon = Double.parseDouble(tmp_lon);
        }

        if (user_type.equals("student")) {
            String student_type = request.getParameter("student_type");
            String student_id = request.getParameter("student_id");
            String student_id_from_date = request.getParameter("student_id_from_date");
            String student_id_to_date = request.getParameter("student_id_to_date");
            String university = request.getParameter("university");
            String department = request.getParameter("department");

            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable elt = new EditLibrarianTable();

            try {
                if (eut.databaseToStudentCheck(username, email, student_id) != null) {
//                    response.getWriter().println("User already exists");
                    response.setStatus(409);
                }
                else if (elt.databaseToLibrarianCheck(username, email) != null) {
//                    response.getWriter().println("User already exists");
                    response.setStatus(409);
                }
                else {
                    if (lon != 0 && lat != 0) {
                        String new_user = "{\"username\":\"" + username + "\","
                                + "\"email\":\"" + email + "\","
                                + "\"password\":\"" + password + "\","
                                + "\"firstname\":\"" + firstname + "\","
                                + "\"lastname\":\"" + lastname + "\","
                                + "\"birthdate\":\"" + birthdate + "\","
                                + "\"gender\":\"" + gender + "\","
                                + "\"country\":\"" + country + "\","
                                + "\"city\":\"" + city + "\","
                                + "\"address\":\"" + address + "\","
                                + "\"student_type\":\"" + student_type + "\","
                                + "\"student_id\":\"" + student_id + "\","
                                + "\"student_id_from_date\":\"" + student_id_from_date + "\","
                                + "\"student_id_to_date\":\"" + student_id_to_date + "\","
                                + "\"university\":\"" + university + "\","
                                + "\"department\":\"" + department + "\","
                                + "\"lat\":\"" + lat + "\","
                                + "\"lon\":\"" + lon + "\","
                                + "\"telephone\":\"" + telephone + "\","
                                + "\"personalpage\":\"" + personalpage + "\"}";

                        try {
                            eut.addStudentFromJSON(new_user);
                        } catch (ClassNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
//                    response.getWriter().println("User " + username + " succesfully registered");
                    response.setStatus(200);
                }
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            String libraryname = request.getParameter("libraryname");
            String libraryinfo = request.getParameter("libraryinfo");

            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable elt = new EditLibrarianTable();

            try {
                if (eut.databaseToStudentforLibrary(username, email) != null) {
//                    response.getWriter().println("User already exists");
                    response.setStatus(409);
                }
                else if (elt.databaseToLibrarianCheck(username, email) != null) {
//                    response.getWriter().println("User already exists");
                    response.setStatus(409);
                }
                else {
                    if (lon != 0 && lat != 0) {
                    String new_user = "{\"username\":\"" + username + "\","
                            + "\"email\":\"" + email + "\","
                        + "\"password\":\"" + password + "\","
                        + "\"firstname\":\"" + firstname + "\","
                        + "\"lastname\":\"" + lastname + "\","
                        + "\"birthdate\":\"" + birthdate + "\","
                        + "\"gender\":\"" + gender + "\","
                        + "\"country\":\"" + country + "\","
                        + "\"city\":\"" + city + "\","
                        + "\"address\":\"" + address + "\","
                        + "\"libraryname\":\"" + libraryname + "\","
                        + "\"libraryinfo\":\"" + libraryinfo + "\","
                        + "\"lat\":\"" + lat + "\","
                        + "\"lon\":\"" + lon + "\","
                        + "\"telephone\":\"" + telephone + "\","
                        + "\"personalpage\":\"" + personalpage + "\"}";

                    try {
                        elt.addLibrarianFromJSON(new_user);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        }
                    }
//                    response.getWriter().println("User " + username + " succesfully registered");
                    response.setStatus(200);
                }
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
        doGet(request, response);
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
