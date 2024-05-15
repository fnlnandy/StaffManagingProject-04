/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fnln.andy.gpcp.ui;

import fnln.andy.gpcp.DBControl;
import fnln.andy.gpcp.core.Employee;
import javax.swing.JOptionPane;

/**
 *
 * @author andy
 */
public class EmployeeFormUi extends javax.swing.JDialog {

    /**
     * Creates new form EmployeeFormUi
     */
    public EmployeeFormUi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jEmployeeIdLabel = new javax.swing.JLabel();
        jEmployeeIdSpinner = new javax.swing.JSpinner();
        jEmployeeNameLabel = new javax.swing.JLabel();
        jEmployeeNameTextField = new javax.swing.JTextField();
        jEmployeeFirstNameLabel = new javax.swing.JLabel();
        jEmployeeFirstNameTextField = new javax.swing.JTextField();
        jEmployeeJobLabel = new javax.swing.JLabel();
        jEmployeeJobTextField = new javax.swing.JTextField();
        jEmployeeSalaryLabel = new javax.swing.JLabel();
        jEmployeeSalarySpinner = new javax.swing.JSpinner();
        jEmployeeConfirmButton = new javax.swing.JButton();
        jEmployeeResetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulaire d'un employé");
        getContentPane().setLayout(new java.awt.GridLayout(0, 2, 0, 3));

        jEmployeeIdLabel.setText("Numéro de l'employé(e):");
        getContentPane().add(jEmployeeIdLabel);
        getContentPane().add(jEmployeeIdSpinner);

        jEmployeeNameLabel.setText("Nom de l'employé(e):");
        getContentPane().add(jEmployeeNameLabel);

        jEmployeeNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeNameTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(jEmployeeNameTextField);

        jEmployeeFirstNameLabel.setText("Prénom de l'employé(e):");
        getContentPane().add(jEmployeeFirstNameLabel);

        jEmployeeFirstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeFirstNameTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(jEmployeeFirstNameTextField);

        jEmployeeJobLabel.setText("Poste de l'employé(e):");
        getContentPane().add(jEmployeeJobLabel);
        getContentPane().add(jEmployeeJobTextField);

        jEmployeeSalaryLabel.setText("Salaire de l'employé(e):");
        getContentPane().add(jEmployeeSalaryLabel);
        getContentPane().add(jEmployeeSalarySpinner);

        jEmployeeConfirmButton.setText("Confirmer");
        jEmployeeConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeConfirmButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jEmployeeConfirmButton);

        jEmployeeResetButton.setText("Réinitialiser");
        jEmployeeResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeResetButtonActionPerformed(evt);
            }
        });
        getContentPane().add(jEmployeeResetButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jEmployeeNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEmployeeNameTextFieldActionPerformed

    private void jEmployeeFirstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeFirstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEmployeeFirstNameTextFieldActionPerformed

    private void jEmployeeResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeResetButtonActionPerformed
        // TODO add your handling code here:
        jEmployeeNameTextField.setText("");
        jEmployeeFirstNameTextField.setText("");
        jEmployeeJobTextField.setText("");
        jEmployeeSalarySpinner.setValue(0);
    }//GEN-LAST:event_jEmployeeResetButtonActionPerformed
    
    private void jEmployeeConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeConfirmButtonActionPerformed
        // TODO add your handling code here:
        if (!isFormLegit())
        {
            // TODO: add a popup menu
            System.out.println("The form isn't legit.");
            return;
        }
        
        Employee e = new Employee();
        
        e.setNumEmp(String.valueOf((int)jEmployeeIdSpinner.getValue()));
        e.setNom(jEmployeeNameTextField.getText());
        e.setPrenom(jEmployeeFirstNameTextField.getText());
        e.setPoste(jEmployeeJobTextField.getText());
        e.setSalaire((int)jEmployeeSalarySpinner.getValue());
        
        if (m_IsEditMode)
            DBControl.editEmployee(e, m_PreviousEmployeeId);
        else
            DBControl.addEmployee(e);
    }//GEN-LAST:event_jEmployeeConfirmButtonActionPerformed

    public void setIsEditMode(boolean isEditMode) { this.m_IsEditMode = isEditMode; }
    public void setPreviousEmployeeId(String prevEmployeeId) {
        this.m_PreviousEmployeeId = prevEmployeeId;
    }
    public void setEmployeeId(String employeeId) {
        jEmployeeIdSpinner.setValue(Integer.parseInt(employeeId));
    }
    public void setEmployeeName(String employeeName) { 
        jEmployeeNameTextField.setText(employeeName); 
    }
    public void setEmployeeFirstName(String employeeFirstName) {
        jEmployeeFirstNameTextField.setText(employeeFirstName);
    }
    public void setEmployeeJob(String employeeJob) {
        jEmployeeJobTextField.setText(employeeJob);
    }
    public void setEmployeeSalary(int employeeSalary) {
        jEmployeeSalarySpinner.setValue(employeeSalary);
    }
    private boolean isFormLegit() {
        final int minimalSalary = 1;
        String employeeName = jEmployeeNameTextField.getText();
        String employeeFirstName = jEmployeeFirstNameTextField.getText();
        String employeeJob = jEmployeeJobTextField.getText();
        int employeeSalary = (int) jEmployeeSalarySpinner.getValue();
        
        ErrorDialog err = new ErrorDialog((java.awt.Frame)getOwner(), true);
        
        if (employeeName.isEmpty())
        {
            err.setErrorMessage("Le nom de l'employé ne peut pas être vide.");
            err.showWithSwing();
            return false;
        }
        if (employeeFirstName.isEmpty())
        {
            err.setErrorMessage("Le prénom de l'employé ne peut pas être vide.");
            err.showWithSwing();
            return false;
        }
        if (employeeJob.isEmpty())
        {
            err.setErrorMessage("Le poste de l'employé ne peut pas être vide.");
            err.showWithSwing();
            return false;
        }
        if (employeeSalary < minimalSalary)
        {
            err.setErrorMessage("Le salaire ne doit pas être inférieur à "
                                + String.valueOf(minimalSalary) + ".");
            err.showWithSwing();
            return false;
        }
        
        return true;
    }
    
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
            java.util.logging.Logger.getLogger(EmployeeFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeFormUi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EmployeeFormUi dialog = new EmployeeFormUi(new javax.swing.JFrame(), true);
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
    private String m_PreviousEmployeeId;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jEmployeeConfirmButton;
    private javax.swing.JLabel jEmployeeFirstNameLabel;
    private javax.swing.JTextField jEmployeeFirstNameTextField;
    private javax.swing.JLabel jEmployeeIdLabel;
    private javax.swing.JSpinner jEmployeeIdSpinner;
    private javax.swing.JLabel jEmployeeJobLabel;
    private javax.swing.JTextField jEmployeeJobTextField;
    private javax.swing.JLabel jEmployeeNameLabel;
    private javax.swing.JTextField jEmployeeNameTextField;
    private javax.swing.JButton jEmployeeResetButton;
    private javax.swing.JLabel jEmployeeSalaryLabel;
    private javax.swing.JSpinner jEmployeeSalarySpinner;
    // End of variables declaration//GEN-END:variables
}
