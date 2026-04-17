package statistic;

import enums.EntityType;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticService {
    private final AtomicInteger born = new AtomicInteger();
    private final AtomicInteger died = new AtomicInteger();
    private final AtomicInteger moves = new AtomicInteger();
    private final AtomicInteger eatSuccess = new AtomicInteger();
    private final AtomicInteger eatFail = new AtomicInteger();
    private final AtomicInteger hungryDeath = new AtomicInteger();

    public void incBorn() {
        born.incrementAndGet();
    }

    public void incDied() {
        died.incrementAndGet();
    }

    public void incMoves() {
        moves.incrementAndGet();
    }

    public void incEatSuccess() {
        eatSuccess.incrementAndGet();
    }

    public void incEatFail() {
        eatFail.incrementAndGet();
    }

    public void incHungryDeath() {
        hungryDeath.incrementAndGet();
    }


    public Snapshot snapshotAndReset(Map<EntityType, Integer> animalCounts, int total) {
        return new IslandSnapshot(
                born.getAndSet(0),
                died.getAndSet(0),
                moves.getAndSet(0),
                eatSuccess.getAndSet(0),
                eatFail.getAndSet(0),
                hungryDeath.getAndSet(0),
                animalCounts,
                total
        );
    }
}