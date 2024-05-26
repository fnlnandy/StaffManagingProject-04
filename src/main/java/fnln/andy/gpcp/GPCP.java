/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fnln.andy.gpcp;

import fnln.andy.gpcp.ui.GPCPUi;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.UIManager;

/**
 *
 * @author andy
 */
public class GPCP {
    private static GPCPUi m_MainAppUi;
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF."); 
        }
        
        DBControl.initDatabaseConnection();
        DBControl.initBaseTables();
        
        m_MainAppUi = new GPCPUi();
        
        SwingUtilities.invokeLater(() -> {
            m_MainAppUi.setVisible(true);
        });
    }
}
