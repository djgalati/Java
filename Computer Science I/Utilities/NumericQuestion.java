public class NumericQuestion extends Question {
  private double answer;

  public NumericQuestion() {
    answer = 0.0;
  }

  /**
   * This sets the correct answer in Question, and allows NumericQuestion to
   * do the math for checkAnswer
   * @param correctResponse
   */
  public void setAnswer(String correctResponse) {
      super.setAnswer(correctResponse);
      answer = Double.parseDouble(correctResponse);
  }

  /**
   * Checks to see if the inputted answer is in within .01 of the correct answer
   * @param response Users input
   * @return Returns true or false
   */
  public boolean checkAnswer(String response) {
    final double within = 0.01;

    double input = Double.parseDouble(response);
    // Checks to see if the input and answer are wihin 0.01 of each other.
    return (Math.abs(answer - input) < within);
  }

  public void display() {
    super.display();
  }
}
