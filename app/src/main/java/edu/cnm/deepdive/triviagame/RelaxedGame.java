package edu.cnm.deepdive.triviagame;

import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import java.util.List;
import java.util.Scanner;

public class RelaxedGame {

  private int questionsCorrect;
  private int totalQuestions;
  private List<TriviaQuestion> questionList;

  public RelaxedGame(List<TriviaQuestion> questionList) {
    this.questionList = questionList;
    totalQuestions = questionList.size();
    playGame();
  }

  private void playGame() {
    Scanner input = new Scanner(System.in);
    boolean gameOver = false;
    int questionIndex = 0;
    int answerIndex;

    while (!gameOver) {
      if (questionIndex == questionList.size()) {
        System.out.printf("You got %d/%d questions correct!\n", questionsCorrect, totalQuestions);
        gameOver = true;
      } else {
        System.out.println(questionList.get(questionIndex).toString());
        System.out.println(questionList.get(questionIndex).getAnswers());

        System.out.println("Please put the index of your answer: ");
        answerIndex = input.nextInt();

        if (questionList.get(questionIndex)
            .getAnswers().get(answerIndex)
            .equals(questionList.get(questionIndex).getCorrectAnswer())) {

          System.out.println("Correct!");
          System.out.println("Onto the next question!");
          questionsCorrect++;
          questionIndex++;

        } else {
          System.out.println("Incorrect!");
          System.out.println("Onto the next question!");
          questionIndex++;
        }
      }
    }
  }
}
