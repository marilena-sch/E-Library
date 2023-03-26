/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.tables.EditBooksInLibraryTable;
import database.tables.EditBooksTable;
import database.tables.EditBorrowingTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Book;
import mainClasses.Borrowing;

/**
 *
 * @author marilena_sch
 */
public class SendNotification extends HttpServlet {

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
        String user_id = request.getParameter("user_id");
        String todate;
        EditBorrowingTable brt = new EditBorrowingTable();
        EditBooksInLibraryTable tmp = new EditBooksInLibraryTable();
        EditBooksTable book = new EditBooksTable();
        ArrayList<Borrowing> borrow = new ArrayList<Borrowing>();
        ArrayList<Book> book_list = new ArrayList<Book>();
        ArrayList<Borrowing> borr_final = new ArrayList<Borrowing>();
        int copybook_id = 0;
        String today = request.getParameter("today");
        String isbn;
        try (PrintWriter out = response.getWriter()) {
            borrow = brt.databaseToBorrowingUser(user_id);

            if (borrow != null) {
                for (int i = 0; i < borrow.size(); i++) {
                    todate = borrow.get(i).getToDate();
                    LocalDate to_date = LocalDate.parse(todate);
                    LocalDate tmp_today = LocalDate.parse(today);
//                    Date to_date = new SimpleDateFormat("yyyy-MM-dd").parse(todate);
//                    Date tmp_today = new SimpleDateFormat("yyyy-MM-dd").parse(today);
                    LocalDate newDate = to_date.minusDays(3);
//                    if(to_date.after(tmp_today)){
//
//                    }
                    if (tmp_today.compareTo(to_date) > 0) {
                        response.setStatus(403);
                    } else {
                        if (newDate.compareTo(tmp_today) <= 0) {
                            borr_final.add(borrow.get(i));
                        }
                    }
                }
            }
            if (borr_final.isEmpty()) {
                response.setStatus(403);
            } else {
                for (int i = 0; i < borr_final.size(); i++) {
                    copybook_id = borr_final.get(i).getBookcopy_id();

                    isbn = tmp.getBook_isbn(copybook_id);
                    if (book.databaseToBookISBN(isbn) == null) {
                        response.setStatus(403);
                    } else {
//                        response.setStatus(200);
                        book_list.add(book.databaseToBookISBN(isbn));
                    }
                }

            }
            if (book_list.isEmpty()) {
                response.setStatus(403);
            } else {
                response.setStatus(200);
                for (int i = 0; i < book_list.size(); i++) {
                    out.println("<h4>" + "Book Notification" + "</h4>" + "\n"
                            + "<table id='librarian_data' class='user_data'>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Book: </th>" + "<th>" + book_list.get(i).getTitle() + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "</table>" + "\n");

                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        } catch (ParseException ex) {
//            Logger.getLogger(SendNotification.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
