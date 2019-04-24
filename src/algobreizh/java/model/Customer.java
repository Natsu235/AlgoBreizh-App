/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.java.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Dorian
 */
public class Customer {

    int id;
    String firstname;
    String lastname;
    String email;
    City city;
    Date lastDate;

    // Constructor with id
    public Customer(int _id, String _firstname, String _lastname, String _email, City _city, Date _date) {
        this.id = _id;
        this.firstname = _firstname;
        this.lastname = _lastname;
        this.email = _email;
        this.city = _city;
        this.lastDate = _date;
    }

    public Customer() {}

    // Getters
    public int getId() { return id; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }           
    public City getCity() { return city; }
    public Date getLastDate() { return lastDate; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setCity(City city) { this.city = city; }
    public void setLastDate(Date date) { lastDate = date; }

}
