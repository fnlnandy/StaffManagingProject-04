/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fnln.andy.gpcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andy
 */
public class GPCP {
    public static void main(String[] args) {
        DBControl.initDatabaseConnection();
        DBControl.initBaseTables();
        
        GPCPUi.main(args);
    }
}
