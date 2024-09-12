package util;

import model.Reservation;

import java.time.LocalDate;

public class DateValidator {
    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        return checkIn != null && checkOut != null && checkOut.isAfter(checkIn) && checkIn.isAfter(LocalDate.now()) && checkOut.isAfter(LocalDate.now());
    }

    public static boolean isCheckInBeforeCheckOut(LocalDate checkInDate, LocalDate checkOutDate) {
        return checkInDate.isBefore(checkOutDate);
    }

    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now());
    }

    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }

    public static boolean isNotInReservationDateRange(LocalDate checkInDate, LocalDate checkOutDate, Reservation reservation) {
        return isValidDateRange(checkInDate,checkOutDate) && (checkInDate.isAfter(reservation.getCheckOutDate()) || checkOutDate.isBefore(reservation.getCheckInDate()));
    }

    public static boolean isNotInReservationsDateRange(LocalDate checkInDate, LocalDate checkOutDate, Reservation... reservations) {
        for (Reservation reservation : reservations) {
            if (!isNotInReservationDateRange(checkInDate, checkOutDate, reservation)) {
                return false;
            }
        }
        return true;
    }
}
