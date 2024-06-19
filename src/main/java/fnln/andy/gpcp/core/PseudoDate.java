/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author andy
 * 
 * @brief Class that represents a
 * date.
 */
public class PseudoDate {
    private int m_Day;
    private int m_Month;
    private int m_Year;
    private int m_Maxes[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
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
    
    public String constructStringDate(boolean YYmmdd)
    {
        return Util.constructDateString(m_Day, m_Month, m_Year, YYmmdd);
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
    
    public boolean equals(PseudoDate toCompare)
    {
        if (m_Day != toCompare.m_Day)
            return false;
        if (m_Month != toCompare.m_Month)
            return false;
        return m_Year == toCompare.m_Year;
    }
    
    public void advance(int numberOfDays)
    {
        int remaining = numberOfDays;
        
        while (remaining > 0) {
            int currMax = m_Maxes[m_Month - 1]; // A month is 1-indexed.
        
            if ((m_Month == 2) && (m_Year % 4 == 0))
                currMax = 29;
            
            if (m_Day + remaining > currMax) {
                remaining -= Math.abs(currMax - m_Day + 1); // Even if we're the 30, and it's the max, that's still a day.
                m_Day = 1;
                
                if (m_Month == 12) {
                    m_Month = 1;
                    m_Year++;
                }
                else {
                    m_Month++;
                }
            }
            else {
                m_Day += remaining;
                remaining = 0;
            }
        }
    }
    
    public boolean isBefore(PseudoDate date)
    {
        return (m_Year < date.getYear() || 
                (m_Year == date.getYear() && m_Month < date.getMonth()) || 
                (m_Year == date.getYear() && m_Month == date.getMonth() && m_Day < date.getDay()));
    }
    
    public boolean isAfter(PseudoDate date)
    {
        return !isBefore(date) && !equals(date);
    }
    
    public static PseudoDate getCurrentDate()
    {
        LocalDate today = LocalDate.now();
        PseudoDate retVal = new PseudoDate();
        
        retVal.create(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
        
        return retVal;
    }
}
