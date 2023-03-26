/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
import database.tables.EditBorrowingTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.BookInLibrary;

/**
 *
 * @author marilena_sch
 */
public class SelectLibrary extends HttpServlet {

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
        String library_id = request.getParameter("library_id");
        String user_id = request.getParameter("user_id");
        String status = "requested";
        String fromdate = request.getParameter("fromdate");
        String todate = request.getParameter("todate");

        int id = Integer.parseInt(library_id);
//        int u_id = Integer.parseInt(user_id);
        ArrayList<BookInLibrary> books = new ArrayList<BookInLibrary>();
        EditBooksInLibraryTable selected_book = new EditBooksInLibraryTable();

        try {
            books = selected_book.databaseToBookInLibraryBorrow(id, isbn);
            EditBorrowingTable eut = new EditBorrowingTable();

            for (int i = 0; i < books.size(); i++) {
                books.get(i).setAvailable("false");
                selected_book.updateBookInLibraryAvailability(id, "false", isbn);

                String new_borrow = "{\"bookcopy_id\":\"" + books.get(i).getBookcopy_id() + "\","
                        + "\"user_id\":\"" + user_id + "\","
                        + "\"fromDate\":\"" + fromdate + "\","
                        + "\"toDate\":\"" + todate + "\","
                        + "\"status\":\"" + status + "\"}";
                try {
                    eut.addBorrowingFromJSON(new_borrow);
                    response.setStatus(200);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
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
