/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.controller;

import algobreizh.java.AlgoBreizh;
import algobreizh.java.context.Context;
import algobreizh.java.dao.SalesmanDAO;
import algobreizh.java.sql.DatabaseConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Dorian
 */
public class LoginController implements Initializable {

    AlgoBreizh Main = new AlgoBreizh();

    @FXML
    private TextField txtUsername, txtPassword;

    @FXML
    private Label lblRequired1, lblRequired2, lblMessage;

    @FXML
    // Se connecte au compte de l'utilisateur
    private void login(ActionEvent event) throws Exception {
        SalesmanDAO salesmanDAO = new SalesmanDAO(DatabaseConnection.getInstance());
        Context.currUser = salesmanDAO.getByCredentials(txtUsername.getText(), txtPassword.getText());
        if (Context.currUser != null) {
            lblMessage.setVisible(false);
            AlgoBreizh.getPrimaryStage().close();
            Main.newScene("view/GlobalView.fxml", "/algobreizh/resources/AlgoBreizh_Logo_2.png", "AlgoBreizh - Gestionnaire de rendez-vous (" + Context.currUser.getFirstname() + " " + Context.currUser.getLastname()+ ")", false);
        }
        else if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            lblMessage.setText("L'identifiant ou le mot de passe est incorrect !");
            lblMessage.setVisible(true);
        }
        else {
            lblMessage.setVisible(false);
        }
        if (txtUsername.getText().isEmpty())
            lblRequired1.setVisible(true);
        else
            lblRequired1.setVisible(false);
        if (txtPassword.getText().isEmpty())
            lblRequired2.setVisible(true);
        else
            lblRequired2.setVisible(false);
    }

    @FXML
    // Quitte l'application
    private void exit(ActionEvent event) {
        AlgoBreizh.getPrimaryStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }

}
