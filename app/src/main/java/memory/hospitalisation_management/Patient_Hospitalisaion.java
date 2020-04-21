package memory.hospitalisation_management;

import android.widget.Button;

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
