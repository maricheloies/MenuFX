package com.example.menufx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewLimits implements Initializable {
    @FXML
    private TextField limitCalories;
    @FXML
    private TextField limitCarbohydrates;
    @FXML
    private TextField limitFat;

    public static double limitCal=0;
    public static double limitCar=0;
    public static double limitF=0;

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle)
    {
        limitCalories.setText(limitCal+"");
        limitCarbohydrates.setText(limitCar+"");
        limitFat.setText(limitF+"");
    }

    void closeWindow(ActionEvent actionEvent)
    {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        limitF=Double.parseDouble(limitFat.getText());
        limitCar=Double.parseDouble(limitCarbohydrates.getText());
        limitCal=Double.parseDouble(limitCalories.getText());
        closeWindow(actionEvent);
    }
}
