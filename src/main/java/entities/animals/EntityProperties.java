package entities.animals;

import enums.EntityType;
import java.util.Map;

public class EntityProperties {

        private final Double WEIGHT;
        private final Integer MAX_COUNT_ON_CELL;
        private final int SPEED;
        private final Double FOOD_REQUIRED;
        private final EntityType ANIMAL_TYPE;
        private final Map<EntityType,Integer> EAT_RULES;

    public EntityProperties(Properties properties, EntityType entityType, Map<EntityType, Integer> eatRules) {
        this.WEIGHT = properties.getWeight();
        this.MAX_COUNT_ON_CELL = properties.getMaxCountOnCell();
        this.SPEED = properties.getSpeed();
        this.FOOD_REQUIRED = properties.getFoodRequired();
        this.ANIMAL_TYPE = entityType;
        this.EAT_RULES = eatRules;
    }

    public EntityType getAnimalType() {
        return ANIMAL_TYPE;
    }

    public Map<EntityType, Integer> getEatRules() {
        return EAT_RULES;
    }

    public Double getWeight() {return this.WEIGHT;}

        public int getMaxCountOnCell() {
            return this.MAX_COUNT_ON_CELL;
        }

        public int getSpeed() {
            return this.SPEED;
        }

        public Double getFoodRequired() {
            return this.FOOD_REQUIRED;
        }
    }
