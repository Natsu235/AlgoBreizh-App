/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.controller;

import algobreizh.java.AlgoBreizh;
import algobreizh.java.context.Context;
import algobreizh.java.dao.DAO;
import algobreizh.java.dao.factory.AbstractDAOFactory;
import algobreizh.java.model.City;
import algobreizh.java.model.Customer;
import algobreizh.java.model.Meeting;
import algobreizh.java.model.Salesman;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Dorian
 */
public class GlobalController implements Initializable {

    AlgoBreizh Main = new AlgoBreizh();

    @FXML private TableView<Customer> tblCustomers;
    @FXML private TableColumn<Customer, String> c_Id;
    @FXML private TableColumn<Customer, String> c_Username;
    @FXML private TableColumn<Customer, String> c_Email;
    @FXML private TableColumn<Customer, String> c_City;
    @FXML private TableColumn<Customer, LocalDateTime> c_Date;
    @FXML private TableColumn<Customer, String> c_Meeting;

    @FXML private TableView<Meeting> tblMeetings;
    @FXML private TableColumn<Meeting, String> m_Id;
    @FXML private TableColumn<Meeting, Salesman> m_Salesman;
    @FXML private TableColumn<Meeting, Customer> m_Customer;

    @FXML
    // Décconnecte le compte de l'utilisateur
    private void disconnect(ActionEvent event) throws Exception {
        AlgoBreizh.getPrimaryStage().close();
        Context.currUser = null;
        Main.newScene("view/LoginView.fxml", "/algobreizh/resources/AlgoBreizh_Logo.png", "AlgoBreizh - Connexion", false);
    }

    @FXML
    // Quitte l'application
    private void exit(ActionEvent event) {
        AlgoBreizh.getPrimaryStage().close();
    }

    private void displayCustomers() {
        // Récupère les données des cliens en BDD
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Customer> customerDAO = adf.getCustomerDAO();
        ObservableList<Customer> customers = customerDAO.getAll();
        //c_Id.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        c_Username.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstname"));
        c_Email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        c_City.setCellValueFactory(new PropertyValueFactory<Customer, String>("cityDAO.get(id_tCities)"));
        c_Date.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("lastDate"));
        // Insère les données dans le tableau
        tblCustomers.setItems(customers);
    }

    private void displayMeetings() {
        // Récupère les données des rendez-vous en BDD
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Meeting> meetingDAO = adf.getMeetingDAO();
        ObservableList<Meeting> meetings = meetingDAO.getAll();
        //m_Salesman.setCellValueFactory(new PropertyValueFactory<Meeting, Salesman>("salesman"));
        //m_Customer.setCellValueFactory(features -> features.getValue().nameProperty());
        //cCity.setCellValueFactory(new PropertyValueFactory<Customer, City>("city"));
        //cDate.setCellValueFactory(new PropertyValueFactory<Customer, LocalDateTime>("lastDate"));
        tblMeetings.setItems(meetings);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.displayCustomers();
        this.displayMeetings();
    }

}
