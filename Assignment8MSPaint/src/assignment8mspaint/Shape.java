/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment8mspaint;

import javafx.scene.paint.Color;

/**
 * This is the Shape class that the Rect and Circ classes extend. Contains all
 * the variables required to create shape objects, as well as getters and setters
 * for each.
 * 
 * I, Sean Flannigan, student number 000270501, certify that all code submitted
 * is my own work; that I have not copied it from any other source. I also 
 * certify that I have not allowed my work to be copied by others.
 * 
 * @author Sean Flannigan
 */
public class Shape {
    // these 5 variables are used to create either rectangles or circles
    private double x, y, width, height;
    private Color color;
    
    /**
     * Empty constructor for extension
     */
    public Shape(){}
    
    /**
     * Shape constructor called from Rect or Circ classes to store their specific
     * variable values
     * 
     * @param x the x position on the canvas
     * @param y the y position on the canvas
     * @param width the width of the object
     * @param height the height of the object
     * @param color the color of the object
     */
    public Shape(double x, double y, double width, double height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Getter for x variable
     * 
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Setter for x variable
     * 
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter for y variable
     * 
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Setter for y variable
     * 
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Getter for width variable
     * 
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter for width variable
     * 
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Getter for height variable
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter for height variable
     * 
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Getter for color variable
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for color variable
     * 
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
