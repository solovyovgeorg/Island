package config;

import config.enums.ConfigType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigGenerator {
    private ConfigLoader configLoader;
    private EatConfig eatConfig;
    private EntityConfig entityConfig;
    private IslandConfig islandConfig;
    private List<String> errors;
    private final String PATH_ISLAND_CONFIG = "island_config.yaml";
    private final String PATH_EAT_CONFIG = "eat_config.yaml";
    private final String PATH_ANIMAL_CONFIG = "entity_config.yaml";

    public ConfigGenerator() {
        errors = new ArrayList<>();
    }

    public void loadAndCheckConfig() {
        EatConfigValidator eatConfigValidator = new EatConfigValidator();
        IslandConfigValidator islandConfigValidator = new IslandConfigValidator();
        EntityConfigValidator entityConfigValidator = new EntityConfigValidator();
        islandConfig = (IslandConfig) loadPart(PATH_ISLAND_CONFIG, ConfigType.ISLAND_CONFIG);
        entityConfig = (EntityConfig) loadPart(PATH_ANIMAL_CONFIG, ConfigType.ENTITY_CONFIG);
        eatConfig = (EatConfig) loadPart(PATH_EAT_CONFIG, ConfigType.EAT_CONFIG);
        errors.addAll(islandConfigValidator.getErrorsOnValidate(islandConfig));
        errors.addAll(eatConfigValidator.getErrorsOnValidate(eatConfig));
        errors.addAll(entityConfigValidator.getErrorsOnValidate(entityConfig));
    }
    public List<String> getErrors(){
        return errors;
    }

    private PartConfig loadPart(String fileName, ConfigType configType) {
        PartConfig config = null;
        switch (configType) {
            case ISLAND_CONFIG:
                config = new IslandConfig();
                break;
            case ENTITY_CONFIG:
                config = new EntityConfig();
                break;
            case EAT_CONFIG:
                config = new EatConfig();
                break;
        }
        configLoader = new ConfigLoader<>(config.getClass());
        try {
            configLoader.loadFromFile(fileName);
        } catch (IOException | IllegalArgumentException e) {
            errors.add(" Не удалось загрузить " + fileName);
        }
        return (PartConfig) configLoader.getConfig();
    }
    public Config getConfig () {
        GlobalConfig globalConfig = new GlobalConfig(islandConfig,entityConfig,eatConfig);
        return globalConfig;
    }
}
