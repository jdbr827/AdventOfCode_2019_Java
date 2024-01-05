package year_2023.day_19;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
class MachinePartSpace {
    private MachinePartSpaceDimension x;
    private MachinePartSpaceDimension m;
    private MachinePartSpaceDimension a;
    private MachinePartSpaceDimension s;
    String currentWorkflow;

    public MachinePartSpace(int xMin, int xMax, int mMin, int mMax, int aMin, int aMax, int sMin, int sMax, String currentWorkflow) {
        x = new MachinePartSpaceDimension(xMin, xMax);
        m = new MachinePartSpaceDimension(mMin, mMax);
        a = new MachinePartSpaceDimension(aMin, aMax);
        s = new MachinePartSpaceDimension(sMin, sMax);
        this.currentWorkflow = currentWorkflow;
    }


    MachinePartSpaceDimension getField(String fieldName) {
        switch (fieldName) {
            case "x":
                return x;
            case "m":
                return m;
            case "a":
                return a;
            case "s":
                return s;
        }
        throw new Error("Did not recognize field name \"" + fieldName + "\". fieldName should only be one of x, m, a, s");
    }

    public void setMax(String fieldName, int i) {
        getField(fieldName).setMaxInclusive(i);
    }

    public void setMin(String fieldName, int i) {
        getField(fieldName).setMinInclusive(i);
    }

    public int getMax(String fieldName) {
        return getField(fieldName).getMaxInclusive();
    }

    public int getMin(String fieldName) {
        return getField(fieldName).getMinInclusive();
    }

    public MachinePartSpace copy() {
        return new MachinePartSpace(x.minInclusive, x.maxInclusive, m.minInclusive, m.maxInclusive, a.minInclusive, a.maxInclusive, s.minInclusive, s.maxInclusive, currentWorkflow);
    }


    public long getArea() {
        return x.getLength() * m.getLength() * a.getLength() * s.getLength();
    }

    @Data
    @AllArgsConstructor
    static class MachinePartSpaceDimension {
        int minInclusive;
        int maxInclusive;

        long getLength() {
            return maxInclusive - minInclusive + 1;
        }

        boolean hasPositiveLength() {
            return maxInclusive > minInclusive;
        }
    }
}
