package service;

import model.Room;
import model.RoomType;
import repository.RoomRepository;

import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository = new RoomRepository();

    public void createRoom(RoomType roomType, double price, int hotelId) {
        Room room = new Room(0, roomType, price, hotelId);
        roomRepository.saveRoom(room);
        System.out.println("Room created successfully!");
    }

    public Room getRoomById(int id) {
        return roomRepository.getRoomById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public boolean deleteRoom(int id) {
        return roomRepository.deleteRoom(id);
    }

    public boolean updateRoom(int id, RoomType roomType, double price, int hotelId) {
        Room room = new Room(id, roomType, price, hotelId);
        return roomRepository.updateRoom(room);
    }
}
