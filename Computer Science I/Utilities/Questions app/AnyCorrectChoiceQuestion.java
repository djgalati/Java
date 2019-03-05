import java.util.ArrayList;

public class AnyCorrectChoiceQuestion extends ChoiceQuestion {

  private ArrayList<String> possibleAnswers;
  private ArrayList<String> anyChoices;

  public AnyCorrectChoiceQuestion() {
    possibleAnswers = new ArrayList<>();
    anyChoices = new ArrayList<>();
  }

  /**
   * Adds the choices for the question
   * @param choice One of the options for the question
   * @param correct The correct answer
   */
  public void addChoice(String choice, boolean correct) {
    // Adds the choice to the superclass, ChoiceQuestion
    super.addChoice(choice, correct);

    // Does the calculations in this class to set the correct answers
    anyChoices.add(choice);
    if (correct) {
         // Convert choices.size() to string
         String choiceString = "" + anyChoices.size();
         possibleAnswers.add(choiceString);
         setAnswer(choiceString);
      }

  }

  /**
   * Checks the answers to see if correct
   * @param response The users input to the question
   * @return Returns true or false depending if the response was a right answer
   */
  public boolean checkAnswer(String response) {
    return possibleAnswers.contains(response);
  }

  public void display() {
    super.display();
    System.out.println("Note, there may be more than one correct answer.");
  }
}
