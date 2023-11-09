package com.example.assignment3_mds569;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AppController {
    /**
     * The controller to handle all user events.
     */

    private BoxModel model;
    private InteractionModel iModel;

    private enum InteractionState {IDLE, CTRL_, /*HTKEY_GUIDE*/};
    private InteractionState state = InteractionState.IDLE;

    private Timer timer/*= new Timer()*/;

    public void setModel(BoxModel newModel){
        model = newModel;
    }

    public void setiModel(InteractionModel newIModel){
        iModel = newIModel;
    }


    // Attempt 2 For Question 2
    // Handles any incoming key presses
    public void handleKeyPressed(KeyEvent event){
        switch (state){
            case IDLE:
                handleDefaultState(event);
                //break;
            case CTRL_:
                handleCtrlDownState(event);
                state = InteractionState.IDLE;
                //break;
            /*case HTKEY_GUIDE:
                handleHotKeyGuideState(event);
                state = InteractionState.IDLE;*/
            default:
                break;
        }
    }

    // Attempt 2 For Question 2
    // Handles any key presses while control is not held
    public void handleDefaultState(KeyEvent event){
        switch (event.getCode()){
            case CONTROL:
                state = InteractionState.CTRL_;
                break;
            case TAB:
                handleTabDefault();
                break;
            case LEFT:
                handleLeftKeyDefault();
                break;
            case RIGHT:
                handleRightKeyDefault();
                break;
            case UP:
                handleUpKeyDefault();
                break;
            case DOWN:
                handleDownKeyDefault();
                break;
            default:
                break;
        }
    }

    // Attempt 2 For Question 2
    // Handles any key presses while control is held
    public void handleCtrlDownState(KeyEvent event){
        if (event.isControlDown()) {
            switch (event.getCode()) {
                case C -> handleCCtrl();
                case S -> handleSCtrl();
                case A -> handleACtrl();
                case D -> handleDCtrl();
                case LEFT -> handleLeftKeyCtrl();
                case RIGHT -> handleRightKeyCtrl();
                case UP -> handleUpKeyCtrl();
                case DOWN -> handleDownKeyCtrl();
                case U -> handleUCtrl();
                case J -> handleJCtrl();
                case L -> handleLCtrl();
                case R -> handleRCtrl();
                case T -> handleTCtrl();
                case B -> handleBCtrl();
                case H -> handleHCtrl();
                case V -> handleVCtrl();
            }
        }
    }

    public void handleHotKeyGuideState(KeyEvent event){

    }

    public void startTimer(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openGuide();
            }
        }, 1000);
    }

    // Stops and resets timer
    public void stopTimer(){
        timer.cancel();
        createNewTimer();
    }

    // Show guide
    public void openGuide(){

    }

    // Hide guide
    public void closeGuide(){

    }

    // Assigns a new timer to the variable "timer"
    public void createNewTimer(){
        timer = new Timer();
    }

    public void handleTabDefault(){
        // If there are boxes
        if (!model.getBoxes().isEmpty()) {
            // If the cursor is not set, set it to the first box in the list
            if (iModel.getCursorIndex() == -1) {
                iModel.setCursor(0);
                // If the cursor is at the end of the box list, set it back to the front
            } else if (iModel.getCursorIndex() == (model.getBoxes().size() - 1)) {
                iModel.setCursor(0);
            } else {
                // Otherwise move the cursor to the next box in the model
                iModel.setCursor(iModel.getCursorIndex() + 1);
            }
        }
    }

    // If Control is not held down, this method is used to find and set the cursor to the next left box
    public void handleLeftKeyDefault(){
        int cursorIndex = iModel.getCursorIndex();
        int listSize = model.getBoxes().size();
        if (listSize > 1 && cursorIndex >= 0) {
            double currentX = model.getBox(cursorIndex).getX();
            int next = -1;
            for (int i = 0; i < listSize; i++) {
                Box checkBox = model.getBox(i);
                if (checkBox.getX() < currentX && (next == -1 || checkBox.getX() > model.getBox(next).getX())) {
                    next = i;
                }
            }
            if (next != -1) {
                iModel.setCursor(next);
            }
        }
    }

    // If Control is not held down, this method is used to find and set the cursor to the next right box
    public void handleRightKeyDefault(){
        int cursorIndex = iModel.getCursorIndex();
        int listSize = model.getBoxes().size();
        if (listSize > 1 && cursorIndex >= 0) {
            double currentX = model.getBox(cursorIndex).getX();
            int next = -1;
            for (int i = 0; i < listSize; i++) {
                Box checkBox = model.getBox(i);
                if (checkBox.getX() > currentX && (next == -1 || checkBox.getX() < model.getBox(next).getX())) {
                    next = i;
                }
            }
            if (next != -1) {
                iModel.setCursor(next);
            }
        }
    }

    // If Control is not held down, this method is used to find and set the cursor to the next above box
    public void handleUpKeyDefault(){
        int cursorIndex = iModel.getCursorIndex();
        int listSize = model.getBoxes().size();
        if (listSize > 1 && cursorIndex >= 0) {
            double currentY = model.getBox(cursorIndex).getY();
            int next = -1;
            for (int i = 0; i < listSize; i++) {
                Box checkBox = model.getBox(i);
                if (checkBox.getY() < currentY && (next == -1 || checkBox.getY() > model.getBox(next).getY())) {
                    next = i;
                }
            }
            if (next != -1) {
                iModel.setCursor(next);
            }
        }
    }

    // If Control is not held down, this method is used to find and set the cursor to the next under box
    public void handleDownKeyDefault(){
        int cursorIndex = iModel.getCursorIndex();
        int listSize = model.getBoxes().size();
        if (listSize > 1 && cursorIndex >= 0) {
            double currentY = model.getBox(cursorIndex).getY();
            int next = -1;
            for (int i = 0; i < listSize; i++) {
                Box checkBox = model.getBox(i);
                if (checkBox.getY() > currentY && (next == -1 || checkBox.getY() < model.getBox(next).getY())) {
                    next = i;
                }
            }
            if (next != -1) {
                iModel.setCursor(next);
            }
        }
    }

    public void handleCCtrl(){
        // Find the coordinates for creating a new box
        iModel.increaseValue();
        // Create a new box and add it to the model
        model.addBox(iModel.getCurrentX(), iModel.getCurrentY(), 100, 100);
    }

    public void handleSCtrl(){
        // Get the index of the box that the cursor is on
        int cursorBox = iModel.getCursorIndex();
        // If the selected list already contains this index, remove it (unselecting it)
        if (iModel.selectedListContains(cursorBox)) {
            iModel.removeFromSelectedList(cursorBox);
            // Otherwise we must want to select it, so add it to the selected list
        } else {
            iModel.addSelected(cursorBox);
        }
    }

    public void handleACtrl(){
        // Get the size of the total list of boxes
        int listSize = model.getBoxes().size();
        // If the amount of selected boxes are less than the size of the total boxes list and the total boxes list isnt empty...
        if (iModel.getSelectedList().size() < listSize && !model.getBoxes().isEmpty()) {
            // Then we must need to select all the boxes
            // If the index is not already stored in the selected boxes list, add it
            for (int i = 0; i < listSize; i++) {
                if (!iModel.selectedListContains(i)){
                    iModel.addSelected(i);
                }
            }
            // Otherwise we must want to unselect everything, so we clear the selected list
        } else {
            iModel.clearSelectedList();
        }
    }

    public void handleDCtrl(){
        // Set the cursor back to default to ensure its out of the way (this was causing issues before this was added)
        iModel.setCursor(-1);

        // Loop through the list backwards, this needs to be done because doing it from front to back causes MAJOR issues
        for (int i = iModel.getSelectedList().size() - 1; i >= 0; i--) {
            int index = iModel.getSelectedList().get(i);
            if (index >= 0 && index < model.getBoxes().size()) {
                model.deleteBox(index);
                iModel.removeFromSelectedList(index);
            }
        }
        // If all the boxes have been deleted, make creation of new boxes start at the top left original coordinates
        if (model.getBoxes().isEmpty()) {
            iModel.resetCoords();
        }
    }

    public void handleUCtrl(){
        // Cycle through each selected box and increase it's current width AND height value by 5 each
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setSize(box.getWidth()+5, box.getHeight()+5);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleJCtrl(){
        // Cycle through each selected box and decrease it's current width AND height value by 5 each
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setSize(box.getWidth()-5, box.getHeight()-5);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleLeftKeyCtrl(){
        // Cycle through each selected box and decrease it's current X value by 15
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setX(box.getX()-15);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleRightKeyCtrl(){
        // Cycle through each selected box and increase it's current X value by 15
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setX(box.getX()+15);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleUpKeyCtrl(){
        // Cycle through each selected box and decrease it's current Y value by 15
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setY(box.getY()-15);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleDownKeyCtrl(){
        // Cycle through each selected box and increase it's current Y value by 15
        for (int i = 0; i < iModel.getSelectedList().size(); i++) {
            int index = iModel.getSelectedList().get(i);
            Box box = model.getBox(index);
            box.setY(box.getY()+15);
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleLCtrl(){
        if (!iModel.getSelectedList().isEmpty()) {
            int boxIndex = iModel.getCursorIndex();
            if (iModel.getCursorIndex() == -1) {
                boxIndex = iModel.getSelectedList().get(0);
            }

            Box box = model.getBox(boxIndex);
            double xValueForAlignment = box.getX();

            for (int i = 0; i < iModel.getSelectedList().size(); i++) {
                Box currentBox = model.getBox(iModel.getSelectedList().get(i));
                double newX = xValueForAlignment;
                currentBox.setPosition(newX, currentBox.getX());
            }
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleRCtrl(){
        if (!iModel.getSelectedList().isEmpty()) {
            int boxIndex = iModel.getCursorIndex();
            if (iModel.getCursorIndex() == -1) {
                boxIndex = iModel.getSelectedList().get(0);
            }

            Box box = model.getBox(boxIndex);
            double xValueForAlignment = box.getX() + box.getWidth();

            for (int i = 0; i < iModel.getSelectedList().size(); i++) {
                Box currentBox = model.getBox(iModel.getSelectedList().get(i));
                double newX = xValueForAlignment - currentBox.getWidth();
                currentBox.setPosition(newX, currentBox.getY());
            }
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleTCtrl(){
        if (!iModel.getSelectedList().isEmpty()) {
            int boxIndex = iModel.getCursorIndex();
            if (iModel.getCursorIndex() == -1) {
                boxIndex = iModel.getSelectedList().get(0);
            }

            Box box = model.getBox(boxIndex);
            double yValueForAlignment = box.getY();

            for (int i = 0; i < iModel.getSelectedList().size(); i++) {
                Box currentBox = model.getBox(iModel.getSelectedList().get(i));
                double newY = yValueForAlignment;
                currentBox.setPosition(currentBox.getX(), newY);
            }
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleBCtrl(){
        if (!iModel.getSelectedList().isEmpty()) {
            int boxIndex = iModel.getCursorIndex();
            if (iModel.getCursorIndex() == -1) {
                boxIndex = iModel.getSelectedList().get(0);
            }

            Box box = model.getBox(boxIndex);
            double yValueForAlignment = box.getY() + box.getHeight();

            for (int i = 0; i < iModel.getSelectedList().size(); i++) {
                Box currentBox = model.getBox(iModel.getSelectedList().get(i));
                double newY = yValueForAlignment - currentBox.getHeight();
                currentBox.setPosition(currentBox.getX(), newY);
            }
        }
        //iModel.notifySubscribers();
        iModel.getPubSub().publish("update");
    }

    public void handleHCtrl(){
        // Find out which boxes are the furthest left and right
        int leftBoundInd = 0, rightBoundInd = 0;
        int listSize = iModel.getSelectedList().size();
        if (listSize > 1) {
            for (int i = 0; i < listSize; i++) {
                if (model.getBox(iModel.getSelectedList().get(i)).getX() < model.getBox(iModel.getSelectedList().get(leftBoundInd)).getX()) {
                    leftBoundInd = iModel.getSelectedList().get(i);
                }
                if (model.getBox(iModel.getSelectedList().get(i)).getX() > model.getBox(iModel.getSelectedList().get(rightBoundInd)).getX()) {
                    rightBoundInd = iModel.getSelectedList().get(i);
                }
            }
            // Store those left and right boxes
            Box leftBoundBox = model.getBox(leftBoundInd);
            Box rightBoundBox = model.getBox(rightBoundInd);
            // Get the two X values from those boxes to find out the parcel bounds
            double leftBoundXVal = leftBoundBox.getX();
            double rightBoundXVal = rightBoundBox.getX() + rightBoundBox.getWidth();
            // Calculate the total amount of space given and use that to find the size of each parcel
            double space = rightBoundXVal - leftBoundXVal;
            double parcel = space/listSize;

            // Calculate each boxes new X value
            for (int j = 0; j < listSize; j++) {
                int index = iModel.getSelectedList().get(j);
                if (index != leftBoundInd && index != rightBoundInd) {
                    double xVal = leftBoundXVal + j * parcel;
                    model.getBox(index).setPosition(xVal, model.getBox(index).getY());
                }
            }
        }
        iModel.getPubSub().publish("update");
    }

    public void handleVCtrl(){
        // Find out which boxes are the highest and lowest
        int topBoundInd = 0, bottomBoundInd = 0;
        int listSize = iModel.getSelectedList().size();
        if (listSize > 1) {
            for (int i = 0; i < listSize; i++) {
                if (model.getBox(iModel.getSelectedList().get(i)).getY() < model.getBox(iModel.getSelectedList().get(topBoundInd)).getY()) {
                    topBoundInd = iModel.getSelectedList().get(i);
                }
                if (model.getBox(iModel.getSelectedList().get(i)).getY() > model.getBox(iModel.getSelectedList().get(bottomBoundInd)).getY()) {
                    bottomBoundInd = iModel.getSelectedList().get(i);
                }
            }
            // Store the highest and lowest boxes
            Box topBoundBox = model.getBox(topBoundInd);
            Box bottomBoundBox = model.getBox(bottomBoundInd);
            // Get the two Y values from those boxes to find out the parcel bounds
            double topBoundYVal = topBoundBox.getY();
            double bottomBoundYVal = bottomBoundBox.getY() + bottomBoundBox.getHeight();
            // Calculate the total amount of space given and use that to find the size of each parcel
            double space = bottomBoundYVal - topBoundYVal;
            double parcel = space/listSize;

            // Calculate each boxes new Y value
            for (int j = 0; j < listSize; j++) {
                int index = iModel.getSelectedList().get(j);
                if (index != topBoundInd && index != bottomBoundInd) {
                    double yVal = topBoundYVal + j * parcel;
                    model.getBox(index).setPosition(model.getBox(index).getX(), yVal);
                }
            }
        }
        iModel.getPubSub().publish("update");
    }

}
