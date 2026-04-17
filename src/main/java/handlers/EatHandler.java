package handlers;

import entities.animals.Entity;
import entities.animals.Herbivore;
import entities.animals.Predator;
import enums.HungryLevel;
import island.Cell;
import statistic.StatisticService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class EatHandler implements EventHandler<Cell> {
private final StatisticService statisticService;

    public EatHandler(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public void process(Cell cell, ExecutorService executor) {
        List<Entity> predators = new ArrayList<>();
        List<Entity> herbivores = new ArrayList<>();
        /// Обработка уровня голода животного в начале фазы еды
        for (Entity animal : cell.getAnimals()) {
            HungryLevel downgradedLevel = downgradeHungryLevel(animal);
            if (downgradedLevel == HungryLevel.LETHAL) {
                statisticService.incHungryDeath();
                animal.die();
                continue;
            }
            animal.setHungryLevel(downgradedLevel);
        }
        /// Разделение живых животных на тип хищника и травоядное
        for (Entity animal : cell.getAnimals()) {
            if (!animal.isAlive()) {
                continue;
            }
            if (animal instanceof Predator) {
                predators.add(animal);
            }
            if (animal.isAlive() && animal instanceof Herbivore) {
                herbivores.add(animal);
            }
        }
        /** Хищник обходит травоядных и пробует кого нибудь из них съесть.
         * При успешном поедании травоядное умирает,
         * а хищник прекращает выбор жертвы если его уровень голода изменился.
         */
        for (Entity predator : predators) {
            for (Entity herbivore : herbivores) {
                predator.eat(herbivore);
                if (herbivore.isAlive()) {
                    statisticService.incEatFail();
                } else {
                    statisticService.incEatSuccess();
                    statisticService.incDied();
                }
                if (!herbivore.isAlive() && predator.getHungryLevel() == HungryLevel.NONE) {
                    break;
                }
            }
        }
        /// Теперь выжившие травоядные едят растения
        for (Entity herbivore: herbivores) {
            if (!herbivore.isAlive()) {
                continue;
            }
            for (Entity plant: cell.getPlants()) {
                if(plant.isAlive() && herbivore.getHungryLevel() != HungryLevel.NONE)
                    herbivore.eat(plant);
            }
        }
    }

    private HungryLevel downgradeHungryLevel(Entity animal) {
        HungryLevel current = animal.getHungryLevel();
        switch (current) {
            case NONE:
                current = HungryLevel.MEDIUM;
                break;
            case MEDIUM:
                current = HungryLevel.HIGH;
                break;
            case HIGH:
                current = HungryLevel.LETHAL;
                break;
        }
        return current;
    }
}
