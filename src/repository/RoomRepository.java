package repository;

import conn.DatabaseConnection;
import model.Room;
import model.RoomType;
import repository.inter.IRoomRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository implements IRoomRepository {
    private final Connection connection;

    public RoomRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void saveRoom(Room room) {
        String sql = "INSERT INTO rooms (room_type, price, hotel_id) VALUES (?::room_type, ?, ?)";  // Cast room_type to enum
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getRoomType().name());  // Set room_type as string
            statement.setDouble(2, room.getPrice());
            statement.setInt(3, room.getHotelId());
            statement.executeUpdate();
            room.setRoomId(getLastRoomId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private int getLastRoomId() {
        String sql = "SELECT MAX(room_id) AS max_id FROM rooms";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Room getRoomById(int roomId) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                RoomType roomType = RoomType.valueOf(resultSet.getString("room_type"));
                double price = resultSet.getDouble("price");
                int hotelId = resultSet.getInt("hotel_id");
                return new Room(roomId, roomType, price, hotelId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                RoomType roomType = RoomType.valueOf(resultSet.getString("room_type"));
                double price = resultSet.getDouble("price");
                int hotelId = resultSet.getInt("hotel_id");
                rooms.add(new Room(roomId, roomType, price, hotelId));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rooms;
    }

    public boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM rooms WHERE room_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_type = ?::room_type, price = ? WHERE room_id = ?";  // Cast room_type to enum
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, room.getRoomType().toString());  // Set room_type as string
            statement.setDouble(2, room.getPrice());
            statement.setInt(3, room.getRoomId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
