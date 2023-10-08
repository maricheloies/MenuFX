package com.example.menufx.utils;

import javafx.scene.control.Alert;

/**
 * This class has some useful, static methods to print alert messages
 */
public class MessageUtils 
{
    public static void showError(String header, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Error");
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        dialog.showAndWait();                    
    }

    public static void showMessage(String header, String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Information");
        dialog.setHeaderText(header);
        dialog.setContentText(message);
        dialog.showAndWait();                    
    }
    
}
