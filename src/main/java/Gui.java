/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author cody
 */
public class Gui {
    
    public Gui() {
        
        JFrame frame = new JFrame("Brewery Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
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
        JTextField searchBar = new JTextField(20);
        JButton searchButton = new JButton("Search");
        panel.add(label);
        panel.add(searchBar);
        panel.add(searchButton);
        
        searchButton.addActionListener(e ->
            System.out.println("Searching..."));
        
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, searchBar);
        frame.setVisible(true);
        
        
    }
    
}
