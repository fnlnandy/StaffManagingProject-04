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
 * 
 * @brief The main class of this app.
 */
public class GPCP {
    /**
     * @brief This app's main Ui.
     */
    private static GPCPUi m_MainAppUi;
    
    /**
     * @param args
     * 
     * @brief The entry point.
     */
    public static void main(String[] args) {
        // Loading the FlatLaf look and feel, to make the app feel more 'comfy'.
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize LaF."); 
        }
        
        DBControl.initDatabaseConnection(); // Tries to connect to the PSQL database.
        DBControl.initBaseTables();         // Initializes the default tables, if they don't exist already.
        
        m_MainAppUi = new GPCPUi();         // Our GUI.
        
        SwingUtilities.invokeLater(() -> {  // Scheduling its display, this is almost instant though, I believe.
            m_MainAppUi.setVisible(true);
        });
    }
}
