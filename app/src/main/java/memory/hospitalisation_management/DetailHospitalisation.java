package memory.hospitalisation_management;

public class DetailHospitalisation {
    private String lit;
    private String nom;
    public DetailHospitalisation(String lit,String nom){
        this.lit=lit;
        this.nom=nom;
    }

    public String getLit() {
        return lit;
    }

    public String getNom() {
        return nom;
    }

    public void setLit(String lit) {
        this.lit = lit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
}

