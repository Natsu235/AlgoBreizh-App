/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.dao;

import algobreizh.java.model.Salesman;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorian
 */
public class SalesmanDAO extends DAO<Salesman>{

    public SalesmanDAO(Connection conn) {
        super(conn);
    }

    @Override
    // Ajoute un nouveau vendeur
    public boolean create(Salesman obj) {
        String query = "INSERT INTO tCustomers VALUES ("
                + "\'" + obj.getFirstname() 
                + "\',\'" + obj.getLastname()
                + "\')";
        this.execute(query);  
        return true;
    }

    @Override
    // Supprime un vendeur existant
    public boolean delete(Salesman obj) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");  // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    // Modifie un vendeur existant
    public boolean update(Salesman obj) {
        String query = "UPDATE FROM tSalesman SET (firstname = "
            + "\'" + obj.getFirstname() 
            + "\',lastname =\'" + obj.getLastname()
            + "\')";
        ResultSet res = this.execute(query);
        return true;
    }

    @Override
    // Retourne un vendeur avec l'id correspondant
    public Salesman get(int id) {
        String querry = "SELECT * FROM tSalesman WHERE id = " + id;
        Salesman salesman = null;
        ResultSet res = this.execute(querry);
        if (res != null) {
            try {
		while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    salesman = new Salesman(id,firstname,lastname);
		}
            }
            catch (SQLException ex) {
                Logger.getLogger(SalesmanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        return salesman;
    }

    // Retourne un vendeur avec l'identifiant et le mot de passe
    public Salesman getByCredentials(String username, String password) {
        String query = "SELECT * FROM tSalesman WHERE username = '" + username + "' AND password = SHA1('"  + password+ "')";
        Salesman salesman = null;
        ResultSet res = this.execute(query);
        if (res != null) {
            try {
		while (res.next()) {
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    int id = res.getInt("id");
                    salesman = new Salesman(id,firstname,lastname);
		}
            }
            catch (SQLException ex) {
                Logger.getLogger(SalesmanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        return salesman;
    }

    @Override
    // Retourne la liste de tous les vendeurs
    public ObservableList<Salesman> getAll() {
        String querry = "SELECT * FROM tSalesman";
        ObservableList<Salesman> salesmans = FXCollections.observableArrayList();
        ResultSet res = this.execute(querry);
        if (res != null) {
            try {
		while (res.next()) {
                    int id = res.getInt("id");
                    String lastname = res.getString("lastname");
                    String firstname = res.getString("firstname");
                    salesmans.add(new Salesman(id,firstname,lastname));
		}
            }
            catch (SQLException ex) {
                Logger.getLogger(SalesmanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        return salesmans;
    }

}
