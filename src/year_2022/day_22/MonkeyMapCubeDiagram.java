package year_2022.day_22;

import lombok.AllArgsConstructor;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;
import java.util.stream.Collectors;

public class MonkeyMapCubeDiagram implements IMonkeyMapDiagram {
    List<MonkeyMapCubeFace> faces = new ArrayList<>();
    MonkeyMapDiagram rawDiagram;

    MonkeyMapCubeFace[][] facesMatrix;

    public MonkeyMapCubeDiagram(int n, List<String> rawDiagram) {
        this.rawDiagram = new MonkeyMapDiagram(rawDiagram);
        facesMatrix = new MonkeyMapCubeFace[(int) Math.pow(n, 2)][(int) Math.pow(n, 2)];

        for (int y=0; y>-rawDiagram.size()+1; y-=n) {
            for (int x=0; x<rawDiagram.get(-y).length(); x+=n) {
                //System.out.println(x + " " + y);
                if (this.rawDiagram.readAtCartesianPoint(x, y) != MonkeyMapEnum.WARP_ZONE) {
                    MonkeyMapCubeFace newFace = new MonkeyMapCubeFace(rawDiagram, n, x, y);
                    faces.add(newFace);
                    if (y!=0) {
                        if (this.rawDiagram.readAtCartesianPoint(x, y+n) != MonkeyMapEnum.WARP_ZONE) {
                            System.out.println(x + " " + (-n + y) + " " + y);
                            // this is below another face
                            int finalY = y;
                            int finalX = x;
                            MonkeyMapCubeFace northNeighborFace = faces.stream().filter(f -> f.topRightCorner.x == finalX && f.topRightCorner.y == finalY + n).findFirst().get();
                            newFace.setNeighbor(CardinalDirection.NORTH, northNeighborFace);
                            northNeighborFace.setNeighbor(CardinalDirection.SOUTH, newFace);
                        }
                    }
                    if (x != 0) {
                        if (this.rawDiagram.readAtCartesianPoint(x-n, y) != MonkeyMapEnum.WARP_ZONE) {
                            int finalY = y;
                            int finalX = x;
                            MonkeyMapCubeFace westNeighborFace = faces.stream().filter(f -> f.topRightCorner.x == finalX - n && f.topRightCorner.y == finalY).findFirst().get();
                            newFace.setNeighbor(CardinalDirection.WEST, westNeighborFace);
                            westNeighborFace.setNeighbor(CardinalDirection.EAST, newFace);
                        }
                     }
                }
            }
        }


        for (MonkeyMapCubeFace face : faces) {
            System.out.println(face.topRightCorner + " " + face.neighbors.entrySet());
        }




    }

    @Override
    public MonkeyMapEnum readAtCartesianPoint(CartesianPoint p) {
        return null;
    }

    @Override
    public CartesianPoint getNextPointInDirection(CartesianPoint position, CardinalDirection facing) {
        return null;
    }
}

@AllArgsConstructor
class MonkeyMapCubeFace {
    int n;
    IMonkeyMapDiagram subDiagram;
    CartesianPoint topRightCorner;
    Map<CardinalDirection, MonkeyMapCubeFace> neighbors = new HashMap<>();

    public MonkeyMapCubeFace(List<String> rawDiagram, int n, int x, int y) {
        List<String> newRawDiagram = rawDiagram.subList(-y, -y+n).stream().map(s -> s.substring(x, x+n)).collect(Collectors.toList());
        this.subDiagram = new MonkeyMapDiagram(newRawDiagram);
        this.topRightCorner = new CartesianPoint(x, y);
    }

    @Override
    public String toString() {
        return "MonkeyMapCubeFace{" +
                ", topRightCorner=" + topRightCorner +
                '}';
    }

    public void setNeighbor(CardinalDirection direction, MonkeyMapCubeFace neighborFace) {
        neighbors.put(direction, neighborFace);
    }
}
