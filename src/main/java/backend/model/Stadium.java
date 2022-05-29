package backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Stadium {

    @Id
    private Long stadiumID;

    @Column
    private String name;

    @Column
    private Integer capacity;

    @Column
    private Date inauguration;

    @Column
    private Integer availableTickets;

    @Column
    private String extraDetails;

    public Stadium(String name, Integer capacity, Date inauguration, Integer availableTickets, String extraDetails) {
        this.name = name;
        this.capacity = capacity;
        this.inauguration = inauguration;
        this.availableTickets = availableTickets;
        this.extraDetails = extraDetails;
    }

    public Stadium() {
    }

    public Long getStadiumID() {
        return stadiumID;
    }

    public void setStadiumID(Long stadiumID) {
        this.stadiumID = stadiumID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getInauguration() {
        return inauguration;
    }

    public void setInauguration(Date inauguration) {
        this.inauguration = inauguration;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }
}
