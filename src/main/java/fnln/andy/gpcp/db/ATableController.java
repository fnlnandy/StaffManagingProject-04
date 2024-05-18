/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Util;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andy
 */
public abstract class ATableController<Type> {
    protected List<Type> m_Entries;
    protected Statement m_SQLStatement;
    protected Connection m_SQLConnection;
    protected JTable m_PersonalDestTable;
    
    public ATableController(Statement statement, Connection connection)
    {
        m_SQLStatement = statement;
        m_SQLConnection = connection;
    }
    public abstract void pushEntryIntoTable(DataArg args, Object dest);
    public abstract void loadEntries(Object dest);
    public abstract List<Type> fetchEntries();
    public abstract boolean addEntry(DataArg args);
    public abstract boolean editEntry(DataArg args);
    public abstract boolean removeEntry(DataArg args);
    
    public void reloadEntries(Object dest)
    {
        if (dest == null)
        {
            System.out.println("dest is null, in reloadEntries.");
            return;
        }
            
        JTable table = (JTable)(dest);
        DefaultTableModel tableModel = Util.getDefaultTableModel(table);
        
        if (tableModel == null)
        {
            System.out.println("The tableModel is null, in reloadEntries.");
            tableModel = new DefaultTableModel();
            table.setModel(tableModel);
        }
        
        for (int i = tableModel.getRowCount() - 1; i >= 0; i--)
            tableModel.removeRow(i);
        
        loadEntries(dest);
    }
    
    public void reloadEntries()
    {
        reloadEntries(m_PersonalDestTable);
    }
    
    public List<Type> getAllEntries()
    {
        if (m_Entries == null)
            m_Entries = fetchEntries();
        
        return m_Entries;
    }
    
    public void pushEntryIntoTable(Object[] objects, Object dest)
    {
        pushEntryIntoTable(DataArg.makeDataArg(objects), dest);
    }
    
    public void pushEntryIntoTable(Object object, Object dest)
    {
        pushEntryIntoTable(DataArg.makeDataArg(object), dest);
    }
    
    public boolean addEntry(Object[] objects)
    {
        return addEntry(DataArg.makeDataArg(objects));
    }
    
    public boolean addEntry(Object object)
    {
        return addEntry(DataArg.makeDataArg(object));
    }
    
    public boolean editEntry(Object[] objects)
    {
        return editEntry(DataArg.makeDataArg(objects));
    }
    
    public boolean editEntry(Object object)
    {
        return editEntry(DataArg.makeDataArg(object));
    }
    
    public boolean removeEntry(Object[] objects)
    {
        return removeEntry(DataArg.makeDataArg(objects));
    }
    
    public boolean removeEntry(Object object)
    {
        return removeEntry(DataArg.makeDataArg(object));
    }
}
