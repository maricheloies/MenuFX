package com.example.menufx;

import com.example.menufx.model.Aliment;
import com.example.menufx.utils.FileUtils;
import com.example.menufx.utils.MessageUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAliment implements Initializable {
    @FXML
    private TextField txCarbohydrates;
    @FXML
    private TextField txFat;
    @FXML
    private CheckBox checkMilk;
    @FXML
    private CheckBox checkNuts;
    @FXML
    private CheckBox checkGluten;
    @FXML
    private CheckBox checkEgg;
    @FXML
    private TextField txFrequency;
    @FXML
    private TextField txDescription;
    @FXML
    private TextField txName;
    @FXML
    private TextField txCalories;

    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle)
    {

    }

    boolean NoEmptyFields()
    {
        return !txName.getText().equals("") && !txDescription.getText().equals("") && !txCalories.getText().equals("") &&
                !txCarbohydrates.getText().equals("") && !txFat.getText().equals("") && !txFrequency.getText().equals("");
    }

    void cleanFields()
    {
        txName.setText("");
        txDescription.setText("");
        txFrequency.setText("");
        txCarbohydrates.setText("");
        txCalories.setText("");
        txFat.setText("");
        checkEgg.setSelected(false);
        checkNuts.setSelected(false);
        checkMilk.setSelected(false);
        checkGluten.setSelected(false);
    }

    public void save(ActionEvent actionEvent) {
        if(NoEmptyFields())
        {
            FileUtils.storeAliment(new Aliment(txName.getText(),txDescription.getText(),txFrequency.getText(),
                    checkGluten.isSelected(),checkMilk.isSelected(),checkNuts.isSelected(),checkEgg.isSelected(),
                    Double.parseDouble(txCalories.getText()),Double.parseDouble(txCarbohydrates.getText()),
                    Double.parseDouble(txFat.getText())));
            MessageUtils.showMessage("Success","The aliment has been successfully saved");
            cleanFields();
        }
        else {
            MessageUtils.showError("Empty values", "All the information is needed");
        }

    }
}
