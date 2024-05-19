/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

import java.sql.Date;

/**
 *
 * @author andy
 */
public class PseudoDate {
    private int m_Day;
    private int m_Month;
    private int m_Year;
    
    public PseudoDate()
    {
        m_Day = 1;
        m_Month = 1;
        m_Year = 1999;
    }
    
    public PseudoDate(int day, int month, int year)
    {
        create(day, month, year);
    }
    
    public PseudoDate(String formatted)
    {
        this();
        create(formatted);
    }
    
    public String constructStringDate()
    {
        return Util.constructDateString(m_Day, m_Month, m_Year);
    }
    
    public Date toSQLDate()
    {
        String date = constructStringDate();
        
        
        return Date.valueOf(date);
    }
    
    public void create(int day, int month, int year)
    {
        m_Day = day;
        m_Month = month;
        m_Year = year;
    }
    
    public void create(String date)
    {
        String[] parts = date.split("-");
        
        if (parts.length < 3)
            return;
        
        m_Year = Integer.parseInt(parts[0]);
        m_Month = Integer.parseInt(parts[1]);
        m_Day = Integer.parseInt(parts[2]);
    }
    
    public int getDay() { return m_Day; }
    
    public int getMonth() { return m_Month; }
    
    public int getYear() { return m_Year; }
    
}
