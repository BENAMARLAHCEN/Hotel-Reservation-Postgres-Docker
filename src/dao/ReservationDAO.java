package dao;

import model.Customer;
import model.Hotel;
import model.PaymentStatus;
import model.Reservation;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;

    public ReservationDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (customer_id, room_id, hotel_id, check_in_date, check_out_date, payment_status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getCustomer().getCustomerId());
            statement.setInt(2, reservation.getRoom().getRoomId());
            statement.setInt(3, reservation.getHotel().getHotelId());
            statement.setObject(4, reservation.getCheckInDate());
            statement.setObject(5, reservation.getCheckOutDate());
            statement.setString(6, reservation.getPaymentStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

                Customer customer = new CustomerDAO().getCustomerById(customerId);
                Room room = new RoomDAO().getRoomById(roomId);
                Hotel hotel = new HotelDAO().getHotelById(hotelId);

                return new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus);
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

                Customer customer = new CustomerDAO().getCustomerById(customerId);
                Room room = new RoomDAO().getRoomById(roomId);
                Hotel hotel = new HotelDAO().getHotelById(hotelId);

                reservations.add(new Reservation(reservationId, customer, room, hotel, checkInDate, checkOutDate, paymentStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public boolean cancelReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    }
