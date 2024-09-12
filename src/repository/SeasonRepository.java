package repository;


import conn.DatabaseConnection;
import model.Season;
import repository.inter.ISeasonRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeasonRepository implements ISeasonRepository {
    private final Connection connection;

    public SeasonRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<List<Season>> getSeasons() {
        String sql = "SELECT * FROM seasons";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Season> seasons = new ArrayList<>();
            while (resultSet.next()) {
                int seasonId = resultSet.getInt("season_id");
                String name = resultSet.getString("name");
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
                double priceMultiplier = resultSet.getDouble("price_multiplier");
                Season season = new Season(seasonId, name, startDate, endDate, priceMultiplier);
                seasons.add(season);
            }
            return Optional.of(seasons);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
