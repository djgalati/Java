import java.util.Scanner;
import java.util.ArrayList;

public class QuestionsApp
{
   public static void main(String[] args)
   {
     // ArrayList to hold the questions once assigned
     ArrayList<Question> questions = new ArrayList<>();

      // Creates all the object questions and answers
      NumericQuestion numericOne = new NumericQuestion();
      numericOne.setText("What is the value of PI to the nearest hundreth?");
      numericOne.setAnswer("3.14");

      NumericQuestion numericTwo = new NumericQuestion();
      numericTwo.setText("What is 10 / 3?");
      numericTwo.setAnswer("3.33");

      FillInQuestion fillOne = new FillInQuestion();
      fillOne.setQuestion("The inventor of java was _James Gosling_");

      FillInQuestion fillTwo = new FillInQuestion();
      fillTwo.setQuestion("The teacher of 136 is _Mohamed_");

      AnyCorrectChoiceQuestion anyOne = new AnyCorrectChoiceQuestion();
      anyOne.setText("Were is NAU located?");
      anyOne.addChoice("Australia", false);
      anyOne.addChoice("America", true);
      anyOne.addChoice("Flagstaff", true);
      anyOne.addChoice("United States", false);

      AnyCorrectChoiceQuestion anyTwo = new AnyCorrectChoiceQuestion();
      anyTwo.setText("Where is the engineering building?");
      anyTwo.addChoice("Twelve", false);
      anyTwo.addChoice("Building 69", true);
      anyTwo.addChoice("Russia", false);
      anyTwo.addChoice("South Campus", true);
      anyTwo.addChoice("Grandmas House", false);

      // Adds all the questions to an array list
      questions.add(numericOne);
      questions.add(numericTwo);
      questions.add(fillOne);
      questions.add(fillTwo);
      questions.add(anyOne);
      questions.add(anyTwo);

      // Enhanced for loop to display and check each question for correctness
      int points = 0;
      for (Question i: questions) {
      boolean ans = presentQuestion(i);
        if (ans) {
            if (i instanceof NumericQuestion){
                points++;
              }
            if (i instanceof FillInQuestion) {
                points += 2;
                }
            if (i instanceof AnyCorrectChoiceQuestion) {
                points += 3;
            }
            System.out.println("Correct answer :)\n");
        } else {
            System.out.println("Wrong answer :(\n");
        }
      }

      System.out.println("Total score is " + points + " out of 12");
  }

   /**
      Presents a question to the user and checks the response.
      @param q the question
      @return Returns whether the question is true or false
   */
   public static boolean presentQuestion(Question q)
   {
      q.display();
      System.out.print("Your answer: ");
      Scanner in = new Scanner(System.in);
      String response = in.nextLine();
      return q.checkAnswer(response);
   }
}
