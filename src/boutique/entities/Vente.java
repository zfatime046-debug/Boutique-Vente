package boutique.entities;

import java.sql.Date;

public class Vente {
    private int idVente;
    private Date dateVente;
    private int idProduit;
    private int idClient;
    private int quantite;

    public Vente() { }

    public Vente(int idVente, Date dateVente, int idProduit, int idClient, int quantite) {
        this.idVente = idVente;
        this.dateVente = dateVente;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.quantite = quantite;
    }

    public int getIdVente() { return idVente; }
    public void setIdVente(int idVente) { this.idVente = idVente; }

    public Date getDateVente() { return dateVente; }
    public void setDateVente(Date dateVente) { this.dateVente = dateVente; }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    @Override
    public String toString() {
        return "Vente{" +
                "idVente=" + idVente +
                ", dateVente=" + dateVente +
                ", idProduit=" + idProduit +
                ", idClient=" + idClient +
                ", quantite=" + quantite +
                '}';
    }
}
