package service;

public class PricingService {

    public double calculatePrice(double price, int discount) {
        return price - (price * discount / 100);
    }

    public double calculatePrice(double price, int discount, int tax) {
        return price - (price * discount / 100) + (price * tax / 100);
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge;
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge;
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge, int tip) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge + tip;
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge, int tip, int extraCharge) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge + tip + extraCharge;
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge, int tip, int extraCharge, int discount2) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge + tip + extraCharge - (price * discount2 / 100);
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge, int tip, int extraCharge, int discount2, int tax2) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge + tip + extraCharge - (price * discount2 / 100) + (price * tax2 / 100);
    }

    public double calculatePrice(double price, int discount, int tax, int serviceCharge, int deliveryCharge, int tip, int extraCharge, int discount2, int tax2, int serviceCharge2) {
        return price - (price * discount / 100) + (price * tax / 100) + serviceCharge + deliveryCharge + tip + extraCharge - (price * discount2 / 100) + (price * tax2 / 100) + serviceCharge2;
    }
}
