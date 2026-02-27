package boutique.ui;

import boutique.entities.Client;
import boutique.services.ClientService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClientForm extends javax.swing.JInternalFrame {

     private ClientService clientService = new ClientService();




    /**
     * Creates new form ClientFrame
     */
    public ClientForm() {
        initComponents();
        setTitle("Gestion des Clients");
        txtIdClient.setEditable(false);
        comboVille.addItem("Tanger");
        comboVille.addItem("Rabat");
        comboVille.addItem("Casablanca");
        comboVille.addItem("Fès");
        loadTable();
    }
private void loadTable() {
    DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
    model.setRowCount(0);

    List<Client> clients = clientService.findAll();

    for (Client c : clients) {
        model.addRow(new Object[]{
            c.getIdClient(),
            c.getNom(),
            c.getVille(),
            c.getEmail()
        });
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        tableClients = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAjouter = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnVider = new javax.swing.JButton();
        btnRetour = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblIdClient = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        lblVille = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtIdClient = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboVille = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tableClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IdClient", "Nom", "ville", "email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableClients);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(61, 6, 0, 76);
        jPanel2.add(btnAjouter, gridBagConstraints);

        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 76);
        jPanel2.add(btnModifier, gridBagConstraints);

        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 76);
        jPanel2.add(btnSupprimer, gridBagConstraints);

        btnVider.setText("vider");
        btnVider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 76);
        jPanel2.add(btnVider, gridBagConstraints);

        btnRetour.setText("Retour");
        btnRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetourActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 20, 76);
        jPanel2.add(btnRetour, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        lblIdClient.setText("IdClient :");

        lblNom.setText("Nom :");

        lblVille.setText("Ville :");

        lblEmail.setText("Email :");

        txtIdClient.setEditable(false);
        txtIdClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClientActionPerformed(evt);
            }
        });

        jLabel1.setText("Formulaire :");

        jLabel3.setText("Actions: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNom)
                    .addComponent(lblIdClient)
                    .addComponent(lblVille)
                    .addComponent(lblEmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail)
                    .addComponent(comboVille, 0, 677, Short.MAX_VALUE)
                    .addComponent(txtNom)
                    .addComponent(txtIdClient))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdClient)
                    .addComponent(txtIdClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNom)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVille)
                    .addComponent(comboVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel2.add(jPanel1, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientsMouseClicked

        int row = tableClients.getSelectedRow();
        txtIdClient.setText(tableClients.getValueAt(row, 0).toString());
        txtNom.setText(tableClients.getValueAt(row, 1).toString());
        comboVille.setSelectedItem(tableClients.getValueAt(row, 2).toString());
        txtEmail.setText(tableClients.getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableClientsMouseClicked

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed

        String nom = txtNom.getText().trim();
        String email = txtEmail.getText().trim();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nom obligatoire !");
            return;
        }
        if (comboVille.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Choisissez une ville !");
            return;
        }
        if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email invalide !");
            return;
        }

        Client c = new Client();
        c.setNom(nom);
        c.setVille(comboVille.getSelectedItem().toString());
        c.setEmail(email);

        clientService.create(c);
        loadTable();
        JOptionPane.showMessageDialog(this, "Client ajouté !");
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed

        int row = tableClients.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un client dans la table !");
            return;
        }

        String nom = txtNom.getText().trim();
        String email = txtEmail.getText().trim();

        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nom obligatoire !");
            return;
        }
        if (comboVille.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Choisissez une ville !");
            return;
        }
        if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Email invalide !");
            return;
        }

        int id = Integer.parseInt(tableClients.getValueAt(row, 0).toString());

        try {
            Client c = new Client();
            c.setIdClient(id);
            c.setNom(nom);
            c.setVille(comboVille.getSelectedItem().toString());
            c.setEmail(email);

            clientService.update(c);
            loadTable();
            JOptionPane.showMessageDialog(this, "Client modifié !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur modification : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed

        int row = tableClients.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez un client dans la table !");
            return;
        }

        int id = Integer.parseInt(tableClients.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Voulez-vous vraiment supprimer ce client ?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            clientService.delete(id);
            loadTable();
            JOptionPane.showMessageDialog(this, "Client supprimé !");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur suppression : " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnViderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViderActionPerformed

        txtIdClient.setText("");
        txtNom.setText("");
        txtEmail.setText("");
        if (comboVille.getItemCount() > 0) comboVille.setSelectedIndex(0);

        tableClients.clearSelection();
        txtNom.requestFocus();
    }//GEN-LAST:event_btnViderActionPerformed

    private void btnRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetourActionPerformed

        MainForm mf = new MainForm();
        mf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRetourActionPerformed

    private void txtIdClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClientActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRetour;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JButton btnVider;
    private javax.swing.JComboBox comboVille;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIdClient;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblVille;
    private javax.swing.JTable tableClients;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdClient;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
