package com.example.menufx;

import com.example.menufx.model.Menu;
import com.example.menufx.model.MenuElement;
import com.example.menufx.utils.FileUtils;
import com.example.menufx.utils.MessageUtils;
import com.example.menufx.utils.ScreenLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private CheckBox radGluten;
    @FXML
    private CheckBox radEggs;
    @FXML
    private CheckBox radNuts;
    @FXML
    private CheckBox radMilk;
    @FXML
    private TableColumn<MenuElement,String> tbMenuDesc;
    @FXML
    private TableColumn<MenuElement,String> tbMenuName;
    @FXML
    private TableView<MenuElement> tbMenu;
    @FXML
    private Label lbFat;
    @FXML
    private Label lbCarbohydrates;
    @FXML
    private Label lbCalories;
    @FXML
    private Button btSave;
    @FXML
    private Button btChar;
    @FXML
    private Button btNewDish;
    @FXML
    private Button btNewAliment;


    @FXML
    private TableColumn<MenuElement,Double> tbColumnFat;
    @FXML
    private TableColumn<MenuElement,Double> tbColumnCarbohydrates;
    @FXML
    private TableColumn<MenuElement,Double> tbColumnCalories;
    @FXML
    private TableColumn<MenuElement,String> tbColumnName;
    @FXML
    private TableView<MenuElement> tbElements;

    @FXML
    private DatePicker txtDate;

    public static List<MenuElement> myListElements;
    ObservableList<MenuElement> data;

    public static com.example.menufx.model.Menu myMenu;
    ObservableList<MenuElement> myObservableMenu;



    @Override
    public void initialize(URL url,
                           ResourceBundle resourceBundle)
    {
        fillElements();
    }

    public void fillElements()
    {
        myListElements= FileUtils.loadElements();
        data=FXCollections.observableArrayList(myListElements);
        tbColumnCalories.setCellValueFactory(new PropertyValueFactory("calories"));
        tbColumnCarbohydrates.setCellValueFactory(new PropertyValueFactory("carbohydrates"));
        tbColumnFat.setCellValueFactory(new PropertyValueFactory("fat"));
        tbColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        tbElements.setItems(data);
        tbElements.setPlaceholder(new Label("No items to show..."));
        myObservableMenu=FXCollections.observableArrayList(new ArrayList<MenuElement>());
        tbMenuName.setCellValueFactory(new PropertyValueFactory("name"));
        tbMenuDesc.setCellValueFactory(new PropertyValueFactory("description"));
        txtDate.setValue(LocalDate.now());
        myMenu=new Menu(txtDate.getValue());
        myMenu.setElements(myObservableMenu);
        tbMenu.setItems(myObservableMenu);
        tbMenu.setPlaceholder(new Label("No items to show..."));

    }

    public void AddElementToMenu(ActionEvent actionEvent) {

        myObservableMenu.add(tbElements.getItems().get(tbElements.getSelectionModel().getSelectedIndex()));
        drawNutritionalValues();


    }

    public void newAliment(ActionEvent actionEvent) throws Exception{
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage secondaryStage= ScreenLoader.loadScreen(
                "new-aliment.fxml",stage);
        secondaryStage.setOnCloseRequest(e -> fillElements());
        secondaryStage.show();
    }

    public void newDish(ActionEvent actionEvent) throws Exception{
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage secondaryStage= ScreenLoader.loadScreen(
                "new-dish.fxml",stage);
        secondaryStage.setOnCloseRequest(e -> fillElements());
        secondaryStage.show();
    }

    public void createChar(ActionEvent actionEvent) {
    }

    public void save(ActionEvent actionEvent) {
        if(myMenu.getElements().size()>0 && myMenu.getDate()!=null)
        {
            FileUtils.storeMenu(myMenu);
            MessageUtils.showMessage("Success","Menu successufully saved");
        }
        else
        {
            MessageUtils.showError("Empty values","Menu information is missing");
        }
    }
    public Color whichColor(double value,double limit){
        if (value>limit)
            return Color.RED;
        else
            return Color.BLACK;
    }

    public void drawNutritionalValues() {

        lbCalories.setText(myMenu.getCalories()+"");
        lbCarbohydrates.setText(myMenu.getCarbohydrates()+"");
        lbFat.setText(myMenu.getFat()+"");

        lbCalories.setTextFill(whichColor(myMenu.getCalories(), NewLimits.limitCal));
        lbFat.setTextFill(whichColor(myMenu.getFat(), NewLimits.limitF));
        lbCarbohydrates.setTextFill(whichColor(myMenu.getCarbohydrates(), NewLimits.limitCar));


    }
    public void statLimits(ActionEvent actionEvent) throws Exception{
        Stage stage=(Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Stage secondaryStage= ScreenLoader.loadScreen(
                "new-limits.fxml",stage);
        secondaryStage.setOnCloseRequest(e -> drawNutritionalValues());
        secondaryStage.show();
    }

    public void milkElements(ActionEvent actionEvent) {
        if(radMilk.isSelected()) {
            tbElements.setItems(
                    FXCollections.observableArrayList(
                            myListElements.stream()
                                    .filter(e -> e.isMilk())
                                    .collect(Collectors.toList())
                    )
            );
            radEggs.setSelected(false);
            radGluten.setSelected(false);
            radNuts.setSelected(false);
        }
        else{
            tbElements.setItems(
                    FXCollections.observableArrayList(myListElements)
            );
        }
    }

    public void nutsElements(ActionEvent actionEvent) {
        if(radNuts.isSelected()) {
            tbElements.setItems(
                    FXCollections.observableArrayList(
                            myListElements.stream()
                                    .filter(e -> e.isNuts())
                                    .collect(Collectors.toList())
                    )
            );
            radEggs.setSelected(false);
            radGluten.setSelected(false);
            radMilk.setSelected(false);
        }
        else {
            tbElements.setItems(
                    FXCollections.observableArrayList(myListElements)
            );
        }
    }

    public void eggsElements(ActionEvent actionEvent) {
        if (radEggs.isSelected()) {
            tbElements.setItems(
                    FXCollections.observableArrayList(
                            myListElements.stream()
                                    .filter(e -> e.isEgg())
                                    .collect(Collectors.toList())
                    )
            );
            radMilk.setSelected(false);
            radGluten.setSelected(false);
            radNuts.setSelected(false);
        }
        else {
            tbElements.setItems(
                    FXCollections.observableArrayList(myListElements)
            );
        }
    }

    public void glutenElements(ActionEvent actionEvent) {
        if(radGluten.isSelected()) {
            tbElements.setItems(
                    FXCollections.observableArrayList(
                            myListElements.stream()
                                    .filter(e -> e.isGluten())
                                    .collect(Collectors.toList())
                    )
            );
            radEggs.setSelected(false);
            radMilk.setSelected(false);
            radNuts.setSelected(false);
        }
        else {
            tbElements.setItems(
                    FXCollections.observableArrayList(myListElements)
            );
        }
    }

    public void removeElementFromMenu(ActionEvent actionEvent) {
        myObservableMenu.remove(myObservableMenu.get(tbMenu.getSelectionModel().getSelectedIndex()));
        drawNutritionalValues();
    }

    public void searchElements(KeyEvent keyEvent) {
        tbElements.setItems(
                FXCollections.observableArrayList(
                        myListElements.stream().filter(e->e.getName().contains(txtSearch.getText())).collect(Collectors.toList())));
    }
}