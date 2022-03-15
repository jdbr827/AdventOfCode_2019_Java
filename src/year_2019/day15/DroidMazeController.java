package year_2019.day15;

import year_2019.CartesianPoint;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

import static year_2019.day15.DroidMazeOutputInstruction.*;

public class DroidMazeController {
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model;
    MapDistanceTracker currentTracker = new TankFindingDistanceTracker(Color.BLACK);



    public DroidMazeController(long[] brainTape) {
        model = new DroidMazeModel(this, brainTape);
        currentTracker.resetOrigin();
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    // TODO! Memory Leak?

    public int findOxygenTank() throws InterruptedException {
        currentTracker = new TankFindingDistanceTracker(Color.BLACK);
        resetOrigin();
        model.unifiedDFS();
        return currentTracker.getDistanceAtCurrentLocation();
    }



    public void paintPointInView(DroidMazeOutputInstruction outputInstruction, CartesianPoint desiredPoint) {
        view.paintPoint(desiredPoint, outputInstruction.getPaintColor());
        view.repaint();
    }

    public void moveDroid(CardinalDirection direction) {
        model.moveDroid(direction);
        moveDroidInView();
    }

    public void moveDroidInView() {
        view.setDroidLocation(model.getDroidLocation());
        updateStackInView();
        view.repaint();
    }


    private void updateStackInView() {
        view.setDirectionStack(model.getDirectionStack().stream().map(CardinalDirection::getShortName).collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString)));
    }

    /**
     * Searches the entire maze space and records distances from the starting point
     * @throws InterruptedException
     */
    public void computeAllDistancesFromPoint() throws InterruptedException {
        currentTracker = new AllPointsDistanceTracker(Color.BLUE);
        resetOrigin();
        model.unifiedDFS();
    }

    public void resetOrigin() {
        view.resetOrigin(model.getDroidLocation());
        model.resetOrigin();
        currentTracker.resetOrigin();
        updateStackInView();
        view.repaint();
    }

    public void setCurrentTrackerToTank() {
        currentTracker = new TankFindingDistanceTracker(Color.BLACK);
        resetOrigin();
    }

    public void setCurrentTrackerToAllPoints() {
        currentTracker = new AllPointsDistanceTracker(Color.BLUE);
        resetOrigin();
    }

    abstract class MapDistanceTracker implements DistanceTracker {
        protected final Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point
        Color viewColor;

        public MapDistanceTracker(Color color) {
            setViewColor(color);
        }

        public void setViewColor(Color color) {
            viewColor = color;
        }
        @Override
        public Integer getDistanceAtCurrentLocation() {
            return dfsDistance.getOrDefault(model.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             dfsDistance.put(model.getDroidLocation(), distance);
             view.setDistance(model.getDroidLocation(), distance, viewColor);
        }

        public void resetOrigin() {
            dfsDistance.clear();
            setDistanceAtCurrentLocation(0);
        }
    }

    class TankFindingDistanceTracker extends MapDistanceTracker {

        public TankFindingDistanceTracker(Color color) {
            super(color);
        }

        public Boolean searchIsFinished() {
            return model.result == TANK;
        }
    }

    class AllPointsDistanceTracker extends MapDistanceTracker {

        public AllPointsDistanceTracker(Color color) {
            super(color);
        }

        int directionsChecked = 0;

        public Boolean searchIsFinished() {
            if(model.directionStack.isEmpty()) {
                directionsChecked++;
                return directionsChecked == 5;
            }
            return false;
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             super.setDistanceAtCurrentLocation(distance);
             view.setFurthestDistance(Math.max(view.getFurthestDistance(), distance));


        }
    }

}
