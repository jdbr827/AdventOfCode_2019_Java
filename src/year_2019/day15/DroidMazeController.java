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


    public DroidMazeController(long[] brainTape) {
        model = new DroidMazeModel(this, brainTape);
        model.currentTracker.resetOrigin();
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    /**
     * Functions that the view uses to update the model
     */


    public void findOxygenTank() throws InterruptedException {
        model.setCurrentTrackerToTank();
        model.unifiedDFS();
        //return currentTracker.getDistanceAtCurrentLocation();
    }


    /**
     * Searches the entire maze space and records distances from the starting point
     * @throws InterruptedException
     */
    public void computeAllDistancesFromPoint() throws InterruptedException {
        model.setCurrentTrackerToAllPoints();
        model.unifiedDFS();
    }

    public void attemptDroidMove(CardinalDirection direction) throws InterruptedException {
        model.attemptDroidMove(direction);
    }

    public void setCurrentTrackerToTank() {
        model.setCurrentTrackerToTank();
    }

    public void setCurrentTrackerToAllPoints() {
        model.setCurrentTrackerToAllPoints();
    }


    /**
     *  Functions that the model uses to update the view
     */

    void setFurthestDistanceInView(int furthestDistance) {
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


    public void resetOrigin() {
        view.resetOrigin(model.getDroidLocation());
        model.resetOrigin();
        view.repaint();
    }

}
