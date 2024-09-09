import model.Room;
import model.RoomType;
import repository.RoomRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Lancement de l'application
        // Menu de navigation pour gérer les réservations et les chambres



        //test add room
        Room room = new Room(5, RoomType.SINGLE, 100);
        RoomRepository roomRepository = new RoomRepository();
        roomRepository.saveRoom(room);
        //  roomRepository.getAllRooms().forEach(room1 -> System.out.println(room1));
        //test get room by id
        System.out.println(roomRepository.getRoomById(1));
        //test get all rooms
        roomRepository.getAllRooms().forEach(room1 -> System.out.println(room1.getRoomType()+" "+room1.getPrice()));
        //test update room
        Room room1 = new Room(5, RoomType.DOUBLE, 200);
        roomRepository.updateRoom(room1);
        roomRepository.getAllRooms().forEach(room2 -> System.out.println(room2.getRoomType()+" "+room2.getPrice()));
        //test delete room
        System.out.println(roomRepository.deleteRoom(5));



    }
}