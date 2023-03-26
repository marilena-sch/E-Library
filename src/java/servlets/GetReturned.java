/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
import database.tables.EditBorrowingTable;
import database.tables.EditLibrarianTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Borrowing;

/**
 *
 * @author marilena_sch
 */
public class GetReturned extends HttpServlet {

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
        EditLibrarianTable elt = new EditLibrarianTable();
        EditBooksInLibraryTable ebilt = new EditBooksInLibraryTable();
        EditBorrowingTable ebt = new EditBorrowingTable();

        try (PrintWriter out = response.getWriter()) {
            int lib_id = elt.return_Library_id(username);
            ArrayList<String> book_copies = new ArrayList<String>();
            book_copies = ebilt.return_bookcopy_ids(lib_id);
            ArrayList<Borrowing> borr = new ArrayList<Borrowing>();
            ArrayList<Borrowing> borr_final = new ArrayList<Borrowing>();

            for (int i = 0; i < book_copies.size(); i++) {
                borr = ebt.databaseToBorrowing_bookcopy(book_copies.get(i));
                for (int j = 0; j < borr.size(); j++) {
                    if (borr.get(j).getStatus().equals("returned")) {
                        borr_final.add(borr.get(j));
                    }
                }
            }
            if (borr_final.isEmpty()) {
                response.setStatus(403);
            } else {
                response.setStatus(200);
                for (int i = 0; i < borr_final.size(); i++) {
                    out.println(ebt.borrowingToJSON(borr_final.get(i)));

                }

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GetReturned.class.getName()).log(Level.SEVERE, null, ex);
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
