package repository;

import conn.*;
import model.*;
import repository.inter.IReservationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements IReservationRepository {
    private final Connection connection;

    public ReservationRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Reservation saveReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (customer_id, room_id, hotel_id, check_in_date, check_out_date, payment_status, total_cost) " +
                "VALUES (?, ?, ?, ?, ?, ?::payment_status, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getCustomer().getCustomerId());
            statement.setInt(2, reservation.getRoom().getRoomId());
            statement.setInt(3, reservation.getHotel().getHotelId());
            statement.setObject(4, reservation.getCheckInDate());
            statement.setObject(5, reservation.getCheckOutDate());
            statement.setString(6, reservation.getPaymentStatus().name());
            statement.setDouble(7, reservation.getTotalCost());
            statement.executeUpdate();
            return getReservationById(getLastReservationId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getLastReservationId() {
        String sql = "SELECT MAX(reservation_id) AS max_id FROM reservations";
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


    public Reservation getReservationById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                int roomId = resultSet.getInt("room_id");
                int hotelId = resultSet.getInt("hotel_id");
                LocalDate checkInDate = resultSet.getObject("check_in_date", LocalDate.class);
                LocalDate checkOutDate = resultSet.getObject("check_out_date", LocalDate.class);
                PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                double totalCost = resultSet.getDouble("total_cost");

                Customer customer = new CustomerRepository().getCustomerById(customerId);
                Room room = new RoomRepository().getRoomById(roomId);
                Hotel hotel = new HotelRepository().getHotelById(hotelId);

                return new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus, totalCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int customerId = resultSet.getInt("customer_id");
                int roomId = resultSet.getInt("room_id");
                int hotelId = resultSet.getInt("hotel_id");
                LocalDate checkInDate = resultSet.getObject("check_in_date", LocalDate.class);
                LocalDate checkOutDate = resultSet.getObject("check_out_date", LocalDate.class);
                PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                double totalCost = resultSet.getDouble("total_cost");

                Customer customer = new CustomerRepository().getCustomerById(customerId);
                Room room = new RoomRepository().getRoomById(roomId);
                Hotel hotel = new HotelRepository().getHotelById(hotelId);

                reservations.add(new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus, totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public boolean deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancelReservation(int id) {
        String sql = "UPDATE reservations SET payment_status = ?::payment_status WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, PaymentStatus.CANCELLED.name());
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET customer_id = ?, room_id = ?, hotel_id = ?, check_in_date = ?, check_out_date = ?, payment_status = ? WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getCustomer().getCustomerId());
            statement.setInt(2, reservation.getRoom().getRoomId());
            statement.setInt(3, reservation.getHotel().getHotelId());
            statement.setObject(4, reservation.getCheckInDate());
            statement.setObject(5, reservation.getCheckOutDate());
            statement.setString(6, reservation.getPaymentStatus().name());
            statement.setInt(7, reservation.getReservationId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Reservation> getReservationsByCustomerId(int customerId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int roomId = resultSet.getInt("room_id");
                int hotelId = resultSet.getInt("hotel_id");
                LocalDate checkInDate = resultSet.getObject("check_in_date", LocalDate.class);
                LocalDate checkOutDate = resultSet.getObject("check_out_date", LocalDate.class);
                PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                double totalCost = resultSet.getDouble("total_cost");

                Customer customer = new CustomerRepository().getCustomerById(customerId);
                Room room = new RoomRepository().getRoomById(roomId);
                Hotel hotel = new HotelRepository().getHotelById(hotelId);

                reservations.add(new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus, totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }


    public List<Reservation> getReservationByCustomerId(int id) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int roomId = resultSet.getInt("room_id");
                int hotelId = resultSet.getInt("hotel_id");
                LocalDate checkInDate = resultSet.getObject("check_in_date", LocalDate.class);
                LocalDate checkOutDate = resultSet.getObject("check_out_date", LocalDate.class);
                PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                double totalCost = resultSet.getDouble("total_cost");

                Customer customer = new CustomerRepository().getCustomerById(id);
                Room room = new RoomRepository().getRoomById(roomId);
                Hotel hotel = new HotelRepository().getHotelById(hotelId);

                reservations.add(new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus, totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getRoomInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = " SELECT * FROM reservations r " +
                "JOIN rooms ro ON r.room_id = ro.room_id " +
                "WHERE ro.room_type = ?::room_type AND r.check_in_date <= ? AND r.check_out_date >= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomType.name());
            statement.setObject(2, checkOutDate);
            statement.setObject(3, checkInDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                int customerId = resultSet.getInt("customer_id");
                int roomId = resultSet.getInt("room_id");
                int hotelId = resultSet.getInt("hotel_id");
                LocalDate checkInDate2 = resultSet.getObject("check_in_date", LocalDate.class);
                LocalDate checkOutDate2 = resultSet.getObject("check_out_date", LocalDate.class);
                PaymentStatus paymentStatus = PaymentStatus.valueOf(resultSet.getString("payment_status"));
                double totalCost = resultSet.getDouble("total_cost");

                Customer customer = new CustomerRepository().getCustomerById(customerId);
                Room room = new RoomRepository().getRoomById(roomId);
                Hotel hotel = new HotelRepository().getHotelById(hotelId);

                reservations.add(new Reservation(reservationId, customer, room, hotel, checkInDate2, checkOutDate2, paymentStatus, totalCost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Room> getAvailableRoomsInDateRange(LocalDate checkInDate, LocalDate checkOutDate, RoomType roomType, String location) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE room_type = ?::room_type AND room_id NOT IN " +
                "(SELECT room_id FROM reservations WHERE payment_status = 'PAID'::payment_status AND (check_in_date <= ? AND check_out_date >= ?)) AND hotel_id IN " +
                "(SELECT hotel_id FROM hotels WHERE location = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, roomType.toString());
            statement.setObject(2, checkOutDate);
            statement.setObject(3, checkInDate);
            statement.setString(4, location);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                double price = resultSet.getDouble("price");
                int hotelId = resultSet.getInt("hotel_id");
                rooms.add(new Room(roomId, roomType, price, hotelId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
