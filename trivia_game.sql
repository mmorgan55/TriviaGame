 CREATE TABLE IF NOT EXISTS `TriviaCategory`(
  `category_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `category_title` TEXT NOT NULL
);,

CREATE TABLE IF NOT EXISTS `TriviaQuestion`(
  `question_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `category_id` INTEGER NOT NULL,
  `difficulty` TEXT NOT NULL,
  `question` TEXT NOT NULL,
  FOREIGN KEY(`category_id`)
  REFERENCES`TriviaCategory`(`category_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);,

CREATE TABLE IF NOT EXISTS `TriviaAnswers`(
  `answers_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `question_id` INTEGER NOT NULL,
  `answer` TEXT NOT NULL,
  `is_correct` INTEGER NOT NULL,
  FOREIGN KEY(`question_id`)
  REFERENCES `TriviaQuestion`(`question_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

