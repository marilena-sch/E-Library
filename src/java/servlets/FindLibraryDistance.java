/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import database.tables.EditBooksInLibraryTable;
import database.tables.EditLibrarianTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.BookInLibrary;
import mainClasses.Librarian;

/**
 *
 * @author marilena_sch
 */
public class FindLibraryDistance extends HttpServlet {

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
        String isbn = request.getParameter("isbn");
        int library_id;

        ArrayList<BookInLibrary> books = new ArrayList<BookInLibrary>();
        EditBooksInLibraryTable available_books = new EditBooksInLibraryTable();
        EditLibrarianTable elt = new EditLibrarianTable();
        ArrayList<Librarian> libraries = new ArrayList<Librarian>();
        Librarian li = new Librarian();

        try (PrintWriter out = response.getWriter()) {
            books = available_books.databaseToBookInLibraryAvailable(isbn, "true");
            for (int i = 0; i < books.size(); i++) {
                library_id = books.get(i).getLibrary_id();
                li = elt.databaseToLibraryId(library_id);
                libraries.add(li);
            }
//            for (int i = 0; i < books.size(); i++) {
//                out.println("<table id='librarian_data' class='user_data'>" + "\n"
//                        + "<tr>" + "\n"
//                        + "<th>Library's Name: </th>" + "<th>" + libraries.get(i).getLibraryname() + "</th>" + "\n"
//                        + "</tr>" + "\n"
//                        + "<tr>" + "\n"
//                        + "<th>Library's Name: </th>" + "<th>" + libraries.get(i).getLibraryinfo() + "</th>" + "\n"
//                        + "</tr>" + "\n"
//                        + "</table>" + "\n");
//            }
            Gson gson = new Gson();
            JsonArray jsonDoc = gson.toJsonTree(libraries).getAsJsonArray();
            response.getWriter().write(jsonDoc.toString());

            response.setStatus(200);
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
