# TriviaGame Project

## Why I made this app.
* The original concept I had for this game was to be a multiplayer trivia game with the ability to have
many customizable features that could be added from the backend. Unfortunately that was out of scope, but
it gets to the main reason that I wanted to build this game, and that was to really get practice with 
developing databases for applications as that is something I had no knowledge of coming into the bootcamp.
I thought that making a simple game with a more complex backend would be a great way to learn the basics
and use that to help me build a more complex backend for the capstone projects.

* Trivia games are a great way to learn random facts that you might otherwise never learn. In this way, I
think the app would appeal to anyone that likes learning random facts and anyone who would like to learn
in a fun and engaging way.

## App Readiness
* The app is in a state of functional completeness and in a decent design state. Navigation in any fragment
is functional, other than a minor issue when playing the timed game mode. All game modes can be played
without having any crashes (that I am aware of). There are no major bugs that are known but there are a few 
minor design flaws based on the size of the phone's screen.

#### Bugs:
* If a question is long enough, it is possible for the card view on each game fragment to cover the tallies
  at the top for any screen 1280 pixels or lower in height.
* Buttons in all game fragments will overlap on any screen less than 720 pixels in width.
* Sudden Death" button text will go onto two lines and partially disappear on any screen less than 720 
  pixels in width.
* Difficulty" title in difficulty selection will go to two lines on any screen less than 720 pixels in width.
* If you are playing the timed game mode and hit the back button, the timer will not stop and could go below 0
  while the alert dialog is still up. This will not cause a crash even if an option is selected from the 
  dialog, though it will somewhat slow to disappear.
  
## Devices, API, and Restrictions
#### Devices
* Nexus 5X with API levels 28, 26
* Nexus 4 with API levels  28, 26
  
#### Minimum API: 21
  
#### Restrictions
* App is locked in portrait mode.
* All text and questions within app are in American english and no translations are being given.
  
## External Libraries
#### External Libraries used:
* com.jakewharton:butterknife:9.0.0-rc1
* com.jakewharton:butterknife-compiler:9.0.0-rc1
* com.squareup.retrofit2:retrofit:2.4.0
* com.squareup.retrofit2:converter-moshi:2.4.0
* org.apache.commons:commons-text:1.6
* com.google.android.gms:play-services-auth:16.0.1

## External Services
#### External Services used:
* [Google Sign-In Services](https://developers.google.com/identity/sign-in/android/start)
* [Open Trivia Database](https://opentdb.com/)

## Aesthetic/Cosmetic improvements
#### List of improvements that could be made:
* Make correct/incorrect answers more obvious with pop-up texts (not toasts) and sound effects.
* Add more animations to make browsing through the app more pleasant to the eye.
* Differentiate the types and levels of questions with either colors and tags.
* Animate the questions as each one is brought up for a more interesting experience.
* Use more external grpahic resources to make the app more interesting than just the solid colors that are
used currently.

## Functional Stretch Goals
#### List of functional stretch goals:
* Originally, I wanted this to be a multiplayer game, so that would be the first stretch goal.
* A better scoring system for each game type to reflect how well each person did.
* Rework the "Sudden Death" game mode to instead give three chances instead of one.
* Better mix and matching of different categories and difficulties, such as being able to pick multiple or all
categories.
* Give ability to users to choose the amount of questions in certain game modes.
* A highscore system by game-type, category, difficulty, and combinations of them all that can be viewed by all 
players by storing them in a database.
* More game modes.
* A larger list of trivia questions.

## Wireframes

## Entity Relationship Diagram

## JavaDocs

## Licenses

## Build Instructions

## User Instructions
