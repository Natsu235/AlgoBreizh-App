/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorian
 */
public abstract class DAO<T> {

    protected Connection connect = null;

    public DAO(Connection conn) {
        this.connect = conn;
    }

    /**
     * Méthode d'éxecution des requêtes SQL
     * @param requete
     * @return ResultSet 
     */
    protected ResultSet execute(String requete) {
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = connect.createStatement();
            if(stmt.execute(requete)) {
                res = stmt.getResultSet();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * Méthode de création
     * @param obj
     * @return boolean 
     */
    public abstract boolean create(T obj);

    /**
     * Méthode de suppression
     * @param obj
     * @return boolean 
     */
    public abstract boolean delete(T obj);

    /**
     * Méthode de mise à jour
     * @param obj
     * @return boolean
     */
    public abstract boolean update(T obj);

    /**
     * Méthode de recherche d'un élement utilisant l'id comme paramètre
     * @param id
     * @return T
     */
    public abstract T get(int id);

    /**
     * Méthode de lecture de tous les paramètres d'une table
     * @param id
     * @return T
     */
    public abstract ObservableList<T> getAll();
}
