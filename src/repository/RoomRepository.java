package repository;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class RoomRepository {
    private final RoomDAO roomDAO = new RoomDAO();

    public void saveRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public boolean deleteRoom(int id) {
        return roomDAO.deleteRoom(id);
    }

    public boolean updateRoom(Room room) {
        return roomDAO.updateRoom(room);
    }
}
