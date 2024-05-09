/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp;

import java.awt.Component;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author andy
 */
public class DBControl {
    private static Connection m_DatabaseConnection;
    private static Statement m_DatabaseStatement;
    
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
    
    public static void pushEmployee(JTable employeeTable, Employee toAdd) {
        SwingUtilities.invokeLater(() -> {
            if (employeeTable == null) {
                System.out.println("\"employeeTable\" is null.");
                return;
            }
            
            TableModel tableModel = employeeTable.getModel();
        
            if (!(tableModel instanceof DefaultTableModel)) {
                System.out.println("\"tableModel\" is not a DefaultTableModel.");
                return;
            }
        
            DefaultTableModel defaultModel = (DefaultTableModel)(tableModel);
        
            defaultModel.addRow(new Object[]{
                                toAdd.getNumEmp(),
                                toAdd.getNom(),
                                toAdd.getPrenom(),
                                toAdd.getPoste(),
                                toAdd.getSalaire()});
            
        });
    }
    
    public static List<Employee> getEmployees() {
        List<Employee> retVal = new ArrayList<>();
        String selectEmps = "SELECT * FROM Employe;";
        ResultSet res = null;
        
        try {
            Employee toPush = new Employee();
            
            res = m_DatabaseStatement.executeQuery(selectEmps);
            
            if (res == null)
                return retVal;
            
            while (res.next()) {
                toPush.setNumEmp(res.getString("NumEmp"));
                toPush.setNom(res.getString("Nom"));
                toPush.setPrenom(res.getString("Prenom"));
                toPush.setPoste(res.getString("Poste"));
                toPush.setSalaire(res.getInt("Salaire"));
                
                retVal.add(toPush);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return retVal;
    }
    
    public static void loadEmployees(JTable dest) {
        List<Employee> toPush = getEmployees();
        
        for (Employee emp : toPush) {
            pushEmployee(dest, emp);
            System.out.println(emp.getNom());
        }
    }
}
