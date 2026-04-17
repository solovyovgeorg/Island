package entities.animals;

public class Duck extends Entity implements Herbivore,Predator {

    public Duck(EntityProperties entityProperties) {
        super(entityProperties);
    }

    @Override
    public double getFoodWeight() {
        return 0;
    }
}
