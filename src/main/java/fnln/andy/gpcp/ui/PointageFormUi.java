/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Pointage;
import fnln.andy.gpcp.core.PseudoDate;
import fnln.andy.gpcp.core.Util;
import fnln.andy.gpcp.inputverifiers.DayInDateInputVerifier;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author andy
 */
public class PointageFormUi extends javax.swing.JDialog {

    /**
     * Creates new form PointageFormUi
     */
    public PointageFormUi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void initEmployeeData(List<Employee> employees)
    {
        ComboBoxModel<String> numEmpCBM = jNumEmpComboBox.getModel();
        ComboBoxModel<String> empNameCBM = jEmployeeNameComboBox.getModel();
        
        if ((!(numEmpCBM instanceof DefaultComboBoxModel)) ||
             !(empNameCBM instanceof DefaultComboBoxModel))
            return;
        
        DefaultComboBoxModel<String> numEmpCBDefaultM = (DefaultComboBoxModel<String>)(numEmpCBM);
        DefaultComboBoxModel<String> empNameCBDefaultM = (DefaultComboBoxModel<String>)(empNameCBM);
        
        for (Employee e : employees) {
            numEmpCBDefaultM.addElement(e.getNumEmp());
            empNameCBDefaultM.addElement(e.getNom() + " " + e.getPrenom());
        }
    }
    
    private boolean isNonRedundantEntry(PseudoDate newDate, int newNumEmp)
    {
        PseudoDate prevDate = (m_IsEditMode ? m_PreviousPointage.getDatePointage() : newDate) ;
        int prevNumEmp =  (m_IsEditMode ? Integer.parseInt(m_PreviousPointage.getNumEmp()) : newNumEmp);
        
        if (m_IsEditMode && prevDate.equals(newDate) && prevNumEmp == newNumEmp)
            return true;
        
        return !DBControl.deferPointageController().entryExists(DataArg.makeDataArg(new Object[] { newDate, newNumEmp }));
    }
    
    public boolean isFormLegit()
    {
        final java.awt.Frame parent = (java.awt.Frame)getOwner();
        
        if (jNumEmpComboBox.getItemCount() == 0)
        {
            Util.invokeErrorMessage(parent, "Aucun employé dans cette base de données.");
            return false;
        }
        
        final int currentDay = (int)jDatePointageDaySpinner.getValue();
        final int currentMonth = jDatePointageMonthComboBox.getSelectedIndex() + 1;
        final int currentYear = (int)jDatePointageYearSpinner.getValue();
        
        PseudoDate pointageDate = new PseudoDate(currentDay, currentMonth, currentYear);
        
        final int numEmp = Integer.parseInt(jNumEmpComboBox.getSelectedItem().toString());
        
        if (!isNonRedundantEntry(pointageDate, numEmp))
        {
            Util.invokeErrorMessage(parent, "La date de pointage et l'employé concerné correspondent déjà à une donnée présente.");
            return false;
        }
        if (pointageDate.getYear() < 2000)
        {
            Util.invokeErrorMessage(parent, "L'année de pointage doit être >= 2000.");
            return false;
        }
        if (numEmp == -1)
        {
            Util.invokeErrorMessage(parent, "L'employé séléctionné doit être valide.");
            return false;
        }
        
        
        return true;
    }
    
    public void setPreviousPointage(String datePointage, String numEmp, String pointage)
    {
        m_PreviousPointage.setDatePointage(datePointage);
        m_PreviousPointage.setNumEmp(numEmp);
        m_PreviousPointage.setPointage(pointage);
    }
    
    public void setIsEditMode(boolean isEditMode)
    {
        m_IsEditMode = isEditMode;
    }
    
