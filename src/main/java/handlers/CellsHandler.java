package handlers;

import config.Config;
import entities.Position;
import entities.animals.Entity;
import entities.animals.EntityFactory;
import island.Cell;
import island.Island;
import statistic.StatisticService;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class CellsHandler {
    private final Island island;
    private final EventHandler<Cell> moveHandler;
    private final EventHandler<Cell> eatHandler;
    private final EventHandler<Cell> reproduceHandler;
    private final EventHandler<Cell> plantGrowHandler;
    private List<Callable<Void>> tasks;
    private final EntityFactory entityFactory;
    private final StatisticService statisticService;

    public CellsHandler(Island island, Config config, StatisticService statisticService) {
        this.island = island;
        this.entityFactory = new EntityFactory(config);
        this.statisticService = statisticService;
        moveHandler = new MoveHandler(config, statisticService);
        eatHandler = new EatHandler(statisticService);
        reproduceHandler = new ReproduceHandler(entityFactory, config, statisticService);
        plantGrowHandler = new PlantGrowHandler(entityFactory, config);
    }


    public void process(List<Cell> cells, ExecutorService executor) {
        phaseForCell(moveHandler, cells, executor);
        phaseForCell(eatHandler, cells, executor);
        phaseForCell(reproduceHandler, cells, executor);
        phaseForCell(plantGrowHandler, cells, executor);
        confirmChangesPhase();
    }

    private void phaseForCell(EventHandler<Cell> cellHandler, List<Cell> cells, ExecutorService executor) {
        tasks = new ArrayList<>();
        for (Cell cell : cells) {
            tasks.add(() -> {
                cellHandler.process(cell, executor);
                return null;
            });
        }
        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void confirmChangesPhase() {
        eraseDeadAnimals();
        addNewBorned();
        growingPlants();
        confirmMove();
    }

    private void addNewBorned() {
        for (Cell cell : island.getCells()) {
            cell.getAnimals().addAll(cell.getNewborned());
        }
    }

    private void confirmMove() {
        /// создаем временные списки животных согласно их позиций
        Map<Position, List<Entity>> tempListsByPosition = new HashMap<>();
        for (Cell cell : island.getCells()) {
            tempListsByPosition.put(cell.getPosition(), new ArrayList<>());
        }
        /// формируем временные списки из животных по их позиции
        for (Cell cell : island.getCells()) {
            for (Entity animal : cell.getAnimals()) {
                tempListsByPosition.get(animal.getToPosition()).add(animal);
            }
        }
        ///  очищаем старые списки, заполняем согласно позиций животных из временных списков
        for (Cell cell : island.getCells()) {
            cell.getNewborned().clear();
            cell.getAnimals().clear();
            cell.getAnimals().addAll(tempListsByPosition.get(cell.getPosition()));
        }
    }

    private void eraseDeadAnimals() {
        for (Cell cell : island.getCells()) {
            List<Entity> tempDeadAnimals = new ArrayList<>();
            for (Entity animal : cell.getAnimals()) {
                if (animal.isAlive()) {
                    continue;
                }
                tempDeadAnimals.add(animal);
            }
            cell.getAnimals().removeAll(tempDeadAnimals);
        }
    }

    private void growingPlants() {
        for (Cell cell : island.getCells()) {
            List<Entity> tempPlants = new ArrayList<>();
            for (Entity plant : cell.getPlants()) {
                if (plant.isAlive()) {
                    continue;
                }
                tempPlants.add(plant);
            }
            cell.getPlants().removeAll(tempPlants);
            cell.getPlants().addAll(cell.getNewPlants());
            cell.getNewPlants().clear();
        }
    }

}
