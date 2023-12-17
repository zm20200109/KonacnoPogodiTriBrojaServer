/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dbb.DbBroker;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import model.Korisnik;
import model.SkriveniBroj;

/**
 *
 * @author Korisnik
 */
public class Controller {
    
    private static Controller instance;
    private List<Korisnik> korisnici = new ArrayList<>();
    private List<SkriveniBroj> brojevi = new ArrayList<>();
    private DbBroker dbb;
    
    private Controller() {
        dbb = new DbBroker();
        korisnici.add(new Korisnik("pera@gmail.com","123456","Pera","Peric"));
        korisnici.add(new Korisnik("mika@gmail.com","654321","Mika","Mikic"));
        korisnici.add(new Korisnik("laza@gmail.com","246835","Laza","Lazic"));
        
    }
    
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public Korisnik ulogujSe(Korisnik korisnik) {
        for(Korisnik k: korisnici){
            if(k.equals(korisnik))
                return k;
        }
        return null;
    }

    public boolean daLiPostojiSkriveniBroj(SkriveniBroj sb) {
        for(SkriveniBroj broj:brojevi){
            if(broj.equals(sb)){
                return true; // postoji
            }
        }
        return false; // ne postoji
    }

    public void dodajBroj(SkriveniBroj sb) {
        brojevi.add(sb);
    }

    public List<SkriveniBroj> getBrojevi() {
        return brojevi;
    }

    public SkriveniBroj vratiSkriveniBroj(SkriveniBroj skriveniBroj) {
        for(SkriveniBroj sb: brojevi){
            if(sb.getRed() == skriveniBroj.getRed() && sb.getKolona() == skriveniBroj.getKolona()){
                return sb;
            }
        
        }
        return null;
    }

    public List<Autor> vratiListuAutora() {
        return dbb.vratiListuAutoraIzBaze();
    }
    
    
    
    
}
