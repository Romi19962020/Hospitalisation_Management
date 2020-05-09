package memory.hospitalisation_management;

import java.util.Date;

public class Consultation {
    private long id;
    private String motif;
    private String diagnostic;
    private Date date;

    public Consultation(long id, String motif, String diagnostic, Date date) {
        this.id = id;
        this.motif = motif;
        this.diagnostic = diagnostic;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
