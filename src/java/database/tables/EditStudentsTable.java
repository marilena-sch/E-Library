/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import mainClasses.Student;
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
public class EditStudentsTable {

 
    public void addStudentFromJSON(String json) throws ClassNotFoundException{
         Student user=jsonToStudent(json);
         addNewStudent(user);
    }
    
     public Student jsonToStudent(String json){
         Gson gson = new Gson();

        Student user = gson.fromJson(json, Student.class);
        return user;
    }
    
    public String studentToJSON(Student user){
         Gson gson = new Gson();

        String json = gson.toJson(user, Student.class);
        return json;
    }
    
   
    
    public void updateStudent(String username,String personalpage) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update="UPDATE students SET personalpage='"+personalpage+"' WHERE username = '"+username+"'";
        stmt.executeUpdate(update);
    }

    public void updateStudentPassword(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET password='" + password + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentFirstname(String username, String firstname) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET firstname='" + firstname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentLastname(String username, String lastname) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET lastname='" + lastname + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentBirthdate(String username, String birthdate) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET birthdate='" + birthdate + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentGender(String username, String gender) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET gender='" + gender + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentCountry(String username, String country) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET country='" + country + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentCity(String username, String city) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET city='" + city + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentAddress(String username, String address, double lat, double lon) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET address='" + address + "', lat='" + lat + "', lon='" + lon + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public void updateStudentTelephone(String username, String telephone) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE students SET telephone='" + telephone + "' WHERE username = '" + username + "'";
        stmt.executeUpdate(update);
    }

    public Student databaseToStudent_exists_user_id(int id) throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE user_id = '" + id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student user = gson.fromJson(json, Student.class);
            if (!"".equals(user.getUsername())) {
                return user;
            } else {
                return null;
            }
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Student databaseUpdateStudent(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student Student = gson.fromJson(json, Student.class);
            return Student;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void printStudentDetails(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "' AND password='"+password+"'");
            while (rs.next()) {
                System.out.println("===Result===");
                DB_Connection.printResults(rs);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    
    public Student databaseToStudent(String username, String password) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "' AND password='"+password+"'");
            System.out.println("SELECT * FROM students WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student user = gson.fromJson(json, Student.class);
            return user;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public String databaseStudentToJSON(String username, String password) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "' AND password='"+password+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }


     public void createStudentsTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE students "
                + "(user_id INTEGER not NULL AUTO_INCREMENT, "
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
                + "    student_type VARCHAR(50) not null,"
                + "    student_id VARCHAR(14) not null unique,"
                + "    student_id_from_date DATE not null,"
                + "    student_id_to_date DATE not null,"
                + "   university VARCHAR(50) not null,"
                + "   department VARCHAR(50) not null,"
                + "    lat DOUBLE,"
                + "    lon DOUBLE,"
                + "    telephone VARCHAR(14),"
                + "   personalpage VARCHAR(200),"
                + " PRIMARY KEY ( user_id))";
        stmt.execute(query);
        stmt.close();
    }
    
    
    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void addNewStudent(Student user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " students (username,email,password,firstname,lastname,birthdate,gender,country,city,address,student_type,"
                    + "student_id,student_id_from_date,student_id_to_date,university,department,lat,lon,telephone,personalpage)"
                    + " VALUES ("
                    + "'" + user.getUsername() + "',"
                    + "'" + user.getEmail() + "',"
                    + "'" + user.getPassword() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getBirthdate() + "',"
                    + "'" + user.getGender() + "',"
                    + "'" + user.getCountry() + "',"
                    + "'" + user.getCity() + "',"
                    + "'" + user.getAddress() + "',"
                    + "'" + user.getStudent_type() + "',"
                    + "'" + user.getStudent_id() + "',"
                    + "'" + user.getStudent_id_from_date() + "',"
                    + "'" + user.getStudent_id_to_date()+ "',"
                    + "'" + user.getUniversity() + "',"
                    + "'" + user.getDepartment() + "',"
                    + "'" + user.getLat() + "',"
                    + "'" + user.getLon() + "',"
                    + "'" + user.getTelephone() + "',"
                    + "'" + user.getPersonalpage()+ "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The user was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditStudentsTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Student databaseToStudentCheck(String username, String email, String student_id) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "' OR email='" + email + "' OR student_id='" + student_id + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student newstudent = gson.fromJson(json, Student.class);

            return newstudent;
        } catch (Exception e) {
        }
        return null;
    }

    public Student databaseToStudentCheckUsername(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student newstudent = gson.fromJson(json, Student.class);

            return newstudent;
        } catch (Exception e) {
        }
        return null;
    }

    public Student databaseToStudentforLibrary(String username, String email) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students WHERE username = '" + username + "' OR email='" + email + "'");
            rs.next();
            String json = DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Student newstudent = gson.fromJson(json, Student.class);

            return newstudent;
        } catch (Exception e) {
        }
        return null;
    }
    public ArrayList<Student> AllStudents() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Student> stds = new ArrayList<Student>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Student std = gson.fromJson(json, Student.class);
                stds.add(std);
            }
            return stds;

        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deleteStudent(String username) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM students WHERE username='" + username + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }

}
