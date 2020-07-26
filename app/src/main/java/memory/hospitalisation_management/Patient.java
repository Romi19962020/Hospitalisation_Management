package memory.hospitalisation_management;

import java.util.List;

public class Patient  {
    private long id;
    private String firstName,lastName;
    private int sexe;
    private String typehosp;
    private List<Consultation> consultations;
    private List<Hospitalisation> hospitalisations;


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

    public List<Hospitalisation> getHospitalisations() {
        return hospitalisations;
    }

    public void setHospitalisations(List<Hospitalisation> hospitalisations) {
        this.hospitalisations = hospitalisations;
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

    public String getTypehosp() {
        return typehosp;
    }

    public void setTypehosp(String typehosp) {
        this.typehosp = typehosp;
    }
}
