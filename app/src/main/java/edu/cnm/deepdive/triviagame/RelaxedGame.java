//package edu.cnm.deepdive.triviagame;
//
//import android.content.res.Resources;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
//import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RelaxedGame {
//
//  private List<TriviaQuestion> questions;
//  private List<TriviaAnswers> answers;
//  private View view;
//  private int questionsCorrect = 0;
//  private int questionsIncorrect = 0;
//  private TextView correctTally;
//  private TextView incorrectTally;
//  private TextView questionText;
//  private List<Button> answerButtons;
//  private int questionIndex = 0;
//  private OnClickListener listener;
//  private TriviaAnswers correctAnswer;
//
//  public RelaxedGame(List<TriviaQuestion> questions, List<TriviaAnswers> answers, View view) {
//
//    this.questions = questions;
//    this.answers = answers;
//    this.view = view;
//    questionText = view.findViewById(R.id.relaxed_question_text);
//    correctTally = view.findViewById(R.id.relaxed_correct_tally);
//    incorrectTally = view.findViewById(R.id.relaxed_incorrect_tally);
//    Button answers1 = view.findViewById(R.id.relaxed_answer_button1);
//    Button answers2 = view.findViewById(R.id.relaxed_answer_button2);
//    Button answers3 = view.findViewById(R.id.relaxed_answer_button3);
//    Button answers4 = view.findViewById(R.id.relaxed_answer_button4);
//    answerButtons = new ArrayList<>();
//
//    setListener();
//
//    answers1.setOnClickListener(listener);
//    answers2.setOnClickListener(listener);
//    answers3.setOnClickListener(listener);
//    answers4.setOnClickListener(listener);
//    answerButtons.add(answers1);
//    answerButtons.add(answers2);
//    answerButtons.add(answers3);
//    answerButtons.add(answers4);
//
//    updateUI();
//  }
//
//  private void updateUI() {
//    updateTally(questionsCorrect, questionsIncorrect);
//    long qId = questions.get(questionIndex).getQuestionId();
//    questionText.setText(questions.get(questionIndex).getQuestion());
//    int answerListIndex = 0;
//    for (TriviaAnswers answer : answers) {
//      if (answer.getQuestionId() == qId) {
//        if (answer.isCorrect()) {
//          correctAnswer = answer;
//          answerButtons.get(answerListIndex).setText(answer.getAnswer());
//          answerListIndex++;
//        } else {
//          answerButtons.get(answerListIndex).setText(answer.getAnswer());
//          answerListIndex++;
//        }
//      }
//    }
//  }
//
//  private void updateGame(boolean isCorrect) {
//    if (isCorrect) {
//      questionIndex++;
//      questionsCorrect++;
//    } else {
//      questionIndex++;
//      questionsIncorrect++;
//    }
//
//    if (questionIndex < questions.size()) {
//      updateUI();
//    } else {
//      updateTally(questionsCorrect, questionsIncorrect);
//      for (Button button : answerButtons) {
//        button.setEnabled(false);
//      }
//      Toast.makeText(view.getContext(), R.string.relaxed_win_text, Toast.LENGTH_LONG).show();
//    }
//  }
//
//
//  private void setListener() {
//    listener = v -> {
//      switch (v.getId()) {
//        case R.id.relaxed_answer_button1:
//          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
//          break;
//        case R.id.relaxed_answer_button2:
//          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
//          break;
//        case R.id.relaxed_answer_button3:
//          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
//          break;
//        case R.id.relaxed_answer_button4:
//          updateGame(isAnswerCorrect(((Button) v).getText().toString()));
//          break;
//      }
//    };
//  }
//
//  private boolean isAnswerCorrect(String answer) {
//    return correctAnswer.getAnswer().equals(answer);
//  }
//
//  private void updateTally(int correct, int incorrect) {
//    correctTally.setText(Resources.getSystem().getString(R.string.tally_correct, correct));
//    incorrectTally.setText(Resources.getSystem().getString(R.string.tally_incorrect, incorrect));
//  }
//
//
//}
