package handlers;

import config.Config;
import entities.Position;
import entities.animals.Entity;
import generators.NeighborCalculator;
import island.Cell;
import statistic.StatisticService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MoveHandler implements EventHandler<Cell> {
    private final int WIDTH;
    private final int HEIGHT;
    private final NeighborCalculator neighborCalculator;
    private final StatisticService statisticService;

    public MoveHandler(Config config, StatisticService statisticService) {
        this.WIDTH = config.getIslandWidth();
        this.HEIGHT = config.getIslandHeight();
        this.neighborCalculator = new NeighborCalculator(WIDTH,HEIGHT);
        this.statisticService = statisticService;
    }

    @Override
    public void process(Cell cell, ExecutorService executor) {
        List<Entity> tempAnimals = new ArrayList<>();
       for (Entity animal : cell.getAnimals()) {
           if (animal.isAlive()) {
            Entity temp = animal;
             calculatePositionsToMove(temp);
             tempAnimals.add(temp);
             if (!animal.getFromPosition().equals(temp.getToPosition())) {
                 statisticService.incMoves();
             }
           }
       }
       cell.getAnimals().clear();
       cell.getAnimals().addAll(tempAnimals);
    }
    private void calculatePositionsToMove(Entity animal) {
        animal.setFromPosition(animal.getToPosition());
        Position beginPosition = animal.getFromPosition();
        if (animal.getSpeed() != 0) {
            for (int i = 0; i < animal.getSpeed(); i++) {
                Position nextPosition = animal.getToPosition();
                animal.setPositionsToMove(neighborCalculator.getPositionNeighbhors(nextPosition));
                animal.move();
            }
        }
        animal.setFromPosition(beginPosition);
    }
}
