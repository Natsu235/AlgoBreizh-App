/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.dao.factory;

import algobreizh.java.dao.CityDAO;
import algobreizh.java.dao.CustomerDAO;
import algobreizh.java.dao.DAO;
import algobreizh.java.dao.MeetingDAO;
import algobreizh.java.dao.SalesmanDAO;
import algobreizh.java.dao.factory.AbstractDAOFactory;
import algobreizh.java.model.City;
import algobreizh.java.model.Customer;
import algobreizh.java.model.Meeting;
import algobreizh.java.model.Salesman;
import algobreizh.java.sql.DatabaseConnection;
import java.sql.Connection;

/**
 *
 * @author Dorian
 */
public class DAOFactory extends AbstractDAOFactory {

    protected static final Connection conn = DatabaseConnection.getInstance();   

    // Permet la connexion avec les tables de la BDD

    @Override
    public DAO<City> getCityDAO() {
        return new CityDAO(conn);
    }

    @Override
    public DAO<Customer> getCustomerDAO() {
        return new CustomerDAO(conn); 
    }

    @Override
    public DAO<Meeting> getMeetingDAO() {
        return new MeetingDAO(conn);
    }

    @Override
    public DAO<Salesman> getSalesmanDAO() {
        return new SalesmanDAO(conn);
    }

}
