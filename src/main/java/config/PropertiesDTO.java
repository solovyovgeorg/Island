package config;

import entities.animals.Properties;

public class PropertiesDTO implements Properties {
    private Double weight;
    private int maxCountOnCell;
    private int speed;
    private Double foodRequired;

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public int getMaxCountOnCell() {
        return maxCountOnCell;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Double getFoodRequired() {
        return foodRequired;
    }
}
