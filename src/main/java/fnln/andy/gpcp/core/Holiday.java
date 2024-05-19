/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

import fnln.andy.gpcp.core.PseudoDate;

/**
 *
 * @author andy
 */
public class Holiday {
    private String m_NumConge;
    private String m_NumEmp;
    private String m_Motif;
    private int m_NombreJours;
    private PseudoDate m_DateDemande;
    private PseudoDate m_DateRetour;
    
    public Holiday() {
        this("0", "0", "", 0, "1999-01-01", "1999-01-01");
    }
    
    public Holiday(String numConge, String numEmp, String motif, int nombreJours,
                   String dateDemande, String dateRetour) {
        this.m_NumConge = numConge;
        this.m_NumEmp = numEmp;
        this.m_Motif = motif;
        this.m_NombreJours = nombreJours;
        this.m_DateDemande = new PseudoDate(dateDemande);
        this.m_DateRetour = new PseudoDate(dateRetour);
    }
    
    public String getNumConge() { return this.m_NumConge; }
    public String getNumEmp() { return this.m_NumEmp; }
    public String getMotif() { return this.m_Motif; }
    public int getNombreJours() { return this.m_NombreJours; }
    public PseudoDate getDateDemande() { return this.m_DateDemande; }
    public PseudoDate getDateRetour() { return this.m_DateRetour; }
    public String getPrintable()
    {
        String printable = "Holiday(";
        
        printable += m_NumConge + ", ";
        printable += m_NumEmp + ", ";
        printable += "`" + m_Motif + "`, ";
        printable += String.valueOf(m_NombreJours) + ", ";
        printable += m_DateDemande + ", ";
        printable += m_DateRetour + ")";
        
        return printable;
    }
    
    public void setNumConge(final String numConge) { this.m_NumConge = numConge; }
    public void setNumEmp(final String numEmp) { this.m_NumEmp = numEmp; }
    public void setMotif(final String motif) { this.m_Motif = motif; }
    public void setNombreJours(final int nombreJours) { this.m_NombreJours = nombreJours; }
    public void setDateDemande(final String dateDemande) { this.m_DateDemande.create(dateDemande); }
    public void setDateRetour(final String dateRetour) { this.m_DateRetour.create(dateRetour); }
}
