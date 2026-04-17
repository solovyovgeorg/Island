package config;

import enums.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityConfigValidator {
    public List<String> getErrorsOnValidate(EntityConfig entityConfig) {
        List<String> errors = new ArrayList<>();
        if (entityConfig == null) {
            errors.add("Переданная конфигурация entity_config не может быть null");
            return errors;
        }
        String prefix = entityConfig.getFileName();
        Map<EntityType, PropertiesDTO> propertiesMap = entityConfig.getPropertiesMap();
        if (propertiesMap == null || propertiesMap.isEmpty()) {
            errors.add(prefix + " Нет данных в конфигурации ");
            return errors;
        }
        for (Map.Entry<EntityType, PropertiesDTO> entry : propertiesMap.entrySet()) {
            if (entry.getValue() == null) {
                errors.add(prefix + " Нет конфигураций для типа " + entry.getKey().name());
                continue;
            }

        }
        return errors;
    }
}

