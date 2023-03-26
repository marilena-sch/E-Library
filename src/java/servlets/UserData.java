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
import javax.servlet.ServletException;
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
public class UserData extends HttpServlet {

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

        String data = "";

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String username = session.getAttribute("lusername").toString();
            String password = session.getAttribute("lpassword").toString();

            EditStudentsTable eut = new EditStudentsTable();
            Student su = eut.databaseToStudent(username, password);
            EditLibrarianTable elt = new EditLibrarianTable();
            Librarian li = elt.databaseToLibrarian(username, password);
            if (su == null) {
                if (li == null) {
                    response.setStatus(403);
                } else {
                    response.setStatus(200);
                    out.println("<h4 id='h4' class='h4'>" + "Your Data" + "</h4>" + "\n"
                            + "<table id='librarian_data' class='user_data'>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Username: </th>" + "<th>" + li.getUsername() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Email: </th>" + "<th>" + li.getEmail() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Password: </th>" + "<th>" + li.getPassword() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Firstname: </th>" + "<th>" + li.getFirstname() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Lastname: </th>" + "<th>" + li.getLastname() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Birthdate: </th>" + "<th>" + li.getBirthdate() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Gender: </th>" + "<th>" + li.getGender() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Country: </th>" + "<th>" + li.getCountry() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>City: </th>" + "<th>" + li.getCity() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Address: </th>" + "<th>" + li.getAddress() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Lat: </th>" + "<th>" + li.getLat() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Lon: </th>" + "<th>" + li.getLon() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Telephone: </th>" + "<th>" + li.getTelephone() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Personal Page: </th>" + "<th>" + li.getPersonalpage() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Library Name: </th>" + "<th>" + li.getLibraryname() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Library Info: </th>" + "<th>" + li.getLibraryinfo() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "</table>" + "\n");
                }
            } else {
                response.setStatus(200);
                out.println("<h4 id='h4' class='h4'>" + "Your Data" + "</h4>" + "\n"
                        + "<table id='student_data' class='user_data'>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Username: </th>" + "<th>" + su.getUsername() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Email: </th>" + "<th>" + su.getEmail() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Password: </th>" + "<th>" + su.getPassword() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Firstname: </th>" + "<th>" + su.getFirstname() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Lastname: </th>" + "<th>" + su.getLastname() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Birthdate: </th>" + "<th>" + su.getBirthdate() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Gender: </th>" + "<th>" + su.getGender() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Country: </th>" + "<th>" + su.getCountry() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>City: </th>" + "<th>" + su.getCity() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Address: </th>" + "<th>" + su.getAddress() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Student type: </th>" + "<th>" + su.getStudent_type() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Student ID: </th>" + "<th>" + su.getStudent_id() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Student ID from date: </th>" + "<th>" + su.getStudent_id_from_date() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Student ID to date: </th>" + "<th>" + su.getStudent_id_to_date() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>University: </th>" + "<th>" + su.getUniversity() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Department: </th>" + "<th>" + su.getDepartment() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Lat: </th>" + "<th>" + su.getLat() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Lon: </th>" + "<th>" + su.getLon() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Telephone: </th>" + "<th>" + su.getTelephone() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Personal Page: </th>" + "<th>" + su.getPersonalpage() + "</th>" + "\n"
                        + "<tr>" + "\n"
                        + "</table>" + "\n");
            }
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
