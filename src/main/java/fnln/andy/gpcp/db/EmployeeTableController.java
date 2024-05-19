/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andy
 */
public class EmployeeTableController extends ATableController<Employee> {
    public EmployeeTableController(Statement statement, Connection connection)
    {
        super(statement, connection);
    }

    @Override
    public void pushEntryIntoTable(DataArg args, Object dest)
    {
        JTable table = (JTable)(dest);
        Employee employee = (Employee)(args.popFrontArg());
        
        DefaultTableModel tableModel = Util.getDefaultTableModel(table);
        
        if (tableModel == null)
            return;
        
        tableModel.addRow(new Object[] {    
                                            employee.getNumEmp(),
                                            employee.getNom(),
                                            employee.getPrenom(),
                                            employee.getPoste(),
                                            employee.getSalaire()
                                        }
                        );
    }
    
    @Override
    public void loadEntries(Object dest)
    {
        m_Entries = fetchEntries();
        
        for (Employee e : m_Entries)
            pushEntryIntoTable(e, dest);
        
        m_PersonalDestTable = (JTable)(dest);
    }
    
    @Override
    public List<Employee> fetchEntries()
    {
        List<Employee> retVal = new ArrayList<>();
        String selectEmployeesQuery = "SELECT * FROM Employe;";
        ResultSet resultSet = null;
        
        try {
            resultSet = m_SQLStatement.executeQuery(selectEmployeesQuery);
            
            if (resultSet == null)
                return retVal;
            
            while (resultSet.next()) {
                Employee toPush = new Employee();
                
                toPush.setNumEmp(resultSet.getString("NumEmp"));
                toPush.setNom(resultSet.getString("Nom"));
                toPush.setPrenom(resultSet.getString("Prenom"));
                toPush.setPoste(resultSet.getString("Poste"));
                toPush.setSalaire(resultSet.getInt("Salaire"));
                
                retVal.add(toPush);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return retVal;
    }
    
    @Override
    public boolean addEntry(DataArg args)
    {
        String insertEmployeeQuery = "INSERT INTO Employe VALUES("
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?"
                + ");";
        Employee employee = (Employee)args.popFrontArg();
        PreparedStatement preparedStatement = null;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(insertEmployeeQuery);

            preparedStatement.setString(1, employee.getNumEmp());
            preparedStatement.setString(2, employee.getNom());
            preparedStatement.setString(3, employee.getPrenom());
            preparedStatement.setString(4, employee.getPoste());
            preparedStatement.setInt(5, employee.getSalaire());

            retVal = preparedStatement.executeUpdate() >= 0;
        } catch (SQLException e) {}
        
        return retVal;
    }
    
    @Override
    public boolean editEntry(DataArg args)
    {
        String updateEmployeeQuery = "UPDATE Employe SET "
                + "NumEmp = ?, "
                + "Nom = ?, "
                + "Prenom = ?, "
                + "Poste = ?, "
                + "Salaire = ? "
                + "WHERE NumEmp = ? "
                + ";";
        Employee employee = (Employee)(args.popFrontArg());
        String previousEmployeeId = args.popFrontArg().toString();
        PreparedStatement preparedStatement = null;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(updateEmployeeQuery);

            preparedStatement.setString(1, employee.getNumEmp());
            preparedStatement.setString(2, employee.getNom());
            preparedStatement.setString(3, employee.getPrenom());
            preparedStatement.setString(4, employee.getPoste());
            preparedStatement.setInt(5, employee.getSalaire());
            preparedStatement.setString(6, previousEmployeeId);

            retVal = preparedStatement.executeUpdate() >= 0;
        } catch (SQLException sqlE) {}
        
        return retVal;
    }
    
    @Override
    public boolean removeEntry(DataArg args)
    {
        boolean retVal = false;
        PreparedStatement preparedStatement = null;
        String employeeId = args.popFrontArg().toString();
        String deleteEmployeeQuery = "DELETE FROM Employe WHERE NumEmp = ?;";
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(deleteEmployeeQuery);
            
            preparedStatement.setString(1, employeeId);
            
            retVal = preparedStatement.executeUpdate() >= 0;
        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return retVal;
    }
}
