package island;

import entities.Position;
import entities.animals.Entity;
import java.util.ArrayList;
import java.util.List;

public class SimpleCell implements Cell {
    private final Position position;
    private final List<Entity> entities;
    private final List<Entity> newborned;
    private final List<Entity> plants;
    private final List<Entity> newPlants;


    public SimpleCell(int x, int y) {
        this.position = new Position(x, y);
        this.entities = new ArrayList<>();
        this.newborned = new ArrayList<>();
        this.plants = new ArrayList<>();
        this.newPlants = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }


    public List<Entity> getAnimals() {
        return entities;
    }


    @Override
    public List<Entity> getNewborned() {
        return newborned;
    }

    @Override
    public List<Entity> getPlants() {
        return this.plants;
    }

    @Override
    public List<Entity> getNewPlants() {
        return this.newPlants;
    }

}