    public void setDatePointage(String datePointage)
    {
        PseudoDate date = new PseudoDate(datePointage);
        
        jDatePointageDaySpinner.setValue(date.getDay());
        jDatePointageMonthComboBox.setSelectedIndex(date.getMonth() - 1);
        jDatePointageYearSpinner.setValue(date.getYear());
    }
    public void setNumEmp(String numEmp)
    {
        int index = 0;
        final int max = jNumEmpComboBox.getItemCount();
        
        for (; index < max; index++)
        {
            String curr = jNumEmpComboBox.getItemAt(index);
            
            if (curr.equals(numEmp))
                break;
        }
        
        if (index >= max)
            index = 0;
        
        jNumEmpComboBox.setSelectedIndex(index);
    }
    public void setPointage(String pointage)
    {
        if ("Oui".equals(pointage))
        {
            jPointageYesRadioButton.setSelected(true);
            jPointageNoRadioButton.setSelected(false);
        }
        else
        {
            jPointageYesRadioButton.setSelected(false);
            jPointageNoRadioButton.setSelected(true);
        }
    }
            
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDatePointageWrapperPanel = new javax.swing.JPanel();
        jDatePointageLabel = new javax.swing.JLabel();
        jDatePointagePanel = new javax.swing.JPanel();
        jDatePointageDayLabel = new javax.swing.JLabel();
        jDatePointageMonthLabel = new javax.swing.JLabel();
        jDatePointageYearLabel = new javax.swing.JLabel();
        jDatePointageDaySpinner = new javax.swing.JSpinner();
        jDatePointageMonthComboBox = new javax.swing.JComboBox<>();
        jDatePointageYearSpinner = new javax.swing.JSpinner();
        jNumEmpWrapperPanel = new javax.swing.JPanel();
        jNumEmpLabel = new javax.swing.JLabel();
        jNumEmpPanel = new javax.swing.JPanel();
        jNumEmpComboBox = new javax.swing.JComboBox<>();
        jEmployeeNameComboBox = new javax.swing.JComboBox<>();
        jPointageWrapperPanel = new javax.swing.JPanel();
        jPointageLabel = new javax.swing.JLabel();
        jPointagePanel = new javax.swing.JPanel();
        jPointageYesRadioButton = new javax.swing.JRadioButton();
        jPointageNoRadioButton = new javax.swing.JRadioButton();
        jButtonWrapperPanel = new javax.swing.JPanel();
        jConfirmPointageButton = new javax.swing.JButton();
        jResetPointageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulaire d'un pointage");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jDatePointageWrapperPanel.setLayout(new javax.swing.BoxLayout(jDatePointageWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jDatePointageLabel.setText("Date de pointage:");
        jDatePointageWrapperPanel.add(jDatePointageLabel);

        jDatePointagePanel.setLayout(new java.awt.GridLayout(0, 3, 4, 0));

        jDatePointageDayLabel.setText("Jour");
        jDatePointagePanel.add(jDatePointageDayLabel);

        jDatePointageMonthLabel.setText("Mois");
        jDatePointagePanel.add(jDatePointageMonthLabel);

        jDatePointageYearLabel.setText("Année");
        jDatePointagePanel.add(jDatePointageYearLabel);

