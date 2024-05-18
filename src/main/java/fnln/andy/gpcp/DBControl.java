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
import javax.swing.JTable;

/**
 *
 * @author andy
 */
public class DBControl {
    private static Connection m_DatabaseConnection;
    private static Statement m_DatabaseStatement;
    
    private static EmployeeTableController m_EmployeeController;
    private static PointageTableController m_PointageTableController;
    private static HolidayTableController m_HolidayTableController;
    
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
        
        m_EmployeeController = new EmployeeTableController(m_DatabaseStatement, m_DatabaseConnection);
        m_PointageTableController = new PointageTableController(m_DatabaseStatement, m_DatabaseConnection);
        m_HolidayTableController = new HolidayTableController(m_DatabaseStatement, m_DatabaseConnection);
    }
    
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
        
        for (String createTable : createTables) {
            try {
                m_DatabaseStatement.execute(createTable);
            }catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
    
    public static EmployeeTableController deferEmployeeController() { return m_EmployeeController; }
    public static PointageTableController deferPointageController() { return m_PointageTableController; }
    public static HolidayTableController deferHolidayController() { return m_HolidayTableController; }
    
}
