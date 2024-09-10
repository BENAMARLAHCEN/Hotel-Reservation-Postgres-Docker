package repository.inter;

import model.Room;

import java.util.List;

public interface IRoomRepository {
    void saveRoom(Room room);

    Room getRoomById(int roomId);

    List<Room> getAllRooms();

    boolean deleteRoom(int roomId);

    boolean updateRoom(Room room);

}
