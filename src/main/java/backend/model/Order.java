package backend.model;

public class Order {

    private String clientAddress;
    private String extraDetails;

    public Order(String extraDetails, String clientAddress) {
        this.clientAddress = clientAddress;
        this.extraDetails = extraDetails;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

}
