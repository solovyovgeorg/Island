package generators;

import config.Config;
import entities.Position;
import entities.animals.Entity;
import entities.animals.EntityFactory;
import enums.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlantGenerator implements EngineGenerator<List<Entity>>{
    private final Config config;
    private EntityFactory entityFactory;
    private ThreadLocalRandom random;

    public PlantGenerator(Config config) {
        this.config = config;
        this.entityFactory = new EntityFactory(config);
        this.random = ThreadLocalRandom.current();
    }


    private Entity getPlant() {
        Entity plant = entityFactory.createEntityByType(EntityType.PLANT);
        plant.setFromPosition(getRandomPosition());
        return plant;
    }

    private Position getRandomPosition() {
        int randomX = ThreadLocalRandom.current().nextInt(0,config.getIslandWidth());
        int randomY = ThreadLocalRandom.current().nextInt(0,config.getIslandHeight());
        return new Position(randomX,randomY);
    }

    @Override
    public List<Entity> generate() {
        int maxOnCell = config.getEntityPropertiesByType(EntityType.PLANT).getMaxCountOnCell();
        int cells = config.getIslandWidth() * config.getIslandHeight();
        int maxBound = maxOnCell*cells;
        int randomSize = random.nextInt(0,maxBound);
        List<Entity> plants = new ArrayList<>();
        for (int i = 0; i < randomSize; i++) {
            plants.add(getPlant());
        }
        return plants;
    }
}
