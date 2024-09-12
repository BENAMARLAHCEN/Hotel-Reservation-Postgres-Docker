package repository;

import conn.DatabaseConnection;
import model.SpacialEvent;
import repository.inter.ISpacialEventRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpacialEventRepository implements ISpacialEventRepository {
    private final Connection connection;

    public SpacialEventRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<List<SpacialEvent>> getSpacialEvents() {
        String sql = "SELECT * FROM special_events";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<SpacialEvent> spacialEvents = new ArrayList<>();
            while (resultSet.next()) {
                int spacialEventId = resultSet.getInt("event_id");
                String name = resultSet.getString("name");
                LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
                double priceMultiplier = resultSet.getDouble("price_multiplier");
                SpacialEvent spacialEvent = new SpacialEvent(spacialEventId, name, eventDate, priceMultiplier);
                spacialEvents.add(spacialEvent);
            }
            return Optional.of(spacialEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
