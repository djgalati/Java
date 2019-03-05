public class FillInQuestion extends Question {
  private String question;

  FillInQuestion() {
    question = "";
  }

  /**
   * Gets the answer and the question from the inputted string
   * @param Q The question that is sent in by setQuestion
   */
  public void setQuestion(String Q){
    question = Q;
    Q = Q.substring(Q.indexOf("_") + 1);
    Q = Q.substring(0, Q.indexOf("_"));
    setAnswer(Q);


    setText((question.substring(0, question.indexOf("_")) + "_____"));
  }

}
