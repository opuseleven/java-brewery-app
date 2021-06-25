/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
/**
 *
 * @author cody
 */
public class Gui {
    
    private JTextField searchBar;
    
    public ArrayList<Brewery> search(String searchedTerm) {
        ArrayList<Brewery> breweriesReturned;
        try {
            URL url = new URL("https://api.openbrewerydb.org/breweries"
                + "/search?query=" + searchBar.getText());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());
                
                while (scanner.hasNext()){
                    inline += scanner.nextLine();
                }
                scanner.close();
                
                JSONParser parser = new JSONParser();
                JSONObject jsonBrewery = (JSONObject) parser.parse(inline);
                JSONObject breweryObject = (JSONObject) jsonBrewery.get("Global");
                System.out.println(breweryObject.get("TotalRecovered"));
                
                JSONArray array = (JSONArray) jsonBrewery.get("Object");
                
                for (int i = 0; i < array.size(); i++) {
                    JSONObject newBrewery = (JSONObject) array.get(i);
                    breweriesReturned.add(newBrewery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return breweriesReturned;
    }
    
    public Gui() {
        
        JFrame frame = new JFrame("Brewery Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);
        JMenuItem quitButton = new JMenuItem("Quit");
        JMenuItem helpButton = new JMenuItem("Help");
        file.add(quitButton);
        help.add(helpButton);
        
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter search terms: ");
        searchBar = new JTextField(20);
        JButton searchButton = new JButton("Search");
        panel.add(label);
        panel.add(searchBar);
        panel.add(searchButton);
        
        searchButton.addActionListener(e ->
            System.out.println("Searching..."));
        
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        // frame.getContentPane().add(BorderLayout.CENTER, searchBar);
        frame.setVisible(true);
        
        
    }
    
}
