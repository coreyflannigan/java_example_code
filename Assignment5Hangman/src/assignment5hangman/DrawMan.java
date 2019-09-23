/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5hangman;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * This is the DrawMan class of the Hangman program. It stores the current state
 * of the drawn man (between 0 and 8), provides a getter to return that state
 * to the Word class, and has a draw method where the man is drawn step by step
 * based on wrong letter guesses from the user.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class DrawMan {
    // int variable between 0-8 representing how far along the drawing of the man is
    private int manState;
    
    /**
     * Constructor for the DrawMan class, accepts an int param that will contain
     * a 0, in order to reset the state of the drawing back to 0.
     * 
     * @param manState int variable containing a 0, to reset the state of the
     * drawn man
     */
    public DrawMan(int manState){
        this.manState = manState;
    }
    
    /**
     * wrongGuess method called from within the charEntry method inside the Word
     * class, increments the state of the drawing of the man by 1 due to a wrong
     * letter guess from the user.
     */
    public void wrongGuess(){
        manState += 1;
    }
    
    /**
     * checkState method called from the Word class in order to check how far
     * along the drawing of the man is and decide if the game is over as a loss.
     * User loses when the manState variable reaches 8.
     * 
     * @return manState int variable containing a number from 0-8, representing
     * the current state of the drawing of the man
     */
    public int checkState(){
        return manState;
    }
    
    /**
     * draw method, which is the third of three methods of its type, used to
     * pass along the GraphicsContext param from the view to this final
     * destination in the DrawMan class. Once called, a switch structure checks
     * the manState int variable and will draw the next part of the man based
     * on what state that variable is currently up to.
     * 
     * @param gc Graphics context originally from the view
     */
    public void draw(GraphicsContext gc){
        switch(manState){
            case 0:
                break;
            case 1:
                gc.strokeOval(115, 65, 10, 10);
                break;
            case 2:
                gc.strokeLine(120, 75, 120, 115);
                break;
            case 3:
                gc.strokeLine(105, 75, 120, 95);
                break;
            case 4:
                gc.strokeLine(135, 75, 120, 95);
                break;
            case 5:
                gc.strokeLine(120, 115, 105, 135);
                break;
            case 6:
                gc.strokeLine(120, 115, 135, 135);
                break;
            case 7:
                gc.strokeOval(117, 68, 2, 2);
                break;
            case 8:
                gc.strokeOval(121, 68, 2, 2);
                break;
                
        }
    }
}
