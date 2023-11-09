package com.example.assignment3_mds569;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainUI extends VBox {
    /**
     * This class represents a view that contains and lays out
     * the graphical view.
     * - Used for an additional view in Part 2
     */

    public MainUI(){
        // Create Instances for each important class
        AppController controller = new AppController();
        BoxView view = new BoxView();
        BoxModel model = new BoxModel();
        InteractionModel iModel = new InteractionModel();
        StatusBarView statusBarView = new StatusBarView();
        PublishSubscribe publishSubscribe = new PublishSubscribe();


        // Attaching elements
        //view.setupEvents(controller);
        controller.setModel(model);
        model.setPubSub(publishSubscribe);
        //model.addSubscriber(view);
        controller.setiModel(iModel);
        view.setiModel(iModel);
        view.setModel(model);
        //iModel.addSubscriber(view);
        iModel.setPubSub(publishSubscribe);

        // Set growth constraints
        VBox.setVgrow(view, Priority.ALWAYS);
        VBox.setVgrow(statusBarView, Priority.NEVER);

        // Create PublishSubscribe Channels
        publishSubscribe.createChannel("create");
        publishSubscribe.createChannel("delete");
        publishSubscribe.createChannel("update");
        publishSubscribe.createChannel("selection");

        // Add subscribers to proper channels
        publishSubscribe.addSubscriber("create", statusBarView);
        publishSubscribe.addSubscriber("delete", statusBarView);
        publishSubscribe.addSubscriber("create", view);
        publishSubscribe.addSubscriber("delete", view);
        publishSubscribe.addSubscriber("update", view);
        publishSubscribe.addSubscriber("selection", view);

        // Event routing
        setOnKeyPressed(controller::handleKeyPressed);

        // Add view(s) to child list
        this.getChildren().addAll(view, statusBarView);
    }
}
