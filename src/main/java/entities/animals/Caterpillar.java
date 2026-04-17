package entities.animals;

public class Caterpillar extends Entity implements Herbivore {
    public Caterpillar(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
