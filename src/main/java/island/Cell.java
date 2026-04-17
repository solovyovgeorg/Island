package island;

import entities.Position;
import entities.animals.Entity;
import java.util.List;

public interface Cell {
    Position getPosition();
    List<Entity> getAnimals();
    List<Entity> getNewborned();
    List<Entity> getPlants();
    List<Entity> getNewPlants();
}
