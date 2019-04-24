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
public class Meeting {

    private int id;
    private Salesman salesman;
    private Customer customer;
    private Date date;
    private String infos;
    private String contact;
    private String telephone;

    // Constructor with id
    public Meeting (int _id, Salesman _salesman, Customer _customer, Date _date, String _infos,String _contact,String _telephone) {
        this.id = _id;
        this.salesman = _salesman;
        this.customer = _customer;
        this.date = _date;
        this.infos = _infos;
        this.contact = _contact;
        this.telephone = _telephone;
    }

    // Getters
    public int getId() { return id; }
    public Salesman getSalesman() { return salesman; }
    public Customer getCustomer() { return customer; }
    public Date getDateTime() { return date; }
    public String getInfos() { return infos; }
    public String getContact() { return contact; }
    public String getTelephone() { return telephone; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setSalesman(Salesman Salesman) { this.salesman = Salesman; }
    public void setCustomer(Customer Customer) { this.customer = Customer; }
    public void setDate(Date date) { this.date = date; }
    public void setInfos(String infos) { this.infos = infos; }
    public void setContact(String contact) { this.contact = contact; }
    public String setTelephone(String telephone) { return telephone; }

}
