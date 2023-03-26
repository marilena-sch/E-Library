/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
import database.tables.EditBorrowingTable;
import database.tables.EditReviewsTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marilena_sch
 */
public class LeaveReview extends HttpServlet {

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
        String reviewText = request.getParameter("reviewText");
        String reviewScore = request.getParameter("reviewScore");
        String user_id = request.getParameter("user_id");

        EditBorrowingTable book = new EditBorrowingTable();
        EditBooksInLibraryTable review_book = new EditBooksInLibraryTable();
        ArrayList<Integer> books_id = new ArrayList<Integer>();

        try {
            books_id = review_book.getBookcopy_idlist(isbn);
            int copybook_id = 0;

            for (int i = 0; i < books_id.size(); i++) {
                if (book.databaseToReturnbookCopybook_Id(books_id.get(i), user_id) == null) {
                    copybook_id = 0;
                } else {
                    copybook_id = books_id.get(i);
                    break;
                }
            }

            if (book.databaseToReturnbookCopybook_Id(copybook_id, user_id) == null) {
                response.setStatus(406);
            } else {
                if (book.databaseToReturnbookCheck(copybook_id, user_id, "successEnd") == null) {
                    response.setStatus(406);
                } else {
                    EditReviewsTable eut = new EditReviewsTable();
                    String new_review = "{\"user_id\":\"" + user_id + "\","
                            + "\"isbn\":\"" + isbn + "\","
                            + "\"reviewText\":\"" + reviewText + "\","
                            + "\"reviewScore\":\"" + reviewScore + "\"}";
                    try {
                        eut.addReviewFromJSON(new_review);
                        response.setStatus(200);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
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
