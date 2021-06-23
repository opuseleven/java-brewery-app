/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cody
 */
public class Brewery {
    
    private int id;
    public String name;
    public String breweryType;
    public String street;
    public String city;
    public String state;
    public String breweryUrl;
    
    public Brewery(int id, String name, String breweryType, String street, String city, String state, String breweryUrl) {
        this.id = id;
        this.name = name;
        this.breweryType = breweryType;
        this.street = street;
        this.city = city;
        this.state = state;
        this.breweryUrl = breweryUrl;
    }
    
    public void printBrewery() {
        
    }
    
}
