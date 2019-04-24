/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Dorian
 */
public class AlgoBreizh extends Application {

    private static Stage primaryStage;

    private void setPrimaryStage(Stage stage) {
        AlgoBreizh.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return AlgoBreizh.primaryStage;
    }

    @Override
    // Génère la première vue au lancement de l'application
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        primaryStage.getIcons().add(new Image("/algobreizh/resources/AlgoBreizh_Logo_2.png"));
        primaryStage.setTitle("AlgoBreizh - Connexion");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Génère la vue correspondante
    public void newScene(String scene, String icon, String title, Boolean isResizable) throws Exception {
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource(scene));
        primaryStage.getIcons().add(new Image(icon));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(isResizable);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
