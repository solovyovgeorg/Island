package config;

import entities.animals.EntityProperties;
import enums.EntityType;
import java.util.Map;

public interface Config {

    int getCountOfTicks();
    int getIslandWidth();
    int getIslandHeight();
    int getCountAnimalsOnStart();
    EntityProperties getEntityPropertiesByType(EntityType entityType);
    Map<EntityType,Integer> getEatRulesByType(EntityType entityType);

}
