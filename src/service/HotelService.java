package service;

import model.Hotel;
import repository.HotelRepository;

import java.util.List;

public class HotelService {
    private final HotelRepository hotelRepository = new HotelRepository();

    public void createHotel(String name, String location) {
        Hotel hotel = new Hotel(0,name, location);
        hotelRepository.saveHotel(hotel);
        System.out.println("Hotel created successfully!");
    }

    public Hotel getHotelById(int id) {
        return hotelRepository.getHotelById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.getAllHotels();
    }

    public boolean deleteHotel(int id) {
        return hotelRepository.deleteHotel(id);
    }

    public boolean updateHotel(int id, String name, String location) {
        Hotel hotel = new Hotel(id, name, location);
        return hotelRepository.updateHotel(hotel);
    }

    public Hotel getHotelByRoomId(int roomID) {
        return hotelRepository.getHotelByRoomId(roomID);
    }
}