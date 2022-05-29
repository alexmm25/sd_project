package backend.model;
import java.util.Date;

// Singleton DP

public class CurrentStadium {

    private static final CurrentStadium CURRENT_STADIUM = new CurrentStadium();

    private String name;
    private Integer capacity;
    private Date inauguration;
    private Integer availableTickets;
    private String extraDetails;

    private CurrentStadium() {}

    public static CurrentStadium getSTADIUM() {
        return CURRENT_STADIUM;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Date getInauguration() {
        return inauguration;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setInauguration(Date inauguration) {
        this.inauguration = inauguration;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }
}
