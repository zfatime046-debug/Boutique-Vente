package boutique.entities;

public class Client {
    private int idClient;
    private String nom;
    private String ville;
    private String email;

    public Client() { }

    public Client(int idClient, String nom, String ville, String email) {
        this.idClient = idClient;
        this.nom = nom;
        this.ville = ville;
        this.email = email;
    }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
