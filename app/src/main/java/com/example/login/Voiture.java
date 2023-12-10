package com.example.login;

public class Voiture {
    private Integer id;
    private String matricule;
    private String modele;
    private String marque;
    private String annee;
    private String dispo;

    public Voiture(Integer id, String matricule, String modele, String marque, String annee,String dispo) {
        this.id = id;
        this.matricule = matricule;
        this.modele = modele;
        this.marque = marque;
        this.annee = annee;
        this.dispo = dispo;
    }

    // Getter for Id
    public Integer getId() {
        return id;
    }

    // Setter for Id
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter for Matricule
    public String getMatricule() {
        return matricule;
    }

    // Setter for Matricule
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    // Getter for Marque
    public String getMarque() {
        return marque;
    }

    // Setter for Marque
    public void setMarque(String marque) {
        this.marque = marque;
    }
    // Getter for Modele
    public String getModele() {
        return modele;
    }

    // Setter for Modele
    public void setModele(String modele) {
        this.modele = modele;
    }

    // Getter for Annee
    public String getAnnee() {
        return annee;
    }

    // Setter for Annee
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    // Getter for Dispo
    public String getDispo() {
        return dispo;
    }

    // Setter for Dispo
    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    // toString method
    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", modele='" + modele + '\'' +
                ", marque='" + marque + '\'' +
                ", annee='" + annee + '\'' +
                ", dispo='" + dispo + '\'' +
                '}';
    }
}
