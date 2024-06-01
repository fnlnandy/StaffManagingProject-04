/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Holiday;
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
public class HolidayTableController extends ATableController<Holiday> {
    public HolidayTableController(Statement statement, Connection connection)
    {
        super(statement, connection);
    }

    @Override
    public void pushEntryIntoTable(DataArg args, Object dest)
    {
        JTable table = (JTable)(dest);
        Holiday holiday = (Holiday)(args.popFrontArg());
        
        DefaultTableModel tableModel = Util.getDefaultTableModel(table);
        
        if (tableModel == null)
            return;
        
        tableModel.addRow(new Object[] {    
                                            holiday.getNumConge(),
                                            holiday.getNumEmp(),
                                            holiday.getMotif(),
                                            holiday.getNombreJours(),
                                            holiday.getDateDemande().constructStringDate(),
                                            holiday.getDateRetour().constructStringDate()
                                        }
                        );
    }
    
    @Override
    public List<Holiday> fetchEntries()
    {
        final String selectHolidaysQuery = "SELECT * FROM Conge;";
        
        List<Holiday> retVal = new ArrayList<>();
        ResultSet resultSet;
        
        try {
            resultSet = m_SQLStatement.executeQuery(selectHolidaysQuery);
            
            if (resultSet == null)
                return retVal;
            
            while (resultSet.next()) {
                Holiday toPush = new Holiday();
                
                toPush.setNumConge(resultSet.getString("NumConge"));
                toPush.setNumEmp(resultSet.getString("NumEmp"));
                toPush.setMotif(resultSet.getString("Motif"));
                toPush.setNombreJours(resultSet.getInt("NombreJours"));
                toPush.setDateDemande(resultSet.getString("DateDemande"));
                toPush.setDateRetour(resultSet.getString("DateRetour"));
                
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
        final String insertHolidayQuery = "INSERT INTO Conge VALUES("
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?,"
                + "?"
                + ");";
        final Holiday holiday = (Holiday)(args.popFrontArg());
        
        PreparedStatement preparedStatement;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(insertHolidayQuery);
        
            preparedStatement.setString(1, holiday.getNumConge());
            preparedStatement.setString(2, holiday.getNumEmp());
            preparedStatement.setString(3, holiday.getMotif());
            preparedStatement.setInt(4, holiday.getNombreJours());
            preparedStatement.setDate(5, holiday.getDateDemande().toSQLDate());
            preparedStatement.setDate(6, holiday.getDateRetour().toSQLDate());
        
            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    
    @Override
    public boolean editEntry(DataArg args)
    {
        final String updateHolidayQuery = "UPDATE Conge SET "
                + "NumConge = ?, "
                + "NumEmp = ?, "
                + "Motif = ?, "
                + "NombreJours = ?, "
                + "DateDemande = ?, "
                + "DateRetour = ? "
                + "WHERE "
                + "NumConge = ? "
                + "AND "
                + "NumEmp = ?"
                + ";";
        final Holiday newHoliday = (Holiday)(args.popFrontArg());
        final Holiday oldHoliday = (Holiday)(args.popFrontArg());
        
        PreparedStatement preparedStatement = null;
        boolean retVal = false;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(updateHolidayQuery);
        
            preparedStatement.setString(1, newHoliday.getNumConge());
            preparedStatement.setString(2, newHoliday.getNumEmp());
            preparedStatement.setString(3, newHoliday.getMotif());
            preparedStatement.setInt(4, newHoliday.getNombreJours());
            preparedStatement.setDate(5, newHoliday.getDateDemande().toSQLDate());
            preparedStatement.setDate(6, newHoliday.getDateRetour().toSQLDate());
            
            preparedStatement.setString(7, oldHoliday.getNumConge());
            preparedStatement.setString(8, oldHoliday.getNumEmp());
            
            System.out.println(preparedStatement.toString());
        
            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
        } catch (SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
        
        return retVal;
    }
    
    @Override
    public boolean removeEntry(DataArg args)
    {
        final Holiday holiday = (Holiday)(args.popFrontArg());
        final String deleteHolidayQuery = "DELETE FROM Conge WHERE "
                + "NumConge = ? "
                + "AND NumEmp = ?"
                + ";";
        
        boolean retVal = false;
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = m_SQLConnection.prepareStatement(deleteHolidayQuery);
            
            preparedStatement.setString(1, holiday.getNumConge());
            preparedStatement.setString(2, holiday.getNumEmp());
            
            System.out.println(preparedStatement.toString());
            
            beginTransaction();
            retVal = preparedStatement.executeUpdate() >= 0;
            commitTransaction();
        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        
        return retVal;
    }
}
