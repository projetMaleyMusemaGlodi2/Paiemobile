package isig.example.glodi.paiemobile.Classes;

public class clsUsersessionbus {

    public  static  clsUsersessionbus glos=null;

    public  static  clsUsersessionbus getInstance(){
        if(glos==null)
            glos=new clsUsersessionbus();
        return  glos;
    }

    public String getIdbus() {
        return idbus;
    }

    public void setIdbus(String idbus) {
        this.idbus = idbus;
    }

    String idbus;


}
