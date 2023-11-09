package com.example.assignment3_mds569;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class BoxView extends StackPane implements Subscriber {
    /**
     * A graphical view of the objects in the model.
     */

    private InteractionModel iModel;
    private BoxModel model;

    Canvas myCanvas;

    GraphicsContext graphicsContext;

    public BoxView() {
        myCanvas = new Canvas(800, 800);
        graphicsContext = myCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.TEAL);
        this.setStyle("-fx-background-color: black;");
        this.getChildren().add(myCanvas);
        
        //this.setFocusTraversable(true);
    }

    public void setiModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void setModel(BoxModel model) {
        this.model = model;
    }

    public void draw() {
        // Draw boxes with yellow underneath is selected
        graphicsContext.clearRect(0,0,800,800);
        int listSize = model.getBoxes().size();
        for (int i = 0; i < listSize; i++){
            if (iModel.selectedListContains(i)){
                graphicsContext.setFill(Color.YELLOW);
                graphicsContext.fillRect(model.getBox(i).getX()-5, model.getBox(i).getY()-5, model.getBox(i).getWidth()+10, model.getBox(i).getHeight()+10);
            }
            graphicsContext.setFill(Color.TEAL);
            graphicsContext.fillRect(model.getBox(i).getX(), model.getBox(i).getY(), model.getBox(i).getWidth(), model.getBox(i).getHeight());
        }

        // Find out which box is the cursor and draw its orange color on top
        if (iModel.getCursorIndex() > -1) {
            Box cursor = model.getBox(iModel.getCursorIndex());
            graphicsContext.setFill(Color.DARKORANGE);
            graphicsContext.fillRect(cursor.getX(), cursor.getY(), cursor.getWidth(), cursor.getHeight());
        }
    }


    // If any of these channels are updated, do ______
    @Override
    public void getNotification(String chnl) {
        switch (chnl){
            case "create" -> {
                System.out.println("Create");
                draw();
            }
            case "delete" -> {
                draw();
                System.out.println("Delete");
            }
            case "update" -> {
                draw();
                System.out.println("Update");
            }
            case "selection" -> {
                draw();
                System.out.println("Selection");
            }
        }
    }
}
