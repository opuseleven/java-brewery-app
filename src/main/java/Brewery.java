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
    private final String brewery_type;
    private final String street;
    private final String city;
    private final String state;
    private final String website_url;
    
    public Brewery(int id, String name, String breweryType, String street, String city, String state, String breweryUrl) {
        this.id = id;
        this.name = name;
        this.brewery_type = breweryType;
        this.street = street;
        this.city = city;
        this.state = state;
        this.website_url = breweryUrl;
    }
    
    public String getName() {
        return this.name;
    }
    public String getBreweryType() {
        return this.brewery_type;
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
        return this.website_url;
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
