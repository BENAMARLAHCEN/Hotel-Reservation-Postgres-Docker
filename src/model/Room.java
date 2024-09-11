package model;

public class Room {
    private int roomId;
    private RoomType roomType;
    private double price;
    private int hotelId;

    public Room(int roomId, RoomType roomType, double price, int hotelId) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
        this.hotelId = hotelId;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
