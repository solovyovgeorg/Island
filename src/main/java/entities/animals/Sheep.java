package entities.animals;

public class Sheep extends Entity implements Herbivore {
    public Sheep(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
