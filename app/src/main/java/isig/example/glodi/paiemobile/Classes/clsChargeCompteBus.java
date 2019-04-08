package isig.example.glodi.paiemobile.Classes;

import java.sql.Date;

public class clsChargeCompteBus {

    int codepay;

    public int getRefbuschargement() {
        return refbuschargement;
    }

    public void setRefbuschargement(int refbuschargement) {
        this.refbuschargement = refbuschargement;
    }

    int refbuschargement;
    double montantpay;
    Date datepay;


    clsChargeCompteBus(int codepay,int refbuschargement,double montantpay,Date datepay){
        this.setCodepay(codepay);
        this.setRefbuschargement(refbuschargement);
        this.setMontantpay(montantpay);
        this.setDatepay(datepay);
    }

    public int getCodepay() {
        return codepay;
    }

    public void setCodepay(int codepay) {
        this.codepay = codepay;
    }



    public double getMontantpay() {
        return montantpay;
    }

    public void setMontantpay(double montantpay) {
        this.montantpay = montantpay;
    }

    public Date getDatepay() {
        return datepay;
    }

    public void setDatepay(Date datepay) {
        this.datepay = datepay;
    }


}
