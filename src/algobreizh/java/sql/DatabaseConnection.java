/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dorian
 */
public class DatabaseConnection {

    // URL de connexion à la BDD
    private String url = "jdbc:mysql://localhost/algobreizh2";

    // Identifiant d'accès à la BDD
    private String username = "root";

    // Mot de passe d'accès à la BDD
    private String password = "";

    // Objet de connexion
    private static Connection connect;

    // Constructeur privé
    private DatabaseConnection() {
        try {
            connect = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Méthode d'accès au singleton
    public static Connection getInstance() {
        if (connect == null)
            new DatabaseConnection();

        return connect;
    }

}
