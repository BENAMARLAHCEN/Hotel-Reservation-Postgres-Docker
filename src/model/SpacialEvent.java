package model;

import java.time.LocalDate;

public class SpacialEvent {
    private int spacialEventId;
    private String name;
    private LocalDate eventDate;
    private double priceMultiplier;

    public SpacialEvent(int spacialEventId, String name, LocalDate eventDate, double priceMultiplier) {
        this.spacialEventId = spacialEventId;
        this.name = name;
        this.eventDate = eventDate;
        this.priceMultiplier = priceMultiplier;
    }

    // Getters and Setters

    public int getSpacialEventId() {
        return spacialEventId;
    }

    public void setSpacialEventId(int spacialEventId) {
        this.spacialEventId = spacialEventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}
