/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author andy
 */
public class Util {
    public static DefaultTableModel getDefaultTableModel(TableModel from)
    {
        if (!(from instanceof DefaultTableModel))
            return null;
        
        return (DefaultTableModel)(from);
    }
    public static DefaultTableModel getDefaultTableModel(JTable table)
    {
        TableModel model = table.getModel();
        DefaultTableModel retVal = null;
        
        if (!(model instanceof DefaultTableModel))
            return retVal;
        
        retVal = (DefaultTableModel)(model);
        
        return retVal;
    }
    public static String constructDateString(int day, int month, int year)
    {
        String retVal = "";
        
        retVal += String.valueOf(year) + "-";
        retVal += String.valueOf(month) + "-";
        retVal += String.valueOf(day);
        
        return retVal;
    }
}