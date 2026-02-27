package boutique.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
import boutique.services.VenteService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class Statistiques extends javax.swing.JInternalFrame {
    private VenteService venteService = new VenteService();
    private JPanel chartContainer;
    private JButton btnActualiser;
    
    public Statistiques() {
        initComponents();
        setTitle("Statistiques des ventes");
        setSize(800, 600);
        setMaximizable(true);
    setResizable(true);
     setClosable(true);
    setIconifiable(true);
       // setLocationRelativeTo(null);
    
        // Maximiser automatiquement dans le desktop
    try {
        setMaximum(true); // Ceci agrandit la fenêtre interne
    } catch (Exception e) {}
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Titre
        JLabel title = new JLabel("Chiffre d'affaires par mois", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);
        
        // Conteneur pour le graphique
        chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBackground(Color.WHITE);
        add(chartContainer, BorderLayout.CENTER);
        
        // Bouton actualiser
        JPanel buttonPanel = new JPanel();
        btnActualiser = new JButton("Actualiser le graphique");
        btnActualiser.addActionListener(e -> refreshChart());
        buttonPanel.add(btnActualiser);
        
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            MainForm mf = new MainForm();
            mf.setVisible(true);
            this.dispose();
        });
        buttonPanel.add(btnRetour);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Charger le graphique
        refreshChart();
    }
    
    private void refreshChart() {
        chartContainer.removeAll();
        
        // Récupérer les données
        Map<String, Double> caParMois = venteService.getChiffreAffairesParMois();
        
        System.out.println("=== DÉBOGAGE GRAPHIQUE ===");
    System.out.println("Nombre de mois trouvés : " + caParMois.size());
    for (Map.Entry<String, Double> entry : caParMois.entrySet()) {
        System.out.println(entry.getKey() + " = " + entry.getValue() + " €");
    }
        
        // Créer le dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Ajouter les données
        for (Map.Entry<String, Double> entry : caParMois.entrySet()) {
            String moisAnnee = entry.getKey(); // Format: "2026-02"
            String[] parts = moisAnnee.split("-");
            String annee = parts[0];
            String moisNum = parts[1];
            
            // Convertir en nom de mois
            String nomMois = getNomMois(Integer.parseInt(moisNum));
            String label = nomMois + " " + annee;
            
            dataset.addValue(entry.getValue(), "CA", label);
        }
        
        // Créer le graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
            "Chiffre d'affaires mensuel",
            "Mois",
            "Montant (€)",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false
        );
        
        // Personnalisation
        chart.setBackgroundPaint(Color.WHITE);
        
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setRangeGridlinePaint(Color.BLACK);
        
        // Rotation des étiquettes pour meilleure lisibilité
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        // Ajouter au conteneur
        ChartPanel chartPanel = new ChartPanel(chart);
        
//chartPanel.setPreferredSize(new Dimension(750, 450));
  //      chartContainer.add(chartPanel, BorderLayout.CENTER);
           chartPanel.setPreferredSize(null);
    chartPanel.setMinimumSize(new Dimension(400, 300));
    
    chartContainer.add(chartPanel, BorderLayout.CENTER);
        
        chartContainer.revalidate();
        chartContainer.repaint();
    }
    
    private String getNomMois(int mois) {
        String[] noms = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                         "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        return noms[mois - 1];
    }
public void setLocationRelativeTo(java.awt.Component c) {
}
    //private void setLocationRelativeTo(Object object) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}