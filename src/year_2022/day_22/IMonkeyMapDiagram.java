package year_2022.day_22;

import viewModelUtil.CartesianPoint;

import java.util.List;

public interface IMonkeyMapDiagram {
    MonkeyMapEnum readAtCartesianPoint(CartesianPoint p);
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
}

