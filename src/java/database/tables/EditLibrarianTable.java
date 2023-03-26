/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.Librarian;
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
public class EditLibrarianTable {

    public void addLibrarianFromJSON(String json) throws ClassNotFoundException {
        Librarian lib = jsonToLibrarian(json);
        addNewLibrarian(lib);
    }

    public Librarian jsonToLibrarian(String json) {
        Gson gson = new Gson();

        Librarian lib = gson.fromJson(json, Librarian.class);
        return lib;
    }

    public String librarianToJSON(Librarian lib) {
        Gson gson = new Gson();

        String json = gson.toJson(lib, Librarian.class);
        return json;
    }

    public void updateLibrarian(String username, String personalpage) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET personalpage='" +  personalpage + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void printLibrarianDetails(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "' AND password='" + password + "'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public Librarian databaseToLibrarian(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "' AND password='" + password + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian lib = gson.fromJson(json, Librarian.class);
            return lib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Librarian> databaseToLibrarians() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Librarian> librarians=new ArrayList<Librarian>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Librarian lib = gson.fromJson(json, Librarian.class);
                librarians.add(lib);
            }
            return librarians;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


    public void createLibrariansTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE librarians"
                + "(library_id INTEGER not NULL AUTO_INCREMENT, "
                + "    username VARCHAR(30) not null unique,"
                + "    email VARCHAR(200) not null unique,	"
                + "    password VARCHAR(32) not null,"
                + "    firstname VARCHAR(30) not null,"
                + "    lastname VARCHAR(30) not null,"
                + "    birthdate DATE not null,"
                + "    gender  VARCHAR (7) not null,"
                + "    country VARCHAR(30) not null,"
                + "    city VARCHAR(50) not null,"
                + "    address VARCHAR(50) not null,"
                + "    libraryname VARCHAR(100) not null,"
                + "    libraryinfo VARCHAR(1000) not null,"
                + "    lat DOUBLE,"
                + "    lon DOUBLE,"
                + "    telephone VARCHAR(14),"
                + "    personalpage VARCHAR(200),"
                + " PRIMARY KEY (library_id))";
        stmt.execute(query);
        stmt.close();
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewLibrarian(Librarian lib) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " librarians (username,email,password,firstname,lastname,birthdate,gender,country,city,address,"
                    + "libraryname,libraryinfo,lat,lon,telephone,personalpage)"
                    + " VALUES ("
                    + "'" + lib.getUsername() + "',"
                    + "'" + lib.getEmail() + "',"
                    + "'" + lib.getPassword() + "',"
                    + "'" + lib.getFirstname() + "',"
                    + "'" + lib.getLastname() + "',"
                    + "'" + lib.getBirthdate() + "',"
                    + "'" + lib.getGender() + "',"
                    + "'" + lib.getCountry() + "',"
                    + "'" + lib.getCity() + "',"
                    + "'" + lib.getAddress() + "',"
                      + "'" + lib.getLibraryname()+ "',"
                   + "'" + lib.getLibraryinfo()+ "',"
                    + "'" + lib.getLat() + "',"
                    + "'" + lib.getLon() + "',"
                    + "'" + lib.getTelephone() + "',"
                   + "'" + lib.getPersonalpage()+ "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The libarian was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditLibrarianTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Librarian databaseToLibrarianCheck(String username, String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "' OR email='" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian lib = gson.fromJson(json, Librarian.class);
            return lib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Librarian databaseToLibrarianCheckUsername(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian lib = gson.fromJson(json, Librarian.class);
            return lib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deleteLibrarian(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM librarians WHERE username='" + username + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

    public int return_Library_id(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian lib = gson.fromJson(json, Librarian.class);
            return lib.getLibrary_id();
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public Librarian databaseUpdateLibrarian(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian Librarian = gson.fromJson(json, Librarian.class);
            return Librarian;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateLibrarianPassword(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET password='" + password + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianFirstname(String username, String firstname) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET firstname='" + firstname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianLastname(String username, String lastname) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET lastname='" + lastname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianBirthdate(String username, String birthdate) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET birthdate='" + birthdate + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianGender(String username, String gender) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET gender='" + gender + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianCountry(String username, String country) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET country='" + country + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianCity(String username, String city) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET city='" + city + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianAddress(String username, String address, double lat, double lon) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET address='" + address + "', lat='" + lat + "', lon='" + lon + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibrarianTelephone(String username, String telephone) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET telephone='" + telephone + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibraryName(String username, String libraryname) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET libraryname='" + libraryname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateLibraryInfo(String username, String libraryinfo) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE librarians SET libraryinfo='" + libraryinfo + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public Librarian databaseToLibraryId(int library_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM librarians WHERE library_id = '" + library_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Librarian lib = gson.fromJson(json, Librarian.class);
            return lib;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String librarianListToJSON(ArrayList<Librarian> per) {
        Gson gson = new Gson();

        String json = gson.toJson(per, Librarian.class);
        return json;
    }

}
