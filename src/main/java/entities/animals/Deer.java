package entities.animals;

public class Deer extends Entity implements Herbivore {
    public Deer(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
