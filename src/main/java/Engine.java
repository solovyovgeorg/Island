
import enums.EntityType;
import generators.IslandGenerator;
import handlers.CellsHandler;
import island.Island;
import config.*;
import statistic.StatisticService;
import view.View;
import java.util.Map;
import java.util.concurrent.*;

public class Engine {
    private final Config config;
    private final View view;
    private volatile boolean isRunning = true;
    private int currentTick;
    private final int COUNT_OF_TICKS;
    private CellsHandler cellsHandler;
    private ExecutorService executor;
    private final StatisticService statisticService;
    private final int N_THREADS = Runtime.getRuntime().availableProcessors() * 2;

    public Engine(Config config, View view) {
        this.config = config;
        this.view = view;
        this.COUNT_OF_TICKS = config.getCountOfTicks();
        this.executor = Executors.newFixedThreadPool(N_THREADS);
        this.statisticService = new StatisticService();
    }

    public void startSimulation() {
        Island island = new IslandGenerator(config).generate();
        cellsHandler = new CellsHandler(island, config, statisticService);

        for (currentTick = 0; currentTick <= COUNT_OF_TICKS && isRunning; currentTick++) {
                Map<EntityType, Integer> countByTypes = island.getCountByTypes();
                int totalAnimals = island.getTotalAnimals();
                view.getSnapshotInfo(statisticService.snapshotAndReset(countByTypes, totalAnimals), currentTick);
                cellsHandler.process(island.getCells(), executor);
                if (totalAnimals == 0) {
                    view.setMessage("Остановка симуляции, все животные умерли.");
                    stopSimulation();
                    view.getMessages();
                }
        }

        executor.shutdown();

    }

    public void stopSimulation() {
        isRunning = false;
    }
}

