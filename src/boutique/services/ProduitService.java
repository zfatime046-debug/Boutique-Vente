package boutique.services;

import boutique.DBConnection;
import boutique.dao.IDao;
import boutique.entities.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProduitService implements IDao<Produit> {

 @Override
    public void create(Produit p) {
        String sql = "INSERT INTO produit(libelle, prix, categorie, stock) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getLibelle());
            ps.setDouble(2, p.getPrix());
            ps.setString(3, p.getCategorie());
            ps.setInt(4, p.getStock());

            ps.executeUpdate();

            // récupérer l'id auto-increment
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setIdProduit(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur create Produit", e);
        } 
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Toutes");
        categories.add("Accessoire");
        categories.add("Informatique");
        categories.add("Électroménager");
        return categories;
    }
    
    
    @Override
    public void update(Produit p) {
        String sql = "UPDATE produit SET libelle=?, prix=?, categorie=?, stock=? WHERE idProduit=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getLibelle());
            ps.setDouble(2, p.getPrix());
            ps.setString(3, p.getCategorie());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdProduit());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Aucun produit trouvé avec id=" + p.getIdProduit());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur update Produit", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM produit WHERE idProduit=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur delete Produit", e);
        }
    }

    @Override
   public Produit findById(int id) {
    Produit p = null;
    String sql = "SELECT * FROM produit WHERE idProduit=?";
    try (Connection cn = DBConnection.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            p = new Produit();
            p.setIdProduit(rs.getInt("idProduit"));
            p.setLibelle(rs.getString("libelle"));
            p.setPrix(rs.getDouble("prix"));
            p.setStock(rs.getInt("stock"));
            p.setCategorie(rs.getString("categorie"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return p;
}


    @Override
    public List<Produit> findAll() {
        String sql = "SELECT * FROM produit";
        List<Produit> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapProduit(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll Produit", e);
        }
    }

    // ---- méthode utilitaire pour éviter répéter le code ----
    private Produit mapProduit(ResultSet rs) throws SQLException {
        Produit p = new Produit();
        p.setIdProduit(rs.getInt("idProduit"));
        p.setLibelle(rs.getString("libelle"));
        p.setPrix(rs.getDouble("prix"));
        p.setCategorie(rs.getString("categorie"));
        p.setStock(rs.getInt("stock"));
        return p;
    }
}
