/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class PokreniServer extends Thread{

    @Override
    public void run() {
          
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Klijent se čeka ...");
            Socket socket = serverSocket.accept();
            System.out.println("Klijent je uspesno povezan!");
            
            
            ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(socket);
            okz.start();
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    
    
    }
   
    
   
}
