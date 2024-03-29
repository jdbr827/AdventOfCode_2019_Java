package year_2022.day_22;

import lombok.AllArgsConstructor;
import lombok.Getter;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;
import java.util.stream.Collectors;

public class MonkeyMapCubeDiagram implements IMonkeyMapDiagram {
    List<MonkeyMapCubeFace> faces = new ArrayList<>();
    MonkeyMapDiagram rawDiagram;
    int n;

    MonkeyMapCubeFace[][] facesMatrix;

    public MonkeyMapCubeDiagram(int n, List<String> rawDiagram) {
        this.n = n;
        this.rawDiagram = new MonkeyMapDiagram(rawDiagram);
        facesMatrix = new MonkeyMapCubeFace[(int) Math.pow(n, 2)][(int) Math.pow(n, 2)];

        int id = 1;
        for (int y = 0; y > -rawDiagram.size() + 1; y -= n) {
            for (int x = 0; x < rawDiagram.get(-y).length(); x += n) {
                //System.out.println(x + " " + y);
                if (this.rawDiagram.readAtCartesianPoint(x, y) != MonkeyMapEnum.WARP_ZONE) {
                    MonkeyMapCubeFace newFace = new MonkeyMapCubeFace(id++, rawDiagram, n, x, y);
                    faces.add(newFace);
                    if (y != 0) {
                        if (this.rawDiagram.readAtCartesianPoint(x, y + n) != MonkeyMapEnum.WARP_ZONE) {
                            System.out.println(x + " " + (-n + y) + " " + y);
                            // this is below another face
                            int finalY = y;
                            int finalX = x;
                            MonkeyMapCubeFace northNeighborFace = faces.stream().filter(f -> f.topLeftCorner.x == finalX && f.topLeftCorner.y == finalY + n).findFirst().get();
                            newFace.setNeighbor(CardinalDirection.NORTH, northNeighborFace, CardinalDirection.SOUTH);
                            northNeighborFace.setNeighbor(CardinalDirection.SOUTH, newFace, CardinalDirection.NORTH);
                        }
                    }
                    if (x != 0) {
                        if (this.rawDiagram.readAtCartesianPoint(x - n, y) != MonkeyMapEnum.WARP_ZONE) {
                            int finalY = y;
                            int finalX = x;
                            MonkeyMapCubeFace westNeighborFace = faces.stream().filter(f -> f.topLeftCorner.x == finalX - n && f.topLeftCorner.y == finalY).findFirst().get();
                            newFace.setNeighbor(CardinalDirection.WEST, westNeighborFace, CardinalDirection.EAST);
                            westNeighborFace.setNeighbor(CardinalDirection.EAST, newFace, CardinalDirection.WEST);
                        }
                    }
                }
            }
        }


        for (MonkeyMapCubeFace face : faces) {
            System.out.println(face + " " + face.neighbors.entrySet());
        }

        buildCube();
        for (MonkeyMapCubeFace face : faces) {
            System.out.println(face.topLeftCorner + " " + face.neighbors.entrySet());
        }

    }

    private void buildCube() {
        for (MonkeyMapCubeFace face : faces) {
            for (CardinalDirection direction : CardinalDirection.values()) {
                face.determineAndSetNeighbor(direction);

            }
        }
    }

    @Override
    public MonkeyMapEnum readAtCartesianPoint(CartesianPoint p) {
        return rawDiagram.readAtCartesianPoint(p);
    }

    @Override
    public CartesianPoint getNextPointInDirection(CartesianPoint position, CardinalDirection facing) {
       // TODO: this part;
        return null;
    }

    class FacePoint {
        MonkeyMapCubeFace face;
        CartesianPoint positionInFace;
        CartesianPoint rawPoint;

        FacePoint(CartesianPoint p, int n) {
            rawPoint = p;
            int x = p.x;
            int y = p.y;
            int dx = 0;
            int dy = 0;
            x = Math.floorDiv(p.x, n);
            y = Math.floorDiv(p.y, n);
            int finalX = x;
            int finalY = y + 1;
            face = faces.stream().filter(f -> f.topLeftCorner.x == finalX * n && f.topLeftCorner.y == finalY * n).findFirst().orElse(null);
            positionInFace = new CartesianPoint(Math.floorMod(p.x, n), -Math.floorMod(p.y, n));
        }

        MonkeyMapEnum readValue() {
            if (face == null) {
                return MonkeyMapEnum.WARP_ZONE;
            }
           return face.subDiagram.readAtCartesianPoint(positionInFace);
        }

        boolean isFacingEdge(CardinalDirection facing) {
            switch (facing) {
                case WEST:
                    return positionInFace.x == 0;
                case EAST:
                    return positionInFace.x == n-1;
                case NORTH:
                    return positionInFace.y == 0;
                case SOUTH:
                    return positionInFace.y == -(n-1);
            }
            return false;
        }


    }
}


@AllArgsConstructor
class MonkeyMapCubeFaceEdge {
    @Getter
    CardinalDirection direction;
    @Getter
    MonkeyMapCubeFace neighbor;

    @Override
    public String toString() {
        return "MonkeyMapCubeFaceEdge{" +
                "direction=" + direction +
                ", neighbor=" + neighbor +
                '}';
    }
}

class CubePoint {
    int x;
    int y;
    int z;

}


@AllArgsConstructor
class MonkeyMapCubeFace {
    int id;
    int n;
    IMonkeyMapDiagram subDiagram;
    CartesianPoint topLeftCorner;
    Map<CardinalDirection, MonkeyMapCubeFaceEdge> neighbors = new HashMap<>();

    public MonkeyMapCubeFace(int id, List<String> rawDiagram, int n, int x, int y) {
        this.id = id;
        List<String> newRawDiagram = rawDiagram.subList(-y, -y + n).stream().map(s -> s.substring(x, x + n)).collect(Collectors.toList());
        this.subDiagram = new MonkeyMapDiagram(newRawDiagram);
        this.topLeftCorner = new CartesianPoint(x, y);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public void setNeighbor(CardinalDirection leavingDirection, MonkeyMapCubeFace neighborFace, CardinalDirection enteringDirection) {
        neighbors.put(leavingDirection, new MonkeyMapCubeFaceEdge(enteringDirection, neighborFace));
    }

    public MonkeyMapCubeFaceEdge getNeighbor(CardinalDirection direction) {
        return neighbors.get(direction);

    }

    public MonkeyMapCubeFaceEdge determineAndSetNeighbor(CardinalDirection direction) {
        if (getNeighbor(direction) == null) {
            System.out.println(this + " " + direction);
            MonkeyMapCubeFaceEdge clockwiseEdgeOnFace = determineAndSetNeighbor(direction.clockwise());
            if (clockwiseEdgeOnFace != null) {
                MonkeyMapCubeFace nbrFace = clockwiseEdgeOnFace.getNeighbor();
                CardinalDirection enterDirection = clockwiseEdgeOnFace.getDirection();
                MonkeyMapCubeFaceEdge clockwiseOfNeighbor = nbrFace.determineAndSetNeighbor(enterDirection.clockwise());
                MonkeyMapCubeFace nbrnbrFace = clockwiseOfNeighbor.getNeighbor();
                CardinalDirection nbrnbrDirection = clockwiseOfNeighbor.getDirection();
                setNeighbor(direction, nbrnbrFace, nbrnbrDirection.clockwise());
                //setNeighbor(nbrnbrDirection.clockwise(), this, direction);
            }
        }
        return getNeighbor(direction);
    }
}
