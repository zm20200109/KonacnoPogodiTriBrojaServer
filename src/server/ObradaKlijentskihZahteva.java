/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Autor;
import model.Korisnik;
import model.SkriveniBroj;
import operacije.Operation;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread{

    private Socket socket;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        while(true){
        
            KlijentskiZahtev kz = primiZahtev();
            System.out.println("KORISNIK KOJI JE PRIMLJEN OD STRANE KLIJENTA:"+kz.getArgument().toString());
            ServerskiOdgovor so = new ServerskiOdgovor();
        
            switch(kz.getOperation()){
                case Operation.LOG_IN:
                    Korisnik k = Controller.getInstance().ulogujSe((Korisnik)kz.getArgument()); 
                    System.out.println("KORISNIK KOJEG JE VRATILA METODA ULOGUJ SE KONTROLERA: " + k.toString());
                    if(k!=null){
                        //treba da se posalje klijentu u odgovoru korisnicko ime, prezime i sve da bi se prikazao na njegovoj formi
                        so.setResult(k);
                    }
                    break;
                case Operation.POGODI_SKRIVENI_BROJ:
                    SkriveniBroj sb = Controller.getInstance().vratiSkriveniBroj((SkriveniBroj)kz.getArgument());
                    so.setResult(sb);
                    break;
                case Operation.VRATI_SVE_AUTORE:    
                    List<Autor> autori  = Controller.getInstance().vratiListuAutora();
                    so.setResult(autori);
                    break;
                default:
                    break;
            }
            posaljiOdgovor(so);
        }
        
    }

    private KlijentskiZahtev primiZahtev() {
        
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Primljen je zahtev od klijenta.");
            return (KlijentskiZahtev)ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
            System.out.println("Poslat je odgovor klijentu.");
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    
    
}
