package edu.cnm.deepdive.triviagame.model.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import edu.cnm.deepdive.triviagame.model.dao.TriviaAnswersDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaCategoryDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaQuestionDao;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;

@Database(
    entities = {TriviaCategory.class, TriviaQuestion.class, TriviaAnswers.class},
    version = 1,
    exportSchema = true
)
public abstract class TriviaDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "trivia_game_db";

  private static TriviaDatabase instance = null;

  public static synchronized TriviaDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(),
          TriviaDatabase.class,
          DATABASE_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return null;
  }

  public static synchronized void forgetInstance(Context context) {
    instance = null;
  }

  public abstract TriviaCategoryDao getTriviaCategoryDao();

  public abstract TriviaQuestionDao getTriviaQuestionDao();

  public abstract TriviaAnswersDao getTriviaAnswersDao();

  private static class Callback extends RoomDatabase.Callback {

    private Context context;

    public Callback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new PrepopulateTask(context).execute();
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }

  private static class PrepopulateTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public PrepopulateTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      TriviaDatabase db = getInstance(context);
      TriviaCategoryDao cDao = db.getTriviaCategoryDao();
      TriviaQuestionDao qDao = db.getTriviaQuestionDao();
      TriviaAnswersDao aDao = db.getTriviaAnswersDao();

      long catId = cDao.insert(new TriviaCategory("Initial"));

      long que1Id = qDao
          .insert(new TriviaQuestion("What is the frontman's name in the Band Megadeth?",
              "easy", catId));

      aDao.insert(new TriviaAnswers("Dave Mustaine", true, que1Id));
      aDao.insert(new TriviaAnswers("David Ellefson", true, que1Id));
      aDao.insert(new TriviaAnswers("Greg Handevidt", true, que1Id));
      aDao.insert(new TriviaAnswers("Dijon Carruthers", true, que1Id));

      long que2Id = qDao
          .insert(new TriviaQuestion("What is the name of Eragon's dragon in the book \"Eragon\"?",
              "easy", catId));

      aDao.insert(new TriviaAnswers("Saphira", true, que2Id));
      aDao.insert(new TriviaAnswers("Rubyrta", true, que2Id));
      aDao.insert(new TriviaAnswers("Onyxia", true, que2Id));
      aDao.insert(new TriviaAnswers("Emeralda", true, que2Id));

      long que3Id = qDao.insert(new TriviaQuestion("Who painted \"The Starry Night\"?",
          "easy", catId));

      aDao.insert(new TriviaAnswers("Galileo", true, que3Id));
      aDao.insert(new TriviaAnswers("Van Gogh", true, que3Id));
      aDao.insert(new TriviaAnswers("Michaelangelo", true, que3Id));
      aDao.insert(new TriviaAnswers("Da Vinci", true, que3Id));

      long que4Id = qDao.insert(new TriviaQuestion("From which country did the piano originate?",
          "easy", catId));

      aDao.insert(new TriviaAnswers("Germany", true, que4Id));
      aDao.insert(new TriviaAnswers("France", true, que4Id));
      aDao.insert(new TriviaAnswers("Italy", true, que4Id));
      aDao.insert(new TriviaAnswers("Austria", true, que4Id));

      long que5Id = qDao.insert(
          new TriviaQuestion("The United States was the first country to put a man in Space.",
              "easy", catId));

      aDao.insert(new TriviaAnswers("Yes", true, que5Id));
      aDao.insert(new TriviaAnswers("No", true, que5Id));
      aDao.insert(new TriviaAnswers("Maybe?", true, que5Id));
      aDao.insert(new TriviaAnswers("Soviet Union", true, que5Id));
      forgetInstance(context);
      return null;
    }
  }

}
