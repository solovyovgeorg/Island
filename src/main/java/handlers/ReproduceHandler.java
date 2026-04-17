package handlers;

import config.Config;
import entities.animals.Entity;
import entities.animals.EntityFactory;
import enums.EntityType;
import island.Cell;
import statistic.StatisticService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class ReproduceHandler implements EventHandler<Cell> {
    private final EntityFactory entityFactory;
    private final Config config;
    private final StatisticService statisticService;
    public ReproduceHandler(EntityFactory entityFactory, Config config, StatisticService statisticService) {
        this.entityFactory = entityFactory;
        this.config = config;
        this.statisticService = statisticService;
    }
    @Override
    public void process(Cell cell, ExecutorService executor) {
        Map<EntityType, List<Entity>> pairsByType = new HashMap<>();
        pairsByType = cell.getAnimals().stream().collect(Collectors.groupingBy(animal->animal.getType()));
        for (Map.Entry<EntityType, List<Entity>> entry: pairsByType.entrySet()) {
            int maxAnimal = config.getEntityPropertiesByType(entry.getKey()).getMaxCountOnCell();
           if (entry.getValue().size() < 2 || entry.getValue().size() >= maxAnimal) {
               continue;
           }
           for (Entity entity : entry.getValue()) {
               for (Entity other: entry.getValue()) {
                   if(entity != other && entity.isAlive() && other.isAlive() && entity.reproduce(other)) {
                          Entity baby = entityFactory.createEntityByType(entry.getKey());
                          baby.setFromPosition(cell.getPosition());
                          cell.getNewborned().add(baby);
                          statisticService.incBorn();
                          break;
                   }
               }
           }
        }
    }
}