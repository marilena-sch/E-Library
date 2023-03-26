/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import mainClasses.Borrowing;
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
public class EditBorrowingTable {

   
    public void addBorrowingFromJSON(String json) throws ClassNotFoundException{
         Borrowing r=jsonToBorrowing(json);
         createNewBorrowing(r);
    }
    
    
     public Borrowing databaseToBorrowing(int id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE borrowing_id= '" + id + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

     public Borrowing jsonToBorrowing(String json) {
        Gson gson = new Gson();
        Borrowing r = gson.fromJson(json, Borrowing.class);
        return r;
    }
     
         
      public String borrowingToJSON(Borrowing r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Borrowing.class);
        return json;
    }


    public void updateBorrowing(int borrowingID, int userID, String info, String status) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String updateQuery = "UPDATE borrowing SET status";//...
        
        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }

    public void updateBorrowingStatus(String borrowing_id, String status) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String updateQuery = "UPDATE borrowing SET status='" + status + "' WHERE borrowing_id='" + borrowing_id + "'";

        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }

    public void deleteBorrowing(int borrowing_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM borrowing WHERE borrowing_id='" + borrowing_id + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }



    public void createBorrowingTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE borrowing "
                + "(borrowing_id INTEGER not NULL AUTO_INCREMENT, "
                + " bookcopy_id INTEGER not NULL, "
                + " user_id INTEGER not NULL, "
                + " fromdate DATE not NULL, "
                + " todate DATE not NULL, "
                + " status VARCHAR(15) not NULL, "
                + "FOREIGN KEY (user_id) REFERENCES students(user_id), "
                + "FOREIGN KEY (bookcopy_id) REFERENCES booksinlibraries(bookcopy_id), "
                + " PRIMARY KEY (borrowing_id))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBorrowing(Borrowing bor) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " borrowing (bookcopy_id,user_id,fromDate,toDate,status)"
                    + " VALUES ("
                    + "'" + bor.getBookcopy_id() + "',"
                    + "'" + bor.getUser_id() + "',"
                    + "'" + bor.getFromDate() + "',"
                    + "'" + bor.getToDate() + "',"
                    + "'" + bor.getStatus() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The borrowing was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBorrowingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Borrowing> databaseToBorrowing_status() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            ArrayList<Borrowing> bor = new ArrayList<Borrowing>();
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE status = 'requested'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Borrowing bt = gson.fromJson(json, Borrowing.class);
                bor.add(bt);
            }
            return bor;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Borrowing databaseToReturnbook(int bookcopy_id, String user_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE bookcopy_id= '" + bookcopy_id + "' AND user_id='" + user_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateBookInLibraryStatus(int bookcopy_id, String status, String user_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String update = "UPDATE borrowing SET status='" + status + "'" + "WHERE bookcopy_id= '" + bookcopy_id + "' AND user_id='" + user_id + "'";
        stmt.executeUpdate(update);
    }

    public Borrowing databaseToReturnbookCheck(int bookcopy_id, String user_id, String status) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE bookcopy_id= '" + bookcopy_id + "' AND user_id='" + user_id + "' AND status='" + status + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Borrowing databaseToReturnbookCopybook_Id(int bookcopy_id, String user_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE bookcopy_id= '" + bookcopy_id + "' AND user_id='" + user_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Borrowing> databaseToBorrowingUser(String user_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            ArrayList<Borrowing> bor = new ArrayList<Borrowing>();
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE user_id ='" + user_id + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                String toDate = "";
                int start_todate = json.indexOf("todate");
                for (int i = start_todate + 9; i < start_todate + 9 + 10; i++) {
                    toDate += json.charAt(i);
                }
                Gson gson = new Gson();
                Borrowing bt = gson.fromJson(json, Borrowing.class);
                bt.setToDate(toDate);
                bor.add(bt);
            }
            return bor;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Borrowing> databaseToBorrowing_bookcopy(String bookcopy) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            ArrayList<Borrowing> bor = new ArrayList<Borrowing>();
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE bookcopy_id = '" + Integer.parseInt(bookcopy) + "'");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                int start_f = json.indexOf("fromdate");
                int start_t = json.indexOf("todate");
                String from = "";
                for (int i = start_f + 11; i < start_f + 11 + 10; i++) {
                    from += json.charAt(i);
                }
                String to = "";
                for (int i = start_t + 9; i < start_t + 9 + 10; i++) {
                    to += json.charAt(i);
                }
                Gson gson = new Gson();
                Borrowing bt = gson.fromJson(json, Borrowing.class);
                bt.setFromDate(from);
                bt.setToDate(to);
                bor.add(bt);
            }
            return bor;
        } catch (JsonSyntaxException | NumberFormatException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Borrowing databaseToReturnBorrowId(String borrowing_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE borrowing_id= '" + borrowing_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
