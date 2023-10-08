package com.example.menufx.utils;


import com.example.menufx.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenLoader {
    
    public static Stage loadScreen(String path, Stage stage) throws IOException {
        System.out.println(path);
        Parent view = FXMLLoader.load(MainApplication.class.getResource(path));
        Scene viewScene = new Scene(view);
        Stage secondaryStage= new Stage();
        secondaryStage.setScene(viewScene);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(stage);
        return secondaryStage;
    }
    
}
