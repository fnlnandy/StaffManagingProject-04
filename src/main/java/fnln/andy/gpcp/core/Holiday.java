/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp.core;

/**
 *
 * @author andy
 * 
 * @brief Class that represents
 * a Holiday.
 */
public class Holiday {
    /**
     * @brief The holiday's Id.
     */
    private String m_NumConge;
    /**
     * @brief The employee's Id tied
     * to the holiday.
     */
    private String m_NumEmp;
    /**
     * @brief The reason given for
     * the holiday.
     */
    private String m_Motif;
    /**
     * @brief The number of days
     * the holiday(s) will last.
     */
    private int m_NombreJours;
    /**
     * @brief The date on which
     * the holiday(s) was/were
     * requested.
     */
    private PseudoDate m_DateDemande;
    /**
     * @brief The date on which
     * the employee will come back.
     */
    private PseudoDate m_DateRetour;
    
    /**
     * @brief Default constructor.
     */
    public Holiday() {
        this("0", "0", "", 0, "1999-01-01", "1999-01-01");
    }

    /**
     * @param numConge
     * @param numEmp
     * @param motif
     * @param nombreJours
     * @param dateDemande
     * @param dateRetour 
     * 
     * @brief Constructor that initializes
     * the core fields with the given arguments.
     */
    public Holiday(String numConge, String numEmp, String motif, int nombreJours,
                   String dateDemande, String dateRetour) {
        m_NumConge = numConge;
        m_NumEmp = numEmp;
        m_Motif = motif;
        m_NombreJours = nombreJours;
        m_DateDemande = new PseudoDate(dateDemande);
        m_DateRetour = new PseudoDate(dateRetour);
    }
    
    /**
     * @brief Gets the holiday's Id.
     * 
     * @return 
     */
    public String getNumConge()        { return m_NumConge;    }
    public String getNumEmp()          { return m_NumEmp;      }
    public String getMotif()           { return m_Motif;       }
    public int getNombreJours()        { return m_NombreJours; }
    public PseudoDate getDateDemande() { return m_DateDemande; }
    public PseudoDate getDateRetour()  { return m_DateRetour;  }
    public String getPrintable()
    {
        String printable = "Holiday(";
        
        printable += m_NumConge + ", ";
        printable += m_NumEmp + ", ";
        printable += "`" + m_Motif + "`, ";
        printable += String.valueOf(m_NombreJours) + ", ";
        printable += m_DateDemande.constructStringDate() + ", ";
        printable += m_DateRetour.constructStringDate() + ")";
        
        return printable;
    }
    
    public void setNumConge(final String numConge)       { m_NumConge = numConge;             }
    public void setNumEmp(final String numEmp)           { m_NumEmp = numEmp;                 }
    public void setMotif(final String motif)             { m_Motif = motif;                   }
    public void setNombreJours(final int nombreJours)    { m_NombreJours = nombreJours;       }
    public void setDateDemande(final String dateDemande) { m_DateDemande.create(dateDemande); }
    public void setDateRetour(final String dateRetour)   { m_DateRetour.create(dateRetour);   }
}
