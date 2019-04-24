/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.dao;

import algobreizh.java.dao.factory.AbstractDAOFactory;
import algobreizh.java.model.Customer;
import algobreizh.java.model.Meeting;
import algobreizh.java.model.Salesman;
import algobreizh.java.sql.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorian
 */
public class MeetingDAO extends DAO<Meeting> {

    public MeetingDAO(Connection conn) {
        super(conn);
    }

    @Override
    // Créer un nouveau rendez-vous
    public boolean create(Meeting obj) {
        try {
            Date date = obj.getDateTime();
            String querry = "INSERT INTO tMeetings(description, contact, telephone, meetingDate, id_tSalesman, id_tCustomers) VALUES (?,?,?,?,?,?)"; 
            PreparedStatement ps = DatabaseConnection.getInstance().prepareStatement(querry);
            ps.setString(1, obj.getInfos());
            ps.setString(2, obj.getContact());
            ps.setString(3, obj.getTelephone());
            ps.setDate(4, (java.sql.Date) obj.getDateTime());
            ps.setInt(5, obj.getSalesman().getId());
            ps.setInt(6, obj.getCustomer().getId());
            ps.execute();
        }
        catch (SQLException ex) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    // Supprime un rendez-vous existant
    public boolean delete(Meeting obj) {
        String query = "DELETE FROM tMeetings WHERE id = " + obj.getId();
        this.execute(query);
        return true;
    }

    @Override
    // Modifie un rendez-vous existant
    public boolean update(Meeting obj) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    // Retourne le rendez-vous avec l'id correspondant
    public Meeting get(int id) {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Salesman> salesmanDAO = adf.getSalesmanDAO();
        DAO<Customer> customersDAO = adf.getCustomerDAO();

        List<Meeting> meetings = new ArrayList<>();

        String query = "SELECT * FROM tMeetings WHERE id" + id;
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
		while (res.next()) {
                    Salesman salesman = salesmanDAO.get(res.getInt("id_tSalesman"));
                    Customer customer = customersDAO.get(res.getInt("id_tCustomer"));
                    Date date = res.getDate("MeetingDate");
                    String desc = res.getString("desc");
                    String contact = res.getString("contact");
                    String telephone = res.getString("telephone");
                    return new Meeting(id, salesman,customer, date, desc,contact,telephone);
                }
            }
            catch (SQLException ex) {
                System.out.println("Algobreizh SQL Exception: " + ex);
            }
        }

        return null;       
    }

    @Override
    // Retourne tous les rendez-vous
    public ObservableList<Meeting> getAll() {
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<Salesman> salesmanDAO = adf.getSalesmanDAO();
        DAO<Customer> customersDAO = adf.getCustomerDAO();
        ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        String query = "SELECT * FROM tMeetings";
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
		while (res.next()) {
                    int id = res.getInt("id");
                    Salesman salesman = salesmanDAO.get(res.getInt("id_tSalesman"));
                    Customer customer = customersDAO.get(res.getInt("id_tCustomers"));
                    Date date = res.getDate("MeetingDate");
                    String desc = res.getString("description");
                    String contact = res.getString("contact");
                    String telephone = res.getString("telephone");
                    meetings.add(new Meeting(id, salesman, customer, date, desc, contact, telephone));
		}
            }
            catch (SQLException ex) {
                System.out.println("Algobreizh SQL Exception: " + ex);
            }
        }

        return meetings;   
    }

}
