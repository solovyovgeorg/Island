package generators;

import config.Config;
import island.Cell;
import island.SimpleCell;
import java.util.ArrayList;
import java.util.List;

public class CellGenerator implements EngineGenerator<List<Cell>>{
    private Config config;
    private List<Cell> cells;
    private final int WIDTH;
    private final int HEIGHT;
    private NeighborCalculator neighborCalculator;

    public CellGenerator(Config config) {
        this.config = config;
        this.WIDTH = config.getIslandWidth();
        this.HEIGHT = config.getIslandHeight();
    }
    @Override
    public List<Cell> generate() {
        initCells();
        return cells;
    }
    private void initCells(){
        cells = new ArrayList<>();
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y< HEIGHT; y++) {
                Cell cell = new SimpleCell(x,y);
                cells.add(cell);
            }
        }
    }

}
