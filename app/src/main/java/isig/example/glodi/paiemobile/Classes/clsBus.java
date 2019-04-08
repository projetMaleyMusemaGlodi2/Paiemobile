package isig.example.glodi.paiemobile.Classes;

public class clsBus {

    int code_bus;
    String numeroplaque_bus;
    String marque_bus;
    String nom_proprietaire;
    String adresse_proprietaire;
    String contact_proprietaire;
    String nomuser_proprietaire;
    String password_proprietaire;
    String nom_chauffeur;
    String adresse_chauffeur;
    String contact_chauffeur;
    String nomuser_chauffeur;
    String password_chauffeur;

    public String getDate_save_bus() {
        return date_save_bus;
    }

    public void setDate_save_bus(String date_save_bus) {
        this.date_save_bus = date_save_bus;
    }

    String date_save_bus;

    public Double getCompte_bus() {
        return compte_bus;
    }

    public void setCompte_bus(Double compte_bus) {
        this.compte_bus = compte_bus;
    }

    Double compte_bus;
    int status_sync_bus;

    clsBus(int code_bus,String numeroplaque_bus,
            String marque_bus,
            String nom_proprietaire,
            String adresse_proprietaire,
            String contact_proprietaire,
            String nomuser_proprietaire,
            String password_proprietaire,
            String nom_chauffeur,
            String adresse_chauffeur,
            String contact_chauffeur,
            String nomuser_chauffeur,
            String password_chauffeur,
            Double compte_bus,
            int status_sync_bus){

        this.setCode_bus(code_bus);
        this.setNumeroplaque_bus(numeroplaque_bus);
        this.setMarque_bus(marque_bus);
        this.setNom_proprietaire(nom_proprietaire);
        this.setAdresse_chauffeur(adresse_proprietaire);
        this.setContact_proprietaire(contact_proprietaire);
        this.setNomuser_proprietaire(nomuser_proprietaire);
        this.setPassword_proprietaire(password_proprietaire);
        this.setNom_chauffeur(nom_chauffeur);
        this.setAdresse_chauffeur(adresse_chauffeur);
        this.setContact_chauffeur(contact_chauffeur);
        this.setNomuser_chauffeur(nomuser_chauffeur);
        this.setPassword_chauffeur(password_chauffeur);
        this.setCompte_bus(compte_bus);
        this.setStatus_sync_bus(status_sync_bus);
    }

    public clsBus(
            int code_bus,
           String numeroplaque_bus,
           String marque_bus,
           String nom_proprietaire,
           String nom_chauffeur,
           Double compte_bus
           ){

        this.setCode_bus(code_bus);
        this.setNumeroplaque_bus(numeroplaque_bus);
        this.setMarque_bus(marque_bus);
        this.setNom_proprietaire(nom_proprietaire);
        this.setNom_chauffeur(nom_chauffeur);
        this.setCompte_bus(compte_bus);
    }

    public int getCode_bus() {
        return code_bus;
    }

    public void setCode_bus(int code_bus) {
        this.code_bus = code_bus;
    }

    public String getNumeroplaque_bus() {
        return numeroplaque_bus;
    }

    public void setNumeroplaque_bus(String numeroplaque_bus) {
        this.numeroplaque_bus = numeroplaque_bus;
    }

    public String getMarque_bus() {
        return marque_bus;
    }

    public void setMarque_bus(String marque_bus) {
        this.marque_bus = marque_bus;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public String getAdresse_proprietaire() {
        return adresse_proprietaire;
    }

    public void setAdresse_proprietaire(String adresse_proprietaire) {
        this.adresse_proprietaire = adresse_proprietaire;
    }

    public String getContact_proprietaire() {
        return contact_proprietaire;
    }

    public void setContact_proprietaire(String contact_proprietaire) {
        this.contact_proprietaire = contact_proprietaire;
    }

    public String getNomuser_proprietaire() {
        return nomuser_proprietaire;
    }

    public void setNomuser_proprietaire(String nomuser_proprietaire) {
        this.nomuser_proprietaire = nomuser_proprietaire;
    }

    public String getPassword_proprietaire() {
        return password_proprietaire;
    }

    public void setPassword_proprietaire(String password_proprietaire) {
        this.password_proprietaire = password_proprietaire;
    }

    public String getNom_chauffeur() {
        return nom_chauffeur;
    }

    public void setNom_chauffeur(String nom_chauffeur) {
        this.nom_chauffeur = nom_chauffeur;
    }

    public String getAdresse_chauffeur() {
        return adresse_chauffeur;
    }

    public void setAdresse_chauffeur(String adresse_chauffeur) {
        this.adresse_chauffeur = adresse_chauffeur;
    }

    public String getContact_chauffeur() {
        return contact_chauffeur;
    }

    public void setContact_chauffeur(String contact_chauffeur) {
        this.contact_chauffeur = contact_chauffeur;
    }

    public String getNomuser_chauffeur() {
        return nomuser_chauffeur;
    }

    public void setNomuser_chauffeur(String nomuser_chauffeur) {
        this.nomuser_chauffeur = nomuser_chauffeur;
    }

    public String getPassword_chauffeur() {
        return password_chauffeur;
    }

    public void setPassword_chauffeur(String password_chauffeur) {
        this.password_chauffeur = password_chauffeur;
    }

    public int getStatus_sync_bus() {
        return status_sync_bus;
    }

    public void setStatus_sync_bus(int status_sync_bus) {
        this.status_sync_bus = status_sync_bus;
    }


}
