package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.EntityType;
import java.util.Map;

public class EatConfig implements PartConfig {
    @JsonProperty("eatMap")
    private Map<EntityType, Map<EntityType,Integer>> eatMap;

    public Map<EntityType, Map<EntityType, Integer>> getEatMap() {
        return eatMap;
    }

    @Override
    public String getFileName() {
        return "eat_config";
    }
}
