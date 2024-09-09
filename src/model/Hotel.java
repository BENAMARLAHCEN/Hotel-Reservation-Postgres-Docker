package model;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String location;

    public Hotel(int hotelId, String hotelName, String location) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
    }

    // Getters and Setters

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
