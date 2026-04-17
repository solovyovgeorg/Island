package island;

import entities.animals.Entity;
import enums.EntityType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island {
    private List<Cell> cells;
    private final int WIDTH;
    private final int HEIGHT;

    public Island(int islandWidth, int islandHeight) {
        this.WIDTH = islandWidth;
        this.HEIGHT = islandHeight;
        this.cells = new ArrayList<>();
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Map<EntityType, Integer> getCountByTypes(){
        Map<EntityType, Integer> map = new HashMap<>();
        for (Cell cell : cells) {
            for (Entity animal : cell.getAnimals()) {
                if (!animal.isAlive()) continue;
                map.merge(animal.getType(), 1, Integer::sum);
            }
            map.merge(EntityType.PLANT, cell.getPlants().size(), Integer::sum);
        }
        return map;
    }
    public int getTotalAnimals(){
        int total = 0;
        for (Cell cell: cells) {
            total+=cell.getAnimals().size();
        }
        return total;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
