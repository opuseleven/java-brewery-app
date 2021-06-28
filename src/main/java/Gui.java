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
import com.google.gson.Gson;
/**
 *
 * @author cody
 */
public class Gui {
    
    private JTextField searchBar;
    
    public ArrayList<Brewery> search(String searchedTerm) {
        ArrayList<Brewery> breweriesReturned = new ArrayList<>();
        try {
            URL url = new URL("https://api.openbrewerydb.org/breweries"
                + "/search?query=" + searchedTerm);
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
                    JSONObject newBreweryObject = (JSONObject) array.get(i);
                    Gson gson = new Gson();
                    Brewery newBrewery = gson.fromJson(newBreweryObject.toString(), Brewery.class);
                    breweriesReturned.add(newBrewery);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return breweriesReturned;
    }
    
    public String getSearchTerm() {
        return searchBar.getText();
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
        
        searchButton.addActionListener(e -> {
            System.out.println("Searching...");
            String searchedTerm = getSearchTerm();
            ArrayList<Brewery> data = search(searchedTerm);
            for (int i = 0; i < data.size() || i < 10; i++) {
                Brewery breweryToPrint = data.get(i);
                breweryToPrint.printBrewery();
            }
        });
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        // frame.getContentPane().add(BorderLayout.CENTER, searchBar);
        frame.setVisible(true);
        
        
    }
    
}
