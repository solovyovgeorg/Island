package entities.animals;

public class Mouse extends Entity implements Herbivore{
    public Mouse(EntityProperties entityPropertiesByType) {
        super(entityPropertiesByType);
    }

    @Override
    public double getFoodWeight() {
        return this.WEIGHT;
    }
}
