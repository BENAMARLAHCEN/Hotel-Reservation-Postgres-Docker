package repository.inter;

import model.Season;

import java.util.List;
import java.util.Optional;

public interface ISeasonRepository {
    Optional<List<Season>> getSeasons();
}
