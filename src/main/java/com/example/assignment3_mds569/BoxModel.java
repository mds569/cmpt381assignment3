package com.example.assignment3_mds569;

import java.util.ArrayList;

public class BoxModel {
    /**
     * This is the model storing all the Boxes.
     */

    // List of subscribers
    //private ArrayList<Subscriber> subs;

    // Newly added PublishSubscribe
    private PublishSubscribe pubSub;

    // List of Boxes
    private ArrayList<Box> boxes;

    public BoxModel()
    {
        //subs = new ArrayList<Subscriber>();
        boxes = new ArrayList<Box>();
    }

    // Add a new subscriber
    //public void addSubscriber(Subscriber sub) {subs.add(sub);}

    // Add a box to the list
    public void addBox(double x, double y, double width, double height)
    {
        Box box = new Box(x, y, width, height);
        boxes.add(box);
        //notifySubscribers();
        pubSub.publish("create");

    }

    // Remove box from list
    public void removeBox()
    {
        if (boxes.size()>0)
        {
            boxes.remove(boxes.size()-1);
            //notifySubscribers();
            pubSub.publish("delete");
        }
    }

    // Delete a box at a specific index
    public void deleteBox(int index) {
        boxes.remove(index);
        //notifySubscribers();
        pubSub.publish("delete");
    }

    // Return list of boxes
    public ArrayList<Box> getBoxes() {return this.boxes;}

    // Return Box at index
    public Box getBox(int index) {
        return this.boxes.get(index);
    }

    public void editBoxX(int index, double x) {
        this.getBox(index).setX(x);
        //notifySubscribers();
        pubSub.publish("update");
    }

    public void editBoxY(int index, double y) {
        this.getBox(index).setY(y);
        //notifySubscribers();
        pubSub.publish("update");
    }

    public void editBoxWidth(int index, double width) {
        this.getBox(index).setWidth(width);
        //notifySubscribers();
        pubSub.publish("update");
    }

    public void editBoxHeight(int index, double height) {
        this.getBox(index).setHeight(height);
        //notifySubscribers();
        pubSub.publish("update");
    }

    public void editBox(int index, double x, double y, double width, double height) {
        this.getBox(index).setPosition(x, y);
        this.getBox(index).setSize(width, height);
        //notifySubscribers();
        pubSub.publish("update");
    }

    /*
    // Calls the update method of each of the subscribers
    private void notifySubscribers(){
        for (Subscriber subscriber : subs)
        {
            subscriber.modelChanged();
        }
    }*/

    // Store the instance of the PublishSubscribe instance the program is using so that we can call it
    public void setPubSub(PublishSubscribe pubSub){
        this.pubSub = pubSub;
    }
}
