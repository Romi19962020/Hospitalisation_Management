package memory.hospitalisation_management;

public class RDV {
   private String rdv,heure,date,mois,anee;

    public RDV(String rdv, String heure, String date, String mois, String anee) {
        this.rdv = rdv;
        this.heure = heure;
        this.date = date;
        this.mois = mois;
        this.anee = anee;
    }

    public String getRdv() {
        return rdv;
    }

    public void setRdv(String rdv) {
        this.rdv = rdv;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnee() {
        return anee;
    }

    public void setAnee(String anee) {
        this.anee = anee;
    }
}