        jDatePointageDaySpinner.setValue(PseudoDate.getCurrentDate().getDay());
        jDatePointageDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDatePointageDaySpinnerStateChanged(evt);
            }
        });
        jDatePointagePanel.add(jDatePointageDaySpinner);

        jDatePointageMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Fervrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" }));
        jDatePointageMonthComboBox.setSelectedIndex(PseudoDate.getCurrentDate().getMonth() - 1);
        jDatePointageMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatePointageMonthComboBoxActionPerformed(evt);
            }
        });
        jDatePointagePanel.add(jDatePointageMonthComboBox);

        jDatePointageYearSpinner.setValue(PseudoDate.getCurrentDate().getYear());
        jDatePointageYearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDatePointageYearSpinnerStateChanged(evt);
            }
        });
        jDatePointagePanel.add(jDatePointageYearSpinner);

        jDatePointageWrapperPanel.add(jDatePointagePanel);

        getContentPane().add(jDatePointageWrapperPanel);

        jNumEmpWrapperPanel.setLayout(new javax.swing.BoxLayout(jNumEmpWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jNumEmpLabel.setText("Numéro d'employé:");
        jNumEmpWrapperPanel.add(jNumEmpLabel);

        jNumEmpPanel.setLayout(new java.awt.GridLayout(1, 0));

        jNumEmpComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNumEmpComboBoxActionPerformed(evt);
            }
        });
        jNumEmpPanel.add(jNumEmpComboBox);

        jEmployeeNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeNameComboBoxActionPerformed(evt);
            }
        });
        jNumEmpPanel.add(jEmployeeNameComboBox);

        jNumEmpWrapperPanel.add(jNumEmpPanel);

        getContentPane().add(jNumEmpWrapperPanel);

        jPointageWrapperPanel.setLayout(new javax.swing.BoxLayout(jPointageWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPointageLabel.setText("Pointage:");
        jPointageWrapperPanel.add(jPointageLabel);

        jPointageYesRadioButton.setSelected(true);
        jPointageYesRadioButton.setText("Oui");
        jPointageYesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPointageYesRadioButtonActionPerformed(evt);
            }
        });
        jPointagePanel.add(jPointageYesRadioButton);

        jPointageNoRadioButton.setText("Non");
        jPointageNoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPointageNoRadioButtonActionPerformed(evt);
            }
        });
        jPointagePanel.add(jPointageNoRadioButton);

        jPointageWrapperPanel.add(jPointagePanel);

        getContentPane().add(jPointageWrapperPanel);

        jButtonWrapperPanel.setLayout(new javax.swing.BoxLayout(jButtonWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jConfirmPointageButton.setText("Confirmer");
        jConfirmPointageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfirmPointageButtonActionPerformed(evt);
            }
        });
        jButtonWrapperPanel.add(jConfirmPointageButton);

        jResetPointageButton.setText("Réinitialiser");
        jResetPointageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetPointageButtonActionPerformed(evt);
            }
        });
        jButtonWrapperPanel.add(jResetPointageButton);

        getContentPane().add(jButtonWrapperPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDatePointageDaySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDatePointageDaySpinnerStateChanged
        // TODO add your handling code here:
        int currentMonth = jDatePointageMonthComboBox.getSelectedIndex();
        int currentYear = (int)jDatePointageYearSpinner.getValue();
        
        DayInDateInputVerifier.validateSpinnerValue(currentMonth, currentYear, jDatePointageDaySpinner);
    }//GEN-LAST:event_jDatePointageDaySpinnerStateChanged

    private void jDatePointageYearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDatePointageYearSpinnerStateChanged
        // TODO add your handling code here:
        jDatePointageDaySpinnerStateChanged(evt);
    }//GEN-LAST:event_jDatePointageYearSpinnerStateChanged

    private void jDatePointageMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatePointageMonthComboBoxActionPerformed
        // TODO add your handling code here:
        jDatePointageDaySpinnerStateChanged(null);
    }//GEN-LAST:event_jDatePointageMonthComboBoxActionPerformed

    private void jPointageYesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPointageYesRadioButtonActionPerformed
        // TODO add your handling code here:
        jPointageNoRadioButton.setSelected(!jPointageYesRadioButton.isSelected());
    }//GEN-LAST:event_jPointageYesRadioButtonActionPerformed

    private void jPointageNoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPointageNoRadioButtonActionPerformed
        // TODO add your handling code here:
        jPointageYesRadioButton.setSelected(!jPointageNoRadioButton.isSelected());
    }//GEN-LAST:event_jPointageNoRadioButtonActionPerformed

    private void jNumEmpComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNumEmpComboBoxActionPerformed
        // TODO add your handling code here:
        if (jEmployeeNameComboBox.getItemCount() == 0)
            return;
        
        jEmployeeNameComboBox.setSelectedIndex(jNumEmpComboBox.getSelectedIndex());
    }//GEN-LAST:event_jNumEmpComboBoxActionPerformed

    private void jEmployeeNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeNameComboBoxActionPerformed
        // TODO add your handling code here:
        if (jNumEmpComboBox.getItemCount() == 0)
            return;
        
        jNumEmpComboBox.setSelectedIndex(jEmployeeNameComboBox.getSelectedIndex());
    }//GEN-LAST:event_jEmployeeNameComboBoxActionPerformed

    private void jResetPointageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetPointageButtonActionPerformed
        // TODO add your handling code here:
        final PseudoDate now = PseudoDate.getCurrentDate();
        
        jDatePointageDaySpinner.setValue(now.getDay());
        jDatePointageMonthComboBox.setSelectedIndex(now.getMonth() - 1);
        jDatePointageYearSpinner.setValue(now.getYear());
        
        if (jNumEmpComboBox.getItemCount() > 0)
            jNumEmpComboBox.setSelectedIndex(0);
        
        jPointageNoRadioButton.setSelected(true);
        jPointageYesRadioButton.setSelected(false);
    }//GEN-LAST:event_jResetPointageButtonActionPerformed

    private void jConfirmPointageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfirmPointageButtonActionPerformed
        // TODO add your handling code here:
        if (!isFormLegit())
            return;
        
        final int datePointageDay = (int)jDatePointageDaySpinner.getValue();
        final int datePointageMonth = jDatePointageMonthComboBox.getSelectedIndex() + 1; // The first index (0) is January.
        final int datePointageYear = (int)jDatePointageYearSpinner.getValue();
        final String datePointage = Util.constructDateString(datePointageDay, datePointageMonth, datePointageYear);
        final String numEmp = jNumEmpComboBox.getSelectedItem().toString();
        final String pointage = jPointageYesRadioButton.isSelected() ? "Oui" : "Non";
        
        Pointage p = new Pointage();
        
        p.setDatePointage(datePointage);
        p.setNumEmp(numEmp);
        p.setPointage(pointage);
        
        if (m_IsEditMode)
            DBControl.deferPointageController().editEntry(new Object[] { p, m_PreviousPointage });
        else
            DBControl.deferPointageController().addEntry(p);
        
        DBControl.deferPointageController().reloadEntries(GPCPUi.getPointageTable());
        setVisible(false);
    }//GEN-LAST:event_jConfirmPointageButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PointageFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PointageFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PointageFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PointageFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PointageFormUi dialog = new PointageFormUi(new javax.swing.JFrame(), true);
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
    
    private boolean m_IsEditMode;
    private Pointage m_PreviousPointage = new Pointage();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jButtonWrapperPanel;
    private javax.swing.JButton jConfirmPointageButton;
    private javax.swing.JLabel jDatePointageDayLabel;
    private javax.swing.JSpinner jDatePointageDaySpinner;
    private javax.swing.JLabel jDatePointageLabel;
    private javax.swing.JComboBox<String> jDatePointageMonthComboBox;
    private javax.swing.JLabel jDatePointageMonthLabel;
    private javax.swing.JPanel jDatePointagePanel;
    private javax.swing.JPanel jDatePointageWrapperPanel;
    private javax.swing.JLabel jDatePointageYearLabel;
    private javax.swing.JSpinner jDatePointageYearSpinner;
    private javax.swing.JComboBox<String> jEmployeeNameComboBox;
    private javax.swing.JComboBox<String> jNumEmpComboBox;
    private javax.swing.JLabel jNumEmpLabel;
    private javax.swing.JPanel jNumEmpPanel;
    private javax.swing.JPanel jNumEmpWrapperPanel;
    private javax.swing.JLabel jPointageLabel;
    private javax.swing.JRadioButton jPointageNoRadioButton;
    private javax.swing.JPanel jPointagePanel;
    private javax.swing.JPanel jPointageWrapperPanel;
    private javax.swing.JRadioButton jPointageYesRadioButton;
    private javax.swing.JButton jResetPointageButton;
    // End of variables declaration//GEN-END:variables
}
