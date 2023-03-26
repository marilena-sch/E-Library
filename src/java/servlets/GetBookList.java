/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Book;

/**
 *
 * @author marilena_sch
 */
public class GetBookList extends HttpServlet {

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
        EditBooksTable book = new EditBooksTable();
        ArrayList<Book> books = new ArrayList<Book>();

        try (PrintWriter out = response.getWriter()) {
            books = book.databaseToBooks();
            for (int i = 0; i < books.size(); i++) {
                out.println("<table id='librarian_data' class='user_data'>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Book's Title: </th>" + "<th>" + books.get(i).getTitle() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>ISBN: </th>" + "<th>" + books.get(i).getIsbn() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Author: </th>" + "<th>" + books.get(i).getAuthors() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Genre: </th>" + "<th>" + books.get(i).getGenre() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Publication Year: </th>" + "<th>" + books.get(i).getPublicationyear() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Pages: </th>" + "<th>" + books.get(i).getPages() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>Photo: </th>" + "<th>" + books.get(i).getPhoto() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "<tr>" + "\n"
                        + "<th>URL: </th>" + "<th>" + books.get(i).getUrl() + "</th>" + "\n"
                        + "</tr>" + "\n"
                        + "</table>" + "\n");
            }
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
