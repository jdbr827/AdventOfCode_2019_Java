package year_2022.day_22;

import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.List;

public interface IMonkeyMapDiagram {
    MonkeyMapEnum readAtCartesianPoint(CartesianPoint p);

    CartesianPoint getNextPointInDirection(CartesianPoint position, CardinalDirection facing);
}

class MonkeyMapDiagram implements IMonkeyMapDiagram {
    private List<String> diagram;

    MonkeyMapDiagram(List<String> diagram) {
        this.diagram = diagram;
    }

    @Override
    public MonkeyMapEnum readAtCartesianPoint(CartesianPoint p) {
        if (p.y > 0 || p.y <= -diagram.size() || p.x < 0 || p.x >= diagram.get(-p.y).length()) {
            return MonkeyMapEnum.WARP_ZONE;
        }
        return MonkeyMapEnum.of(diagram.get(-p.y).charAt(p.x));
    }

    @Override
    public CartesianPoint getNextPointInDirection(CartesianPoint position, CardinalDirection facing) {
        return new CartesianPoint(position.x + facing.velocity.x, position.y + facing.velocity.y);
    }
}

