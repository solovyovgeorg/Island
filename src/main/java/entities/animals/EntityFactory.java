package entities.animals;

import config.Config;
import enums.EntityType;

public class EntityFactory {
    private final Config config;

    public EntityFactory(Config config) {
        this.config = config;
    }

    public Entity createEntityByType(EntityType entityType) {
        switch (entityType) {
            case WOLF: {
                return new Wolf(config.getEntityPropertiesByType(EntityType.WOLF));
            }
            case DUCK: {
                return new Duck(config.getEntityPropertiesByType(EntityType.DUCK));
            }
            case SNAKE: {
                return new Snake(config.getEntityPropertiesByType(EntityType.SNAKE));
            }
            case FOX: {
                return new Fox(config.getEntityPropertiesByType(EntityType.FOX));
            }
            case BEAR: {
                return new Bear(config.getEntityPropertiesByType(EntityType.BEAR));
            }
            case EAGLE: {
                return new Eagle(config.getEntityPropertiesByType(EntityType.EAGLE));
            }
            case HORSE: {
                return new Horse(config.getEntityPropertiesByType(EntityType.HORSE));
            }
            case DEER: {
                return new Deer(config.getEntityPropertiesByType(EntityType.DEER));
            }
            case RABBIT: {
                return new Rabbit(config.getEntityPropertiesByType(EntityType.RABBIT));
            }
            case MOUSE: {
                return new Mouse(config.getEntityPropertiesByType(EntityType.MOUSE));
            }
            case GOAT: {
                return new Goat(config.getEntityPropertiesByType(EntityType.GOAT));
            }
            case SHEEP: {
                return new Sheep(config.getEntityPropertiesByType(EntityType.SHEEP));
            }
            case PIG: {
                return new Pig(config.getEntityPropertiesByType(EntityType.PIG));
            }
            case BUFFALO: {
                return new Buffalo(config.getEntityPropertiesByType(EntityType.BUFFALO));
            }
            case CATERPILLAR: {
                return new Caterpillar(config.getEntityPropertiesByType(EntityType.CATERPILLAR));
            }
            case PLANT: {
                return new Plant(config.getEntityPropertiesByType(EntityType.PLANT));
            }
            default: return new Plant(config.getEntityPropertiesByType(EntityType.PLANT));
        }
    }
}
