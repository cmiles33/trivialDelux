package fileParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// SQL Lite imports

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 * This is the file parser class
 * <p>
 * This class is used to parse the text file and create the database object
 * @author calebmiles
 */

public class FileParser 
{
    /**
     * When constructed the file parser class
     * goes ahead and creates the database.
     * This class has no other use and is only used once.
     */
    public FileParser()
    {
        try
        {
            this.makeDatabase("firstOne.db");
            this.addtable();
            this.connectDatabase();
            File myFile = new File("trivia_questions.txt");
            Scanner myFileReader = new Scanner(myFile);
            String newSubject = "FILLERTAB";
            int questionCounter = 0;
            while(myFileReader.hasNextLine())
            {
                try
                {
                   String line = myFileReader.nextLine();
                   String [] splitStrings = line.split(" ");
                   String subject = splitStrings[0];
                   String [] splitSubs = subject.split("\\[");
                   String stripped = splitSubs[1].replaceAll("[^a-zA-z' ]","");
                   
                   String finalSubject = stripped.toLowerCase();
                   
                   if(newSubject.replaceAll(" ","").equals(finalSubject.replaceAll(" ","")))
                   {
                       questionCounter += 1;
                   }
                   else
                   {
                       System.out.println("Num questions: " + Integer.toString(questionCounter));
                       // This gets unique Subjects. 
                       if (questionCounter > 100)
                       {
                           this.insertSubject(newSubject, questionCounter);
                       }
                       newSubject = finalSubject;
                       // add to the database.
                       questionCounter = 0;
                   }
                   
                   
                   // Get the question
                   if (line.contains(":"))
                   {
                       String [] splitQuestion = line.split(":");
                       String [] splitQuestion2 = splitQuestion[1].split("\",");
                       String question = splitQuestion2[0].replaceAll("\\[\"","");
                       //System.out.println("Question: " + question);

                       // Get answer:
                       String answer = splitQuestion2[1];
                       answer = answer.replaceAll("\\]","");
                       answer = answer.replaceAll("\\\"","");
                       //System.out.println("Answer: " + answer);
                       // Add it to the database
                       this.insertQuestion(finalSubject, question, answer);


                   }
                   else
                   {
                       String [] splitQuestion = line.split("\",");
                       String question = splitQuestion[0].replaceAll("\\[","");
                       question = question.replaceAll("\"","");
                       //System.out.println("Question: " + question);
                       String answer = splitQuestion[1];
                       answer = answer.replaceAll("\\]","");
                       answer = answer.replaceAll("\\\"","");
                       //System.out.println("Answer: " + answer);
                       // Add it to the database
                       this.insertQuestion(finalSubject, question, answer);
                   }
                }
                catch(Exception e)
                {
                    //System.out.println("Question doesnt meet our parsing standard");
                    //System.out.println(e.getMessage());
                    
                    // There are so many questions that its okay if some are not
                    // taken in at all.
                }
                        
                
                
            }
            myFileReader.close();
           
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
    }
    /**
     * Private function used to make the data base
     * @param databaseName String name of database
     */
    private void makeDatabase(String databaseName)
    {
        System.out.println("Making new database");
        String path = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + path + "/" + databaseName;
        System.out.println(url);
        
        try (Connection conn = DriverManager.getConnection(url))
        {
            if( conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println(" The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                this.dbLocation = url;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Makes makes connection to the database
     * to test that it was made properly
     */
    public void connectDatabase()
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(this.dbLocation);
            
            System.out.println("connection Made");
            this.conn = conn;
            
            
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Adds table to the sql database using SQL commands
     */
    private void addtable()
    {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS subjects ("
        + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + " subject text NOT NULL UNIQUE, "
        + " numberQuestions INTEGER);";
        String sqlCommand2 = "CREATE TABLE IF NOT EXISTS questions ("
        + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + " subject text, "
        + " question text, "
        + " answer text);";
        
        try(Connection conn = DriverManager.getConnection(this.dbLocation);
                Statement stmt = conn.createStatement())
        {
            stmt.execute(sqlCommand);
            stmt.execute(sqlCommand2);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
    /**
     * 
     * @param subject string subject name
     * @param questionCount number of questions found for subject
     */
    private void insertSubject( String subject, int questionCount)
    {
        String sqlCommand = "INSERT INTO subjects(subject,numberQuestions) "
                + "VALUES(?,?)";
        
        try
        {
            PreparedStatement pstmt = this.conn.prepareStatement(sqlCommand);
            pstmt.setString(1, subject);
            pstmt.setInt(2, questionCount);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
    /**
     * inserts question into the database
     * @param subject string name of subject
     * @param question string question text
     * @param answer string answer text
     */
    private void insertQuestion(String subject, String question, String answer)
    {
                String sqlCommand = "INSERT INTO questions(subject,question,answer) "
                + "VALUES(?,?,?)";
        
        try
        {
            PreparedStatement pstmt = this.conn.prepareStatement(sqlCommand);
            pstmt.setString(1, subject);
            pstmt.setString(2, question);
            pstmt.setString(3, answer);
            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    private String dbLocation = "";
    private Connection conn;
}
