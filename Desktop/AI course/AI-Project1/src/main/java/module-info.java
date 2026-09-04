module com.example.aiproject1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aiproject1 to javafx.fxml;
    exports com.example.aiproject1;
}