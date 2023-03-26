/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.tables.EditBooksTable;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.BookInLibrary;

/**
 *
 * @author mountant
 */
public class EditBooksInLibraryTable {

    
    public void addBookInLibraryFromJSON(String json) throws ClassNotFoundException{
         BookInLibrary msg=jsonTobookInLibrary(json);
         createNewBookInLibrary(msg);
    }
    public String bookInLibraryToJSON(BookInLibrary tr) {
        Gson gson = new Gson();

        String json = gson.toJson(tr, BookInLibrary.class);
        return json;
    }

    public BookInLibrary jsonTobookInLibrary(String json) {
        Gson gson = new Gson();
        BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
        return tr;
    }
    
    public BookInLibrary databaseToBookInLibrary(int id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE library_id= '" + id + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            BookInLibrary tr  = gson.fromJson(json, BookInLibrary.class);
            return tr;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void createBooksInLibrary() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE booksinlibraries "
                + "(bookcopy_id INTEGER not NULL AUTO_INCREMENT, "
                + "isbn  VARCHAR(13) not null,"
                + "library_id INTEGER not null,"
                + "available VARCHAR(5) not null,"
                + "FOREIGN KEY (isbn) REFERENCES books(isbn), "
                + "FOREIGN KEY (library_id) REFERENCES librarians(library_id), "
                + "PRIMARY KEY ( bookcopy_id ))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }
    
    public void updateBookInLibrary(String bookcopy_id, String available) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update="UPDATE booksinlibraries SET available='"+available+"'"+ " WHERE bookcopy_id = '"+bookcopy_id+"'";
        stmt.executeUpdate(update);
    }

    public void updateBookInLibraryByIsbn(String isbn, String available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update = "UPDATE booksinlibraries SET available='" + available + "'" + " WHERE isbn = '" + isbn + "'";
        stmt.executeUpdate(update);
    }

    public void updateBookInLibraryAvailability(int library_id, String available, String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update = "UPDATE booksinlibraries SET available='" + available + "'" + "WHERE library_id= '" + library_id + "' AND isbn='" + isbn + "'";
        stmt.executeUpdate(update);
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBookInLibrary(BookInLibrary bi) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " booksinlibraries (isbn,library_id,available) "
                    + " VALUES ("
                    + "'" + bi.getIsbn() + "',"
                    + "'" + bi.getLibrary_id()+ "',"
                    + "'" + bi.getAvailable()+ "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The entry was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();
                 con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditBooksTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<BookInLibrary> databaseToBookInLibraryList() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<BookInLibrary> blib = new ArrayList<BookInLibrary>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(tr);
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<BookInLibrary> databaseToBookInLibrary_libid(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<BookInLibrary> blib = new ArrayList<BookInLibrary>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE library_id= '" + id + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(tr);
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<BookInLibrary> databaseToBookInLibraryAvailable(String isbn, String available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<BookInLibrary> blib = new ArrayList<BookInLibrary>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE isbn= '" + isbn + "' AND available='" + available + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(tr);
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int getLibrary_id(int library_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE library_id = '" + library_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            BookInLibrary lib = gson.fromJson(json, BookInLibrary.class);
            return lib.getLibrary_id();
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public ArrayList<BookInLibrary> databaseToBookInLibraryBorrow(int library_id, String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<BookInLibrary> blib = new ArrayList<BookInLibrary>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE library_id= '" + library_id + "' AND isbn='" + isbn + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(tr);
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Integer> getBookcopy_idlist(String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        ArrayList<Integer> blib = new ArrayList<Integer>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE isbn = '" + isbn + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary lib = gson.fromJson(json, BookInLibrary.class);
                blib.add(lib.getBookcopy_id());
            }
            return blib;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<String> return_bookcopy_ids(int id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<String> blib = new ArrayList<String>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE library_id= '" + id + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(String.valueOf(tr.getBookcopy_id()));
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String getBook_isbn(int bookcopy_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE bookcopy_id = '" + bookcopy_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            BookInLibrary lib = gson.fromJson(json, BookInLibrary.class);
            return lib.getIsbn();
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<BookInLibrary> databaseToBookInLibrary_aval() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        ArrayList<BookInLibrary> blib = new ArrayList<BookInLibrary>();
        try {
            rs = stmt.executeQuery("SELECT * FROM booksinlibraries WHERE available ='true'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
                blib.add(tr);
            }
            return blib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
