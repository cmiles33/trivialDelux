package modelPack;
import java.util.*;
/**
 * This is the question class.
 * <p>
 * The question class is apart of the model scheme.
 * Its only job is to hold information and return information.
 * It is completely manipulated by controller classes and the views.
 * @author calebmiles
 */
public class Question 
{
    /**
     * Initialize the question with its set Question
     * if necessary. 
     * @param setQuestion question text
     */
    public Question(String setQuestion)
    {
        question = setQuestion;
    }
    /**
     * adds a choice to the list
     * @param choice string choice
     */
    public void addChoice(String choice)
    {
        choices.add(choice);
    }
    /**
     * sets the answer for the question
     * @param answerIn answer text string
     */
    public void setAnswer(String answerIn)
    {
        answer = answerIn;
    }
    /**
     * returns the question held by the object
     * @return question string
     */
    public String getQuestion()
    {
        this.used = true;
        return this.question;
    }
    /**
     * returns a list of strings
     * which contains all the available choices for the question
     * @return string list object
     */
    public List<String> getChoices()
    {
        return choices;
    }
    /**
     * returns the answer held by the object 
     * @return returns the objects answer
     */
    public String getAnswer()
    {
        return answer;
    }
    
    private List<String> choices = new ArrayList<String>();;
    private String answer;
    private String question;
    private Boolean used = false;
    
}
