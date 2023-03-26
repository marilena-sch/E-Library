/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditStudentsTable;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mainClasses.Student;

/**
 *
 * @author marilena_sch
 */
public class UpdateUserInfo extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = session.getAttribute("lusername").toString();
        String field = request.getParameter("field");
        String value = request.getParameter("value");

        try {
            EditStudentsTable eut = new EditStudentsTable();
            Student su = eut.databaseUpdateStudent(username);

            if (field.equals("password")) {
                su.setPassword(value);
                eut.updateStudentPassword(username, value);
                response.setStatus(200);
//                response.getWriter().println("Success Password Update");
            } else if (field.equals("firstname")) {
                su.setFirstname(value);
                eut.updateStudentFirstname(username, value);
                response.setStatus(200);
            } else if (field.equals("lastname")) {
                su.setLastname(value);
                eut.updateStudentLastname(username, value);
                response.setStatus(200);
            } else if (field.equals("birthdate")) {
                su.setBirthdate(value);
                eut.updateStudentBirthdate(username, value);
                response.setStatus(200);
            } else if (field.equals("gender")) {
                su.setGender(value);
                eut.updateStudentGender(username, value);
                response.setStatus(200);
            } else if (field.equals("personalpage")) {
                su.setPersonalpage(value);
                eut.updateStudent(username, value);
                response.setStatus(200);
            } else if (field.equals("country")) {
                su.setCountry(value);
                eut.updateStudentCountry(username, value);
                response.setStatus(200);
            } else if (field.equals("city")) {
                su.setCity(value);
                eut.updateStudentCity(username, value);
                response.setStatus(200);
            } else if (field.equals("address")) {
                String[] addressVal = value.split(",");
                String address = addressVal[0];
                String tmp_lat = addressVal[1];
                String tmp_lon = addressVal[2];
                double lat = Double.parseDouble(tmp_lat);
                double lon = Double.parseDouble(tmp_lon);
                su.setAddress(address);
                su.setLat(lat);
                su.setLon(lon);
                eut.updateStudentAddress(username, address, lat, lon);
                response.setStatus(200);
            } else if (field.equals("telephone")) {
                su.setTelephone(value);
                eut.updateStudentTelephone(username, value);
                response.setStatus(200);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
