package statistic;

import enums.EntityType;
import java.util.Map;

public class IslandSnapshot implements Snapshot {

    private final int BORN;
    private final int DIED;
    private final int MOVES;
    private final int EAT_SUCCESS;
    private final int EAT_FAIL;
    private final int HUNGRY_DEATH;

    private final Map<EntityType, Integer> animalCounts;
    private final int totalAnimals;

    public IslandSnapshot(
            int born,
            int died,
            int moves,
            int eatSuccess,
            int eatFail,
            int hungryDeath,
            Map<EntityType, Integer> animalCounts,
            int totalAnimals
    ) {
        this.BORN = born;
        this.DIED = died;
        this.MOVES = moves;
        this.EAT_SUCCESS = eatSuccess;
        this.EAT_FAIL = eatFail;
        this.HUNGRY_DEATH = hungryDeath;
        this.animalCounts = animalCounts;
        this.totalAnimals = totalAnimals;
    }

    @Override
    public int getBorn() { return BORN; }

    @Override
    public int getMoves() { return MOVES; }

    @Override
    public int getEatSucess() { return EAT_SUCCESS; }

    @Override
    public int getEatFail() { return EAT_FAIL; }

    @Override
    public int getHungryDeath() { return HUNGRY_DEATH; }

    @Override
    public Map<EntityType, Integer> getAnimalCounts() {
        return animalCounts;
    }

    @Override
    public int getTotalAnimals() {
        return totalAnimals;
    }
}