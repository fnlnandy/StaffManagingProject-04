/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Holiday;
import fnln.andy.gpcp.core.Pointage;
import fnln.andy.gpcp.core.PseudoDate;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author andy
 */
public class GPCPUi extends javax.swing.JFrame {

    /**
     * Creates new form GPCPUi
     */
    public GPCPUi() {
        initComponents();
        
        SwingUtilities.invokeLater(() -> {
            DBControl.deferEmployeeController().loadEntries(jEmployeeTable);
            DBControl.deferPointageController().loadEntries(jPointageTable);
            DBControl.deferHolidayController().loadEntries(jHolidayTable);
        });
    }
    
    public static javax.swing.JTable getEmployeeTable() { return jEmployeeTable; }
    public static javax.swing.JTable getPointageTable() { return jPointageTable; }
    public static javax.swing.JTable getHolidayTable() { return jHolidayTable; }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMainTabs = new javax.swing.JTabbedPane();
        jEmployeePanel = new javax.swing.JPanel();
        jSearchBarPanel = new javax.swing.JPanel();
        jSearchEmployeeLabel = new javax.swing.JLabel();
        jSearchContentTextField = new javax.swing.JTextField();
        jSearchEmployeeButton = new javax.swing.JButton();
        jEmployeeCrudPanel = new javax.swing.JPanel();
        jAddNewEmployeeButton = new javax.swing.JButton();
        jEditEmployeeButton = new javax.swing.JButton();
        jRemoveEmployeeButton = new javax.swing.JButton();
        jDisplayRemainingHolidays = new javax.swing.JButton();
        jEmployeeScrollPane = new javax.swing.JScrollPane();
        jEmployeeTable = new javax.swing.JTable();
        jPointagePanel = new javax.swing.JPanel();
        jPointageCrudPanel = new javax.swing.JPanel();
        jAddPointageButton = new javax.swing.JButton();
        jEditPointageButton = new javax.swing.JButton();
        jRemovePointageButton = new javax.swing.JButton();
        jDisplayAbsentEmployeesButton = new javax.swing.JButton();
        jPointageScrollPane = new javax.swing.JScrollPane();
        jPointageTable = new javax.swing.JTable();
        jHolidayPanel = new javax.swing.JPanel();
        jHolidayCrudPanel = new javax.swing.JPanel();
        jAddHolidayButton = new javax.swing.JButton();
        jEditHolidayButton = new javax.swing.JButton();
        jRemoveHolidayButton = new javax.swing.JButton();
        jHolidayScrollPane = new javax.swing.JScrollPane();
        jHolidayTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de Congé(s) et de Pointage(s) du Personnel");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jEmployeePanel.setMaximumSize(new java.awt.Dimension(32767, 78));
        jEmployeePanel.setLayout(new java.awt.GridLayout(0, 1, 0, 30));

        jSearchBarPanel.setLayout(new java.awt.GridLayout(0, 3, 10, 0));

        jSearchEmployeeLabel.setText("Rechercher:");
        jSearchBarPanel.add(jSearchEmployeeLabel);

