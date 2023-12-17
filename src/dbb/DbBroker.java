/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Autor;

/**
 *
 * @author Korisnik
 */
public class DbBroker {

    public List<Autor> vratiListuAutoraIzBaze() {
        List<Autor> autori = new ArrayList<>();
        String upit = "SELECT * FROM author";
        Connection con = Konekcija.getInstance().getConnection();
        Statement st=null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id =(int) rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                java.util.Date datumD =new java.util.Date(rs.getDate("birthday").getTime());
                int marriedInt = rs.getInt("married");
                boolean marBool = false;
                if(marriedInt==1){
                    marBool = true;
                }else{
                    marBool = false;
                }
                Autor a = new Autor(id,firstname,lastname,datumD,marBool);
                autori.add(a);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autori;
    }
    
}
