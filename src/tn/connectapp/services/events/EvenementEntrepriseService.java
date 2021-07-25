/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.connectapp.services.events;

import tn.connectapp.entities.events.EventEntreprise;
import static java.lang.Boolean.parseBoolean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.connectapp.utils.commun.MyConnection;

/**
 *
 * @author Administrator
 */
public class EvenementEntrepriseService {
 
    public List<EventEntreprise> listerEvenementE() {
        List<EventEntreprise> evenementList = new ArrayList<>();
        try {
               Connection con =  MyConnection.getInstance().getCnx();
            String requete = "SELECT * FROM evente";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                EventEntreprise e = new EventEntreprise();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomEvent"));
                e.setDateDeb(rs.getString("dateDebut"));
                e.setDateFin(rs.getString("dateFin"));
               
               e.setOrganisateur(rs.getString("organisateur"));
               e.setType(rs.getString("type"));
               e.setDomaine(rs.getString("domaine"));
                e.setLieu(rs.getString("lieu"));
               e.setPartenaires(rs.getString("prtnrs"));
               e.setDescription(rs.getString("description"));
                if(rs.getString("Paiement").equals("1")){
                   e.setPaiement("true");
               }
               else{
                    e.setPaiement("false");
               }
               System.out.println(e.getPaiement());
                evenementList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }
    public  List<EventEntreprise> findeventEByX(String x) {
        List<EventEntreprise> evenementList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evente where type= ? OR nomEvent= ? OR domaine= ?";
             Connection con = MyConnection.getInstance().getCnx();
             PreparedStatement st; st = con.prepareStatement(requete);
            st.setString(1, x);
            st.setString(2, x);
             st.setString(3, x);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               EventEntreprise e = new EventEntreprise();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomEvent"));
                e.setDateDeb(rs.getString("dateDebut"));
                e.setDateFin(rs.getString("dateFin"));
              
               e.setOrganisateur(rs.getString("organisateur"));
               e.setType(rs.getString("type"));
               e.setDomaine(rs.getString("domaine"));
                e.setLieu(rs.getString("lieu"));
               e.setPartenaires(rs.getString("prtnrs"));
               e.setDescription(rs.getString("description"));
               if(rs.getString("Paiement").equals("1")){
                   e.setPaiement("true");
               }
               else{
                    e.setPaiement("false");
               }
                
                evenementList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }
     public int modifierEvenement(EventEntreprise t) {
        int nbModif = 0;
        try {
            String requete = "UPDATE evente SET nomEvent=? , dateDebut=? , dateFin=? , description=? , domaine=? , type=? , lieu=? , organisateur=? , prtnrs=? WHERE idevent=?";
            Connection con = MyConnection.getInstance().getCnx();
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setString(1, t.getNomEvent());
            pst.setString(2, t.getDateDeb());
            pst.setString(3, t.getDateFin());
            pst.setString(4, t.getDescription());
            pst.setString(5, t.getDomaine());
          
            pst.setString(6, t.getType());
            pst.setString(7, t.getLieu());
              pst.setString(8, t.getOrganisateur());
                pst.setString(9, t.getPartenaires());
           
            pst.setInt(10, t.getIdevent());
            nbModif = pst.executeUpdate();
            System.out.println("Evenement modifiée");
            System.out.println(nbModif);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbModif;
    }
    
}
