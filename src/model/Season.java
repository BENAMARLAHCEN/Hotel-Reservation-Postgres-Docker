package model;

import java.time.LocalDate;

public class Season {
    private int seasonId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double priceMultiplier;

    public Season(int seasonId, String name, LocalDate startDate, LocalDate endDate, double priceMultiplier) {
        this.seasonId = seasonId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceMultiplier = priceMultiplier;
    }

    // Getters and Setters


    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
}
