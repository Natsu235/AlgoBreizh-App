/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.model;

/**
 *
 * @author Dorian
 */
public class Salesman {

    int id;
    String firstname;
    String lastname;

    // Constructor with id
    public Salesman(int _id, String _firstname, String _lastname) {
        this.id = _id;
        this.firstname = _firstname;
        this.lastname = _lastname;
    }

    public Salesman() {}

    public Salesman(int _id) {
        this.id = _id;
    }

    // Getters
    public int getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

}
