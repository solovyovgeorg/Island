package entities.animals;

public class Rabbit extends Entity implements Herbivore{
    public Rabbit(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
