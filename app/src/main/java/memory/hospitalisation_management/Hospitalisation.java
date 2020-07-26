package memory.hospitalisation_management;

import java.util.Date;

public class Hospitalisation {
    private Long id;
    private String hospType;
    private Date date;
    private String cost;

    public Hospitalisation(Long id, String hospType, Date date, String cost) {
        this.id = id;
        this.hospType = hospType;
        this.date = date;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospType() {
        return hospType;
    }

    public void setHospType(String hospType) {
        this.hospType = hospType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
