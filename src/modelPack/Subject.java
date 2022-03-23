package modelPack;

import java.util.*;
/**
 * This is the subject class.
 * <p>
 * This class is apart of the model scheme.
 * it is used to hold information about the subject
 * and also holds all the questions from the subject.
 * This class is completely modified by the other controller
 * classes and viewing classes.
 * 
 * @author calebmiles
 */

public class Subject {
    /**
     * Construct with topic name
     * @param topicName name of the topic
     */
    public Subject(String topicName)
    {
        topic = topicName;
    }
    /**
     * adds question object to list
     * @param newQuestion Question object
     */
    public void addQuestions(Question newQuestion)
    {
        this.listQuestions.add(newQuestion);
    }
    /**
     * returns objects topic
     * @return returns topic
     */
    public String getTopic()
    {
        return this.topic;
    }
    /**
     * Returns a question for use
     * @param i index of the question
     * @return returns a question object
     */
    public Question getQuestion(int i )
    {
        if(i < 0 || i > listQuestions.size())
        {
            Question empty = new Question("Not found");
            return empty;
        }
        else
        {
            return listQuestions.get(i);
        }
    }
    /**
     * returns a list of questions to be used
     * @return returns the list of questions
     */
    public List<Question> getListQuestions()
    {
        return listQuestions;
    }
    /**
     * Sets the number of question in the subject
     * @param numberQuestions number of questions
     */
    public void setNumberQuestions(int numberQuestions)
    {
        this.numQuestion= numberQuestions;
    }
    /**
     * returns the number of question held by the subject
     * @return number of questions
     */
    public int getNumberOfQuestionsInSubject()
    {
        return this.numQuestion;
    }
    

    private String topic;
    private int numQuestion;
    private List<Question> listQuestions = new ArrayList<Question>();
    private List<String> questions = new ArrayList<String>();
}
