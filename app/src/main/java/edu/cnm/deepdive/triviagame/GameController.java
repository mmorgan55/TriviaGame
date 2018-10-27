package edu.cnm.deepdive.triviagame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

  private String category;
  private String difficulty;
  private List<TriviaQuestion> questionList;


  public GameController(String gameType) {

    createDummyQuestions();

    switch (gameType) {
      case "relaxed":
        new RelaxedGame(questionList);
        break;
      case "difficult":
        new SuddenDeathGame(questionList);
        break;
      case "time":
        new TimeConstraintGame(questionList);
        break;
      default:
        new RelaxedGame(questionList);
        break;
    }
  }

  private void createDummyQuestions() {

    questionList = new ArrayList<>();

    String one = "What is the frontman's name in the Band Megadeth?";
    String[] answerOne = {"Dave Mustaine", "David Ellefson", "Greg Handevidt", "Dijon Carruthers"};

    String two = "What is the name of Eragon's dragon in the book \"Eragon\"?";
    String[] answerTwo = {"Saphira", "Rubyrta", "Onyxia", "Emeralda"};

    String three = "Who painted \"The Starry Night\"?";
    String[] answerThree = {"Galileo", "Van Gogh", "Michaelangelo", "Da Vinci"};

    String four = "From which country did the piano originate?";
    String[] answerFour = {"Germany", "France", "Italy", "Austria"};

    String five = "The United States was the first country to put a man in Space.";
    String[] answerFive = {"Yes", "No", "Maybe?", "Soviet Union"};

    questionList.add(new TriviaQuestion(one, answerOne[0], Arrays.asList(answerOne)));
    questionList.add(new TriviaQuestion(two, answerTwo[0], Arrays.asList(answerTwo)));
    questionList.add(new TriviaQuestion(three, answerThree[1], Arrays.asList(answerThree)));
    questionList.add(new TriviaQuestion(four, answerFour[2], Arrays.asList(answerFour)));
    questionList.add(new TriviaQuestion(five, answerFive[3], Arrays.asList(answerFive)));
  }

}
