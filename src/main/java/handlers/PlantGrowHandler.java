package handlers;

import config.Config;
import entities.animals.Entity;
import entities.animals.EntityFactory;
import enums.EntityType;
import island.Cell;
import statistic.StatisticService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGrowHandler implements EventHandler<Cell> {
    private final EntityFactory entityFactory;
    private final Config config;
    private final ThreadLocalRandom random;

    public PlantGrowHandler(EntityFactory entityFactory, Config config) {
        this.entityFactory = entityFactory;
        this.config = config;
        this.random = ThreadLocalRandom.current();
    }

    @Override
    public void process(Cell cell, ExecutorService executor) {
           List<Entity> plants = growSomePlant(cell);
           cell.getNewPlants().addAll(plants);
    }
    private List<Entity> growSomePlant(Cell cell) {
        List<Entity> plants = new ArrayList<>();
        int maxOnCell = config.getEntityPropertiesByType(EntityType.PLANT).getMaxCountOnCell();
        int onCellNow = cell.getPlants().size();
        int maxBound = maxOnCell - onCellNow;
        for (int i = 0; i < random.nextInt(0,maxBound); i++) {
            plants.add(entityFactory.createEntityByType(EntityType.PLANT));
        }
        return plants;
    }
}
