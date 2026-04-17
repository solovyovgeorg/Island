package generators;

import config.Config;
import entities.Position;
import entities.animals.Entity;
import island.Cell;
import island.Island;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IslandGenerator implements EngineGenerator<Island> {
    private Island island;
    private final Config config;
    private final AnimalGenerator animalGenerator;
    private final CellGenerator cellGenerator;
    private final PlantGenerator plantGenerator;

    public IslandGenerator(Config config) {
        this.config = config;
        this.animalGenerator = new AnimalGenerator(config);
        this.cellGenerator = new CellGenerator(config);
        this.plantGenerator = new PlantGenerator(config);
    }


    @Override
    public Island generate() {
        List<Entity> animals = animalGenerator.generate();
        List<Entity> plants = plantGenerator.generate();
        List<Cell> cells = cellGenerator.generate();
        integrateAnimalsToCells(cells, animals);
        integratePlantsToCell(cells,plants);
        island = new Island(config.getIslandWidth(), config.getIslandHeight());
        island.setCells(cells);
        return island;
    }

    private void integrateAnimalsToCells(List<Cell> cells, List<Entity> entities) {
        Map<Position, List<Entity>> map = entities.stream().collect(Collectors.groupingBy(animal -> animal.getToPosition()));
        for (Cell cell : cells) {
            cell.getAnimals().addAll(map.getOrDefault(cell.getPosition(),new ArrayList<>()));
        }
    }
    private void integratePlantsToCell(List<Cell> cells, List<Entity> plants) {
        Map<Position, List<Entity>> map = plants.stream().collect(Collectors.groupingBy(plant->plant.getToPosition()));
        for (Cell cell:cells) {
            cell.getPlants().addAll(map.getOrDefault(cell.getPosition(),new ArrayList<>()));
        }
    }
}
