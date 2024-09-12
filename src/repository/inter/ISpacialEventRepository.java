package repository.inter;

import model.SpacialEvent;

import java.util.Optional;
import java.util.List;

public interface ISpacialEventRepository {
    Optional<List<SpacialEvent>> getSpacialEvents();
}
