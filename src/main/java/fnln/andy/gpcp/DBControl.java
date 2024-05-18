/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp;

import fnln.andy.gpcp.core.Holiday;
import fnln.andy.gpcp.core.Pointage;
import fnln.andy.gpcp.core.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                                toAdd.getSalaire()
                                });
            
        });
    }   
    public static List<Employee> getEmployees() {
        List<Employee> retVal = new ArrayList<>();
        String selectEmps = "SELECT * FROM Employe;";
        ResultSet res = null;
        
        try {
            res = m_DatabaseStatement.executeQuery(selectEmps);
            
            if (res == null)
                return retVal;
            
            while (res.next()) {
                Employee toPush = new Employee();
                
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
    public static void reloadEmployees(JTable dest) {
        TableModel tm = dest.getModel();
        
        if (!(tm instanceof DefaultTableModel))
            return;
        
        DefaultTableModel defaultTM = (DefaultTableModel)(tm);
        
        for (int i = defaultTM.getRowCount() - 1; i >= 0; i--)
            defaultTM.removeRow(i);
        
        loadEmployees(dest);
    }
    public static boolean addEmployee(Employee e)
    {
        String addEmp = "INSERT INTO Employe VALUES("
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?"
                + ");";
        PreparedStatement req = null;
        boolean retVal = false;
        
        try {
            req = m_DatabaseConnection.prepareStatement(addEmp);
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        try {
            req.setString(1, e.getNumEmp());
            req.setString(2, e.getNom());
            req.setString(3, e.getPrenom());
            req.setString(4, e.getPoste());
            req.setInt(5, e.getSalaire());
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
  
        System.out.println(req.toString());
        try {
            retVal = req.executeUpdate() >= 0;
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    public static boolean editEmployee(Employee e, String prevEmpId)
    {
        String addEmp = "UPDATE Employe SET "
                + "NumEmp = ?, "
                + "Nom = ?, "
                + "Prenom = ?, "
                + "Poste = ?, "
                + "Salaire = ? "
                + "WHERE NumEmp = ? "
                + ";";
        PreparedStatement req = null;
        boolean retVal = false;
        
        try {
            req = m_DatabaseConnection.prepareStatement(addEmp);
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        try {
            req.setString(1, e.getNumEmp());
            req.setString(2, e.getNom());
            req.setString(3, e.getPrenom());
            req.setString(4, e.getPoste());
            req.setInt(5, e.getSalaire());
            req.setString(6, prevEmpId);
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        System.out.println(req.toString());
        try {
            retVal = req.executeUpdate() >= 0;
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    public static boolean deleteEmployee(String employeeId)
    {
        boolean retVal = false;
        PreparedStatement req = null;
        String delEmp = "DELETE FROM Employe WHERE NumEmp = ?;";
        
        try {
            req = m_DatabaseConnection.prepareStatement(delEmp);
            
            req.setString(1, employeeId);
            
            retVal = req.executeUpdate() >= 0;
        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        
        return retVal;
    }
    
    public static void pushPointage(JTable pointageTable, Pointage toAdd) {
        SwingUtilities.invokeLater(() -> {
            if (pointageTable == null) {
                System.out.println("\"pointageTable\" is null.");
                return;
            }
            
            TableModel tableModel = pointageTable.getModel();
            
            if (!(tableModel instanceof DefaultTableModel)) {
                System.out.println("\"tableModel\" is not a DefaultTableModel.");
                return;
            }
            
            DefaultTableModel defaultModel = (DefaultTableModel)(tableModel);
            
            defaultModel.addRow(new Object[] {
                                toAdd.getDatePointage(),
                                toAdd.getNumEmp(),
                                toAdd.getPointage()
                                });
        });
    }
    public static List<Pointage> getPointages() {
        List<Pointage> retVal = new ArrayList<>();
        String selectPointage = "SELECT * FROM Pointage;";
        ResultSet res = null;
        
        try { 
            res = m_DatabaseStatement.executeQuery(selectPointage);
            
            if (res == null)
                return retVal;
            
            while (res.next()) {
                Pointage toPush = new Pointage();
                
                toPush.setDatePointage(res.getString("DatePointage"));
                toPush.setNumEmp(res.getString("NumEmp"));
                toPush.setPointage(res.getString("Pointage"));
                
                retVal.add(toPush);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return retVal;
    }    
    public static void loadPointages(JTable dest) {
        List<Pointage> toPush = getPointages();
        
        for (Pointage p : toPush) {
            pushPointage(dest, p);
            System.out.println(p.getDatePointage() + " " + 
                               p.getNumEmp() + " " + 
                               p.getPointage());
        }
    }
    public static void reloadPointages(JTable dest) {
        TableModel tm = dest.getModel();
        
        if (!(tm instanceof DefaultTableModel))
            return;
        
        DefaultTableModel defaultTM = (DefaultTableModel)(tm);
        
        for (int i = defaultTM.getRowCount() - 1; i >= 0; i--)
            defaultTM.removeRow(i);
        
        loadPointages(dest);
    }
    public static boolean addPointage(Pointage p)
    {
        String addPointage = "INSERT INTO Pointage VALUES("
                + "?,"
                + "?,"
                + "?"
                + ");";
        PreparedStatement req = null;
        boolean retVal = false;
        
        try {
            req = m_DatabaseConnection.prepareStatement(addPointage);
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        try {
            req.setDate(1, Date.valueOf(p.getDatePointage()));
            req.setString(2, p.getNumEmp());
            req.setString(3, p.getPointage());
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
  
        System.out.println(req.toString());
        try {
            retVal = req.executeUpdate() >= 0;
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    public static boolean editPointage(Pointage p, Pointage prevPointage)
    {
        String addEmp = "UPDATE Pointage SET "
                + "DatePointage = ?, "
                + "NumEmp = ?, "
                + "Pointage = ? "
                + "WHERE "
                + "DatePointage = ? "
                + "AND NumEmp = ? "
                + "AND Pointage = ?"
                + ";";
        PreparedStatement req = null;
        boolean retVal = false;
        
        try {
            req = m_DatabaseConnection.prepareStatement(addEmp);
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        try {
            req.setDate(1, Date.valueOf(p.getDatePointage()));
            req.setString(2, p.getNumEmp());
            req.setString(3, p.getPointage());
            
            req.setDate(4, Date.valueOf(prevPointage.getDatePointage()));
            req.setString(5, prevPointage.getNumEmp());
            req.setString(6, prevPointage.getPointage());
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        System.out.println(req.toString());
        try {
            retVal = req.executeUpdate() >= 0;
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    public static boolean deletePointage(String datePointage, String numEmp, String pointage)
    {
        boolean retVal = false;
        PreparedStatement req = null;
        String delPointage = "DELETE FROM Pointage WHERE DatePointage = ? "
                + "AND NumEmp = ? "
                + "AND Pointage = ? "
                + ";";
        
        try {
            req = m_DatabaseConnection.prepareStatement(delPointage);
            
            req.setDate(1, Date.valueOf(datePointage));
            req.setString(2, numEmp);
            req.setString(3, pointage);
            
            System.out.println(req.toString());
            
            retVal = req.executeUpdate() >= 0;
        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        
        return retVal;
    }
    
    public static void pushHoliday(JTable holidayTable, Holiday toAdd) {
        SwingUtilities.invokeLater(() -> {
            if (holidayTable == null) {
                System.out.println("\"holidayTable\" is null.");
                return;
            }
            
            TableModel tableModel = holidayTable.getModel();
            
            if (!(tableModel instanceof DefaultTableModel)) {
                System.out.println("\"tableModel\" is not a DefaultTableModel.");
                return;
            }
            
            DefaultTableModel defaultModel = (DefaultTableModel)(tableModel);
            
            defaultModel.addRow(new Object[] {
                                toAdd.getNumConge(),
                                toAdd.getNumEmp(),
                                toAdd.getMotif(),
                                toAdd.getNombreJours(),
                                toAdd.getDateDemande(),
                                toAdd.getDateRetour()
                                });
        });
    }
    public static List<Holiday> getHolidays() {
        List<Holiday> retVal = new ArrayList<>();
        String selectPointage = "SELECT * FROM Conge;";
        ResultSet res = null;
        
        try { 
            res = m_DatabaseStatement.executeQuery(selectPointage);
            
            if (res == null)
                return retVal;
            
            while (res.next()) {
                Holiday toPush = new Holiday();
                
                toPush.setNumConge(res.getString("NumConge"));
                toPush.setNumEmp(res.getString("NumEmp"));
                toPush.setMotif(res.getString("Motif"));
                toPush.setNombreJours(res.getInt("NombreJours"));
                toPush.setDateDemande(res.getString("DateDemande"));
                toPush.setDateRetour(res.getString("DateRetour"));
                
                retVal.add(toPush);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return retVal;
    }
    public static void loadHolidays(JTable dest) {
        List<Holiday> toPush = getHolidays();
        
        for (Holiday h : toPush) {
            pushHoliday(dest, h);
            System.out.println(h.getNumConge());
        }
    }
    public static void reloadHolidays(JTable dest) {
        dest.setModel(new DefaultTableModel());
        loadHolidays(dest);
    }
}
