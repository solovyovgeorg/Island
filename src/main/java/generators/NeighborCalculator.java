package generators;

import entities.Position;
import java.util.ArrayList;
import java.util.List;

public class NeighborCalculator {
    public final int WIDTH;
    public final int HEIGHT;

    public NeighborCalculator(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public List<Position> getPositionNeighbhors(Position position) {
        List<Position> neighbors = new ArrayList<>();

        if (position.getX() > 0) {
            Position left = new Position(position.getX() - 1, position.getY());
            neighbors.add(left);
        }
        if (position.getX() + 1 < WIDTH) {
            Position right = new Position(position.getX() + 1, position.getY());
            neighbors.add(right);
        }
        if (position.getY() > 0) {
            Position top = new Position(position.getX(), position.getY() - 1);
            neighbors.add(top);
        }
        if (position.getY() + 1 < HEIGHT) {
            Position bottom = new Position(position.getX(), position.getY() + 1);
            neighbors.add(bottom);
        }
        return neighbors;
    }
}
