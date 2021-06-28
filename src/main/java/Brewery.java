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
    
    private final int id;
    private final String name;
    private final String breweryType;
    private final String street;
    private final String city;
    private final String state;
    private final String breweryUrl;
    
    public Brewery(int id, String name, String breweryType, String street, String city, String state, String breweryUrl) {
        this.id = id;
        this.name = name;
        this.breweryType = breweryType;
        this.street = street;
        this.city = city;
        this.state = state;
        this.breweryUrl = breweryUrl;
    }
    
    public String getName() {
        return this.name;
    }
    public String getBreweryType() {
        return this.breweryType;
    }
    public String getStreet() {
        return this.street;
    }
    public String getCity() {
        return this.city;
    }
    public String getState() {
        return this.state;
    }
    public String getBreweryUrl() {
        return this.breweryUrl;
    }
    
    public void printBrewery() {
        String breweryInfo = "";
        breweryInfo += this.getName();
        breweryInfo += "\n" + this.getBreweryType();
        breweryInfo += "\n" + this.getStreet() + " "
                + this.getCity() + ", " + this.getState();
        breweryInfo += "\n" + this.getBreweryUrl();
        System.out.println(breweryInfo);
    }
}
