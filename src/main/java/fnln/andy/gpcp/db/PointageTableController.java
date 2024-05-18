/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Pointage;
import fnln.andy.gpcp.core.Util;
import java.sql.Connection;
import java.sql.Date;
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
public class PointageTableController extends ATableController<Pointage> {
    public PointageTableController(Statement statement, Connection connection)
    {
        super(statement, connection);
    }

    @Override
    public void pushEntryIntoTable(DataArg args, Object dest)
    {
        JTable table = (JTable)(dest);
        Pointage pointage = (Pointage)(args.popFrontArg());
        
        DefaultTableModel tableModel = Util.getDefaultTableModel(table);
        
        if (tableModel == null)
            return;
        
        tableModel.addRow(new Object[] {    
                                            pointage.getDatePointage(),
                                            pointage.getNumEmp(),
                                            pointage.getPointage()
                                        }
                        );
    }
    
    @Override
    public void loadEntries(Object dest)
    {
        if (m_Entries == null)
            m_Entries = fetchEntries();
        
        for (Pointage p : m_Entries)
            pushEntryIntoTable(p, dest);
        
        m_PersonalDestTable = (JTable)(dest);
    }
    
    @Override
    public List<Pointage> fetchEntries()
    {
        List<Pointage> retVal = new ArrayList<>();
        String selectPointagesQuery = "SELECT * FROM Pointage;";
        ResultSet resultSet;
        
        try {
            resultSet = m_SQLStatement.executeQuery(selectPointagesQuery);
            
            if (resultSet == null)
                return retVal;
            
            while (resultSet.next()) {
                Pointage toPush = new Pointage();
                
                toPush.setDatePointage(resultSet.getString("DatePointage"));
                toPush.setNumEmp(resultSet.getString("NumEmp"));
                toPush.setPointage(resultSet.getString("Pointage"));
                
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
        String insertPointageQuery = "INSERT INTO Pointage VALUES("
                                    + "?,"
                                    + "?,"
                                    + "?"
                                    + ");";
        Pointage pointage = (Pointage)(args.popFrontArg());
        PreparedStatement preparedStatement;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(insertPointageQuery);
            
            preparedStatement.setDate(1, Date.valueOf(pointage.getDatePointage()));
            preparedStatement.setString(2, pointage.getNumEmp());
            preparedStatement.setString(3, pointage.getPointage());
            
            retVal = preparedStatement.executeUpdate() >= 0;
        } catch (SQLException e) {}
        
        return retVal;
    }
    
    @Override
    public boolean editEntry(DataArg args)
    {
        String updatePointageQuery = "UPDATE Pointage SET "
                                    + "DatePointage = ?, "
                                    + "NumEmp = ?, "
                                    + "Pointage = ? "
                                    + "WHERE "
                                    + "DatePointage = ? "
                                    + "AND NumEmp = ? "
                                    + "AND Pointage = ?"
                                    + ";";
        Pointage newPointage = (Pointage)(args.popFrontArg());
        Pointage oldPointage = (Pointage)(args.popFrontArg());
        PreparedStatement preparedStatement;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(updatePointageQuery);
            
            preparedStatement.setDate(1, Date.valueOf(newPointage.getDatePointage()));
            preparedStatement.setString(2, newPointage.getNumEmp());
            preparedStatement.setString(3, newPointage.getPointage());
            
            preparedStatement.setDate(4, Date.valueOf(oldPointage.getDatePointage()));
            preparedStatement.setString(5, oldPointage.getNumEmp());
            preparedStatement.setString(6, oldPointage.getPointage());
            
            retVal = preparedStatement.executeUpdate() >= 0;
        } catch (SQLException e) {}
        
        return retVal;
    }
    
    @Override
    public boolean removeEntry(DataArg args)
    {
        boolean retVal = false;
        PreparedStatement preparedStatement;
        Pointage pointage = (Pointage)(args.popFrontArg());
        String deletePointageQuery = "DELETE FROM Pointage WHERE DatePointage = ? "
                                    + "AND NumEmp = ? "
                                    + "AND Pointage = ? "
                                    + ";";
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(deletePointageQuery);
            
            preparedStatement.setDate(1, Date.valueOf(pointage.getDatePointage()));
            preparedStatement.setString(2, pointage.getNumEmp());
            preparedStatement.setString(3, pointage.getPointage());
            
            retVal = preparedStatement.executeUpdate() >= 0;
        } catch(SQLException e) {}
        
        
        return retVal;
    }
}
