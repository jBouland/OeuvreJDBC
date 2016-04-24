package modeles;

public class Oeuvre {

    private int id_oeuvre;
    private int id_proprietaire;
    private String titre;
    private double prix;
    private Proprietaire proprietaire;

    public Oeuvre() {
    }
    // <editor-fold desc="Propriétés"> 
    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }
    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }
    public Proprietaire getProprietaire() {
        return proprietaire;
    }
    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    // </editor-fold> 

    public Oeuvre lire_Id(int id_oeuvre) {
        //TODO: Utiliser OeuvreDAO pour récuperer l'oeuvre
        return new Oeuvre();
    }

}
