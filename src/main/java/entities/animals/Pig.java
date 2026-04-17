package entities.animals;

public class Pig extends Entity implements Herbivore{
    public Pig(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
