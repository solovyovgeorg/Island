package config;
import entities.animals.EntityProperties;
import enums.EntityType;
import java.util.Map;

public class GlobalConfig implements Config {
    private final IslandConfig islandConfig;
    private final EntityConfig entityConfig;
    private final EatConfig eatConfig;

    public GlobalConfig(IslandConfig islandConfig, EntityConfig entityConfig, EatConfig eatConfig) {
        this.islandConfig = islandConfig;
        this.entityConfig = entityConfig;
        this.eatConfig = eatConfig;
    }

    @Override
    public int getCountOfTicks() {
        return islandConfig.getCountTicks();
    }

    @Override
    public EntityProperties getEntityPropertiesByType(EntityType entityType) {
       PropertiesDTO properties = entityConfig.getPropertiesMap().get(entityType);
       Map<EntityType,Integer> eatRules = getEatRulesByType(entityType);
        EntityProperties entityProperties = new EntityProperties(properties, entityType,eatRules);
        return entityProperties;
    }

    @Override
    public Map<EntityType, Integer> getEatRulesByType(EntityType entityType) {
        return eatConfig.getEatMap().get(entityType);
    }


    @Override
    public int getIslandWidth() {
        return islandConfig.getWidth();
    }

    @Override
    public int getIslandHeight() {
        return islandConfig.getHeight();
    }

    @Override
    public int getCountAnimalsOnStart() {
        return islandConfig.getCountAnimalsOnStart();
    }
}
