package year_2022.day_18;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
public class ObsidianPlane {
    int x;
    int y;
    int z;
    int perpendicularAxis;

    static ObsidianPlane fromPoint(List<Integer> point, int perpendicularAxis) {
        return new ObsidianPlane(point.get(0), point.get(1), point.get(2), perpendicularAxis);

    }

    ObsidianPlane plus1X() {
        return new ObsidianPlane(x+1, y, z, perpendicularAxis);
    }

    ObsidianPlane plus1Y() {
        return new ObsidianPlane(x, y+1, z, perpendicularAxis);
    }

    ObsidianPlane plus1Z() {
        return new ObsidianPlane(x, y, z+1, perpendicularAxis);
    }

    @Override
    public String toString() {
        return "ObsidianPlane{" +
                "(" + x  +
                "," + y +
                "," + z +
                "), perpendicularAxis=" + perpendicularAxis +
                '}';
    }
}
