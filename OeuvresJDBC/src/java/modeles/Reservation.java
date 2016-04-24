package modeles;

public class Reservation {

    private int id_oeuvre;
    private int id_adherent;
    private java.util.Date date_reservation;
    private String statut;
    private Adherent adherent;
    private Oeuvre oeuvre;

    public Reservation() {
        this.setAdherent(new Adherent());
        this.setOeuvre(new Oeuvre());
        this.setStatut("");
    }
    /**
     * Initialise l'Adhérent et l'Oeuvre de la Reservation
     * @param id_oeuvre Id de l'oeuvre réservée
     * @param id_adherent Id de l'adhérent réservant
     * @throws Exception
     */
    public Reservation(int id_oeuvre, int id_adherent) throws Exception {
        setId_oeuvre(id_oeuvre);
        setId_adherent(id_adherent);
        this.setAdherent(new Adherent().lire_Id(id_adherent));
        this.setOeuvre(new Oeuvre().lire_Id(id_oeuvre));
        this.setDate_reservation(date_reservation);
        this.setStatut(statut);
    }
    
    // <editor-fold desc="Propriétés"> 
    
    public Adherent getAdherent() {
        return adherent;
    }
    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
    public int getId_oeuvre() {
        return id_oeuvre;
    }
    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }
    public int getId_adherent() {
        return id_adherent;
    }
    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }
    public java.util.Date getDate_reservation() {
        return date_reservation;
    }
    public void setDate_reservation(java.util.Date date_reservation) throws Exception {
        this.date_reservation = date_reservation;
    }
    // </editor-fold> 



}
