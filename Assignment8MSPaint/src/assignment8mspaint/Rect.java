/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8mspaint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Rect class, extended from Shape, that is used to draw a Rectangle
 * object on the canvas. Takes in 5 variables, then calls on the super class to
 * store them and create the object.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Rect extends Shape {
    
    /**
     * Rect constructor called from the main program, takes in 5 variables then
     * passes those to its super class to store them and create the Rect object
     * 
     * @param x the x position on the canvas
     * @param y the y position on the canvas
     * @param width the width of the object
     * @param height the height of the object
     * @param color the color of the object
     */
    public Rect(double x, double y, double width, double height, Color color){
        super(x, y, width, height, color);
    }
    
    /**
     * Draw method called from main program, uses the 5 stored variables in its
     * super class to draw a filled in rectangle on the canvas, as well as a
     * different colored border around the rectangle
     * 
     * @param gc GraphicsContext
     */
    public void draw(GraphicsContext gc){
        gc.setFill(super.getColor());
        gc.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        if(super.getColor() == Color.BLACK){
            gc.setStroke(Color.RED);
        } else {
            gc.setStroke(Color.BLACK);
        }
        gc.setLineWidth(2);
        gc.strokeRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        gc.setLineWidth(1);
    }
}
