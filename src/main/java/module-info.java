module com.example.menufx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.menufx to javafx.fxml;
    opens com.example.menufx.model to javafx.base;
    exports com.example.menufx;
}