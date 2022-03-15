package year_2019.day15;

import year_2019.CartesianPoint;
import year_2019.day15.model.CardinalDirection;
import year_2019.day15.model.DroidMazeModel;
import year_2019.day15.model.DroidMazeOutputInstruction;

import java.awt.*;

public class DroidMazeController {
    DroidMazeView view = new DroidMazeView(this);
    DroidMazeModel model;


    public DroidMazeController(long[] brainTape) {
        model = new DroidMazeModel(this, brainTape);
        model.resetOrigin();
        view.paintPoint(new CartesianPoint(0, 0), Color.WHITE);
    }

    /**
     * Functions that the view uses to update the model
     */

     public void resetOriginInModel() {
        model.resetOrigin();
    }


    public void findOxygenTank() throws InterruptedException {
        model.setCurrentTrackerToTank();
        model.droidDFS();
        //return currentTracker.getDistanceAtCurrentLocation();
    }


    /**
     * Searches the entire maze space and records distances from the starting point
     * @throws InterruptedException
     */
    public void computeAllDistancesFromPoint() throws InterruptedException {
        model.setCurrentTrackerToAllPoints();
        model.droidDFS();
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

    public void setFurthestDistanceInView(int furthestDistance) {
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
        view.repaint();
    }


    public void updateStackInView(String newStr) {
        view.setDirectionStack(newStr);
    }


    /* Functions that do neither */




    public void resetOriginInView() {
        view.resetOrigin();
        view.repaint();
    }
}
