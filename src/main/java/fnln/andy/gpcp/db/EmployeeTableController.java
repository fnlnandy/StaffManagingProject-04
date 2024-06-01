/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.PseudoDate;
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

            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
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

            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
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
            
            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return retVal;
    }
    
    private List<Employee> fetchConditional(String query)
    {
        List<Employee> retVal = new ArrayList<>();
        
        try {
            ResultSet resultSet = m_SQLStatement.executeQuery(query);
            
            while (resultSet.next())
            {
                Employee newEmployee = new Employee();
                
                newEmployee.setNumEmp(resultSet.getString("NumEmp"));
                newEmployee.setNom(resultSet.getString("Nom"));
                newEmployee.setPrenom(resultSet.getString("Prenom"));
                newEmployee.setPoste(resultSet.getString("Poste"));
                newEmployee.setSalaire(resultSet.getInt("Salaire"));
                
                retVal.add(newEmployee);
            }
        } catch (SQLException e) {}
        
        return retVal;
    }
    
    public List<Employee> getAbsentEntries(DataArg args)
    {
        PseudoDate date = (PseudoDate)(args.popFrontArg());
        
        if (date == null)
            return null;
        
        String matchPresentQuery = """
                                   SELECT Employe.* FROM Employe, Pointage WHERE 
                                   (Employe.NumEmp NOT IN (SELECT NumEmp FROM Pointage WHERE 
                                   Pointage.DatePointage = ?)) OR 
                                   (Employe.NumEmp = Pointage.NumEmp AND Pointage.Pointage = 'Non'
                                   AND Pointage.DatePointage = ?)
                                   GROUP BY Employe.NumEmp;
                                   """;
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(matchPresentQuery);
            
            preparedStatement.setDate(1, date.toSQLDate());
            preparedStatement.setDate(2, date.toSQLDate());
        } catch (SQLException e) {}
        
        if (preparedStatement == null)
            return null;
        
        return fetchConditional(preparedStatement.toString());
    }
    
    public List<Employee> getAbsentEntries(Object[] objects)
    {
        return getAbsentEntries(DataArg.makeDataArg(objects));
    }
    
    public List<Employee> getAbsentEntries(Object object)
    {
        return getAbsentEntries(DataArg.makeDataArg(object));
    }
    
    public List<Employee> getMatchingName(DataArg args)
    {
        final String matchNameQuery = "SELECT * FROM Employe WHERE (UPPER(Nom) LIKE UPPER(?) AND UPPER(Prenom) LIKE UPPER(?))"
                + "OR UPPER(Nom) LIKE UPPER(?) OR UPPER(Prenom) LIKE UPPER(?);";
        final String toSearch = args.popFrontArg().toString();
        
        if (toSearch == null)
            return null;
        
        final String[] parts = toSearch.split(" ", 2);
       
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(matchNameQuery);
            
            preparedStatement.setString(1, "%" + parts[0] + "%");
            
            if (parts.length == 2)
                preparedStatement.setString(2, "%" + parts[1] + "%");
            else
                preparedStatement.setString(2, "%" + parts[0] + "%");
            
            preparedStatement.setString(3, "%" + toSearch + "%");
            preparedStatement.setString(4, "%" + toSearch + "%");
        } catch (SQLException e) {}
        
        if (preparedStatement == null)
            return null;
        
        return fetchConditional(preparedStatement.toString());
    }
    
    public List<Employee> getMatchingName(Object[] objects)
    {
        return getMatchingName(DataArg.makeDataArg(objects));
    }
    
    public List<Employee> getMatchingName(Object object)
    {
        return getMatchingName(DataArg.makeDataArg(object));
    }
    
    public void changeEmployeeSalary(final String numEmp, boolean decrease, JTable dest)
    {
        final String query = "UPDATE Employe SET Salaire = Salaire " + (decrease ? "-" : "+") + " ? WHERE NumEmp = ?;";
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(query);
            
            preparedStatement.setInt(1, 10_000);
            preparedStatement.setString(2, numEmp);
            
            beginTransaction();
            preparedStatement.executeUpdate();
            commitTransaction();
        } catch (SQLException e) { e.printStackTrace(); }
        
        reloadEntries(dest);
    }
}
