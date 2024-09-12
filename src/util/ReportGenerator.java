package util;

import model.PaymentStatus;
import model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {

    public static String generateOccupancyReport(List<Reservation> reservations) {
        long totalReservations = reservations.size();
        long totalCancelled = reservations.stream()
                .filter(r -> r.getPaymentStatus() == PaymentStatus.CANCELLED)
                .count();
        return "Total Reservations: " + totalReservations + "\nCancelled Reservations: " + totalCancelled + "\nOccupancy Rate: " + (totalReservations - totalCancelled) * 100 / totalReservations + "%";
    }

    public static String generateRevenueReport(List<Reservation> reservations) {
        double totalRevenue = reservations.stream()
                .filter(r -> r.getPaymentStatus() == PaymentStatus.PAID)
                .mapToDouble(Reservation::getTotalCost)
                .sum();
        return "Total Revenue: $" + totalRevenue;
    }

    public static String generateCancellationReport(List<Reservation> reservations) {
        List<Reservation> cancelledReservations = reservations.stream()
                .filter(r -> r.getPaymentStatus() == PaymentStatus.CANCELLED)
                .collect(Collectors.toList());
        StringBuilder report = new StringBuilder("Cancelled Reservations:\n");
        for (Reservation reservation : cancelledReservations) {
            report.append("Reservation ID: ").append(reservation.getReservationId())
                    .append(", Customer: ").append(reservation.getCustomer().getName())
                    .append(", Hotel: ").append(reservation.getHotel().getHotelName())
                    .append("\n");
        }
        return report.toString();
    }
}
