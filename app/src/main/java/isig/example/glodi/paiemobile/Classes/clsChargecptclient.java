package isig.example.glodi.paiemobile.Classes;

import java.sql.Date;

public class clsChargecptclient {
    int codecharge;
    int refclientcharge;

    clsChargecptclient(int codecharge,int refclientcharge,double montantcharge,Date datecharge){
        this.setCodecharge(codecharge);
        this.setRefclientcharge(refclientcharge);
        this.setMontantcharge(montantcharge);
        this.setDatecharge(datecharge);
    }

    public int getCodecharge() {
        return codecharge;
    }

    public void setCodecharge(int codecharge) {
        this.codecharge = codecharge;
    }

    public int getRefclientcharge() {
        return refclientcharge;
    }

    public void setRefclientcharge(int refclientcharge) {
        this.refclientcharge = refclientcharge;
    }

    public double getMontantcharge() {
        return montantcharge;
    }

    public void setMontantcharge(double montantcharge) {
        this.montantcharge = montantcharge;
    }

    public Date getDatecharge() {
        return datecharge;
    }

    public void setDatecharge(Date datecharge) {
        this.datecharge = datecharge;
    }

    double montantcharge;
    Date datecharge;



}
