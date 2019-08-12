package edu.cnm.deepdive.triviagame.model.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import edu.cnm.deepdive.triviagame.R;
import edu.cnm.deepdive.triviagame.model.dao.TriviaAnswersDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaCategoryDao;
import edu.cnm.deepdive.triviagame.model.dao.TriviaQuestionDao;
import edu.cnm.deepdive.triviagame.model.entity.TriviaAnswers;
import edu.cnm.deepdive.triviagame.model.entity.TriviaCategory;
import edu.cnm.deepdive.triviagame.model.entity.TriviaQuestion;
import edu.cnm.deepdive.triviagame.model.pojo.TriviaPojo;
import edu.cnm.deepdive.triviagame.model.pojo.TriviaPojo.TriviaResult;
import java.io.IOException;
import okio.Okio;
import org.apache.commons.text.StringEscapeUtils;

/**
 * This is the class that sets up the database with all necessary tables. It
 * will also pull in data from a file when it is first initialized.
 */
@Database(
    entities = {TriviaCategory.class, TriviaQuestion.class, TriviaAnswers.class},
    version = 2,
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
    return instance;
  }

  private static synchronized void forgetInstance(Context context) {
    instance = null;
  }

  public abstract TriviaCategoryDao getTriviaCategoryDao();

  public abstract TriviaQuestionDao getTriviaQuestionDao();

  public abstract TriviaAnswersDao getTriviaAnswersDao();

  private static class Callback extends RoomDatabase.Callback {

    private Context context;

    Callback(Context context) {
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

    PrepopulateTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      try {
        TriviaDatabase db = getInstance(context);
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<TriviaPojo> jsonAdapter = moshi.adapter(TriviaPojo.class);
        TriviaPojo pojo = jsonAdapter.fromJson(Okio.buffer(Okio.source(
            context.getResources().openRawResource(R.raw.preload))));
        TriviaCategoryDao cDao = db.getTriviaCategoryDao();
        TriviaQuestionDao qDao = db.getTriviaQuestionDao();
        TriviaAnswersDao aDao = db.getTriviaAnswersDao();

        long catId = cDao.insert(new TriviaCategory("General Knowledge"));

        for (TriviaResult result : pojo.getResults()) {
          long queId = qDao
              .insert(new TriviaQuestion(StringEscapeUtils.unescapeHtml4(result.getQuestion()),
                  result.getDifficulty(), catId));
          aDao.insert(
              new TriviaAnswers(StringEscapeUtils.unescapeHtml4(result.getCorrectAnswer()), true,
                  queId));
          for (String s : result.getIncorrectAnswers()) {
            s = StringEscapeUtils.unescapeHtml4(s);
            aDao.insert(new TriviaAnswers(s, false, queId));
          }
        }
        forgetInstance(context);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
  }

}
