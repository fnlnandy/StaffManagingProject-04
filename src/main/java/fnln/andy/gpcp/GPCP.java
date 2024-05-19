/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fnln.andy.gpcp;

import fnln.andy.gpcp.core.NumberToLetter;
import fnln.andy.gpcp.ui.GPCPUi;
import javax.swing.SwingUtilities;

/**
 *
 * @author andy
 */
public class GPCP {
    private static GPCPUi m_MainAppUi;
    
    public static void main(String[] args) {
        DBControl.initDatabaseConnection();
        DBControl.initBaseTables();
        
        m_MainAppUi = new GPCPUi();
        
        SwingUtilities.invokeLater(() -> {
            m_MainAppUi.setVisible(true);
        });
        
        System.out.println("999_999_999 = " + NumberToLetter.convertToLetter(999_999_999));
    }
}
