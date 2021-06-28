/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import javax.swing.*;
import java.net.URLEncoder;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author cody
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        
        Gui gui = new Gui();
        
        System.out.println("Enter search field: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        URL url = new URL("https://api.openbrewerydb.org/breweries/search");
        
    }
    
}
