package isig.example.glodi.paiemobile.Classes;

public class clsParametreprix {
    int codeprix;
    double prix;

    clsParametreprix(int codeprix,double prix){
        this.codeprix=codeprix;
        this.prix=prix;
    }

    public int getCodeprix() {
        return codeprix;
    }

    public void setCodeprix(int codeprix) {
        this.codeprix = codeprix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


}
