/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Holiday;
import fnln.andy.gpcp.inputverifiers.DayInDateInputVerifier;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author andy
 */
public class HolidayFormUi extends javax.swing.JDialog {

    /**
     * Creates new form HolidayFormUi
     */
    public HolidayFormUi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void initEmployeeData(List<Employee> employees)
    {
        ComboBoxModel<String> numEmpModel = jNumEmpComboBox.getModel();
        ComboBoxModel<String> empNameModel = jEmployeeNameComboBox.getModel();
        
        if ((!(numEmpModel instanceof DefaultComboBoxModel)) || 
            (!(empNameModel instanceof  DefaultComboBoxModel)))
            return;
        
        DefaultComboBoxModel<String> numEmpDefaultModel = (DefaultComboBoxModel<String>)numEmpModel;
        DefaultComboBoxModel<String> empNameDefaultModel = (DefaultComboBoxModel<String>)empNameModel;
        
        for (Employee e : employees)
        {
            numEmpDefaultModel.addElement(e.getNumEmp());
            empNameDefaultModel.addElement(e.getNom() + " " + e.getPrenom());
        }
    }
    
    public boolean isFormLegit()
    {
        final int holidayId = (int)jHolidayNumberSpinner.getValue();
        final int numEmp = jNumEmpComboBox.getSelectedIndex();
        final String holidayReason = jReasonTextArea.getText();
        final int daysCount = (int)jDaysCountSpinner.getValue();
        final int demandYear = (int)jDemandDateYearSpinner.getValue();
        final int returnYear = (int)jReturnDateYearSpinner.getValue();
        
        if (holidayId <= 0)
        {
            return false;
        }
        if (numEmp == -1)
        {
            return false;
        }
        if (holidayReason.isEmpty())
        {
            return false;
        }
        if (daysCount <= 0 || daysCount >= 30)
        {
            return false;
        }
        if (demandYear < 2000)
        {
            return false;
        }
        if (returnYear < 2000)
        {
            return false;
        }
        
        return true;
    }
    
    public String constructDate(int day, int month, int year)
    {
        String retVal = "";
        
        retVal += String.valueOf(year) + "-";
        retVal += String.valueOf(month) + "-";
        retVal += String.valueOf(day);
        
        return retVal;
    }
    
