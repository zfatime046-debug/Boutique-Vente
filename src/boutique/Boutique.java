package boutique;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Boutique {

    public static void main(String[] args) {

        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT idClient, nom FROM client")) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("idClient") + " - " + 
                    rs.getString("nom")
                );
            }

            System.out.println("Connexion réussie !");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

