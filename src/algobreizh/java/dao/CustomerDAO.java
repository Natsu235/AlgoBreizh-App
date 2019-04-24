/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.dao;

import algobreizh.java.dao.factory.AbstractDAOFactory;
import algobreizh.java.model.City;
import algobreizh.java.model.Customer;
import algobreizh.java.sql.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorian
 */
public class CustomerDAO extends DAO<Customer>{

    AbstractDAOFactory adf  = null;

    public CustomerDAO(Connection conn) {
        super(conn);
        adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
    }

    @Override
    // Ajoute un nouveau client
    public boolean create(Customer obj) {
        try {
            String query = "INSERT INTO tCustomers VALUES ("
                + "\'" + obj.getFirstname() 
                + "\',\'" + obj.getLastname()
                + "\',\'" + obj.getEmail()
                + "\',\'" + obj.getCity().getId()
                + "\')";
            this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(query);
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    // Supprime un client existant
    public boolean delete(Customer obj) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");  // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    // Supprime un client existant
    public boolean update(Customer obj) {
        try {
            Date newDate = obj.getLastDate();
            String querry = "UPDATE tCustomers SET lastMeetingDate = ? WHERE id = ?";
            PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(querry);
            ps.setDate(1, (java.sql.Date) obj.getLastDate());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    @Override
    public Customer get(int id) {    
        Customer customer = null;
        try {
            ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM tCustomers WHERE id = " + id);
            if (res != null) {
                while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    String email = res.getString("email");
                    int id_tCities = res.getInt("id_tCities");
                    Date lastDate = res.getDate("lastMeetingDate");
                    customer = new Customer(id, firstname, lastname, email, new City(id_tCities), lastDate);
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return customer;
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        try {
            DAO<City> cityDAO = adf.getCityDAO();
            ResultSet res = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM tCustomers ORDER BY lastMeetingDate ASC");
            if (res != null) {
                while (res.next()) {
                    int id = res.getInt("id");
                    String firstname = res.getString("firstname");
                    String lastname = res.getString("lastname");
                    String email = res.getString("email");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date lastDate = res.getDate("lastMeetingDate");
                    int id_tCities = res.getInt("id_tCities");
                    customers.add(new Customer(id, firstname, lastname, email, cityDAO.get(id_tCities), lastDate));
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return customers;
    }
}