    public void setIsEditMode(boolean isEditMode)
    {
        m_IsEditMode = true;
    }
    public void setPreviousHoliday(String numHoliday, String numEmp)
    {
        m_PreviousHoliday.setNumConge(numHoliday);
        m_PreviousHoliday.setNumEmp(numEmp);
    }
    public void setNumConge(String numConge)
    {
        final int numCongeAsInt = Integer.parseInt(numConge);
        
        jHolidayNumberSpinner.setValue(numCongeAsInt);
    }
    public void setNumEmp(String numEmp)
    {
        int index = 0;
        final int max = jNumEmpComboBox.getItemCount();
        
        if (max == 0)
            return;
        
       for(; index < max; index++)
       {
           String curr = jNumEmpComboBox.getItemAt(index);
           
           if (curr.equals(numEmp))
               break;
       }
       
       if (index == max)
           index = 0;
       
       jNumEmpComboBox.setSelectedIndex(index);
    }
    public void setMotif(String motif)
    {
        jReasonTextArea.setText(motif);
    }
    public void setNombreJours(int nombreJours)
    {
        jDaysCountSpinner.setValue(nombreJours);
    }
    public void setDateDemande(String dateDemande)
    {
        final String[] parts = dateDemande.split("-");
        
        if (parts.length < 3)
            return;
        
        final int year = Integer.parseInt(parts[0]);
        final int month = Integer.parseInt(parts[1]);
        final int day = Integer.parseInt(parts[2]);
        
        jDemandDateDaySpinner.setValue(day);
        jDemandDateMonthComboBox.setSelectedIndex(month - 1);
        jDemandDateYearSpinner.setValue(year);
    }
    public void setDateRetour(String dateRetour)
    {
        final String[] parts = dateRetour.split("-");
        
        if (parts.length < 3)
            return;
        
        
        final int year = Integer.parseInt(parts[0]);
        final int month = Integer.parseInt(parts[1]);
        final int day = Integer.parseInt(parts[2]);
        
        jReturnDateDaySpinner.setValue(day);
        jReturnDateMonthComboBox.setSelectedIndex(month - 1);
        jReturnDateYearSpinner.setValue(year);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHolidayNumberLabel = new javax.swing.JLabel();
        jHolidayNumberSpinner = new javax.swing.JSpinner();
        jNumEmpLabel = new javax.swing.JLabel();
        jNumEmpPanel = new javax.swing.JPanel();
        jNumEmpComboBox = new javax.swing.JComboBox<>();
        jEmployeeNameComboBox = new javax.swing.JComboBox<>();
        jReasonLabel = new javax.swing.JLabel();
        jReasonScrollPane = new javax.swing.JScrollPane();
        jReasonTextArea = new javax.swing.JTextArea();
        jDaysCountLabel = new javax.swing.JLabel();
        jDaysCountSpinner = new javax.swing.JSpinner();
        jDemandDateLabel = new javax.swing.JLabel();
        jDemandDatePanel = new javax.swing.JPanel();
        jDemandDateDaySpinner = new javax.swing.JSpinner();
        jDemandDateMonthComboBox = new javax.swing.JComboBox<>();
        jDemandDateYearSpinner = new javax.swing.JSpinner();
        jReturnDateLabel = new javax.swing.JLabel();
        jReturnDatePanel = new javax.swing.JPanel();
        jReturnDateDaySpinner = new javax.swing.JSpinner();
        jReturnDateMonthComboBox = new javax.swing.JComboBox<>();
        jReturnDateYearSpinner = new javax.swing.JSpinner();
        jConfirmButton = new javax.swing.JButton();
        jResetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        jHolidayNumberLabel.setText("Numéro de congé:");
        getContentPane().add(jHolidayNumberLabel);
        getContentPane().add(jHolidayNumberSpinner);

        jNumEmpLabel.setText("Numéro d'employé:");
        getContentPane().add(jNumEmpLabel);

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

        getContentPane().add(jNumEmpPanel);

        jReasonLabel.setText("Motif du congé:");
        getContentPane().add(jReasonLabel);

        jReasonTextArea.setColumns(20);
        jReasonTextArea.setRows(5);
        jReasonScrollPane.setViewportView(jReasonTextArea);

        getContentPane().add(jReasonScrollPane);

        jDaysCountLabel.setText("Nombre de jours:");
        getContentPane().add(jDaysCountLabel);
        getContentPane().add(jDaysCountSpinner);

        jDemandDateLabel.setText("Date de demande:");
        getContentPane().add(jDemandDateLabel);

        jDemandDateDaySpinner.setValue(1);
        jDemandDateDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDemandDateDaySpinnerStateChanged(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateDaySpinner);

        jDemandDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));
        jDemandDateMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDemandDateMonthComboBoxActionPerformed(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateMonthComboBox);

        jDemandDateYearSpinner.setValue(2024);
        jDemandDateYearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDemandDateYearSpinnerStateChanged(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateYearSpinner);

        getContentPane().add(jDemandDatePanel);

        jReturnDateLabel.setText("Date de retour:");
        getContentPane().add(jReturnDateLabel);

        jReturnDateDaySpinner.setValue(1);
        jReturnDateDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReturnDateDaySpinnerStateChanged(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateDaySpinner);

        jReturnDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));
        jReturnDateMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReturnDateMonthComboBoxActionPerformed(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateMonthComboBox);

        jReturnDateYearSpinner.setValue(2024);
        jReturnDateYearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReturnDateYearSpinnerStateChanged(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateYearSpinner);

        getContentPane().add(jReturnDatePanel);

        jConfirmButton.setText("Confirmer");
        jConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jConfirmButton);

        jResetButton.setText("Réinitialiser");
        jResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jResetButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jNumEmpComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNumEmpComboBoxActionPerformed
        // TODO add your handling code here:
        if (jEmployeeNameComboBox.getItemCount() > 0)
            jEmployeeNameComboBox.setSelectedIndex(jNumEmpComboBox.getSelectedIndex());
    }//GEN-LAST:event_jNumEmpComboBoxActionPerformed

    private void jEmployeeNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeNameComboBoxActionPerformed
        // TODO add your handling code here:
        if (jNumEmpComboBox.getItemCount() > 0)
            jNumEmpComboBox.setSelectedIndex(jEmployeeNameComboBox.getSelectedIndex());
    }//GEN-LAST:event_jEmployeeNameComboBoxActionPerformed

    private void jDemandDateDaySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDemandDateDaySpinnerStateChanged
        // TODO add your handling code here:
        final int year = (int)jDemandDateYearSpinner.getValue();
        final int month = jDemandDateMonthComboBox.getSelectedIndex();
        
        DayInDateInputVerifier.validateSpinnerValue(month, year, jDemandDateDaySpinner);
    }//GEN-LAST:event_jDemandDateDaySpinnerStateChanged

    private void jDemandDateMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDemandDateMonthComboBoxActionPerformed
        // TODO add your handling code here:
        jDemandDateDaySpinnerStateChanged(null);
    }//GEN-LAST:event_jDemandDateMonthComboBoxActionPerformed

    private void jDemandDateYearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jDemandDateYearSpinnerStateChanged
        // TODO add your handling code here:
        jDemandDateDaySpinnerStateChanged(null);
    }//GEN-LAST:event_jDemandDateYearSpinnerStateChanged

    private void jReturnDateDaySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jReturnDateDaySpinnerStateChanged
        // TODO add your handling code here:
        final int year = (int)jReturnDateYearSpinner.getValue();
        final int month = jReturnDateMonthComboBox.getSelectedIndex();
        
        DayInDateInputVerifier.validateSpinnerValue(month, year, jReturnDateDaySpinner);
    }//GEN-LAST:event_jReturnDateDaySpinnerStateChanged

    private void jReturnDateMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReturnDateMonthComboBoxActionPerformed
        // TODO add your handling code here:
        jReturnDateDaySpinnerStateChanged(null);
    }//GEN-LAST:event_jReturnDateMonthComboBoxActionPerformed

    private void jReturnDateYearSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jReturnDateYearSpinnerStateChanged
        // TODO add your handling code here:
        jReturnDateDaySpinnerStateChanged(null);
    }//GEN-LAST:event_jReturnDateYearSpinnerStateChanged

    private void jResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetButtonActionPerformed
        // TODO add your handling code here:
        jHolidayNumberSpinner.setValue(0);
        jReasonTextArea.setText("");
        jDaysCountSpinner.setValue(0);
        jDemandDateDaySpinner.setValue(1);
        jDemandDateMonthComboBox.setSelectedIndex(0);
        jDemandDateYearSpinner.setValue(2024);
        jReturnDateDaySpinner.setValue(1);
        jReturnDateMonthComboBox.setSelectedIndex(0);
        jReturnDateYearSpinner.setValue(2024);
        
        if (jNumEmpComboBox.getItemCount() > 0)
            jNumEmpComboBox.setSelectedIndex(0);
    }//GEN-LAST:event_jResetButtonActionPerformed

    private void jConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConfirmButtonActionPerformed
        // TODO add your handling code here:
        if (!isFormLegit())
            return;
        
        final int demandDay = (int)jDemandDateDaySpinner.getValue();
        final int demandMonth = jDemandDateMonthComboBox.getSelectedIndex() + 1;
        final int demandYear = (int)jDemandDateYearSpinner.getValue();
        
        final int returnDay = (int)jReturnDateDaySpinner.getValue();
        final int returnMonth = jReturnDateMonthComboBox.getSelectedIndex() + 1;
        final int returnYear = (int)jReturnDateYearSpinner.getValue();
        
        Holiday h = new Holiday();
        
        h.setNumConge(jHolidayNumberSpinner.getValue().toString());
        h.setNumEmp(jNumEmpComboBox.getSelectedItem().toString());
        h.setMotif(jReasonTextArea.getText());
        h.setNombreJours((int)jDaysCountSpinner.getValue());
        h.setDateDemande(constructDate(demandDay, demandMonth, demandYear));
        h.setDateRetour(constructDate(returnDay, returnMonth, returnYear));
        
        if (m_IsEditMode)
            DBControl.deferHolidayController().editEntry(new Object[] { h, m_PreviousHoliday });
        else
            DBControl.deferHolidayController().addEntry(h);
        
        DBControl.deferHolidayController().reloadEntries(GPCPUi.getHolidayTable());
        setVisible(false);
    }//GEN-LAST:event_jConfirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(HolidayFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HolidayFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HolidayFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HolidayFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HolidayFormUi dialog = new HolidayFormUi(new javax.swing.JFrame(), true);
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
    private Holiday m_PreviousHoliday = new Holiday();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jConfirmButton;
    private javax.swing.JLabel jDaysCountLabel;
    private javax.swing.JSpinner jDaysCountSpinner;
    private javax.swing.JSpinner jDemandDateDaySpinner;
    private javax.swing.JLabel jDemandDateLabel;
    private javax.swing.JComboBox<String> jDemandDateMonthComboBox;
    private javax.swing.JPanel jDemandDatePanel;
    private javax.swing.JSpinner jDemandDateYearSpinner;
    private javax.swing.JComboBox<String> jEmployeeNameComboBox;
    private javax.swing.JLabel jHolidayNumberLabel;
    private javax.swing.JSpinner jHolidayNumberSpinner;
    private javax.swing.JComboBox<String> jNumEmpComboBox;
    private javax.swing.JLabel jNumEmpLabel;
    private javax.swing.JPanel jNumEmpPanel;
    private javax.swing.JLabel jReasonLabel;
    private javax.swing.JScrollPane jReasonScrollPane;
    private javax.swing.JTextArea jReasonTextArea;
    private javax.swing.JButton jResetButton;
    private javax.swing.JSpinner jReturnDateDaySpinner;
    private javax.swing.JLabel jReturnDateLabel;
    private javax.swing.JComboBox<String> jReturnDateMonthComboBox;
    private javax.swing.JPanel jReturnDatePanel;
    private javax.swing.JSpinner jReturnDateYearSpinner;
    // End of variables declaration//GEN-END:variables
}