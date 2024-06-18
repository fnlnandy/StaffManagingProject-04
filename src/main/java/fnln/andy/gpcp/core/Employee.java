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
 * an Employee.
 */
public class Employee {
    /**
     * @brief The employee's Id.
     */
    private String m_NumEmp;
    /**
     * @brief The employee's Name.
     */
    private String m_Nom;
    /**
     * @brief The employee's Last Name.
     */
    private String m_Prenom;
    /**
     * @brief The employee's Job.
     */
    private String m_Poste;
    /**
     * @brief The employee's Salary.
     */
    private int m_Salaire;
    
    /**
     * @brief Default constructor.
     */
    public Employee()
    {
        this("", "", "", "", 0);
    }
    
    /**
     * @param numEmp
     * @param nom
     * @param prenom
     * @param poste
     * @param salaire 
     * 
     * @brief Constructor that initializes
     * the object's fields using the given
     * params.
     */
    public Employee(final String numEmp, final String nom, final String prenom, 
                    final String poste, final int salaire)
    {
        m_NumEmp = numEmp;
        m_Nom = nom;
        m_Prenom = prenom;
        m_Poste = poste;
        m_Salaire = salaire;
    }
    
    /**
     * @brief Returns the employee's Id.
     * 
     * @return 
     */
    public String getNumEmp() { return m_NumEmp;  } 
    /**
     * @brief Returns the employee's Name.
     * 
     * @return 
     */
    public String getNom()    { return m_Nom;     }
    /**
     * @brief Returns the employee's Last Name.
     * 
     * @return 
     */
    public String getPrenom() { return m_Prenom;  }
    /**
     * @brief Returns the employee's Job.
     * 
     * @return 
     */
    public String getPoste()  { return m_Poste;   }
    /**
     * @brief Returns the employee's Salary.
     * 
     * @return 
     */
    public int getSalaire()   { return m_Salaire; }
    /**
     * @brief Returns a printable representation
     * of an Employee, i.e. as String.
     * 
     * @note For debugging purposes.
     * 
     * @return 
     */
    public String getPrintable()
    {
        String printable = "Employee(";
        
        printable += m_NumEmp + ", ";
        printable += m_Nom + ", ";
        printable += m_Prenom + ", ";
        printable += m_Poste + ", ";
        printable += String.valueOf(m_Salaire) + ")";
        
        return printable;
    }
    
    /**
     * @param numEmp
     * 
     * @brief Sets the employee's Id
     * to 'numEmp'.
     */
    public void setNumEmp(final String numEmp) { m_NumEmp = numEmp;   }
    /**
     * @param nom
     * 
     * @brief Sets the employee's Name
     * to 'nom'.
     */
    public void setNom(final String nom)       { m_Nom = nom;         }
    /**
     * @param prenom
     * 
     * @brief Sets the employee's Last
     * Name to 'prenom'.
     */
    public void setPrenom(final String prenom) { m_Prenom = prenom;   }
    /**
     * @param poste
     * 
     * @brief Sets the employee's Job
     * to 'poste'.
     */
    public void setPoste(final String poste)   { m_Poste = poste;     }
    /**
     * @param salaire 
     * 
     * @brief Sets the employee's Salary
     * to 'salaire'.
     */
    public void setSalaire(final int salaire)  { m_Salaire = salaire; }
}
