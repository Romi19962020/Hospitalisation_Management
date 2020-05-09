package memory.hospitalisation_management;

import java.util.List;

public class Patient  {
    private long id;
    private String firstName,lastName;
    private int sexe;
    private List<Consultation> consultations;

    public Patient(long id, String firstName, String lastName, int sexe) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }
}
