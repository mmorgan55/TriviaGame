package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class TriviaCategory {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "category_id")
  private long categoryId;

  @NonNull
  @ColumnInfo(name = "category_title")
  private String categoryTitle;


  public TriviaCategory(@NonNull String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }

  @NonNull
  public String getCategoryTitle() {
    return categoryTitle;
  }

  @NonNull
  @Override
  public String toString() {
    return getCategoryTitle();
  }
}

