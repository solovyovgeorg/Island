package config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IslandConfig implements PartConfig {
    @JsonProperty("island.width")
    private int width;
    @JsonProperty("island.height")
    private int height;
    @JsonProperty("island.count_animals_on_start")
    private int countAnimalsOnStart;
    @JsonProperty("island.count_ticks")
    private int countTicks;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCountAnimalsOnStart() {
        return countAnimalsOnStart;
    }

    public int getCountTicks() {
        return countTicks;
    }

    @Override
    public String getFileName() {
        return "island_config";
    }
}
