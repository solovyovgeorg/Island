package generators;

import config.Config;
import entities.Position;
import entities.animals.Entity;
import entities.animals.EntityFactory;
import enums.EntityType;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnimalGenerator implements EngineGenerator <List<Entity>> {
    private final Config config;
    private EntityFactory entityFactory;
    private ThreadLocalRandom random;
    private final int COUNT_ANIMALS_ON_START;

    public AnimalGenerator(Config config) {
        this.config = config;
        this.COUNT_ANIMALS_ON_START = config.getCountAnimalsOnStart();
        this.entityFactory = new EntityFactory(config);
        this.random = ThreadLocalRandom.current();
    }

    private EntityType getRandomAnimalType() {
        int bound = EntityType.values().length;
        int randomIndex = EntityType.PLANT.ordinal();
        while (randomIndex == EntityType.PLANT.ordinal()) {
            randomIndex = ThreadLocalRandom.current().nextInt(0,bound);
        }
        return EntityType.values()[randomIndex];
    }

    private Entity getRandomAnimal() {
       Entity randomAnimal = entityFactory.createEntityByType(getRandomAnimalType());
       randomAnimal.setFromPosition(getRandomPosition());
        return randomAnimal;
    }

    private Position getRandomPosition() {
        int randomX = ThreadLocalRandom.current().nextInt(0,config.getIslandWidth());
        int randomY = ThreadLocalRandom.current().nextInt(0,config.getIslandHeight());
        return new Position(randomX,randomY);
    }

    @Override
    public List<Entity> generate() {
        List<Entity> animals = new ArrayList<>();
        for (int i = 0; i < COUNT_ANIMALS_ON_START; i++) {
            Entity entity = getRandomAnimal();
            animals.add(entity);
        }
        return animals;
    }
}
