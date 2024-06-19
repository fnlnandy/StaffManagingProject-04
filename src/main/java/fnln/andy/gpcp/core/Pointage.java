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
 * a pointage.
 */
public class Pointage {
    /**
     * @brief The date of the pointage.
     */
    private PseudoDate m_DatePointage;
    /**
     * @brief The concerned employee's Id.
     */
    private String m_NumEmp;
    /**
     * @brief Whether the employee was
     * there or not.
     */
    private String m_Pointage;
    
    /**
     * @brief Default constructor.
     */
    public Pointage()
    {
        this("1999-01-01", "0", "Non");
    }
    
    /**
     * @param datePointage
     * @param numEmp
     * @param pointage 
     * 
     * @brief Constructor that initializes
     * this object's fields using the given
     * params.
     */
    public Pointage(final String datePointage, final String numEmp, final String pointage)
    {
        m_DatePointage = new PseudoDate(datePointage);
        m_NumEmp = numEmp;
        m_Pointage = ("oui".equals(pointage.toLowerCase()) ? "Oui" : "Non");
    }
    
    /**
     * @brief Gets the pointage's
     * date.
     * 
     * @return 
     */
    public PseudoDate getDatePointage() { return m_DatePointage; }
    /**
     * @brief Gets the concerned employee's
     * Id.
     * @return 
     */
    public String getNumEmp()           { return m_NumEmp;       }
    /**
     * @brief Gets whether the concerned
     * employee was there or not.
     * 
     * @return 
     */
    public String getPointage()         { return m_Pointage;     }
    /**
     * @brief Gets the printable string
     * representation of this object.
     * 
     * @return 
     */
    public String getPrintable()
    {
        String printable = "Pointage(";
        
        printable += m_DatePointage.constructStringDate() + ", ";
        printable += m_NumEmp + ", ";
        printable += m_Pointage + ")";
        
        return printable;
    }
    
    /**
     * @param datePointage 
     * 
     * @brief Sets the date of
     * the pointage.
     */
    public void setDatePointage(final String datePointage) { m_DatePointage.create(datePointage); }
    /**
     * @param numEmp
     * 
     * @brief Sets the concerned
     * employee's Id.
     */
    public void setNumEmp(final String numEmp)             { m_NumEmp = numEmp;                   }
    /**
     * @param pointage
     * 
     * @brief Sets whether the
     * employee was there or not.
     */
    public void setPointage(final String pointage)         { m_Pointage = pointage;               }
}
