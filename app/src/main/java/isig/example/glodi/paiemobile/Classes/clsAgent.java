package isig.example.glodi.paiemobile.Classes;

public class clsAgent {

    int codeagent;
    String nomagent;
    String contactagent;
    String datesaveagent;
    String passwordagent;
    String niveauagent;


    clsAgent(int codeagent,String nomagent,String contactagent,String datesaveagent,String passwordagent,String niveauagent){
        this.setCodeagent(codeagent);
        this.setNomagent(nomagent);
        this.setContactagent(contactagent);
        this.setDatesaveagent(datesaveagent);
        this.setPasswordagent(passwordagent);
        this.setNiveauagent(niveauagent);
    }

    public int getCodeagent() {
        return codeagent;
    }

    public void setCodeagent(int codeagent) {
        this.codeagent = codeagent;
    }

    public String getNomagent() {
        return nomagent;
    }

    public void setNomagent(String nomagent) {
        this.nomagent = nomagent;
    }

    public String getContactagent() {
        return contactagent;
    }

    public void setContactagent(String contactagent) {
        this.contactagent = contactagent;
    }

    public String getDatesaveagent() {
        return datesaveagent;
    }

    public void setDatesaveagent(String datesaveagent) {
        this.datesaveagent = datesaveagent;
    }

    public String getPasswordagent() {
        return passwordagent;
    }

    public void setPasswordagent(String passwordagent) {
        this.passwordagent = passwordagent;
    }

    public String getNiveauagent() {
        return niveauagent;
    }

    public void setNiveauagent(String niveauagent) {
        this.niveauagent = niveauagent;
    }



}
