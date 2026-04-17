package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.EntityType;
import java.util.Map;

public class EntityConfig implements PartConfig {
    @JsonProperty("entity.properties")
    private Map<EntityType, PropertiesDTO> propertiesMap;

    @Override
    public String getFileName() {
        return "entity_config";
    }

    public Map<EntityType, PropertiesDTO> getPropertiesMap() {
        return propertiesMap;
    }
}
