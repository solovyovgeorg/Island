package entities.animals;

public class Plant extends Entity {
    public Plant(EntityProperties entityProperties) {
        super(entityProperties);
    }
    @Override
  public boolean reproduce(Entity entity) {
        return true;
    }
    @Override
    public void eat(Entity entity) {
    }
}
