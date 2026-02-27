package boutique.entities;

public class Produit {
    private int idProduit;
    private String libelle;
    private double prix;
    private String categorie;
    private int stock;

    public Produit() { }

    public Produit(int idProduit, String libelle, double prix, String categorie, int stock) {
        this.idProduit = idProduit;
        this.libelle = libelle;
        this.prix = prix;
        this.categorie = categorie;
        this.stock = stock;
    }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", categorie='" + categorie + '\'' +
                ", stock=" + stock +
                '}';
    }
}

