/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp;

import fnln.andy.gpcp.db.EmployeeTableController;
import fnln.andy.gpcp.db.HolidayTableController;
import fnln.andy.gpcp.db.PointageTableController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author andy
 * 
 * @brief The main class behind every operation
 * done on the database.
 */
public class DBControl {
    /**
     * @brief The database connection handler,
     * it's passed around within the Controllers.
     */
    private static Connection m_DatabaseConnection;
    /**
     * @brief The database statement 'handler',
     * it's also passsed around within the Controllers.
     */
    private static Statement m_DatabaseStatement;
    
    /**
     * @brief Main Employee Controller, which basically controls
     * everything that is related to Employees, CRUD, researches,
     * etc...
     */
    private static EmployeeTableController m_EmployeeController;
    /**
     * @brief Main Pointage Controller, it controls everything
     * that is related to Pointages.
     */
    private static PointageTableController m_PointageTableController;
    /**
     * @brief Main Holiday Controller, it controls everything
     * that is related to Holidays.
     */
    private static HolidayTableController m_HolidayTableController;
    
    /**
     * @brief Initializes the database connection to the
     * PSQL 'server'.
     */
    public static void initDatabaseConnection() {
        String databaseUrl = "jdbc:postgresql://localhost:5432/projjava";
        String userName = "dummy";
        String passWord = "1234";
        
        try {
           m_DatabaseConnection =  DriverManager.getConnection(databaseUrl, 
                                                               userName, 
                                                               passWord);
           m_DatabaseStatement = m_DatabaseConnection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        // Also initializes the controllers.
        m_EmployeeController = new EmployeeTableController(m_DatabaseStatement, m_DatabaseConnection);
        m_PointageTableController = new PointageTableController(m_DatabaseStatement, m_DatabaseConnection);
        m_HolidayTableController = new HolidayTableController(m_DatabaseStatement, m_DatabaseConnection);
    }
    
    /**
     * @brief Creates the base tables that
     * this app will use, if they don't exist
     * already.
     */
    public static void initBaseTables() {
        String[] createTables = { 
            "CREATE TABLE IF NOT EXISTS Employe ("
                + "NumEmp VARCHAR(10) PRIMARY KEY,"
                + "Nom VARCHAR(30),"
                + "Prenom VARCHAR(50),"
                + "Poste VARCHAR(100),"
                + "Salaire INT"
                + ");",
            
            "CREATE TABLE IF NOT EXISTS Pointage ("
                + "DatePointage DATE,"
                + "NumEmp VARCHAR(10),"
                + "Pointage VARCHAR(3),"
                + "PRIMARY KEY(DatePointage, NumEmp, Pointage)"
                + ");",
            
            "CREATE TABLE IF NOT EXISTS Conge ("
                + "NumConge VARCHAR(10),"
                + "NumEmp VARCHAR(10),"
                + "Motif TEXT,"
                + "NombreJours INT,"
                + "DateDemande DATE,"
                + "DateRetour DATE,"
                + "PRIMARY KEY(NumConge, NumEmp)"
                + ");"
        };
        
        // We're executing the queries sequentially.
        for (String createTable : createTables) {
            try {
                m_DatabaseStatement.execute(createTable);
            }catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    
    /**
     * @brief 'Defers' the Employee Controller, so
     * that it's exposed to whatever function wants
     * to use it.
     * 
     * @todo Find a better name for it, instead of 'defer' ?
     * 
     * @return
     */
    public static EmployeeTableController deferEmployeeController() { return m_EmployeeController;      }
    /**
     * @brief Exposes the Pointage Controller.
     * 
     * @return 
     */
    public static PointageTableController deferPointageController() { return m_PointageTableController; }
    /**
     * @brief Exposes the Holiday Controller.
     * 
     * @return 
     */
    public static HolidayTableController deferHolidayController()   { return m_HolidayTableController;  }
    
}
