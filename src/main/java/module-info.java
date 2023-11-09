module com.example.assignment3_mds569 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment3_mds569 to javafx.fxml;
    exports com.example.assignment3_mds569;
}