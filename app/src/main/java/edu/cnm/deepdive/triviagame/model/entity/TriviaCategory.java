package edu.cnm.deepdive.triviagame.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * This is the entity class that creates a TriviaCategory object.
 */
@Entity
public class TriviaCategory {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "category_id")
  private long categoryId;

  @NonNull
  @ColumnInfo(name = "category_title")
  private String categoryTitle;

  /**
   * Initializes a TriviaCategory object.
   * @param categoryTitle The title of the category.
   */
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

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public void setCategoryTitle(@NonNull String categoryTitle) {
    this.categoryTitle = categoryTitle;
  }
}

