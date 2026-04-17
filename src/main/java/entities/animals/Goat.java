package entities.animals;

public class Goat extends Entity implements Herbivore{
    public Goat(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
