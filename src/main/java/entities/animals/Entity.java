package entities.animals;

import entities.Position;
import enums.EntityType;
import enums.HungryLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Entity {
    protected static long counter = 1;
    protected final long ID;
    protected final EntityType ANIMAL_TYPE;
    protected final double WEIGHT;
    protected final int SPEED;
    protected final double FOOD_CAPACITY;
    protected double levelCapacity;
    protected final Map<EntityType, Integer> EAT_RULES;
    protected HungryLevel hungryLevel = HungryLevel.NONE;
    protected boolean isAlive;
    protected Position fromPosition = new Position();
    protected Position toPosition = new Position();
    protected List<Position> positionsToMove = new ArrayList<>();
    protected ThreadLocalRandom random = ThreadLocalRandom.current();


    public Entity(EntityProperties entityProperties) {
        this.isAlive = true;
        this.ID = counter;
        counter++;
        this.ANIMAL_TYPE = entityProperties.getAnimalType();
        this.WEIGHT = entityProperties.getWeight();
        this.SPEED = entityProperties.getSpeed();
        this.FOOD_CAPACITY = entityProperties.getFoodRequired();
        this.EAT_RULES = entityProperties.getEatRules();
    }


    public synchronized void move() {
        if (positionsToMove == null || positionsToMove.isEmpty()) {
            return;
        }
        int boundRandom = positionsToMove.size();
        this.fromPosition = this.toPosition;
        this.toPosition = positionsToMove.get(random.nextInt(0, boundRandom));

    }

    public Position getFromPosition() {
        return fromPosition;
    }

    public void setFromPosition(Position position) {
        this.fromPosition = position;
    }

    public Position getToPosition() {
        return this.toPosition;
    }

    /// TODO
    public void eat(Entity entity) {
        int chance = 0;
        if (this.EAT_RULES.containsKey(entity.getType()) && this.hungryLevel != HungryLevel.NONE) {
            chance = EAT_RULES.get(entity.getType());
        }

        if (random.nextInt(0, 100) < chance) {
            entity.die();
        }
        levelCapacity += entity.getWeight();
            if (levelCapacity >= FOOD_CAPACITY) {
                switch (hungryLevel) {
                    case HIGH:
                        hungryLevel = HungryLevel.MEDIUM;
                        levelCapacity = 0.0;
                        break;
                    case MEDIUM:
                        hungryLevel = HungryLevel.NONE;
                        levelCapacity = 0.0;
                        break;
                    case NONE:
                        break;
            }
        }
    }

    public EntityType getType() {
        return this.ANIMAL_TYPE;
    }

    public boolean reproduce(Entity entity) {
        if (this.hungryLevel == HungryLevel.NONE && entity.hungryLevel == HungryLevel.NONE) {
            return true;
        }
        return false;
    }

    public void die() {
        this.setAlive(false);
    }

    public void setHungryLevel(HungryLevel hungryLevel) {
        this.hungryLevel = hungryLevel;
    }

    public List<Position> getPositionsToMove() {
        return positionsToMove;
    }

    public void setPositionsToMove(List<Position> positionToMove) {
        this.positionsToMove = positionToMove;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getSpeed() {
        return this.SPEED;
    }

    public long getId() {
        return ID;
    }
    public double getWeight(){
        return WEIGHT;
    }

    public HungryLevel getHungryLevel() {
        return this.hungryLevel;
    }
}
