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

    /**
     * Functions that the view uses to update the model
     */


    public int findOxygenTank() throws InterruptedException {
        setCurrentTracker(new TankFindingDistanceTracker(Color.BLACK));
        resetOrigin();
        model.unifiedDFS();
        return currentTracker.getDistanceAtCurrentLocation();
    }


    /**
     * Searches the entire maze space and records distances from the starting point
     * @throws InterruptedException
     */
    public void computeAllDistancesFromPoint() throws InterruptedException {
        setCurrentTracker(new AllPointsDistanceTracker(Color.BLUE));
        resetOrigin();
        model.unifiedDFS();
    }


    /**
     *  Functions that the model uses to update the view
     */

    private void setFurthestDistanceInView(int furthestDistance) {
            view.setFurthestDistance(furthestDistance);
    }

    public void setDistanceInView(CartesianPoint point, Integer distance, Color color) {
         view.setDistance(point, distance, color);
    }

    public void paintPointInView(DroidMazeOutputInstruction outputInstruction, CartesianPoint desiredPoint) {
        view.paintPoint(desiredPoint, outputInstruction.getPaintColor());
        view.repaint();
    }

    public void moveDroidInView() {
        view.setDroidLocation(model.getDroidLocation());
        updateStackInView();
        view.repaint();
    }


    void updateStackInView() {
        view.setDirectionStack(model.getDirectionStack().stream().map(CardinalDirection::getShortName).collect(Collector.of(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append,
            StringBuilder::toString)));
    }


    /* Functions that do neither */

    public void setCurrentTrackerToTank() {
        setCurrentTracker(new TankFindingDistanceTracker(Color.BLACK));
    }

    public void setCurrentTrackerToAllPoints() {
        setCurrentTracker(new AllPointsDistanceTracker(Color.BLUE));
    }

    private void setCurrentTracker(MapDistanceTracker tracker){
        currentTracker = tracker;
        resetOrigin();
    }

    public void resetOrigin() {
        view.resetOrigin(model.getDroidLocation());
        model.resetOrigin();
        currentTracker.resetOrigin();
        view.repaint();
    }





    abstract class MapDistanceTracker extends DistanceTracker {
        protected final Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point

        public MapDistanceTracker(Color color) {
            super(color);
        }

        @Override
        public Integer getDistanceAtCurrentLocation() {
            return dfsDistance.getOrDefault(model.getDroidLocation(), Integer.MAX_VALUE);
        }

        @Override
        public void setDistanceAtCurrentLocation(Integer distance) {
             dfsDistance.put(model.getDroidLocation(), distance);
             setDistanceInView(model.getDroidLocation(), distance, viewColor);
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
        int furthestDistance = 0;

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
             furthestDistance = Math.max(furthestDistance, distance);
             setFurthestDistanceInView(furthestDistance);
        }


    }

}
