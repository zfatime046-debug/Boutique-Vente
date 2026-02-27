package boutique.ui;

import boutique.entities.Client;
import boutique.services.ClientService;
import java.util.List;

public class TestClient {
    public static void main(String[] args) {

        ClientService service = new ClientService();

        // CREATE
        Client c = new Client();
        c.setNom("Fatima");
        c.setVille("Casablanca");
        c.setEmail("fatima@gmail.com");

        service.create(c);
        System.out.println("Client ajouté, id = " + c.getIdClient());

        // FIND BY ID
        Client c2 = service.findById(c.getIdClient());
        System.out.println("findById = " + (c2 != null ? c2.getNom() : "null"));

        // UPDATE
        c.setVille("Rabat");
        service.update(c);
        System.out.println("Client modifié");

        // FIND ALL
        List<Client> clients = service.findAll();
        System.out.println("Nombre total clients = " + clients.size());

        // DELETE
        service.delete(c.getIdClient());
        System.out.println("Client supprimé");

        // Vérification
        Client c3 = service.findById(c.getIdClient());
        System.out.println("Après delete = " + c3);
    }
}