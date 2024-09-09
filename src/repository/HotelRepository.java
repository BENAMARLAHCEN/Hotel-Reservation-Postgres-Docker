package repository;

import dao.HotelDAO;
import model.Hotel;

import java.util.List;

public class HotelRepository {
    private final HotelDAO hotelDAO = new HotelDAO();

    public void saveHotel(Hotel hotel) {
        hotelDAO.addHotel(hotel);
    }

    public Hotel getHotelById(int id) {
        return hotelDAO.getHotelById(id);
    }

    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    public boolean deleteHotel(int id) {
        return hotelDAO.deleteHotel(id);
    }
}
