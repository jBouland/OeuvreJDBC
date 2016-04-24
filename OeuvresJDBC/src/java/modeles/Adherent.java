package modeles;

public class Adherent {
    private int id_adherent;
    private String nom_adherent;
    private String prenom_adherent;

    public Adherent() {
    }
    // <editor-fold desc="Propriétés"> 
    public int getId_adherent() {
        return id_adherent;
    }

    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    public String getNom_adherent() {
        return nom_adherent;
    }

    public void setNom_adherent(String nom_adherent) {
        this.nom_adherent = nom_adherent;
    }

    public String getPrenom_adherent() {
        return prenom_adherent;
    }

    public void setPrenom_adherent(String prenom_adherent) {
        this.prenom_adherent = prenom_adherent;
    }
    // </editor-fold> 
    
    public Adherent lire_Id(int id){
        //Récuperer ID avec Adherent DAO
        Adherent adherent  = new Adherent();
        
        return adherent;
    }
    
}
