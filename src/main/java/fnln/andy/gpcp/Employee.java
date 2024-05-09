/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fnln.andy.gpcp;

/**
 *
 * @author andy
 */
public class Employee {
    private String m_NumEmp;
    private String m_Nom;
    private String m_Prenom;
    private String m_Poste;
    private int m_Salaire;
    
    public Employee()
    {
        m_NumEmp = "";
        m_Nom = "";
        m_Prenom = "";
        m_Poste = "";
        m_Salaire = 0;
    }
    
    public Employee(final String numEmp, final String nom, final String prenom, 
                    final String poste, final int salaire)
    {
        this.m_NumEmp = numEmp;
        this.m_Nom = nom;
        this.m_Prenom = prenom;
        this.m_Poste = poste;
        this.m_Salaire = salaire;
    }
    
    public String getNumEmp() { return m_NumEmp; } 
    public String getNom() { return m_Nom; }
    public String getPrenom() { return m_Prenom; }
    public String getPoste() { return m_Poste; }
    public int getSalaire() { return m_Salaire; }
    
    public void setNumEmp(final String numEmp) { this.m_NumEmp = numEmp; }
    public void setNom(final String nom) { this.m_Nom = nom; }
    public void setPrenom(final String prenom) { this.m_Prenom = prenom; }
    public void setPoste(final String poste) { this.m_Poste = poste; }
    public void setSalaire(final int salaire) { this.m_Salaire = salaire; }
}
