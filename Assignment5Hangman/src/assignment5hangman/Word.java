/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5hangman;

import javafx.scene.canvas.GraphicsContext;


/**
 *
 * This is the Word class for the Hangman program. A word object is created within
 * the Hangman class (the game's main logic class) and is overwritten each time
 * the user presses the 'get word' button in the gui view. Word class stores the
 * hidden word/phrase, the current, initially dash-filled word, and a String
 * containing all the letters the user has guessed so far for the current word.
 * Word class accepts a validated single char String and checks it against the
 * hidden word/phrase, then responds to any calls from the Hangman game logic
 * class.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Word {
    /**
     * variables initialized in order to store the hidden word/phrase, the
     * current, initially dash-filled word, the letters the user has guessed,
     * the latest letter the user guessed, a DrawMan class object, the int
     * variable that will hold the current game play state, and a checkflag for
     * the character check method.
     */
    private String solvedWord;
    private String currentWord;
    private String guessedLetters = "";
    private String currentGuess = "";
    private DrawMan draw;
    private int playState;
    private boolean wrongFlag = true;
    
    /**
     * Word class Constructor which accepts 2 Strings from the Hangman class,
     * the hidden word/phrase and a String of dashes to represent the hidden
     * word/phrase. DrawMan object draw is then overwritten and passed a 0
     * as an argument, which sets the DrawMan state to 0.
     * 
     * @param solvedWord the hidden word/phrase as passed from the Hangman class
     * @param currentWord the dash-filled string representing the hidden word/phrase
     */
    public Word(String solvedWord, String currentWord){
        this.solvedWord = solvedWord;
        this.currentWord = currentWord;
        draw = new DrawMan(0);
    }
    
    /**
     * charEntry method that takes in a single character String from the Hangman
     * class, already validated for length and to ensure it is an alphabet 
     * character. Method runs a for loop to iterate over the length of the hidden
     * word/phrase, checking the guess character against each character in the
     * hidden word/phrase. If the two characters match, the currentWord variable,
     * which is a String filled with dashes to represent the hidden word/phrase, 
     * is then converted into a char array in order to replace the char at the
     * index of where the match was found with the now revealed char from the
     * hidden word/phrase. currentWord is then built back into a String, and the
     * check flag wrongFlag is set to false temporarily, indicating a correct guess.
     * 
     * If not match is found, the wrongFlag check flag will remain true, in which
     * case the wrongGuess method is called from the DrawMan object draw.
     * currentGuess variable is then set to the guess passed into the method, and
     * the check flag is reset back to true.
     * 
     * @param guess single character String pre-validated and passed from the
     * Hangman class
     */
    public void charEntry(String guess){
        for (int i = 0; i < solvedWord.length(); i++){
            if (guess.charAt(0) == (solvedWord.toLowerCase().charAt(i))){
                char[] currentWordArray = currentWord.toCharArray();
                currentWordArray[i] = solvedWord.charAt(i);
                currentWord = String.valueOf(currentWordArray);
                wrongFlag = false;
            }
        }
        if (wrongFlag == true){
            draw.wrongGuess();
        }
        currentGuess = guess;
        wrongFlag = true;
    }
    
    /**
     * checkState method called from the Hangman class to check what state the
     * game is in. using an if, elif, else structure, the method checks if the
     * hidden word/phrase is equal to the currentWord indicating a win, or if
     * the checkState method from the DrawMan draw object returns a value of 8,
     * indicating a game loss, else sets the playState value to 3, indicating an
     * ongoing game.
     * 
     * @return playState int between 1 and 3 representing a win, loss, or game
     * currently in progress
     */
    public int checkState(){
        if(solvedWord.equals(currentWord)){
            playState = 1;
        } else if (draw.checkState() == 8){
            playState = 2;
        } else{
            playState = 3;
        }
        return playState;
    }
    
    /**
     * getCurrentWord method that is called from the Hangman class in order to
     * return the up to date currentWord, which will then be used to update the
     * current word label in the gui view.
     * 
     * @return currentWord String representing the hidden word/phrase, made up
     * initially of dashes until correct guesses begin replacing letters
     */
    public String getCurrentWord(){
        return currentWord;
    }
    
    /**
     * letterCheck method called from the Hangman class in order to return the
     * String containing all the letters the user has guessed thus far in the
     * current word iteration. Method first checks to see if the String already
     * contains the currentGuess, which is a letter stored during the charEntry
     * method, and if not adds it to the guessedLetters String with a space for
     * separation.
     * 
     * @return guessedLetters String containing all the letters the user has
     * guessed for this current word iteration
     */
    public String letterCheck(){
        if (!(guessedLetters.contains(currentGuess))){
            guessedLetters += currentGuess + " ";
        }
        return guessedLetters;
    }
    
    /**
     * draw method, which is the second of three methods of its type, used to
     * pass along the GraphicsContext param from the view to its eventual
     * destination in the DrawMan class.
     * 
     * @param gc Graphics context originally from the view
     */
    public void draw(GraphicsContext gc){
        draw.draw(gc);
    }
}
