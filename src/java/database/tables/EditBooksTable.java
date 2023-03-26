/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.Book;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class EditBooksTable {

    public void addBookFromJSON(String json) throws ClassNotFoundException {
        Book bt = jsonToBook(json);
        createNewBook(bt);
    }

    public Book jsonToBook(String json) {
        Gson gson = new Gson();
        Book btest = gson.fromJson(json, Book.class);
        return btest;
    }

    public String bookToJSON(Book bt) {
        Gson gson = new Gson();

        String json = gson.toJson(bt, Book.class);
        return json;
    }

    public ArrayList<Book> databaseToBooks() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> books = new ArrayList<Book>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                books.add(book);
            }
            return books;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Book> databaseToBooks(String genre) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> books = new ArrayList<Book>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre= '" + genre + "'");
           
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateBook(String isbn, String url) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        Book bt = new Book();

        String update = "UPDATE books SET url='" + url + "'" + "WHERE isbn = '" + isbn + "'";
        //stmt.executeUpdate(update);
    }

    public void deleteBook(String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM books WHERE isbn='" + isbn + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public void createBooksTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE books "
                + "(isbn VARCHAR(13) not NULL, "
                + "title VARCHAR(500) not null,"
                + "authors VARCHAR(500)  not null, "
                + "genre VARCHAR(500)  not null, "
                + "pages INTEGER not null , "
                + "publicationyear INTEGER not null , "
                + "url VARCHAR (500), "
                + "photo VARCHAR (500), "
                + "PRIMARY KEY ( isbn ))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBook(Book bt) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " books (isbn,title,authors,genre,pages,publicationyear,url,photo) "
                    + " VALUES ("
                    + "'" + bt.getIsbn() + "',"
                    + "'" + bt.getTitle() + "',"
                    + "'" + bt.getAuthors() + "',"
                    + "'" + bt.getGenre() + "',"
                    + "'" + bt.getPages() + "',"
                    + "'" + bt.getPublicationyear() + "',"
                    + "'" + bt.getUrl() + "',"
                    + "'" + bt.getPhoto() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The book was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBooksTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Book databaseToBook(String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE isbn = '" + isbn + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Book bt = gson.fromJson(json, Book.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookGenreFromYearToYear(String genre, String fromYear, String toYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int from_year = Integer.parseInt(fromYear);
        int to_year = Integer.parseInt(toYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre= '" + genre + "' AND publicationyear >= '" + from_year + "'AND publicationyear <= '" + to_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookFromYearToYear(String fromYear, String toYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int from_year = Integer.parseInt(fromYear);
        int to_year = Integer.parseInt(toYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE publicationyear >= '" + from_year + "'AND publicationyear <= '" + to_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookGenreFromYear(String genre, String fromYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int from_year = Integer.parseInt(fromYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre= '" + genre + "' AND publicationyear >= '" + from_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookFromYear(String fromYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int from_year = Integer.parseInt(fromYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE publicationyear >= '" + from_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookGenreToYear(String genre, String toYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int to_year = Integer.parseInt(toYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre= '" + genre + "' AND publicationyear <= '" + to_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookToYear(String toYear) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        int to_year = Integer.parseInt(toYear);

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE publicationyear <= '" + to_year + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBookGenre(String genre) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre= '" + genre + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public ArrayList<Book> databaseToBook() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> booksList = new ArrayList<Book>();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                booksList.add(book);
            }
            return booksList;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public Book databaseToBookISBN(String isbn) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM books WHERE isbn = '" + isbn + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Book book = gson.fromJson(json, Book.class);
            return book;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        stmt.close();
        con.close();
        return null;
    }

    public void updateBookPages(String isbn, int pages) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        Book book = new Book();

        book.setPages(pages);
        String update = "UPDATE books SET pages='" + pages + "'" + "WHERE isbn = '" + isbn + "'";
        stmt.executeUpdate(update);
        stmt.close();
        con.close();
    }

    public ArrayList<Book> databaseToBooks_specific(String genre, int yearFrom, int yearTo,
            String title, String author, int pageFrom, int pageTo) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Book> books = new ArrayList<Book>();
        ResultSet rs;
        String exec = "";
        System.out.println(genre);
        int flag_page = 0;
        int flag_year = 0;
        int goto_nxt = 0;

        if ("all".equals(genre) || "All".equals(genre) || "ALL".equals(genre)) {
            exec += "SELECT * FROM books";
            if (yearFrom == 0 && yearTo == 0 && pageFrom == 0 && pageTo == 0 && title == null && author == null) {
                exec = "SELECT * FROM books";
            }
            if (yearFrom != 0) {
                flag_year = 1;
                goto_nxt = 1;
                exec += " WHERE publicationyear >= '" + yearFrom + "'";
            }
            if (yearTo != 0) {
                goto_nxt = 1;
                if (flag_year == 1) {
                    exec += " AND publicationyear <= '" + yearTo + "'";
                } else {
                    exec += " WHERE publicationyear <= '" + yearTo + "'";
                }
            }

            if (title != null) {
                if (goto_nxt == 1) {
                    exec += " AND title LIKE '%" + title + "%'";
                } else {
                    exec += " WHERE title LIKE '%" + title + "%'";
                }
                goto_nxt = 1;
            }
            if (author != null) {
                if (goto_nxt == 1) {
                    exec += " AND authors LIKE '%" + author + "%'";
                } else {
                    exec += " WHERE authors LIKE '%" + author + "%'";
                }
                goto_nxt = 1;
            }
            if (pageFrom != 0) {
                flag_page = 1;
                if (goto_nxt == 1) {
                    exec += " AND pages >= '" + pageFrom + "'";
                } else {
                    goto_nxt = 1;
                    exec += " WHERE pages >= '" + pageFrom + "'";
                }
            }
            if (pageTo != 0) {
                if (flag_page == 1 || goto_nxt == 1) {
                    exec += " AND pages <= '" + pageTo + "'";
                } else {
                    exec += " WHERE pages <= '" + pageTo + "'";
                }
            }

        } else if (genre != null) {
            exec += "SELECT * FROM books WHERE genre ='" + genre + "'";
            if (yearFrom == 0 && yearTo == 0 && pageFrom == 0 && pageTo == 0 && title == null && author == null) {
                exec = "SELECT * FROM books WHERE genre ='" + genre + "'";
            }
            if (yearFrom != 0) {
                flag_year = 1;
                goto_nxt = 1;
                exec += " AND publicationyear >= '" + yearFrom + "'";
            }
            if (yearTo != 0) {
                goto_nxt = 1;
                if (flag_year == 1) {
                    exec += " AND publicationyear <= '" + yearTo + "'";
                } else {
                    exec += " AND publicationyear <= '" + yearTo + "'";
                }
            }

            if (title != null) {
                if (goto_nxt == 1) {
                    exec += " AND title LIKE '%" + title + "%'";
                } else {
                    exec += " AND title LIKE '%" + title + "%'";
                }
                goto_nxt = 1;
            }
            if (author != null) {
                if (goto_nxt == 1) {
                    exec += " AND authors LIKE '%" + author + "%'";
                } else {
                    exec += " AND authors LIKE '%" + author + "%'";
                }
                goto_nxt = 1;
            }
            if (pageFrom != 0) {
                flag_page = 1;
                if (goto_nxt == 1) {
                    exec += " AND pages >= '" + pageFrom + "'";
                } else {
                    goto_nxt = 1;
                    exec += " AND pages >= '" + pageFrom + "'";
                }
            }
            if (pageTo != 0) {
                if (flag_page == 1 || goto_nxt == 1) {
                    exec += " AND pages <= '" + pageTo + "'";
                } else {
                    exec += " AND pages <= '" + pageTo + "'";
                }
            }
        }

        try {
            rs = stmt.executeQuery(exec);
            while (rs.next()) {
                String json = DB_Connection
                        .getResultsToJSON(rs);
                Gson gson = new Gson();
                Book book = gson.fromJson(json, Book.class);
                books.add(book);
            }
            return books;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
