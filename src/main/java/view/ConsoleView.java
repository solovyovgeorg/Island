package view;

import enums.EntityType;
import statistic.Snapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsoleView implements View{
    private List<String> messages;

    public ConsoleView(){
        this.messages = new ArrayList<>();
    }
@Override
    public void getSnapshotInfo(Snapshot snapshot, int tick) {
        System.out.println("========== Статистика действий шага: " + tick + " ==========");
        System.out.println("За текущий шаг на острове:");
        System.out.println("Всего животных: " + snapshot.getTotalAnimals());
        System.out.println("Было рождено: " + snapshot.getBorn());
        System.out.println("Умерло в результате охоты: " + snapshot.getEatSucess()); // можно трактовать как killed
        System.out.println("Умерло от голода: " + snapshot.getHungryDeath());
        System.out.println("Передвижений совершено: " + snapshot.getMoves());
        System.out.println("Неудачная охота: " + snapshot.getEatFail());
        System.out.println("На острове находятся животные с типом:");
        printAnimals(snapshot.getAnimalCounts());
        System.out.println();
    }

    private void printAnimals(Map<EntityType, Integer> map) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<EntityType, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey().getNameRus())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" ед.; ");
        }

        System.out.println(sb);
    }


    @Override
    public void setMessage(String message) {
        messages.add(message);
    }

    @Override
    public void getMessages() {
        String COLOR_RED = "\u001B[31m";
        String COLOR_RESET = "\u001B[0m";
        for (String message: messages) {
            System.out.println(COLOR_RED + " " + message + COLOR_RESET);
        }
    }
}