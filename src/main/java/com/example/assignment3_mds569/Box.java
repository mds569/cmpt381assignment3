package com.example.assignment3_mds569;

import javafx.scene.paint.Color;

public class Box {
    /**
     * Boxes are the objects stored in the model.
     */

    // X and Y values for this Box
    // Width and Height values for this Box
    private double x, y, width, height;


    public Box(double x, double y, double width, double height)
    {
        // Set instance variables
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public double getWidth() {return this.width;}

    public double getHeight() {return this.height;}

    /**
     * Set the new position for the box.
     * @param newX - The new x value.
     * @param newY - The new y value.
     */
    public void setPosition(double newX, double newY)
    {
        this.x = newX;
        this.y = newY;
    }

    public void setWidth(double newWidth) {this.width = newWidth;}

    public void setHeight(double newHeight) {this.height = newHeight;}

    /**
     * Set the new size for the box.
     * @param newWidth - The new width for this box.
     * @param newHeight - The new height for this box.
     */
    public void setSize(double newWidth, double newHeight)
    {
        this.width = newWidth;
        this.height = newHeight;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

}
