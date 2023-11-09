package com.example.assignment3_mds569;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class InteractionModel {
    /**
     * This class will store all information related to the app's
     * interaction state, including the object cursor.
     */

    // Current x and y values for creating a new box
    private double currentX, currentY;

    // Holds the index of the cursor box
    // FORMERLY SELECTEDINDEX
    private int cursorIndex;

    // List of selected box indexes
    private ArrayList<Integer> selectedList;

    // Subscriber list
    //private ArrayList<Subscriber> subs;

    // Newly added PublishSubscribe
    private PublishSubscribe pubSub;

    public InteractionModel(){
        this.currentX = 50;
        this.currentY = 50;
        this.cursorIndex = -1;
        //this.subs = new ArrayList<Subscriber>();
        this.selectedList = new ArrayList<Integer>();
    }

    public void increaseValue(){
        this.currentX += 50;
        this.currentY += 50;
    }

    public double getCurrentX() {return this.currentX;}

    public void setCurrentX(double x) {this.currentX = x;}

    public void setCurrentY(double y) {this.currentY = y;}

    public void resetCoords() {
        this.currentX = 50;
        this.currentY = 50;
    }

    public double getCurrentY() {return this.currentY;}

    public void setCursor(int cursorIndex){
        this.cursorIndex = cursorIndex;
        //notifySubscribers();
        pubSub.publish("update");
    }


    public int getCursorIndex(){return this.cursorIndex;}

    // Adds index to list of selected indexes
    public void addSelected(int index){
        selectedList.add(index);
        //notifySubscribers();
        pubSub.publish("selection");
    }

    // Return list of indexes representing selected boxes
    public ArrayList<Integer> getSelectedList() {return this.selectedList;}

    // Removes the provided index from the list of selected indexes
    public void removeFromSelectedList(int index) {
        Integer listIndex = index;
        selectedList.remove(listIndex);
        //notifySubscribers();
        pubSub.publish("selection");
    }

    // Clears list of selected indexes
    public void clearSelectedList() {
        selectedList.clear();
        //notifySubscribers();
        pubSub.publish("selection");
    }

    // Check if the selected list contains the index provided
    public Boolean selectedListContains(int index){
        return this.selectedList.contains(index);
    }

    /*
    // Add a new subscriber
    public void addSubscriber(Subscriber sub) {subs.add(sub);}

    // Calls the update method of each of the subscribers
    public void notifySubscribers(){
        for (Subscriber subscriber : subs)
        {
            subscriber.iModelChanged();
        }
    } */

    // Store the instance of the PublishSubscribe instance the program is using so that we can call it
    public void setPubSub(PublishSubscribe pubSub){
        this.pubSub = pubSub;
    }

    // Call the instance of the PublishSubscribe the program is using
    public PublishSubscribe getPubSub() {return this.pubSub;}
}
