package dao;

import model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private Connection connection;

    public HotelDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotels (hotel_name, location) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hotel.getHotelName());
            statement.setString(2, hotel.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Hotel getHotelById(int hotelId) {
        String sql = "SELECT * FROM hotels WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String hotelName = resultSet.getString("hotel_name");
                String location = resultSet.getString("location");
                return new Hotel(hotelId, hotelName, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int hotelId = resultSet.getInt("hotel_id");
                String hotelName = resultSet.getString("hotel_name");
                String location = resultSet.getString("location");
                hotels.add(new Hotel(hotelId, hotelName, location));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public boolean deleteHotel(int id) {
        String sql = "DELETE FROM hotels WHERE hotel_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    }

