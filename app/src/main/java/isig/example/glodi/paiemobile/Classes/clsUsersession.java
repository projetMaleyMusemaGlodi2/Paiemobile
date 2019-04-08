package isig.example.glodi.paiemobile.Classes;

public class clsUsersession {

    public static clsUsersession glos=null;

    public static clsUsersession getInstance(){
        if(glos==null)
            glos=new clsUsersession();
        return  glos;
    }

    public String getDatesave() {
        return datesave;
    }

    public void setDatesave(String datesave) {
        this.datesave = datesave;
    }

    String datesave;
    String name;
    String adresse;

    public String getNiveauAgent() {
        return niveauAgent;
    }

    public void setNiveauAgent(String niveauAgent) {
        this.niveauAgent = niveauAgent;
    }

    String niveauAgent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public clsUsersession getGlos() {
        return glos;
    }

    public void setGlos(clsUsersession glos) {
        this.glos = glos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getCompte() {
        return compte;
    }

    public void setCompte(Double compte) {
        this.compte = compte;
    }

    String contact;
    Double compte;


}
