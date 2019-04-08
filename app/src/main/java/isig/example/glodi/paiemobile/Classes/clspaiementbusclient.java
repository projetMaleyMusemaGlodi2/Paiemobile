package isig.example.glodi.paiemobile.Classes;

import java.sql.Date;

public class clspaiementbusclient {

    int codepay;
    int refclientpay;
    double montantpay;
    Date datepay;

    clspaiementbusclient(int codepay,int refclientpay,double montantpay,Date datepay){
        this.setCodepay(codepay);
        this.setRefclientpay(refclientpay);
        this.setMontantpay(montantpay);
        this.setDatepay(datepay);
    }


    public int getCodepay() {
        return codepay;
    }

    public void setCodepay(int codepay) {
        this.codepay = codepay;
    }

    public int getRefclientpay() {
        return refclientpay;
    }

    public void setRefclientpay(int refclientpay) {
        this.refclientpay = refclientpay;
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
