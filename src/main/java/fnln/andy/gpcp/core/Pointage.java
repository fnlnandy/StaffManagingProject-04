/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

/**
 *
 * @author andy
 */
public class Pointage {
    private PseudoDate m_DatePointage;
    private String m_NumEmp;
    private String m_Pointage;
    
    public Pointage()
    {
        this("1999-01-01", "0", "Non");
    }
    
    public Pointage(final String datePointage, final String numEmp, final String pointage)
    {
        this.m_DatePointage = new PseudoDate(datePointage);
        this.m_NumEmp = numEmp;
        this.m_Pointage = ("oui".equals(pointage.toLowerCase()) ? "Oui" : "Non");
    }
    
    public PseudoDate getDatePointage() { return m_DatePointage; }
    public String getNumEmp() { return m_NumEmp; }
    public String getPointage() { return m_Pointage; }
    public String getPrintable()
    {
        String printable = "Pointage(";
        
        printable += m_DatePointage.constructStringDate() + ", ";
        printable += m_NumEmp + ", ";
        printable += m_Pointage + ")";
        
        return printable;
    }
    
    public void setDatePointage(final String datePointage) { this.m_DatePointage.create(datePointage); }
    public void setNumEmp(final String numEmp) { this.m_NumEmp = numEmp; }
    public void setPointage(final String pointage) { this.m_Pointage = pointage; }
    
}
