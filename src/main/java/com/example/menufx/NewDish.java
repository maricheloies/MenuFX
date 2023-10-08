package com.example.menufx;


import com.example.menufx.model.Aliment;
import com.example.menufx.model.Dish;
import com.example.menufx.model.Ingredient;
import com.example.menufx.utils.FileUtils;
import com.example.menufx.utils.MessageUtils;
import com.example.menufx.model.*;
import com.example.menufx.utils.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewDish implements Initializable {
    @FXML
    private TableView<Ingredient> tbIngredients;
    @FXML
    private TableColumn<Ingredient,String> colName;
    @FXML
    private TableColumn<Ingredient,String> colDescription;
    @FXML
    private TableColumn<Ingredient,Double> colQuantity;
    @FXML
    private TextField txQuantity;
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
    private TextField txDescriptionDish;
    @FXML
    private TextField txNameDish;
    @FXML
    private TextField txCalories;

    public static List<Ingredient> myIngredients;
    ObservableList<Ingredient> data;

    public static Dish myDish;


    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle)
    {
        data= FXCollections.observableArrayList(new ArrayList<Ingredient>());
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        tbIngredients.setPlaceholder(new Label("No items to show..."));
        tbIngredients.setItems(data);

    }

    boolean NoEmptyFields()
    {
        return !txName.getText().equals("") && !txDescription.getText().equals("") && !txCalories.getText().equals("") &&
                !txCarbohydrates.getText().equals("") && !txFat.getText().equals("") && !txFrequency.getText().equals("")
                && !txQuantity.getText().equals("");
    }

    void cleanFields()
    {
        txDescriptionDish.setText("");
        txNameDish.setText("");
        cleanFieldsIngredients();
    }

    void cleanFieldsIngredients()
    {
        txName.setText("");
        txDescription.setText("");
        txFrequency.setText("");
        txCarbohydrates.setText("");
        txCalories.setText("");
        txFat.setText("");
        txQuantity.setText("");
        checkEgg.setSelected(false);
        checkNuts.setSelected(false);
        checkMilk.setSelected(false);
        checkGluten.setSelected(false);
    }

    public void save(ActionEvent actionEvent) {
        if(!txNameDish.equals("") && !txDescriptionDish.equals("") && data.size()>0)
        {
            myDish.setIngredients(data);
            FileUtils.storeDish(myDish);
            MessageUtils.showMessage("Success","The dish has been successfully saved");
            cleanFields();
            data.clear();
        }
        else {
            MessageUtils.showError("Empty values", "All the information is needed");
        }

    }

    public void addIngredient(ActionEvent actionEvent) {
        if(!txNameDish.getText().equals("") && !txDescriptionDish.equals(""))
        {
            if (myDish==null)
                myDish=new Dish(txNameDish.getText(),txDescription.getText());
            if(NoEmptyFields()) {
                data.add(new Ingredient(Double.parseDouble(txQuantity.getText()), new Aliment(txName.getText(), txDescription.getText(), txFrequency.getText(),
                        checkGluten.isSelected(), checkMilk.isSelected(), checkNuts.isSelected(), checkEgg.isSelected(),
                        Double.parseDouble(txCalories.getText()), Double.parseDouble(txCarbohydrates.getText()),
                        Double.parseDouble(txFat.getText()))));
                cleanFieldsIngredients();
            }
            else
            {
                MessageUtils.showError("Empty values", "All the information is needed");
            }

        }
        else {
            MessageUtils.showError("Name or description missing!",
                            "You need a name and a description before inserting ingredientes");
        }

    }


}
