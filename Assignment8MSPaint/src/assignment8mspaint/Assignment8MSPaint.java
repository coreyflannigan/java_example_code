/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8mspaint;

import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This is the Mohawk Super Paint (MSPaint) Assignment program. It creates a
 * large scene with a large white canvas on top. Users are able to draw circles
 * or rectangles, using buttons to switch between, by clicking and dragging on
 * the canvas or by manual number entry. Error exceptions are handled by displaying
 * the issue in a pop up to the user. Users can also change shape color, undo
 * last shape, and reset the canvas via respective buttons.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Assignment8MSPaint extends Application {

    // TODO: Instance Variables for View Components and Model
    /**
     * Buttons, Labels, TextFields, and Canvas objects set up here for arrangement
     * in the scene. Double variables, boolean flags, ArrayList, and required
     * objects also initialized here.
     */
    Button btnReset, btnRect, btnCirc, btnUndo, btnBox, btnMouse, btnMove,
            btnRecolor;
    Label lblWidth, lblHeight, lblInstructions, lblShape, lblSizing;
    TextField txtWidth, txtHeight;
    Canvas canvas = new Canvas(980, 580);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double x1, x2, y1, y2, width, height;
    boolean rectFlag = false, circFlag = false, mouseFlag = true, boxFlag = false,
            moveFlag = false, recolorFlag = false;
    ArrayList<Shape> shapes = new ArrayList<>();
    Circ circ;
    Rect rect;
    ColorPicker picker;
    Color color;
    
    // TODO: Private Event Handlers and Helper Methods
    
    /**
     * Action Handler for mouse click on canvas, stores x,y values into x1,y1
     * variables.
     * 
     * @param me the event that signifies a mouse click
     */
    private void pressHandler(MouseEvent me) {
        x1 = me.getX();
        y1 = me.getY();
    }
    
    /**
     * Action Handler for mouse click release on canvas, stores x,y values into
     * x2,y2 variables. Boolean flags are checked to confirm what buttons are
     * currently selected, then an object is created and drawn using x1,y1,x2,y2,
     * or an existing object is moved, or recolored.
     * 
     * @param me the event that signifies the release following a mouse click
     */
    private void releaseHandler(MouseEvent me) {
        if (rectFlag){
            if (mouseFlag){
                x2 = me.getX();
                y2 = me.getY();
                color = picker.getValue();
                checkXY();
                rect = new Rect(x1, y1, width, height, color);
                shapes.add(rect);
                rect.draw(gc);
            } else if (boxFlag){
                color = picker.getValue();
                try{
                    rect = new Rect(x1, y1, Double.parseDouble(txtWidth.getText()), 
                        Double.parseDouble(txtHeight.getText()), color);
                    shapes.add(rect);
                    rect.draw(gc);
                } catch (NumberFormatException e){
                    new Alert(Alert.AlertType.WARNING, 
                            "Width and Height Boxes Must Contain a Number").showAndWait();
                }
            }
        } else if (circFlag) {
            if (mouseFlag){
                x2 = me.getX();
                y2 = me.getY();
                color = picker.getValue();
                checkXY();
                circ = new Circ(x1, y1, width, height, color);
                shapes.add(circ);
                circ.draw(gc);
            } else if (boxFlag){
                color = picker.getValue();
                try{
                    circ = new Circ(x1, y1, Double.parseDouble(txtWidth.getText()), 
                            Double.parseDouble(txtHeight.getText()), color);
                    shapes.add(circ);
                    circ.draw(gc);
                } catch(NumberFormatException e){
                    new Alert(Alert.AlertType.WARNING, 
                            "Width and Height Boxes Must Contain a Number").showAndWait();
                }
            }
        } else if (moveFlag){
            x2 = me.getX();
            y2 = me.getY();
            color = picker.getValue();
            Collections.reverse(shapes);
            for (Shape s: shapes){
                if (x1 > s.getX() && x1 < (s.getX() + s.getWidth()) &&
                        y1 > s.getY() && y1 < (s.getY() + s.getHeight())){
                    x1 = x1 - s.getX();
                    y1 = y1 - s.getY();
                    s.setX(x2 - x1);
                    s.setY(y2 - y1);
                }
            }
            Collections.reverse(shapes);
            drawCanvas();
            for (Shape s: shapes){
                if (s instanceof Rect){
                    ((Rect) s).draw(gc);
                } else if (s instanceof Circ){
                    ((Circ) s).draw(gc);
                }
            }
        } else if (recolorFlag){
            color = picker.getValue();
            Collections.reverse(shapes);
            for (Shape s: shapes){
                if (x1 > s.getX() && x1 < (s.getX() + s.getWidth()) &&
                        y1 > s.getY() && y1 < (s.getY() + s.getHeight())){
                    s.setColor(color);
                    break;
                }
            }
            Collections.reverse(shapes);
            drawCanvas();
            for (Shape s: shapes){
                if (s instanceof Rect){
                    ((Rect) s).draw(gc);
                } else if (s instanceof Circ){
                    ((Circ) s).draw(gc);
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, 
                            "Must Select a Rectangle or a Circle").showAndWait();
        }
    }
    
    /**
     * Method called when using mouse sizing, to ensure shape is drawn in the
     * right direction and with the correct width and height values
     */
    private void checkXY(){
        if (x1 <= x2){
            width = x2 - x1;
        } else{
            width = x1 - x2;
            x1 = x2;
        }
        
        if (y1 <= y2){
            height = y2 - y1;
        } else{
            height = y1 - y2;
            y1 = y2;
        }
    }
    
    /**
     * Action Handler for the reset canvas button, redraws the blank canvas.
     * 
     * @param e the event that signifies the reset button is clicked
     */
    private void resetButton(ActionEvent e){
        drawCanvas();
        shapes.clear();
    }
    
    /**
     * Action Handler for the undo button, removes the last object in the shapes
     * ArrayList, redraws the blank canvas, then redraws the remaining objects
     * in the ArrayList
     * 
     * @param e the event that signifies the undo button is clicked
     */
    private void undoButton(ActionEvent e){
        try {
            shapes.remove((shapes.size() - 1));
            drawCanvas();
            for (Shape s: shapes){
                if (s instanceof Rect){
                    ((Rect) s).draw(gc);
                } else if (s instanceof Circ){
                    ((Circ) s).draw(gc);
                }
            }
        } catch(ArrayIndexOutOfBoundsException er){
            new Alert(Alert.AlertType.WARNING, 
                    "No More Shapes to Undo").showAndWait();
        }
    }
    
    /**
     * Action Handler for the rectangle button, sets the rectangle flag to true,
     * the circle flag to false, and updates the shape label
     * 
     * @param e the event that signifies the rectangle button is clicked
     */
    private void rectButton(ActionEvent e){
        rectFlag = true;
        circFlag = false;
        moveFlag = false;
        recolorFlag = false;
        lblShape.setText("Rectangle Selected");
    }
    
    /**
     * Action Handler for the circle button, sets the circle flag to true,
     * the rectangle flag to false, and updates the shape label
     * 
     * @param e the event that signifies the circle button is clicked
     */
    private void circButton(ActionEvent e){
        circFlag = true;
        rectFlag = false;
        moveFlag = false;
        recolorFlag = false;
        lblShape.setText("Circle Selected");
    }
    
    /**
     * Action Handler for the move button, drags the object clicked to a new
     * location
     * 
     * @param e the event that signifies the move button is clicked
     */
    private void moveButton(ActionEvent e){
        moveFlag = true;
        circFlag = false;
        rectFlag = false;
        recolorFlag = false;
        lblShape.setText("Move Shape");
    }
    
    /**
     * Action Handler for the recolor button, allows user to click on existing
     * shapes to change their color
     * 
     * @param e the event that signifies the recolor button is clicked
     */
    private void recolorButton(ActionEvent e){
        recolorFlag = true;
        circFlag = false;
        rectFlag = false;
        moveFlag = false;
        lblShape.setText("ReColor Shape");
    }
    
    /**
     * Action Handler for the mouse sizing button, sets the mouse flag to true,
     * the manual box flag to false, and updates the sizing label
     * 
     * @param e the event that signifies the mouse sizing button is clicked
     */
    private void mouseButton(ActionEvent e){
        mouseFlag = true;
        boxFlag = false;
        lblSizing.setText("Mouse Sizing Selected");
    }
    
    /**
     * Action Handler for the manual sizing button, sets the manual box flag to
     * true, the mouse flag to false, and updates the sizing label
     * 
     * @param e the event that signifies the manual sizing button is clicked
     */
    private void boxButton(ActionEvent e){
        boxFlag = true;
        mouseFlag = false;
        lblSizing.setText("Manual Sizing Selected");
    }
    
    /**
     * Method to redraw a blank white canvas
     */
    public void drawCanvas(){
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 980, 580);
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
        // Scene is sized and given a Dark Gray background
        root.setStyle("-fx-background-color:transparent;");
        Scene scene = new Scene(root, 1000, 800, Color.DARKGRAY); // set the size here
        scene.setFill(Color.DARKGRAY);
        stage.setTitle("Mohawk Super Paint"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        drawCanvas();
        // 2. Create the GUI components
        btnReset = new Button("Reset Canvas");
        btnMove = new Button("Move Shape");
        btnRecolor = new Button("ReColor");
        btnRect = new Button("Rectangle");
        btnCirc = new Button("Circle");
        btnUndo = new Button("Undo");
        btnBox = new Button("Manual Size");
        btnMouse = new Button("Mouse Size");
        txtWidth = new TextField("");
        txtHeight = new TextField("");
        picker = new ColorPicker(Color.BLACK);
        lblShape = new Label("No Shape Selected");
        lblSizing = new Label("Mouse Sizing Selected");
        lblWidth = new Label("Width");
        lblHeight = new Label("Height");
        lblInstructions = new Label("Instructions: \n"
                + "1) Select a shape to draw\n"
                + "2) Select a color from the color picker\n"
                + "3) Select whether to size by mouse drag or by manual entry\n"
                + "4) If mouse size selected, click and drag on the canvas to draw your shape\n"
                + "5) If manual size selected, enter a width and height in numbers in their respective boxes,\n"
                + "        then click on the canvas to draw your shape\n"
                + "6) Select undo to remove last object, or reset canvas to wipe the screen clean\n"
                + "7) Select Move Shape button to click and drag existing shapes on the canvas\n"
                + "8) Select ReColor button then click on an existing shape to change its color");
        // 3. Add components to the root
        root.getChildren().addAll(canvas, btnReset, btnRect, btnCirc, btnUndo,
                picker, btnMouse, btnBox, txtWidth, txtHeight, lblWidth,
                lblHeight, lblInstructions, lblShape, lblSizing, btnMove, btnRecolor);
        // 4. Configure the components (colors, fonts, size, location)

        lblShape.setLayoutX(320);
        lblShape.setLayoutY(600);
        lblShape.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lblShape.setTextFill(Color.DODGERBLUE);
        
        lblSizing.setLayoutX(475);
        lblSizing.setLayoutY(600);
        lblSizing.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lblSizing.setTextFill(Color.DODGERBLUE);

        lblInstructions.setLayoutX(243);
        lblInstructions.setLayoutY(626);
        
        btnReset.setLayoutX(900);
        btnReset.setLayoutY(640);
        
        btnUndo.setLayoutX(900);
        btnUndo.setLayoutY(670);
        
        btnMouse.setLayoutX(720);
        btnMouse.setLayoutY(600);
        
        btnBox.setLayoutX(800);
        btnBox.setLayoutY(600);
        
        txtWidth.setLayoutX(740);
        txtWidth.setLayoutY(700);
        txtWidth.setPrefWidth(60);
        
        lblWidth.setLayoutX(755);
        lblWidth.setLayoutY(685);
        
        txtHeight.setLayoutX(805);
        txtHeight.setLayoutY(700);
        txtHeight.setPrefWidth(60);
        
        lblHeight.setLayoutX(815);
        lblHeight.setLayoutY(685);
        
        btnRect.setLayoutX(10);
        btnRect.setLayoutY(600);
        
        btnCirc.setLayoutX(80);
        btnCirc.setLayoutY(600);
        
        btnMove.setLayoutX(127);
        btnMove.setLayoutY(600);
        
        btnRecolor.setLayoutX(211);
        btnRecolor.setLayoutY(600);
        
        picker.setLayoutX(10);
        picker.setLayoutY(630);
        
        canvas.setLayoutX(10);
        canvas.setLayoutY(10);
        
        // 5. Add Event Handlers and do final setup
        btnReset.setOnAction(this::resetButton);
        btnUndo.setOnAction(this::undoButton);
        btnMouse.setOnAction(this::mouseButton);
        btnBox.setOnAction(this::boxButton);
        btnRect.setOnAction(this::rectButton);
        btnCirc.setOnAction(this::circButton);
        btnMove.setOnAction(this::moveButton);
        btnRecolor.setOnAction(this::recolorButton);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
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