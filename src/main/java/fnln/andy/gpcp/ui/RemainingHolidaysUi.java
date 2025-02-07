/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Holiday;
import fnln.andy.gpcp.core.PseudoDate;
import fnln.andy.gpcp.core.Util;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andy
 */
public class RemainingHolidaysUi extends javax.swing.JDialog {

    /**
     * Creates new form RemainingHolidaysUi
     */
    public RemainingHolidaysUi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private int getRemainingDays(Employee e)
    {
        final List<Holiday> holidays = DBControl.deferHolidayController().getAllEntries();
        final int maxDays = 30;
        final PseudoDate now = PseudoDate.getCurrentDate();
        int totalDays = 0;
        
        for (Holiday holiday : holidays)
        {
            String numEmp = e.getNumEmp();
            
            if (!numEmp.equals(holiday.getNumEmp()))
                continue;
            
            PseudoDate date = holiday.getDateDemande();
            
            if (date.getYear() != now.getYear())
                continue;
            
            totalDays += holiday.getNombreJours();
        }
        
        return maxDays - totalDays;
    }
    
    public void initDataWithEmployee(Employee e)
    {
        DefaultTableModel tableModel = Util.getDefaultTableModel(jCurrentEmployeeTable);
        
        if (tableModel == null)
            return;
        
        tableModel.addRow(new Object[] { e.getNumEmp(), e.getNom(), e.getPrenom() });
        jRemainingDaysSpinner.setValue(getRemainingDays(e));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRemainingPanel = new javax.swing.JPanel();
        jRemainingDaysLabel = new javax.swing.JLabel();
        jRemainingDaysSpinner = new javax.swing.JSpinner();
        jCurrentEmployeePanel = new javax.swing.JPanel();
        jCurrentEmployeeLabel = new javax.swing.JLabel();
        jCurrentEmployeeScrollPane = new javax.swing.JScrollPane();
        jCurrentEmployeeTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jRemainingPanel.setLayout(new javax.swing.BoxLayout(jRemainingPanel, javax.swing.BoxLayout.LINE_AXIS));

        jRemainingDaysLabel.setText("Jours restants pour cette année:");
        jRemainingPanel.add(jRemainingDaysLabel);

        jRemainingDaysSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 0, 1));
        jRemainingPanel.add(jRemainingDaysSpinner);

        getContentPane().add(jRemainingPanel);

        jCurrentEmployeeLabel.setText("Employé concerné:");
        jCurrentEmployeePanel.add(jCurrentEmployeeLabel);

        jCurrentEmployeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numéro d'employé", "Nom de l'employé", "Prénom de l'employé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jCurrentEmployeeScrollPane.setViewportView(jCurrentEmployeeTable);

        jCurrentEmployeePanel.add(jCurrentEmployeeScrollPane);

        getContentPane().add(jCurrentEmployeePanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RemainingHolidaysUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemainingHolidaysUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemainingHolidaysUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemainingHolidaysUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RemainingHolidaysUi dialog = new RemainingHolidaysUi(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jCurrentEmployeeLabel;
    private javax.swing.JPanel jCurrentEmployeePanel;
    private javax.swing.JScrollPane jCurrentEmployeeScrollPane;
    private javax.swing.JTable jCurrentEmployeeTable;
    private javax.swing.JLabel jRemainingDaysLabel;
    private javax.swing.JSpinner jRemainingDaysSpinner;
    private javax.swing.JPanel jRemainingPanel;
    // End of variables declaration//GEN-END:variables
}
