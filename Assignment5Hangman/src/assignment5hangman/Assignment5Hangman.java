/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5hangman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * This is the view portion of the Hangman Game. Variety of TextField, Labels,
 * Buttons, and a Canvas are used to provide information to the user and set the
 * stage for the game. Canvas and Hangman stand are initially drawn, but play
 * does not commence until the user clicks the 'get word' button, controlled by
 * use of a firstPlay boolean flag check. The 'get word' button, changed to 'get
 * new word' after first press, will then call out to the main game logic class
 * which will initialize a word to be passed back to a label on the gui. 'Submit
 * guess' button calls the main game logic with the guess as an arg for validation
 * and subsequent checking against the secret word or phrase, canvas is then
 * amended with draw calls on an incorrect guess, or the dashed out word is
 * updated in its label with the correct guess replacing respective dashes.
 * Play continues until win or lose, in which case player is locked from input
 * until they press the 'get new word' button again. Wins and losses are tracked
 * across multiple plays.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Assignment5Hangman extends Application {

    // TODO: Instance Variables for View Components and Model
    /**
     * firstPlay boolean check flag, labels, textfields, buttons, canvas, and
     * final label titles intialized here. Hangman logic class created.
     */
    boolean firstPlay = true;
    TextField txtUsrEntry;
    Label lblTitle, lblUsrEntry, lblWord, lblGuessedLetters, lblHint;
    Label lblWins, lblLosses;
    Button btnSubmit, btnGetWord;
    Canvas canvas = new Canvas(200, 200);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    final String GUESSED_LETTER_TITLE = "Guessed Letters: ";
    final String HINT_TITLE = "Hint: ";
    final String WIN_TITLE = "Wins: ";
    final String LOSS_TITLE = "Losses: ";
    
    Hangman game = new Hangman();
    
    // TODO: Private Event Handlers and Helper Methods
    /**
     * action event for the submit button. on click, first checked against the
     * boolean firstPlay flag to ensure user has requested a word before play 
     * can begin. if flag returns false, handler then passes the guess to the
     * game logic class for validation. game canvas is then updated based on
     * game logic's decision and follow on calls regarding the passed guess arg.
     * after that, all labels are updated based on calls to the game logic class.
     * 
     * @param e listener for 'submit guess' button click
     */
    public void submitHandler(ActionEvent e){
        if (firstPlay == true){
            lblUsrEntry.setText("Error: You must get a word first to begin play.");
        } else {
            game.charEntry(txtUsrEntry.getText());
            game.draw(gc);
            lblTitle.setText(game.titleCheck());
            lblUsrEntry.setText(game.usrEntryCheck());
            lblWord.setText(game.getCurrentWord());
            lblGuessedLetters.setText(GUESSED_LETTER_TITLE + game.letterCheck());
            lblWins.setText(WIN_TITLE + game.getWins());
            lblLosses.setText(LOSS_TITLE + game.getLosses());
        }
    }
    
    /**
     * action even for the 'get word' button. on click, game logic class is
     * called on to implement its code and set both a hidden word/phrase and a 
     * String of dashes to represent that word/phrase. firstPlay check flag is
     * changed to false, indicating play and guesses can commence, and 'get word'
     * button text is updated to 'get new word'. all labels are then updated with
     * calls to the game logic class, with the error label first cleared to ensure
     * a clear canvas in the event of no errors returned after a 'submit guess'
     * button click. finally, canvas and empty hangman stand are redrawn to clear
     * the board.
     * 
     * @param e listener for 'get word' button click
     */
    public void getWordHandler(ActionEvent e){
        game.getWord();
        firstPlay = false;
        lblGuessedLetters.setText(GUESSED_LETTER_TITLE);
        btnGetWord.setText("Get new Word");
        lblTitle.setText(game.titleCheck());
        txtUsrEntry.setText("");
        lblUsrEntry.setText("");
        lblUsrEntry.setText(game.usrEntryCheck());
        lblWord.setText(game.getCurrentWord());
        lblHint.setText(HINT_TITLE + game.getHint());
        lblWins.setText(WIN_TITLE + game.getWins());
        lblLosses.setText(LOSS_TITLE + game.getLosses());
        drawCanvas();
    }
    
    /**
     * draw canvas method called from the 'get word' action event in order to
     * clear the board and redraw the hangman's stand.
     */
    public void drawCanvas(){
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, 200, 200);
        gc.setFill(Color.BLACK);
        gc.strokeLine(40, 40, 40, 195);
        gc.strokeLine(0, 195, 80, 195);
        gc.strokeLine(40, 40, 120, 40);
        gc.strokeLine(120, 40, 120, 60);
    }
    
    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setStyle("-fx-background-color:transparent;");
        Scene scene = new Scene(root, 600, 600, Color.BURLYWOOD); // set the size here
        scene.setFill(Color.BURLYWOOD);
        stage.setTitle("Game of Hangman"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        drawCanvas();
        // 2. Create the GUI components
        lblTitle = new Label("Playing Hangman!");
        txtUsrEntry = new TextField("");
        lblUsrEntry = new Label("Click the Get a Word Button then Enter a letter guess here!");
        lblWord = new Label("");
        btnSubmit = new Button("Submit Guess");
        btnGetWord = new Button("Get a Word");
        lblGuessedLetters = new Label(GUESSED_LETTER_TITLE);
        lblHint = new Label(HINT_TITLE);
        lblWins = new Label(WIN_TITLE + game.getWins());
        lblLosses = new Label(LOSS_TITLE + game.getLosses());
        // 3. Add components to the root
        root.getChildren().addAll(lblTitle, txtUsrEntry, lblUsrEntry, lblWord,
                btnSubmit, btnGetWord, canvas, lblGuessedLetters, lblHint, 
                lblWins, lblLosses);
        // 4. Configure the components (colors, fonts, size, location)
        lblTitle.setLayoutX(250);
        lblTitle.setLayoutY(50);
        
        txtUsrEntry.setLayoutX(270);
        txtUsrEntry.setLayoutY(420);
        txtUsrEntry.setPrefColumnCount(3);
        
        lblUsrEntry.setLayoutX(160);
        lblUsrEntry.setLayoutY(400);
        
        lblHint.setLayoutX(230);
        lblHint.setLayoutY(320);
        
        lblWord.setLayoutX(235);
        lblWord.setLayoutY(340);
        lblWord.setFont(new Font("Arial", 20));
        
        lblGuessedLetters.setLayoutX(50);
        lblGuessedLetters.setLayoutY(520);
        
        btnSubmit.setLayoutX(170);
        btnSubmit.setLayoutY(460);
        
        btnGetWord.setLayoutX(330);
        btnGetWord.setLayoutY(460);
        
        canvas.setLayoutX(200);
        canvas.setLayoutY(100);
        
        lblWins.setLayoutX(30);
        lblWins.setLayoutY(30);
        
        lblLosses.setLayoutX(30);
        lblLosses.setLayoutY(50);
        
        // 5. Add Event Handlers and do final setup
        btnSubmit.setOnAction(this::submitHandler);
        btnGetWord.setOnAction(this::getWordHandler);
        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
