package controllerPack;

// SQL Packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// import lists
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;

// Import our model data
import modelPack.*;

/**
 * This is the databaseConnector class
 * <p>
 * This is the main class that interacts with the 
 * database and fills our subjects and also
 * fills subjects with their respective questions.
 * @author calebmiles
 */

public class DatabaseConnector 
{
    /**
     * This function takes in the name of the database
     * and makes the connection to the database for future use.
     * @param databaseName name of database
     */
    public DatabaseConnector(String databaseName)
    {
        // Fixed Pathing issue across platforms
        String univseralPath;
        File database = new File("src/dataFiles/triviaDatabase.db");
        
        Path newPath = Paths.get(System.getProperty("user.dir"),databaseName);
        univseralPath = newPath.toString();
        
        System.out.println(database.getAbsolutePath());
        String url = "jdbc:sqlite::resource:dataFiles/triviaDatabase.db";
        //String url = "jdbc:sqlite:" + "src/dataFiles/triviadatabase.db";
        // Conect to the database
        try
        {
            this.conn = DriverManager.getConnection(url);
            System.out.println("Connection made to: " + databaseName);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    /**
     * This function only works once there has been
     * a connection to the database. This function
     * fills up a list of subjects to be used in the program
     * once they are filled, they can be retrieved by another function
     * 
     */
    public void fillSubjects()
    {
        // This Function will create a list of subjects
        // It will also fill the subject with a list of 
        // available questions
        // This function Does a lot, maybe too much but
        // its not that complex
        String sqlCommand = "SELECT subject, numberQuestions FROM subjects "
                + "ORDER BY numberQuestions  ";
        
        // This try catch block executes the sql statement
        // The result contains all of the results from the query
        // the query consists of just the subject and the number of
        // questions asociated with the subject 
        try
        {
            numberOfSubjects = 0;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCommand);
            
            while(rs.next())
            {

                // Create our list of subjects
                // Also fill the subjects with there questions
                Subject tempSub = new Subject(rs.getString("subject"));
                // THe number of questions field does get changed 
                tempSub.setNumberQuestions(rs.getInt("numberQuestions"));
                
                // This calls the funcion that fills the subject's list of
                // questions with all of the questions
                tempSub = this.fillSubjectQuestions(tempSub);
                
                // This calls the function that randomly assigns choices
                // to all of the questions, see the subject class for that.
                //tempSub.setChoices();
                this.setChoices(tempSub);
                this.usuableSubjects.add(tempSub);
                numberOfSubjects++;
                
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        // This fills a list of strings with the names
        // of the subjects
        this.fillSubjectList();
        System.out.println("Complete. Done filling Subjects.");
    }
    /**
     * This function is responsible for filling
     * each subject with questions.
     * @param inputSub
     * @return 
     */
    private Subject fillSubjectQuestions(Subject inputSub)
    {
        String sqlCommand = "select question, answer from questions where subject"
                + " = '" + inputSub.getTopic() + "'";
        
        // The try catch block executes the sql statement
        // and retrieves all the questions associated with a 
        // certian subject
        try
        {
            numberOfQuestionsInSubject = 0;
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCommand);
            
            while(rs.next())
            {
                Question tempQuest = new Question(rs.getString("question"));
                tempQuest.setAnswer(rs.getString("answer"));
                inputSub.addQuestions(tempQuest);
                numberOfQuestionsInSubject++;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        // returns the object back 

        
        return inputSub;
        
    }
    /**
     * This function fills the list of strings that contains
     * the plain text of the topics of all the subjects
     * for reference.
     */
    private void fillSubjectList()
    {
        for(int counter = 0;  counter < this.usuableSubjects.size(); counter++)
        {
            String tempTopic = this.usuableSubjects.get(counter).getTopic();
            this.listOfSubjectNames.add(tempTopic);
                    
        }
            
    }
    /**
     * This is the setChoices function
     * <p>
     * This function is important. It goes through each question
     * in the subject and sets it choices with randomized answers
     * from other questions within the subject.
     * @param inputSub 
     * @return subject
     */
    private Subject setChoices(Subject inputSub)
    {
                // THIS SHOULD only be ran after the list of questions
        // has been filled. This function is crazy
        
        // This statement fixes the number of questions 
        // to the actual amount, something's wrong with the parser
        // but this fixes it.
        inputSub.setNumberQuestions(inputSub.getListQuestions().size()); 
        //*****
        // This four loop generates random numbers and selects 
        // 3 random question answers and adds them to the selected
        // question choices. It also puts the answer in a suedo random
        // place. 
        int answerPlacement = 0;
        for( int counter = 0; counter < inputSub.getNumberOfQuestionsInSubject(); counter++)
        {
            
            Random rand = new Random();
            for(int counter2 = 0; counter2 < 4; counter2++)
            {
                if(counter2 == answerPlacement)
                {
                    String actualAnswer = inputSub.getListQuestions().get(counter).getAnswer();
                    inputSub.getListQuestions().get(counter).addChoice(actualAnswer);
                }
                else
                {
                    int upper = inputSub.getNumberOfQuestionsInSubject()-1;
                    int randomNum = rand.nextInt(upper);
                    String randomAnswer = inputSub.getListQuestions().get(randomNum).getAnswer();
                    inputSub.getListQuestions().get(counter).addChoice(randomAnswer);
                    while((randomAnswer.equals( inputSub.getListQuestions().get(counter).getAnswer())))
                    {
                        randomNum = rand.nextInt(upper);
                        randomAnswer = inputSub.getListQuestions().get(randomNum).getAnswer();
                        inputSub.getListQuestions().get(counter).addChoice(randomAnswer);
                    }
                }

            }
            answerPlacement +=1;
            
            if(answerPlacement == 2)
            {
                answerPlacement = 0;
            }
                    
        }
        
        return inputSub;
    }
    /**
     * This returns a list of subject objects held by the object
     * @return returns java list of subjects
     */
    public List<Subject> getListSubjects()
    {
        return this.usuableSubjects;
    }
    /**
     * This returns a list of strings containing the 
     * @return string list
     */
    public List<String> getSubjectNames()
    {
        return this.listOfSubjectNames;
    }
    /**
     * Prints subjects at a given index
     * @param i index to print subject at
     */
    public void printASubject(int i)
    {
        System.out.print(listOfSubjectNames.get(i));
    }
    /**
     * gets the number of subjects
     * @return returns number of subjects
     */
    public int getNumberOfSubjects()
    {
        return this.numberOfSubjects;
    }
    /** 
     * Gets the numbers of question in the subject
     * @param subjectNumber subject number index
     * @return int number of questions in subject int
     */
    public int getNumberOfQuestionsInSubject(int subjectNumber)
    {
        
        return this.numberOfQuestionsInSubject;
    }
    
    private Connection conn = null;
    private List<Subject> usuableSubjects = new ArrayList<Subject>();
    private List<String> listOfSubjectNames = new ArrayList<String>();
    private int numberOfSubjects = 0;
    private int numberOfQuestionsInSubject = 0;
    
}
