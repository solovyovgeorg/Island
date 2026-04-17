package config;

import java.util.ArrayList;
import java.util.List;

public class IslandConfigValidator {
    private final int MIN_HEIGHT = 1;
    private final int MAX_HEIGHT = 100;
    private final int MIN_WIDTH = 1;
    private final int MAX_WIDTH = 100;
    private final int MIN_COUNT_ANIMALS = 1;
    private final int MAX_COUNT_ANIMALS = 1000;
    private final int MIN_COUNT_OF_TICKS = 1;
    private final int MAX_COUNT_OF_TICKS = 2000;

    public List<String> getErrorsOnValidate(IslandConfig islandConfig) {
        List<String> errors = new ArrayList<>();
        if (islandConfig == null) {
            errors.add("Переданная конфигурация island_config не может быть null");
            return errors;
        }
        String prefix = islandConfig.getFileName();
        if (islandConfig.getHeight() < MIN_HEIGHT || islandConfig.getHeight() > MAX_HEIGHT) {
            errors.add(prefix + ": Параметр island.height должен быть в диапазоне от "
                    + MIN_HEIGHT + " до " + MAX_HEIGHT);
        }
        if (islandConfig.getWidth() < MIN_WIDTH || islandConfig.getWidth() > MAX_WIDTH) {
            errors.add(prefix + ": Параметр island.width должен быть в диапазоне от "
                    + MIN_WIDTH + " до " + MAX_WIDTH);
        }
        if (islandConfig.getCountAnimalsOnStart() < MIN_COUNT_ANIMALS || islandConfig.getCountAnimalsOnStart() > MAX_COUNT_ANIMALS) {
            errors.add(prefix + ": Параметр island.count_animals_on_start должен быть в диапазоне от "
                    + MIN_COUNT_ANIMALS + " до " + MAX_COUNT_ANIMALS);
        }
        if (islandConfig.getCountTicks() < MIN_COUNT_OF_TICKS || islandConfig.getCountTicks() > MAX_COUNT_OF_TICKS) {
            errors.add(prefix + ": Параметр island.count_animals_on_start должен быть в диапазоне от "
                    + MIN_COUNT_OF_TICKS + " до " + MAX_COUNT_OF_TICKS);
        }
        return errors;
    }
}