        jSearchContentTextField.setMaximumSize(new java.awt.Dimension(2147483647, 24));
        jSearchContentTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchContentTextFieldActionPerformed(evt);
            }
        });
        jSearchBarPanel.add(jSearchContentTextField);

        jSearchEmployeeButton.setText("Commencer la recherche");
        jSearchEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchEmployeeButtonActionPerformed(evt);
            }
        });
        jSearchBarPanel.add(jSearchEmployeeButton);

        jEmployeePanel.add(jSearchBarPanel);

        jAddNewEmployeeButton.setText("Ajouter un employé");
        jAddNewEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddNewEmployeeButtonActionPerformed(evt);
            }
        });
        jEmployeeCrudPanel.add(jAddNewEmployeeButton);

        jEditEmployeeButton.setText("Modifier un employé");
        jEditEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditEmployeeButtonActionPerformed(evt);
            }
        });
        jEmployeeCrudPanel.add(jEditEmployeeButton);

        jRemoveEmployeeButton.setText("Retirer un employé");
        jRemoveEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemoveEmployeeButtonActionPerformed(evt);
            }
        });
        jEmployeeCrudPanel.add(jRemoveEmployeeButton);

        jDisplayRemainingHolidays.setText("Consulter le reste des congés de cet employé");
        jDisplayRemainingHolidays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDisplayRemainingHolidaysActionPerformed(evt);
            }
        });
        jEmployeeCrudPanel.add(jDisplayRemainingHolidays);

        jEmployeePanel.add(jEmployeeCrudPanel);

        jEmployeeScrollPane.setMaximumSize(new java.awt.Dimension(32767, 100));

        jEmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro", "Nom", "Prenom", "Poste", "Salaire"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jEmployeeTable.getTableHeader().setReorderingAllowed(false);
        jEmployeeScrollPane.setViewportView(jEmployeeTable);

        jEmployeePanel.add(jEmployeeScrollPane);

        jMainTabs.addTab("Employé(s)", jEmployeePanel);

        jPointagePanel.setLayout(new java.awt.GridLayout(0, 1));

        jAddPointageButton.setText("Ajouter un pointage");
        jAddPointageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddPointageButtonActionPerformed(evt);
            }
        });
        jPointageCrudPanel.add(jAddPointageButton);

        jEditPointageButton.setText("Modifier un pointage");
        jEditPointageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditPointageButtonActionPerformed(evt);
            }
        });
        jPointageCrudPanel.add(jEditPointageButton);

        jRemovePointageButton.setText("Retirer un pointage");
        jRemovePointageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemovePointageButtonActionPerformed(evt);
            }
        });
        jPointageCrudPanel.add(jRemovePointageButton);

        jDisplayAbsentEmployeesButton.setText("Afficher les employés absent pour une date");
        jDisplayAbsentEmployeesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDisplayAbsentEmployeesButtonActionPerformed(evt);
            }
        });
        jPointageCrudPanel.add(jDisplayAbsentEmployeesButton);

        jPointagePanel.add(jPointageCrudPanel);

        jPointageScrollPane.setMaximumSize(new java.awt.Dimension(32767, 100));

        jPointageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date de pointage", "Numéro d'employé", "Pointage"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jPointageTable.getTableHeader().setReorderingAllowed(false);
        jPointageScrollPane.setViewportView(jPointageTable);

        jPointagePanel.add(jPointageScrollPane);

        jMainTabs.addTab("Pointage(s)", jPointagePanel);

        jHolidayPanel.setLayout(new java.awt.GridLayout(0, 1));

        jAddHolidayButton.setText("Ajouter un congé");
        jAddHolidayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddHolidayButtonActionPerformed(evt);
            }
        });
        jHolidayCrudPanel.add(jAddHolidayButton);

        jEditHolidayButton.setText("Modifer un congé");
        jEditHolidayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditHolidayButtonActionPerformed(evt);
            }
        });
        jHolidayCrudPanel.add(jEditHolidayButton);

        jRemoveHolidayButton.setText("Supprimer un congé");
        jRemoveHolidayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRemoveHolidayButtonActionPerformed(evt);
            }
        });
        jHolidayCrudPanel.add(jRemoveHolidayButton);

        jHolidayPanel.add(jHolidayCrudPanel);

        jHolidayScrollPane.setMaximumSize(new java.awt.Dimension(32767, 100));

        jHolidayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro de congé", "Numéro d'employé", "Motif", "Nombre de jours", "Date de demande", "Date de retour"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jHolidayScrollPane.setViewportView(jHolidayTable);

        jHolidayPanel.add(jHolidayScrollPane);

        jMainTabs.addTab("Congé(s)", jHolidayPanel);

        getContentPane().add(jMainTabs);
        jMainTabs.getAccessibleContext().setAccessibleName("employeeTab");
        jMainTabs.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchContentTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchContentTextFieldActionPerformed
        // TODO add your handling code here:
        jSearchEmployeeButtonActionPerformed(null);
    }//GEN-LAST:event_jSearchContentTextFieldActionPerformed

    private void jAddNewEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddNewEmployeeButtonActionPerformed
        // TODO add your handling code here:
        EmployeeFormUi ui = new EmployeeFormUi(this, true);
        
        ui.setIsEditMode(false);
        ui.setTitle("Ajouter un nouvel employé");
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jAddNewEmployeeButtonActionPerformed

    private void jEditEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditEmployeeButtonActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jEmployeeTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup menu for no selected index
            System.out.println("No selected employee.");
            return;
        }
        
        final String numEmp = jEmployeeTable.getValueAt(selectedIndex, 0).toString();
        final String nom = jEmployeeTable.getValueAt(selectedIndex, 1).toString();
        final String prenom = jEmployeeTable.getValueAt(selectedIndex, 2).toString();
        final String poste = jEmployeeTable.getValueAt(selectedIndex, 3).toString();
        final int salaire = (int) jEmployeeTable.getValueAt(selectedIndex, 4);

        Employee employee = new Employee(numEmp, nom, prenom, poste, salaire);
        EmployeeFormUi ui = new EmployeeFormUi(this, true);
        
        ui.setIsEditMode(true);
        ui.setPreviousEmployeeId(numEmp);
        ui.setEmployeeId(numEmp);
        ui.setEmployeeName(nom);
        ui.setEmployeeFirstName(prenom);
        ui.setEmployeeJob(poste);
        ui.setEmployeeSalary(salaire);
        ui.setTitle("Modifier l'employé: " + employee.getPrintable());
        
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jEditEmployeeButtonActionPerformed

    private void jRemoveEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoveEmployeeButtonActionPerformed
        // TODO add your handling code here:
        int selectedIndex = jEmployeeTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            System.out.println("No employee row selected for deletion.");
            return;
        }
        
        final String numEmp = jEmployeeTable.getValueAt(selectedIndex, 0).toString();
        
        DBControl.deferEmployeeController().removeEntry(numEmp);
        DBControl.deferEmployeeController().reloadEntries(jEmployeeTable);
    }//GEN-LAST:event_jRemoveEmployeeButtonActionPerformed

    private void jAddPointageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddPointageButtonActionPerformed
        // TODO add your handling code here:
        PointageFormUi ui = new PointageFormUi(this, true);
        
        ui.initEmployeeData(DBControl.deferEmployeeController().getAllEntries());
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jAddPointageButtonActionPerformed

    private void jRemovePointageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemovePointageButtonActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jPointageTable.getSelectedRow();
        Pointage p = new Pointage();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            return;
        }
        
        p.setDatePointage(jPointageTable.getValueAt(selectedIndex, 0).toString());
        p.setNumEmp(jPointageTable.getValueAt(selectedIndex, 1).toString());
        p.setPointage(jPointageTable.getValueAt(selectedIndex, 2).toString());
        
        DBControl.deferPointageController().removeEntry(p);
        DBControl.deferPointageController().reloadEntries(jPointageTable);
    }//GEN-LAST:event_jRemovePointageButtonActionPerformed

    private void jEditPointageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditPointageButtonActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jPointageTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            return;
        }
        
        final String datePointage = jPointageTable.getValueAt(selectedIndex, 0).toString();
        final String numEmp = jPointageTable.getValueAt(selectedIndex, 1).toString();
        final String pointage = jPointageTable.getValueAt(selectedIndex, 2).toString();

        PointageFormUi ui = new PointageFormUi(this, true);
        
        ui.initEmployeeData(DBControl.deferEmployeeController().getAllEntries());
        ui.setPreviousPointage(datePointage, numEmp, pointage);
        ui.setDatePointage(datePointage);
        ui.setNumEmp(numEmp);
        ui.setPointage(pointage);
        ui.setIsEditMode(true);
        
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);  
        });
    }//GEN-LAST:event_jEditPointageButtonActionPerformed

    private void jAddHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddHolidayButtonActionPerformed
        // TODO add your handling code here:
        HolidayFormUi ui = new HolidayFormUi(this, true);
        
        ui.initEmployeeData(DBControl.deferEmployeeController().getAllEntries());
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jAddHolidayButtonActionPerformed

    private void jRemoveHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRemoveHolidayButtonActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jHolidayTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            return;
        }
        
        final String numConge = jHolidayTable.getValueAt(selectedIndex, 0).toString();
        final String numEmp = jHolidayTable.getValueAt(selectedIndex, 1).toString();
        
        Holiday holiday = new Holiday(numConge, numEmp, null, 0, null, null);
        
        DBControl.deferHolidayController().removeEntry(holiday);
        DBControl.deferHolidayController().reloadEntries(jHolidayTable);
    }//GEN-LAST:event_jRemoveHolidayButtonActionPerformed

    private void jEditHolidayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditHolidayButtonActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jHolidayTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            return;
        }
        
        HolidayFormUi ui = new HolidayFormUi(this, true);
        
        final String numConge = jHolidayTable.getValueAt(selectedIndex, 0).toString();
        final String numEmp = jHolidayTable.getValueAt(selectedIndex, 1).toString();
        final String motif = jHolidayTable.getValueAt(selectedIndex, 2).toString();
        final int nombreJours = (int)jHolidayTable.getValueAt(selectedIndex, 3);
        final String dateDemande = jHolidayTable.getValueAt(selectedIndex, 4).toString();
        final String dateRetour = jHolidayTable.getValueAt(selectedIndex, 5).toString();
        
        ui.initEmployeeData(DBControl.deferEmployeeController().getAllEntries());
        ui.setNumConge(numConge);
        ui.setNumEmp(numEmp);
        ui.setMotif(motif);
        ui.setNombreJours(nombreJours);
        ui.setDateDemande(dateDemande);
        ui.setDateRetour(dateRetour);
        ui.setPreviousHoliday(numConge, numEmp);
        ui.setIsEditMode(true);
        
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jEditHolidayButtonActionPerformed

    private void jSearchEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchEmployeeButtonActionPerformed
        // TODO add your handling code here:
        String searchContent = jSearchContentTextField.getText().trim();
        
        if (searchContent.isEmpty())
        {
            DBControl.deferEmployeeController().reloadEntries(jEmployeeTable);
        }
        else
        {
            List<Employee> elements = DBControl.deferEmployeeController().getMatchingName(searchContent);
            
            DBControl.deferEmployeeController().reloadEntries(elements, jEmployeeTable);
        }
    }//GEN-LAST:event_jSearchEmployeeButtonActionPerformed

    private void jDisplayAbsentEmployeesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDisplayAbsentEmployeesButtonActionPerformed
        // TODO add your handling code here:
        AbsentEmployeesUi ui = new AbsentEmployeesUi(this, true);
        
        SwingUtilities.invokeLater(() -> {
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jDisplayAbsentEmployeesButtonActionPerformed

    private void jDisplayRemainingHolidaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDisplayRemainingHolidaysActionPerformed
        // TODO add your handling code here:
        final int selectedIndex = jEmployeeTable.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            // TODO: add a popup window
            return;
        }
        
        final String numEmp = jEmployeeTable.getValueAt(selectedIndex, 0).toString();
        final String nom = jEmployeeTable.getValueAt(selectedIndex, 1).toString();
        final String prenom = jEmployeeTable.getValueAt(selectedIndex, 2).toString();
        
        RemainingHolidaysUi ui = new RemainingHolidaysUi(this, true);
        
        ui.initDataWithEmployee(new Employee(numEmp, nom, prenom, null, 0));
        
        SwingUtilities.invokeLater(() -> { 
            ui.setVisible(true);
        });
    }//GEN-LAST:event_jDisplayRemainingHolidaysActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GPCPUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GPCPUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GPCPUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GPCPUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GPCPUi().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddHolidayButton;
    private javax.swing.JButton jAddNewEmployeeButton;
    private javax.swing.JButton jAddPointageButton;
    private javax.swing.JButton jDisplayAbsentEmployeesButton;
    private javax.swing.JButton jDisplayRemainingHolidays;
    private javax.swing.JButton jEditEmployeeButton;
    private javax.swing.JButton jEditHolidayButton;
    private javax.swing.JButton jEditPointageButton;
    private javax.swing.JPanel jEmployeeCrudPanel;
    private javax.swing.JPanel jEmployeePanel;
    private javax.swing.JScrollPane jEmployeeScrollPane;
    private static javax.swing.JTable jEmployeeTable;
    private javax.swing.JPanel jHolidayCrudPanel;
    private javax.swing.JPanel jHolidayPanel;
    private javax.swing.JScrollPane jHolidayScrollPane;
    private static javax.swing.JTable jHolidayTable;
    private javax.swing.JTabbedPane jMainTabs;
    private javax.swing.JPanel jPointageCrudPanel;
    private javax.swing.JPanel jPointagePanel;
    private javax.swing.JScrollPane jPointageScrollPane;
    private static javax.swing.JTable jPointageTable;
    private javax.swing.JButton jRemoveEmployeeButton;
    private javax.swing.JButton jRemoveHolidayButton;
    private javax.swing.JButton jRemovePointageButton;
    private javax.swing.JPanel jSearchBarPanel;
    private javax.swing.JTextField jSearchContentTextField;
    private javax.swing.JButton jSearchEmployeeButton;
    private javax.swing.JLabel jSearchEmployeeLabel;
    // End of variables declaration//GEN-END:variables
}
