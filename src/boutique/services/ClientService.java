package boutique.services;

import boutique.DBConnection;
import boutique.dao.IDao;
import boutique.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IDao<Client> {
     @Override
    public void create(Client c) {
        String sql = "INSERT INTO client(nom, ville, email) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNom());
            ps.setString(2, c.getVille());
            ps.setString(3, c.getEmail());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setIdClient(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur create Client", e);
        }
    }

    @Override
    public void update(Client c) {
        String sql = "UPDATE client SET nom=?, ville=?, email=? WHERE idClient=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNom());
            ps.setString(2, c.getVille());
            ps.setString(3, c.getEmail());
            ps.setInt(4, c.getIdClient());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Aucun client trouvé avec id=" + c.getIdClient());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur update Client", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM client WHERE idClient=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur delete Client", e);
        }
    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM client WHERE idClient=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapClient(rs);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findById Client", e);
        }
    }

    @Override
    public List<Client> findAll() {
        String sql = "SELECT * FROM client";
        List<Client> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapClient(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur findAll Client", e);
        }
    }

    private Client mapClient(ResultSet rs) throws SQLException {
        Client c = new Client();
        c.setIdClient(rs.getInt("idClient"));
        c.setNom(rs.getString("nom"));
        c.setVille(rs.getString("ville"));
        c.setEmail(rs.getString("email"));
        return c;
    }
}
