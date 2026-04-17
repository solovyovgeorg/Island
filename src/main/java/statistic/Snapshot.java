package statistic;

import enums.EntityType;
import java.util.Map;

public interface Snapshot {
    int getBorn();
    int getMoves();
    int getEatSucess();
    int getEatFail();
    int getHungryDeath();

    Map<EntityType, Integer> getAnimalCounts();
    int getTotalAnimals();
}