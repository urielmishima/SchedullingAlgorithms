package br.usjt.scheduling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scheduling extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Scheduling.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Projeto A1 – Sistemas Operacionais CCP3BN-MCA");               
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
