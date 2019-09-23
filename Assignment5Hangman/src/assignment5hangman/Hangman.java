/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5hangman;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * This is the main game logic for the Hangman game. the game's view's action
 * handlers will call all the methods within this class in order to validate
 * guess entries and update the variety of labels on the game board. error
 * messages are also stored on this class level in order for label update calls
 * to get the proper error information to display to the user. Hangman class only
 * uses the empty default Constructor, as it is purely a logic class and requires
 * no params to function.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Hangman {
    /**
     * initial variable settings, used to determine the proper error codes and
     * to acquire all the information needed by the view to update its game
     * board labels. Word class object also initialized, which will be 
     * overwritten whenever the user presses the game board's 'get word' button.
     */
    private int error = 0;
    private Word word;
    private String message;
    private String hint;
    private boolean winLossFlag = false;
    private int wins = 0;
    private int loss = 0;
    
    /**
     * titleCheck method called from the view to update the title label. calls
     * on the word object's checkState method to determine if the game is over
     * with either a win or a loss, or is still in current play. checkState
     * method's return is checked in a switch case in order to set the String
     * message with the proper value for the view. on a win or loss, the winLoss
     * check flag is checked to see if it is still false, and if so the win or
     * loss variable is incremented by 1 and winLoss check flag is set to true
     * to prevent additional incrementation within this current game state.
     * 
     * @return message String message of one of the 3 possible current play states
     */
    public String titleCheck(){
        message = "";
        switch(word.checkState()){
            case 1:
                message = "Game Over! You Win!";
                if (winLossFlag == false){
                    wins += 1;
                    winLossFlag = true;
                }
                break;
            case 2:
                message = "Game Over! You Lose!";
                if (winLossFlag == false){
                    loss += 1;
                    winLossFlag = true;
                }
                break;
            case 3:
                message = "Playing Hangman!";
                break;
        }
        return message;
    }
    
    /**
     * usrEntryCheck method called from the view to update its respective label
     * with either a null string or one of 3 error messages. message variable
     * set by running the error variable through a switch case. error variable
     * is established by the Hangman class charEntry validation method, unless
     * it is still a 0 as per its initial declaration at the beginning of the
     * Hangman class.
     * 
     * @return message String message of one of 3 error messages
     */
    public String usrEntryCheck(){
        message = "";
        switch(error){
            case 0:
                break;
            case 1:
                message = "Error: Please enter only a single letter as a guess.";
                break;
            case 2:
                message = "Error: Game over, please click the get new word button to play again.";
                break;
            case 3:
                message = "Error: Please enter at least one letter as a guess.";
        }
        return message;
    }
    
    /**
     * charEntry method called from the view's action handler for the 'submit
     * guess' button. this method validates the String guess parameter by setting
     * the String to all lowercase, then by ensuring that its not an empty 
     * String (length of 0), then ensuring that it is a length of 1 (single 
     * character in the string) AND is between 'a' and 'z' (ensuring an alphabet
     * character) AND the word object's checkState call returns a 3 (game is not
     * over). If all validation goes through, the word object's charEntry method
     * is called and the error variable set to 0. Otherwise the error variable
     * is set to some number depending on which validation failed.
     * 
     * @param guess text entry from the user, acquired from the TextField in the
     * view gui
     */
    public void charEntry(String guess){
        guess = guess.toLowerCase();
        if (guess.length() == 0){
            error = 3;
        } else {
            if ((guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z') 
                    && (guess.length() == 1) 
                    && (word.checkState() == 3)){
                word.charEntry(guess);
                error = 0;
            } else if (!(guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z') 
                    || !(guess.length() == 1)){
                error = 1;
            } else if (!(word.checkState() == 3)){
                error = 2;
            }
        }
    }
    
    /**
     * getWord method that sets a tempNum int variable to a random number between
     * 1 and 12, then runs that number through a switch case which will construct
     * a new word object with the two string arguments required (the solved word,
     * and the dashed string representing the characters of that solved word) and
     * then set the String hint variable to the word object's respective hint.
     * after a new word is set, error variable is set back to 0, and winLoss flag
     * is reset to false.
     */
    public void getWord(){
        int tempNum = (int) (Math.random() * 12 + 1);
        switch(tempNum){
            case 1:
                word = new Word("Dr Robotnik", "-- --------");
                hint = "Video Game Character";
                break;
            case 2:
                word = new Word("Bunny Rabbit", "----- ------");
                hint = "Animal or Rapper";
                break;
            case 3:
                word = new Word("Roberto Luongo", "------- ------");
                hint = "Hockey Player";
                break;
            case 4:
                word = new Word("Backgammon", "----------");
                hint = "Board Game";
                break;
            case 5:
                word = new Word("Costa Rica", "----- ----");
                hint = "Central American Country";
                break;
            case 6:
                word = new Word("Saint Lucia", "----- -----");
                hint = "Island Country";
                break;
            case 7:
                word = new Word("Carpe Diem", "----- ----");
                hint = "Dead Poets Society";
                break;
            case 8:
                word = new Word("Batman and Robin", "------ --- -----");
                hint = "Comic Book Characters";
                break;
            case 9:
                word = new Word("Rectangular Box", "----------- ---");
                hint = "Shape, Often Cardboard";
                break;
            case 10:
                word = new Word("Scotch Tape", "------ ----");
                hint = "Typically Clear Adhesive";
                break;
            case 11:
                word = new Word("Passchendaele", "-------------");
                hint = "Battle in WW1";
                break;
            case 12:
                word = new Word("Eucalyptus Tree", "---------- ----");
                hint = "Plant, Very Popular with Koala Bears";
                break;
            
        }
        error = 0;
        winLossFlag = false;
    }
    
    /**
     * getCurrentWord method call that calls on the word object's getCurrentWord
     * method in order to return the updated current dash filled String
     * representing the hidden word.
     * 
     * @return currentWord String variable from the word object, acquired from
     * a word object method call
     */
    public String getCurrentWord(){
        return word.getCurrentWord();
    }
    
    /**
     * draw method, which is the first of three methods of its type, used to
     * pass along the GraphicsContext param from the view to its eventual
     * destination in the DrawMan class.
     * 
     * @param gc GraphicsContext from the view, passed along to the word object
     */
    public void draw(GraphicsContext gc){
        word.draw(gc);
    }
    
    /**
     * letterCheck method called from the views action handler to query the word
     * object for the guessedLetters String, which holds all the letters the
     * user has currently guessed in the current game state.
     * 
     * @return guessedLetters String variable from the Word class object 
     * representing all the letters the user has guess so far, acquired with a
     * word object letterCheck method call
     */
    public String letterCheck(){
        return word.letterCheck();
    }
    
    /**
     * getHint method called from views action handler for the 'get word' button,
     * returns the String variable hint that is set when the Hangman class
     * getWord method is called in order to update the hint label in the view.
     * 
     * @return hint String variable containing the hint for the hidden word.
     */
    public String getHint(){
        return hint;
    }
    
    /**
     * getWins method called from the views action handlers, returns the int
     * variable wins in order to update the win label in the view.
     * 
     * @return wins int variable containing the number of wins the user has since
     * the Hangman program began running
     */
    public int getWins(){
        return wins;
    }
    
    /**
     * getLosses method called from the views action handlers, returns the int
     * variable loss in order to update the loss label in the view.
     * 
     * @return loss int variable containing the number of losses the user has since
     * the Hangman program began running
     */
    public int getLosses(){
        return loss;
    }
}
