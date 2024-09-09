package util;

import java.time.LocalDate;

public class DateValidator {
    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        return checkIn != null && checkOut != null && checkOut.isAfter(checkIn);
    }

    // Validate that the check-in date is before the check-out date
    public static boolean isCheckInBeforeCheckOut(LocalDate checkInDate, LocalDate checkOutDate) {
        return checkInDate.isBefore(checkOutDate);
    }

    // Validate that the date is not in the past
    public static boolean isFutureDate(LocalDate date) {
        return date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now());
    }

    // Validate that the date is not null
    public static boolean isValidDate(LocalDate date) {
        return date != null;
    }
}
