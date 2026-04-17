package config;

import enums.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Клас для проверки спарсенного конфига правил еды
 */
public class EatConfigValidator {
    private final int MIN_CHANCE = 0;
    private final int MAX_CHANCE = 100;

    /**
     * @param eatConfig - спарсенный конфиг правил еды
     * @Return возвращаем список ошибок неуспешности валидации, при успешной валидации список пуст
     */
    public List<String> getErrorsOnValidate(EatConfig eatConfig) {
        List<String> errors = new ArrayList<>();
        if (eatConfig == null) {
            errors.add("Переданная конфигурация не может быть null");
            return errors;
        }
        Map<EntityType, Map<EntityType, Integer>> eatMap = eatConfig.getEatMap();
        if (eatMap == null || eatMap.isEmpty()) {
            errors.add("Нет данных в конфигурации " + eatConfig.getFileName());
            return errors;
        }
        for (Map.Entry<EntityType,Map<EntityType,Integer>> entry : eatMap.entrySet()) {
            Map<EntityType, Integer> innerMap = entry.getValue();
            if (innerMap == null || innerMap.isEmpty()) {
                errors.add("В файле "+ eatConfig.getFileName() + " Нет конфигураций для типа животного " + entry.getKey().name());
                continue;
            }
            for (Map.Entry<EntityType,Integer> entryInner : innerMap.entrySet()) {
                if (entryInner.getValue() == null) {
                    errors.add("В файле "+ eatConfig.getFileName() +  "Значение для " + entryInner.getKey().name()
                            + " внутри " + entry.getKey() + " отсутствует ");
                    continue;
                }
                if (entryInner.getValue() < MIN_CHANCE) {
                    errors.add("В файле "+ eatConfig.getFileName() +  "Значение для " + entryInner.getKey().name()
                            + " внутри " + entry.getKey() + " не может быть меньше " + MIN_CHANCE);
                }
                if (entryInner.getValue() > MAX_CHANCE) {
                    errors.add("В файле "+ eatConfig.getFileName() +  "Значение для " + entryInner.getKey().name()
                            + " внутри " + entry.getKey() + " не может быть больше " + MAX_CHANCE);
                }
            }
        }
        return errors;
    }
}
