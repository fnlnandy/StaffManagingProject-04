/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fnln.andy.gpcp.db;

import fnln.andy.gpcp.core.DataArg;
import fnln.andy.gpcp.core.Employee;
import fnln.andy.gpcp.core.Holiday;
import fnln.andy.gpcp.core.Pointage;
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
    public abstract List<Type> fetchEntries();
    public abstract boolean addEntry(DataArg args);
    public abstract boolean editEntry(DataArg args);
    public abstract boolean removeEntry(DataArg args);
    
    public void loadEntries(List<Type> elements, Object dest)
    {
        if (elements == null || elements.isEmpty())
            return;
        
        for (Object o : elements)
        {
            if (o instanceof Employee)
                System.out.println(((Employee) o).getPrintable());
            else if (o instanceof Pointage)
                System.out.println(((Pointage) o).getPrintable());
            else if (o instanceof Holiday)
                System.out.println(((Holiday) o).getPrintable());
            else
                System.out.println(o.toString());
        }
        
        for (Type element : elements)
            pushEntryIntoTable(element, dest);
    }
    
    public void loadEntries(Object dest)
    {
        m_Entries = fetchEntries();

        loadEntries(m_Entries, dest);
        
        m_PersonalDestTable = (JTable)(dest);
    }
    
    public void reloadEntries(List<Type> elements, Object dest)
    {
        System.out.println("reloadEntries(List<Type> elements, Object dest)");
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
        
        tableModel.setRowCount(0);
        loadEntries(elements, dest);
    }
    
    public void reloadEntries(Object dest)
    {
        System.out.println("reloadEntries(Object dest)");
        
        m_Entries = fetchEntries();
        reloadEntries(m_Entries, dest);
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
