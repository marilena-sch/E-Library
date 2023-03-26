/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marilena_sch
 */
public class BadCode extends HttpServlet {

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        username = AttackPrevent.PreventXSS(username);
        String email = request.getParameter("email");
        email = AttackPrevent.PreventXSS(email);
        String password = request.getParameter("password");
        password = AttackPrevent.PreventXSS(password);
        String firstname = request.getParameter("firstname");
        firstname = AttackPrevent.PreventXSS(firstname);
        String lastname = request.getParameter("lastname");
        lastname = AttackPrevent.PreventXSS(lastname);
        String birthdate = request.getParameter("birthdate");
        birthdate = AttackPrevent.PreventXSS(birthdate);
        String gender = request.getParameter("gender");
        gender = AttackPrevent.PreventXSS(gender);
        String country = request.getParameter("country");
        country = AttackPrevent.PreventXSS(country);
        String city = request.getParameter("city");
        city = AttackPrevent.PreventXSS(city);
        String address = request.getParameter("address");
        address = AttackPrevent.PreventXSS(address);
        String telephone = request.getParameter("telephone");
        telephone = AttackPrevent.PreventXSS(telephone);
        String personalpage = request.getParameter("personalpage");
        personalpage = AttackPrevent.PreventXSS(personalpage);
        String user_type = request.getParameter("user_type");

//        if (tmp_lat != null && tmp_lon != null) {
//            lat = Double.parseDouble(tmp_lat);
//            lon = Double.parseDouble(tmp_lon);
//        }

        if (user_type.equals("student")) {
            String student_type = request.getParameter("student_type");
            student_type = AttackPrevent.PreventXSS(student_type);
            String student_id = request.getParameter("student_id");
            student_id = AttackPrevent.PreventXSS(student_id);
            String student_id_from_date = request.getParameter("student_id_from_date");
            student_id_from_date = AttackPrevent.PreventXSS(student_id_from_date);
            String student_id_to_date = request.getParameter("student_id_to_date");
            student_id_to_date = AttackPrevent.PreventXSS(student_id_to_date);
            String university = request.getParameter("university");
            university = AttackPrevent.PreventXSS(university);
            String department = request.getParameter("department");
            department = AttackPrevent.PreventXSS(department);
            out.println("<h4 id='h4' class='h4'>" + "Your Data" + "</h4>" + "\n"
                    + "<table id='student_data' class='user_data'>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Username: </th>" + "<th>" + username + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Email: </th>" + "<th>" + email + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Password: </th>" + "<th>" + password + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Firstname: </th>" + "<th>" + firstname + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Lastname: </th>" + "<th>" + lastname + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Birthdate: </th>" + "<th>" + birthdate + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Gender: </th>" + "<th>" + gender + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Country: </th>" + "<th>" + country + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>City: </th>" + "<th>" + city + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Address: </th>" + "<th>" + address + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Student ID: </th>" + "<th>" + student_type + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Student ID: </th>" + "<th>" + student_id + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Student ID from date: </th>" + "<th>" + student_id_from_date + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Student ID to date: </th>" + "<th>" + student_id_to_date + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>University: </th>" + "<th>" + university + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Department: </th>" + "<th>" + department + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    //                    + "<th>Lat: </th>" + "<th>" + lat + "</th>" + "\n"
                    //                    + "</tr>" + "\n"
                    //                    + "<tr>" + "\n"
                    //                    + "<th>Lon: </th>" + "<th>" + lon + "</th>" + "\n"
                    //                    + "</tr>" + "\n"
                    //                    + "<tr>" + "\n"
                    + "<th>Telephone: </th>" + "<th>" + telephone + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Personal Page: </th>" + "<th>" + personalpage + "</th>" + "\n"
                    + "<tr>" + "\n"
                    + "</table>" + "\n");
        } else {
            String libraryname = request.getParameter("libraryname");
            libraryname = AttackPrevent.PreventXSS(libraryname);
            String libraryinfo = request.getParameter("libraryinfo");
            libraryinfo = AttackPrevent.PreventXSS(libraryinfo);

            out.println("<h4 id='h4' class='h4'>" + "Your Data" + "</h4>" + "\n"
                    + "<table id='librarian_data' class='user_data'>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Username: </th>" + "<th>" + username + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Email: </th>" + "<th>" + email + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Password: </th>" + "<th>" + password + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Firstname: </th>" + "<th>" + firstname + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Lastname: </th>" + "<th>" + lastname + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Birthdate: </th>" + "<th>" + birthdate + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Gender: </th>" + "<th>" + gender + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Country: </th>" + "<th>" + country + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>City: </th>" + "<th>" + city + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Address: </th>" + "<th>" + address + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Telephone: </th>" + "<th>" + telephone + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Personal Page: </th>" + "<th>" + personalpage + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Library Name: </th>" + "<th>" + libraryname + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Library Info: </th>" + "<th>" + libraryinfo + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "</table>" + "\n");
        }

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
