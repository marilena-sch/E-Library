/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import database.tables.EditBooksInLibraryTable;
import database.tables.EditBorrowingTable;
import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Borrowing;
import mainClasses.Student;

/**
 *
 * @author theap
 */
@WebServlet(name = "CreatePDF", urlPatterns = {"/CreatePDF"})
public class CreatePDF extends HttpServlet {

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
        String username = request.getParameter("username");
        EditLibrarianTable elt = new EditLibrarianTable();
        EditBooksInLibraryTable ebilt = new EditBooksInLibraryTable();
        EditBorrowingTable ebt = new EditBorrowingTable();

        try {
            int lib_id = elt.return_Library_id(username);
            ArrayList<String> book_copies = new ArrayList<String>();
            book_copies = ebilt.return_bookcopy_ids(lib_id);
            ArrayList<Borrowing> borr = new ArrayList<Borrowing>();
            ArrayList<Borrowing> borr_final = new ArrayList<Borrowing>();
            Borrowing help = new Borrowing();

            for (int i = 0; i < book_copies.size(); i++) {
                borr = ebt.databaseToBorrowing_bookcopy(book_copies.get(i));
                for (int j = 0; j < borr.size(); j++) {
                    if (borr.get(j).getStatus().equals("returned") || borr.get(j).getStatus().equals("borrowed"))
                    borr_final.add(borr.get(j));
                }
            }
            if (borr_final.isEmpty()) {
                response.setStatus(403);
            } else {
                OutputStream out = new FileOutputStream("borrowed_returned.pdf");
                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, out);
                document.open();
                EditStudentsTable est = new EditStudentsTable();
                Student su = new Student();
                document.add(new Paragraph("Results\n\n"));
                for (int i = 0; i < borr_final.size(); i++) {
                    help = borr_final.get(i);
                    su = est.databaseToStudent_exists_user_id(help.getUser_id());
                    String firstname = su.getFirstname();
                    String lastname = su.getLastname();
                    String fromdate = help.getFromDate();
                    String todate = help.getToDate();
                    String output = "";
                    int cnt = i + 1;
                    output += "Result --> " + cnt + "\n";
                    output += "Student's Firstname:" + firstname + "\n";
                    output += "Student's Lastname:" + lastname + "\n";
                    output += "borrowing_id: " + help.getBorrowing_id() + "\n";
                    output += "bookcopy_id: " + help.getBookcopy_id() + "\n";
                    output += "user_id: " + help.getUser_id() + "\n";
                    output += "fromdate: " + fromdate + "\n";
                    output += "todate: " + todate + "\n";
                    output += "status: " + help.getStatus() + "\n\n\n";

                    document.add(new Paragraph(output));
                }
                document.close();
                response.setStatus(200);
            }

        } catch (SQLException | ClassNotFoundException | DocumentException ex) {
            Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
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
