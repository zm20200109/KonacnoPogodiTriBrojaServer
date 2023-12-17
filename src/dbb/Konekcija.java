/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;

/**
 *
 * @author Korisnik
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Konekcija {
    
    private static Konekcija instance;
    private Connection connection;
    
    private Konekcija(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/client_server_author","root","");
            connection.setAutoCommit(false);
            System.out.println("Uspostavljena konekcija!!!");
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Konekcija getInstance(){
        if(instance == null){
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    
    
}
