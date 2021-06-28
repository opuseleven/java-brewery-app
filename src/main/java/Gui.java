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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.text.DefaultCaret;
/**
 *
 * @author cody
 */
public class Gui {
    
    private JTextField searchBar;
    private JTextArea resultsTextArea;
    
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
                JSONArray jsonBreweryArray = (JSONArray) parser.parse(inline);
                System.out.println(jsonBreweryArray);
                
                for (int i = 0; i < jsonBreweryArray.size(); i++) {
                    JSONObject newBreweryObject = (JSONObject) jsonBreweryArray.get(i);
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
        String searchText = searchBar.getText();
        char[] characters = searchText.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == ' ') {
                characters[i] = '_';
            }
        }
        String formattedSearch = new String(characters);
        System.out.println(formattedSearch);
        return formattedSearch;
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
        resultsTextArea = new JTextArea("");
        resultsTextArea.setEditable(false);
        JButton searchButton = new JButton("Search");
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        panel.add(scrollPane);
        panel.add(label);
        panel.add(searchBar);
        panel.add(searchButton);
        
        searchButton.addActionListener(e -> {
            System.out.println("Searching...");
            String searchedTerm = getSearchTerm();
            ArrayList<Brewery> data = search(searchedTerm);
            System.out.println(data.size());
            for (int i = 0; i < data.size() || i < 10; i++) {
                Brewery breweryToPrint = data.get(i);
                System.out.println(breweryToPrint.getBreweryInfo());
                resultsTextArea.append(breweryToPrint.getBreweryInfo());
                resultsTextArea.append("\n");
            }
        });
        JScrollBar vertical = scrollPane.createVerticalScrollBar();
        
        DefaultCaret caret = (DefaultCaret) this.searchBar.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        caret.setVisible(true);
        this.searchBar.setText("");
        
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(scrollPane);
        frame.getRootPane().setDefaultButton(searchButton);
        
        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                searchBar.requestFocusInWindow();
            }
        });
        
        frame.setVisible(true);
        
        
    }
    
}
