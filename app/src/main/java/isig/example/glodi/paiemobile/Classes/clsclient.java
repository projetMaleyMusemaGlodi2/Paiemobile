package isig.example.glodi.paiemobile.Classes;

import java.sql.Date;

public class clsclient {

    int codeclient;
    String nomclient;
    String adresseclient;
    String datesave;
    String nomuserclient;
    clsclient glos=null;
    String contactclient;
    String paswordclient;
    byte[] image;
    double compteclient;
    int status_syncclient;

    public String getContactclient() {
        return contactclient;
    }

    public void setContactclient(String contactclient) {
        this.contactclient = contactclient;
    }

    public String getNomuserclient() {
        return nomuserclient;
    }

    public void setNomuserclient(String nomuserclient) {
        this.nomuserclient = nomuserclient;
    }

    public double getCompteclient() {
        return compteclient;
    }

    public void setCompteclient(double compteclient) {
        this.compteclient = compteclient;
    }



    public clsclient(int codeclient,String nomclient,String adresseclient,String contactclient,String datesave,byte[] image,int compteclient){
        this.setCodeclient(codeclient);
        this.setNomclient(nomclient);
        this.setAdresseclient(adresseclient);
        this.setContactclient(contactclient);
        this.setDatesave(datesave);
        this.setImage(image);
        this.setCompteclient(compteclient);
    }

    //clsclient(int codeclient,String nomclient,String adresseclient,String contactclient,
              //String datesave,String nomuserclient,String paswordclient,byte[] image,
              //double compteclient,int status_syncclient){

        //this.setCodeclient(codeclient);
        //this.setNomclient(nomclient);
        //this.setAdresseclient(adresseclient);
        //this.setContactclient(contactclient);
        //this.setDatesave(datesave);
        //this.setNomuserclient(nomuserclient);
        //this.setPaswordclient(paswordclient);
        //this.setImage(image);
        //this.setStatus_syncclient(status_syncclient);
        //this.setCompteclient(compteclient);
    //}

    public int getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(int codeclient) {
        this.codeclient = codeclient;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getAdresseclient() {
        return adresseclient;
    }

    public void setAdresseclient(String adresseclient) {
        this.adresseclient = adresseclient;
    }

    public String getDatesave() {
        return datesave;
    }

    public void setDatesave(String datesave) {
        this.datesave = datesave;
    }

    public String getNumuserclient() {
        return nomuserclient;
    }


    public String getPaswordclient() {
        return paswordclient;
    }

    public void setPaswordclient(String paswordclient) {
        this.paswordclient = paswordclient;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStatus_syncclient() {
        return status_syncclient;
    }

    public void setStatus_syncclient(int status_syncclient) {
        this.status_syncclient = status_syncclient;
    }
}
