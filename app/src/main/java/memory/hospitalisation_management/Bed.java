package memory.hospitalisation_management;

public class Bed {
    private long id;
    private String nom;
    private String type;
    private boolean available;

    public Bed(long id,String nom, String type, boolean available) {
        this.id = id;
        this.nom=nom;
        this.type = type;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
