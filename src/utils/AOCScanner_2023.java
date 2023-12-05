package utils;

import year_2023.day_02.Day2;

public abstract class AOCScanner_2023<T> extends AOCScanner {

    public AOCScanner_2023(String fileName) {
        super(fileName);
    }

    /**
     * Parses the data in the file into an object of the type necessary for the specific class.
     * @return
     */
    protected abstract T scan();
}
