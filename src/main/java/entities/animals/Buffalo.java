package entities.animals;

public class Buffalo extends Entity implements Herbivore {
    public Buffalo(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
