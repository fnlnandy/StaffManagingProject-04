/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Holiday;
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
    
    private boolean isNonRedundantEntry(int newNumConge)
    {
        int prevNumConge = (m_IsEditMode ? Integer.parseInt(m_PreviousHoliday.getNumConge()) : newNumConge);
        
        if (m_IsEditMode && prevNumConge == newNumConge)
            return true;
        
        return !DBControl.deferHolidayController().entryExists(DataArg.makeDataArg(newNumConge));
    }
    
    public boolean isFormLegit()
    {
        final java.awt.Frame parent = (java.awt.Frame)getOwner();
        
        if (jNumEmpComboBox.getItemCount() == 0)
        {
            Util.invokeErrorMessage(parent, "Aucun employé dans cette base de données.");
            return false;
        }
        
        final int holidayId = (int)jHolidayNumberSpinner.getValue();
        final int numEmp = Integer.parseInt(jNumEmpComboBox.getSelectedItem().toString());
        final String holidayReason = jReasonTextArea.getText();
        final int daysCount = (int)jDaysCountSpinner.getValue();
        
        final int employeeHolidaysSum = DBControl.deferEmployeeController().getEmployeeDaysSum(String.valueOf(numEmp));
        
        final int demandDay = (int)jDemandDateDaySpinner.getValue();
        final int demandMonth = jDemandDateMonthComboBox.getSelectedIndex() + 1;
        final int demandYear = (int)jDemandDateYearSpinner.getValue();
        PseudoDate demandDate = new PseudoDate(demandDay, demandMonth, demandYear);
        
        final int returnDay = (int)jReturnDateDaySpinner.getValue();
        final int returnMonth = jReturnDateMonthComboBox.getSelectedIndex() + 1;
        final int returnYear = (int)jReturnDateYearSpinner.getValue();
        PseudoDate returnDate = new PseudoDate(returnDay, returnMonth, returnYear);
        PseudoDate minimalReturnDate = new PseudoDate(demandDay, demandMonth, demandYear);
        
        minimalReturnDate.advance(daysCount);
        
        if (holidayId <= 0)
        {
            Util.invokeErrorMessage(parent, "Le numéro de congé doit être >= 1.");
            return false;
        }
        if (!isNonRedundantEntry(holidayId))
        {
            Util.invokeErrorMessage(parent, "Un congé avec ce numéro de congé existe déjà.");
            return false;
        }
        if (numEmp == -1)
        {
            Util.invokeErrorMessage(parent, "L'employé séléctionné doit être valide.");
            return false;
        }
        if (holidayReason.isEmpty())
        {
            Util.invokeErrorMessage(parent, "Le motif de congé ne peut pas être vide.");
            return false;
        }
        if (daysCount < 1|| daysCount > 30)
        {
            Util.invokeErrorMessage(parent, "Le nombre de jours doit appartenir à l'intervalle [1, 30].");
            return false;
        }
        if (demandYear == PseudoDate.getCurrentDate().getYear() && (m_IsEditMode ? daysCount- m_PreviousHoliday.getNombreJours() + employeeHolidaysSum > 30 :
                daysCount + employeeHolidaysSum > 30))
        {
            Util.invokeErrorMessage(parent, "Le nombre de jours excède la limite autorisée de 30 jours pour un an.");
            return false;
        }
        if (demandYear < 2000)
        {
            Util.invokeErrorMessage(parent, "L'année de demande doit être >= 2000.");
            return false;
        }
        if (returnYear < 2000)
        {
            Util.invokeErrorMessage(parent, "L'année de retour doit être >= 2000.");
            return false;
        }
        if (returnDate.isBefore(demandDate))
        {
            Util.invokeErrorMessage(parent, "La date de retour ne peut pas être inférieure à la date de demande.");
            return false;
        }
        if (minimalReturnDate.isAfter(returnDate)) {
            Util.invokeErrorMessage(parent, "La date de retour ne correspond pas au nombre de jours restants.");
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
    public void setPreviousDaysCount(int daysCount)
    {
        m_PreviousHoliday.setNombreJours(daysCount);
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

        jHolidayNumberWrapperPanel = new javax.swing.JPanel();
        jHolidayNumberLabel = new javax.swing.JLabel();
        jHolidayNumberSpinner = new javax.swing.JSpinner();
        jNumEmpWrapperPanel = new javax.swing.JPanel();
        jNumEmpLabel = new javax.swing.JLabel();
        jNumEmpPanel = new javax.swing.JPanel();
        jNumEmpComboBox = new javax.swing.JComboBox<>();
        jEmployeeNameComboBox = new javax.swing.JComboBox<>();
        jReasonWrapperPanel = new javax.swing.JPanel();
        jReasonLabel = new javax.swing.JLabel();
        jReasonScrollPane = new javax.swing.JScrollPane();
        jReasonTextArea = new javax.swing.JTextArea();
        jDaysCountWrapperPanel = new javax.swing.JPanel();
        jDaysCountLabel = new javax.swing.JLabel();
        jDaysCountSpinner = new javax.swing.JSpinner();
        jDemandDateWrapperPanel = new javax.swing.JPanel();
        jDemandDateLabel = new javax.swing.JLabel();
        jDemandDatePanel = new javax.swing.JPanel();
        jDemandDateDaySpinner = new javax.swing.JSpinner();
        jDemandDateMonthComboBox = new javax.swing.JComboBox<>();
        jDemandDateYearSpinner = new javax.swing.JSpinner();
        jReturnDateWrapperPanel = new javax.swing.JPanel();
        jReturnDateLabel = new javax.swing.JLabel();
        jReturnDatePanel = new javax.swing.JPanel();
        jReturnDateDaySpinner = new javax.swing.JSpinner();
        jReturnDateMonthComboBox = new javax.swing.JComboBox<>();
        jReturnDateYearSpinner = new javax.swing.JSpinner();
        jButtonsPane = new javax.swing.JPanel();
        jConfirmButton = new javax.swing.JButton();
        jResetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jHolidayNumberWrapperPanel.setLayout(new javax.swing.BoxLayout(jHolidayNumberWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jHolidayNumberLabel.setText("Numéro de congé:");
        jHolidayNumberWrapperPanel.add(jHolidayNumberLabel);
        jHolidayNumberWrapperPanel.add(jHolidayNumberSpinner);

        getContentPane().add(jHolidayNumberWrapperPanel);

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

        jReasonWrapperPanel.setMinimumSize(new java.awt.Dimension(600, 500));
        jReasonWrapperPanel.setLayout(new javax.swing.BoxLayout(jReasonWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jReasonLabel.setText("Motif du congé:");
        jReasonWrapperPanel.add(jReasonLabel);

        jReasonTextArea.setColumns(20);
        jReasonTextArea.setRows(5);
        jReasonScrollPane.setViewportView(jReasonTextArea);

        jReasonWrapperPanel.add(jReasonScrollPane);

        getContentPane().add(jReasonWrapperPanel);

        jDaysCountWrapperPanel.setLayout(new javax.swing.BoxLayout(jDaysCountWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jDaysCountLabel.setText("Nombre de jours:");
        jDaysCountWrapperPanel.add(jDaysCountLabel);

        jDaysCountSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 30, 1));
        jDaysCountWrapperPanel.add(jDaysCountSpinner);

        getContentPane().add(jDaysCountWrapperPanel);

        jDemandDateWrapperPanel.setLayout(new javax.swing.BoxLayout(jDemandDateWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jDemandDateLabel.setText("Date de demande:");
        jDemandDateWrapperPanel.add(jDemandDateLabel);

        jDemandDateDaySpinner.setValue(PseudoDate.getCurrentDate().getDay());
        jDemandDateDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDemandDateDaySpinnerStateChanged(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateDaySpinner);

        jDemandDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));
        jDemandDateMonthComboBox.setSelectedIndex(PseudoDate.getCurrentDate().getMonth() - 1);
        jDemandDateMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDemandDateMonthComboBoxActionPerformed(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateMonthComboBox);

        jDemandDateYearSpinner.setValue(PseudoDate.getCurrentDate().getYear());
        jDemandDateYearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jDemandDateYearSpinnerStateChanged(evt);
            }
        });
        jDemandDatePanel.add(jDemandDateYearSpinner);

        jDemandDateWrapperPanel.add(jDemandDatePanel);

        getContentPane().add(jDemandDateWrapperPanel);

        jReturnDateWrapperPanel.setLayout(new javax.swing.BoxLayout(jReturnDateWrapperPanel, javax.swing.BoxLayout.LINE_AXIS));

        jReturnDateLabel.setText("Date de retour:");
        jReturnDateWrapperPanel.add(jReturnDateLabel);

        jReturnDateDaySpinner.setValue(PseudoDate.getCurrentDate().getDay());
        jReturnDateDaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReturnDateDaySpinnerStateChanged(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateDaySpinner);

        jReturnDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));
        jReturnDateMonthComboBox.setSelectedIndex(PseudoDate.getCurrentDate().getMonth() - 1);
        jReturnDateMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReturnDateMonthComboBoxActionPerformed(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateMonthComboBox);

        jReturnDateYearSpinner.setValue(PseudoDate.getCurrentDate().getYear());
        jReturnDateYearSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReturnDateYearSpinnerStateChanged(evt);
            }
        });
        jReturnDatePanel.add(jReturnDateYearSpinner);

        jReturnDateWrapperPanel.add(jReturnDatePanel);

        getContentPane().add(jReturnDateWrapperPanel);

        jButtonsPane.setLayout(new javax.swing.BoxLayout(jButtonsPane, javax.swing.BoxLayout.LINE_AXIS));

        jConfirmButton.setText("Confirmer");
        jConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConfirmButtonActionPerformed(evt);
            }
        });
        jButtonsPane.add(jConfirmButton);

        jResetButton.setText("Réinitialiser");
        jResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetButtonActionPerformed(evt);
            }
        });
        jButtonsPane.add(jResetButton);

        getContentPane().add(jButtonsPane);

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
        final PseudoDate now = PseudoDate.getCurrentDate();
        
        jHolidayNumberSpinner.setValue(0);
        jReasonTextArea.setText("");
        jDaysCountSpinner.setValue(0);
        
        jDemandDateDaySpinner.setValue(now.getDay());
        jDemandDateMonthComboBox.setSelectedIndex(now.getMonth() - 1);
        jDemandDateYearSpinner.setValue(now.getYear());
        
        jReturnDateDaySpinner.setValue(now.getDay());
        jReturnDateMonthComboBox.setSelectedIndex(now.getMonth() - 1);
        jReturnDateYearSpinner.setValue(now.getYear());
        
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
    private javax.swing.JPanel jButtonsPane;
    private javax.swing.JButton jConfirmButton;
    private javax.swing.JLabel jDaysCountLabel;
    private javax.swing.JSpinner jDaysCountSpinner;
    private javax.swing.JPanel jDaysCountWrapperPanel;
    private javax.swing.JSpinner jDemandDateDaySpinner;
    private javax.swing.JLabel jDemandDateLabel;
    private javax.swing.JComboBox<String> jDemandDateMonthComboBox;
    private javax.swing.JPanel jDemandDatePanel;
    private javax.swing.JPanel jDemandDateWrapperPanel;
    private javax.swing.JSpinner jDemandDateYearSpinner;
    private javax.swing.JComboBox<String> jEmployeeNameComboBox;
    private javax.swing.JLabel jHolidayNumberLabel;
    private javax.swing.JSpinner jHolidayNumberSpinner;
    private javax.swing.JPanel jHolidayNumberWrapperPanel;
    private javax.swing.JComboBox<String> jNumEmpComboBox;
    private javax.swing.JLabel jNumEmpLabel;
    private javax.swing.JPanel jNumEmpPanel;
    private javax.swing.JPanel jNumEmpWrapperPanel;
    private javax.swing.JLabel jReasonLabel;
    private javax.swing.JScrollPane jReasonScrollPane;
    private javax.swing.JTextArea jReasonTextArea;
    private javax.swing.JPanel jReasonWrapperPanel;
    private javax.swing.JButton jResetButton;
    private javax.swing.JSpinner jReturnDateDaySpinner;
    private javax.swing.JLabel jReturnDateLabel;
    private javax.swing.JComboBox<String> jReturnDateMonthComboBox;
    private javax.swing.JPanel jReturnDatePanel;
    private javax.swing.JPanel jReturnDateWrapperPanel;
    private javax.swing.JSpinner jReturnDateYearSpinner;
    // End of variables declaration//GEN-END:variables
}
