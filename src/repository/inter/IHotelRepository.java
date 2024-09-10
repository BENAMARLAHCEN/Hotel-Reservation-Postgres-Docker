package repository.inter;

import model.Hotel;

import java.util.List;

public interface IHotelRepository {

    void saveHotel(Hotel hotel);

    Hotel getHotelById(int hotelId);

    List<Hotel> getAllHotels();

    boolean deleteHotel(int hotelId);

    boolean updateHotel(Hotel hotel);
}
