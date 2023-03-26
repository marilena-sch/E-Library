/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.JsonObject;
import database.tables.EditBooksInLibraryTable;
import database.tables.EditBooksTable;
import database.tables.EditLibrarianTable;
import database.tables.EditStudentsTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Book;
import mainClasses.BookInLibrary;
import mainClasses.Librarian;
import mainClasses.Student;

/**
 *
 * @author theap
 */
@WebServlet(name = "GetStatistics", urlPatterns = {"/GetStatistics"})
public class GetStatistics extends HttpServlet {

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
        String type = request.getParameter("type");
        JsonObject jo = new JsonObject();
        if (type.equals("bk_lb")) {
            ArrayList<BookInLibrary> blt = new ArrayList<BookInLibrary>();
            EditBooksInLibraryTable eblt = new EditBooksInLibraryTable();

            try {
                blt = eblt.databaseToBookInLibraryList();
                if (blt.isEmpty()) {
                    response.setStatus(403);
                } else {
                    PrintWriter out = response.getWriter();
                    int num_libs = blt.get(0).getLibrary_id();
                    for (int i = 0; i < blt.size(); i++) {
                        if (blt.get(i).getLibrary_id() > num_libs) {
                            num_libs = blt.get(i).getLibrary_id();
                        }
                    }
                    List books = new ArrayList();
                    for (int i = 1; i <= num_libs; i++) {
                        int cnt = 0;
                        for (int j = 0; j < blt.size(); j++) {
                            if (blt.get(j).getLibrary_id() == i) {
                                cnt++;
                            }
                        }
                        books.add(cnt);
                    }
                    ArrayList<Librarian> librarians = new ArrayList<Librarian>();
                    EditLibrarianTable ebt = new EditLibrarianTable();
                    librarians = ebt.databaseToLibrarians();
                    ArrayList<String> names = new ArrayList<String>();

                    for (int i = 1; i <= num_libs; i++) {
                        for (int j = 0; j < librarians.size(); j++) {
                            if (librarians.get(j).getLibrary_id() == i) {
                                System.out.println(librarians.get(j).getLibraryname());
                                names.add(librarians.get(j).getLibraryname());
                            }
                        }
                    }

                    for (int i = 0; i < books.size(); i++) {
                        jo.addProperty(names.get(i), books.get(i).toString());
                    }

                    String json = jo.toString();
                    response.setStatus(200);
                    response.getWriter().write(json);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(GetStatistics.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (type.equals("bk_gn")) {
            ArrayList<Book> books = new ArrayList<Book>();
            EditBooksTable ebt = new EditBooksTable();
            try {
                books = ebt.databaseToBooks();
                if (books.isEmpty()) {
                    response.setStatus(403);
                } else {
                    PrintWriter out = response.getWriter();
                    int size_books = books.size();
                    ArrayList<String> genre = new ArrayList<String>();
                    for (int i = 0; i < size_books; i++) {
                        if (!genre.contains(books.get(i).getGenre())) {
                            genre.add(books.get(i).getGenre());
                        }
                    }
                    ArrayList<String> books_per_genre = new ArrayList<String>();
                    for (int i = 0; i < genre.size(); i++) {
                        Integer cnt = 0;
                        for (int j = 0; j < size_books; j++) {
                            if (books.get(j).getGenre().equals(genre.get(i))) {
                                cnt++;
                            }
                        }
                        books_per_genre.add(cnt.toString());
                    }
                    for (int i = 0; i < genre.size(); i++) {
                        jo.addProperty(genre.get(i), books_per_genre.get(i));
                    }
                    String json = jo.toString();
                    response.setStatus(200);
                    response.getWriter().write(json);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(GetStatistics.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equals("stud")) {
            ArrayList<Student> studs = new ArrayList<Student>();
            EditStudentsTable est = new EditStudentsTable();
            try {
                studs = est.AllStudents();
                if (studs.isEmpty()) {
                    response.setStatus(403);
                } else {
                    PrintWriter out = response.getWriter();
                    int phd = 0;
                    int bsc = 0;
                    int ud = 0;

                    for (int i = 0; i < studs.size(); i++) {
                        if (studs.get(i).getStudent_type().equals("PhD")
                                || studs.get(i).getStudent_type().equals("phd")) {
                            phd++;
                        } else if (studs.get(i).getStudent_type().equals("BSc")
                                || studs.get(i).getStudent_type().equals("pd")) {
                            bsc++;
                        } else if (studs.get(i).getStudent_type().equals("ud")) {
                            ud++;
                        }
                    }

                    jo.addProperty("PhD", phd);
                    jo.addProperty("BSc", bsc);
                    jo.addProperty("UnderGraduate", ud);

                    String json = jo.toString();
                    response.setStatus(200);
                    response.getWriter().write(json);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(GetStatistics.class.getName()).log(Level.SEVERE, null, ex);
            }
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
