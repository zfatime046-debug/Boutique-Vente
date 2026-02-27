package boutique.services;

import boutique.DBConnection;
import boutique.dao.IDao;
import boutique.entities.Vente;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VenteService implements IDao<Vente> {

    @Override
    public void create(Vente v) {

        String sqlCheckStock = "SELECT stock FROM produit WHERE idProduit = ?";
        String sqlInsertVente = "INSERT INTO vente(dateVente, idProduit, idClient, quantite) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE produit SET stock = stock - ? WHERE idProduit = ?";

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // ✅ begin transaction

            // 1) vérifier stock
            int stockActuel;
            try (PreparedStatement ps = con.prepareStatement(sqlCheckStock)) {
                ps.setInt(1, v.getIdProduit());
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new RuntimeException("Produit introuvable : idProduit=" + v.getIdProduit());
                    }
                    stockActuel = rs.getInt("stock");
                }
            }

            if (v.getQuantite() <= 0) {
                throw new RuntimeException("Quantité invalide !");
            }

            if (stockActuel < v.getQuantite()) {
                throw new RuntimeException("Stock insuffisant ! Stock=" + stockActuel + ", demandé=" + v.getQuantite());
            }

            // 2) INSERT vente
            try (PreparedStatement ps = con.prepareStatement(sqlInsertVente, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDate(1, v.getDateVente());
                ps.setInt(2, v.getIdProduit());
                ps.setInt(3, v.getIdClient());
                ps.setInt(4, v.getQuantite());

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        v.setIdVente(rs.getInt(1));
                    }
                }
            }

            // 3) UPDATE stock
            try (PreparedStatement ps = con.prepareStatement(sqlUpdateStock)) {
                ps.setInt(1, v.getQuantite());
                ps.setInt(2, v.getIdProduit());
                ps.executeUpdate();
            }

            con.commit(); // ✅ commit transaction
            System.out.println("Vente ajoutée + stock mis à jour ✅");

        } catch (Exception e) {
            try {
                if (con != null) con.rollback(); // ✅ rollback si erreur
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Erreur create Vente", e);

        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Vente v) {
        // ⚠️ simple update (ne touche pas au stock)
        String sql = "UPDATE vente SET dateVente=?, idProduit=?, idClient=?, quantite=? WHERE idVente=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, v.getDateVente());
            ps.setInt(2, v.getIdProduit());
            ps.setInt(3, v.getIdClient());
            ps.setInt(4, v.getQuantite());
            ps.setInt(5, v.getIdVente());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur update Vente", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM vente WHERE idVente=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur delete Vente", e);
        }
    }

    @Override
    public Vente findById(int id) {
        String sql = "SELECT * FROM vente WHERE idVente=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapVente(rs);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById Vente", e);
        }
    }

    @Override
    public List<Vente> findAll() {
        String sql = "SELECT * FROM vente";
        List<Vente> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(mapVente(rs));
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll Vente", e);
        }
    }

    private Vente mapVente(ResultSet rs) throws SQLException {
        Vente v = new Vente();
        v.setIdVente(rs.getInt("idVente"));
        v.setDateVente(rs.getDate("dateVente"));
        v.setIdProduit(rs.getInt("idProduit"));
        v.setIdClient(rs.getInt("idClient"));
        v.setQuantite(rs.getInt("quantite"));
        return v;
    }
  public List<Object[]> venteParProduit() {
    List<Object[]> rows = new ArrayList<>();

    String sql =
    "SELECT p.idProduit, p.libelle, " +
    "SUM(v.quantite) AS totalVentes, " +
    "COUNT(v.idVente) AS nbrVentes, " +
    "SUM(v.quantite * p.prix) AS prixTotal " +
    "FROM vente v " +
    "JOIN produit p ON p.idProduit = v.idProduit " +
    "GROUP BY p.idProduit, p.libelle, p.prix " +
    "ORDER BY totalVentes DESC";

    try (Connection cn = DBConnection.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            rows.add(new Object[]{
                rs.getInt("idProduit"),
                rs.getString("libelle"),
                rs.getInt("totalVentes"),
                rs.getInt("nbrVentes"),
                rs.getDouble("prixTotal")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rows;
}
  public List<Vente> findByPeriode(Date d1, Date d2) {
    String sql = "SELECT * FROM vente WHERE dateVente BETWEEN ? AND ?";
    List<Vente> list = new ArrayList<>();

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, d1);
        ps.setDate(2, d2);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapVente(rs));
            }
        }
        return list;

    } catch (SQLException e) {
        throw new RuntimeException("Erreur findByPeriode Vente", e);
    }

}
  public List<Vente> findByCategorieAndPeriode(String categorie, Date d1, Date d2) {
    String sql =
        "SELECT v.* " +
        "FROM vente v " +
        "JOIN produit p ON p.idProduit = v.idProduit " +
        "WHERE p.categorie = ? AND v.dateVente BETWEEN ? AND ?";

    List<Vente> list = new ArrayList<>();

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, categorie);
        ps.setDate(2, d1);
        ps.setDate(3, d2);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapVente(rs));
            }
        }
        return list;

    } catch (SQLException e) {
        throw new RuntimeException("Erreur findByCategorieAndPeriode Vente", e);
    }
}
  public List<String> getAllCategories() {
    String sql = "SELECT DISTINCT categorie FROM produit ORDER BY categorie";
    List<String> list = new ArrayList<>();

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("categorie"));
        }
        return list;

    } catch (SQLException e) {
        throw new RuntimeException("Erreur getAllCategories", e);
    }
}
public Map<String, Double> getChiffreAffairesParMois() {

    String sql =
        "SELECT DATE_FORMAT(v.dateVente, '%Y-%m') AS mois, " +
        "SUM(v.quantite * p.prix) AS chiffreAffaires " +
        "FROM vente v " +
        "JOIN produit p ON v.idProduit = p.idProduit " +
        "GROUP BY mois " +
        "ORDER BY mois";

    Map<String, Double> map = new LinkedHashMap<>();

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String mois = rs.getString("mois");
            double total = rs.getDouble("chiffreAffaires");
            map.put(mois, total);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erreur getChiffreAffairesParMois", e);
    }

    return map;
}
}
