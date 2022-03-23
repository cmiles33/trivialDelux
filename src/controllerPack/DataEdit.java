/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerPack;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is the Data edit class
 * <p>
 * This class is used specifically by the database viewer
 * for altering information in the database.
 * @author calebmiles
 */
public class DataEdit {
    /**
     * Takes in the name of the database and connects to it
     * @param databaseName name of database
     */
    public DataEdit( String databaseName)
    {
        this.connect(databaseName);
    }
    /**
     * This function makes the connection to the database
     * @param databaseName name of database
     */
    private void connect(String databaseName)
    {
        String universalPath;
        Path newPath = Paths.get(System.getProperty("user.dir"),databaseName);
        universalPath = newPath.toString();
        String url = "jdbc:sqlite::resource:dataFiles/triviaDatabase.db";
        
        Connection conn = null;
        
        try
        {
            conn = DriverManager.getConnection(url);
            
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        this.ourConnect = conn;
    }

    /**
     * This function takes a subject, question, and its answer
     * and inserts it and updates it into the database.
     * @param subject name of subject
     * @param question name of question
     * @param answer text answer to question
     * @return true or false
     */
    public Boolean insertQuestion( String subject, String question, String answer)
    {
        String sqlStatement = "INSERT INTO questions(subject,question,answer) "
                + "VALUES(?,?,?)";
        
        try
        {
            PreparedStatement pstmt = this.ourConnect.prepareStatement(sqlStatement);
            pstmt.setString(1, subject);
            pstmt.setString(2, question);
            pstmt.setString(3, answer);
            pstmt.executeUpdate();
            return true;
            
        }
        catch(SQLException e)
        {
                System.out.println(e.getMessage());
                return false;
        }
        
        
        

    }
    /**
     * This function just updates a answer for a specific question
     * 
     * @param question question text
     * @param answer answer text to question
     * @return true or false
     */
    public Boolean updateAnswer(String question, String answer)
    {
        String sqlStatement = "UPDATE questions SET answer = ? "
                + "WHERE question = ?";
                
        
        try
        {
            PreparedStatement pstmt = this.ourConnect.prepareStatement(sqlStatement);
            pstmt.setString(1, answer);
            pstmt.setString(2, question);
            pstmt.executeUpdate();
            return true;
            
        }
        catch(SQLException e)
        {
                System.out.println(e.getMessage());
                return false;
        }
        
        
        

    }
    
    private Connection ourConnect = null;
}
