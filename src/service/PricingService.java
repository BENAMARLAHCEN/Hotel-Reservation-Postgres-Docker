package service;

import model.Season;
import model.SpacialEvent;
import repository.SeasonRepository;
import repository.SpacialEventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PricingService {
    private final SeasonRepository seasonRepository;
    private final SpacialEventRepository spacialEventRepository;

    public PricingService() {
        this.seasonRepository = new SeasonRepository();
        this.spacialEventRepository = new SpacialEventRepository();
    }

    public double calculateTotalCost(double basePrice, LocalDate checkInDate, LocalDate checkOutDate) {
        double totalCost = 0;
        double dayPrice ;
        Optional<List<Season>> seasons = seasonRepository.getSeasons();
        Optional<List<SpacialEvent>> spacialEvents = spacialEventRepository.getSpacialEvents();
        while (checkInDate.isBefore(checkOutDate) || checkInDate.isEqual(checkOutDate)) {
            dayPrice = basePrice;
            if (seasons.isPresent()) {
                for (Season season : seasons.get()) {
                    if (checkInDate.isAfter(season.getStartDate()) && checkInDate.isBefore(season.getEndDate())) {
                        dayPrice *= season.getPriceMultiplier();
                    }
                }
            }
            if (spacialEvents.isPresent()) {
                for (SpacialEvent spacialEvent : spacialEvents.get()) {
                    if (checkInDate.equals(spacialEvent.getEventDate())) {
                        dayPrice *= spacialEvent.getPriceMultiplier();
                    }
                }
            }
            totalCost += dayPrice;
            checkInDate = checkInDate.plusDays(1);
        }
        return totalCost;
    }
}
