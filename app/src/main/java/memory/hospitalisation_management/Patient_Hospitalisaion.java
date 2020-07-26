package memory.hospitalisation_management;

import android.widget.Button;
import android.widget.CheckBox;

public class Patient_Hospitalisaion {
   private String nom;
   Button admission;
   Button dm;

    public Patient_Hospitalisaion(String nom, Button admission, Button dm) {
        this.nom = nom;
        this.admission = admission;
        this.dm = dm;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
class LitHospitalisation{
    private String lit;
    CheckBox checkBox;

    public LitHospitalisation(String lit, CheckBox checkBox) {
        this.lit = lit;
        this.checkBox = checkBox;
    }

    public String getLit() {
        return lit;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
