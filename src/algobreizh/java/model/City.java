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
public class City {

    private int id;
    private String name;
    private Salesman salesman;

    public City() {}

    public City(int _id) {
        this.id = _id;
    }

    // Constructor with id
    public City(int _id, String _name, Salesman _salesman) {
        this.setId(_id);
        this.setName(_name);
        this.setSalesman(_salesman);       
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Salesman getSalesman() { return salesman; }

    // Setters
    public void setId(int id) { this.id = id;}
    public void setName(String name) { this.name = name; }
    public void setSalesman(Salesman salesman) { this.salesman = salesman; }

}
