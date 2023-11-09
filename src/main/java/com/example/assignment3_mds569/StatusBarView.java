package com.example.assignment3_mds569;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBarView extends HBox implements Subscriber {

    public int boxesSinceStart, boxesDeleted, boxesTotal;
    public Label created, deleted, total;

    public StatusBarView(){
        this.boxesSinceStart = 0;
        this.boxesDeleted = 0;
        this.boxesTotal = 0;
        this.created = new Label("Created: " + boxesSinceStart + " ");
        this.deleted = new Label("Deleted: " + boxesDeleted + " ");
        this.total = new Label("Current: " + boxesTotal + " ");
        this.getChildren().addAll(created, deleted, total);
    }


    // If any of these channels are updated, do ______
    @Override
    public void getNotification(String chnl) {
        switch (chnl){
            case "create" -> {
                boxesSinceStart++;
                boxesTotal++;
                created.setText("Created: " + boxesSinceStart + " ");
                total.setText("Current: " + boxesTotal + " ");
            }
            case "delete" -> {
                boxesTotal--;
                boxesDeleted++;
                total.setText("Current: " + boxesTotal + " ");
                deleted.setText("Deleted: " + boxesDeleted + " ");
            }
        }
    }

}
