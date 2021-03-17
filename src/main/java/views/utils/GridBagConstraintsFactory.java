package views.utils;

import java.awt.*;

public class GridBagConstraintsFactory {
    private final static int DEFAULT_FILL = GridBagConstraints.BOTH;

    private GridBagConstraintsFactory() {

    }

    public static GridBagConstraints getConstraints() {
        return getConstraints(DEFAULT_FILL, 0, 0, 1, 1);
    }

    public static GridBagConstraints getConstraints(int row, int col) {
        return getConstraints(DEFAULT_FILL, row, col, 1, 1);
    }

    public static GridBagConstraints getConstraints(int row, int col, double weightx, double weighty) {
        return getConstraints(DEFAULT_FILL, row, col, weightx, weighty);
    }

    public static GridBagConstraints getConstraints(int fill, int row, int col, double weightx, double weighty) {
        var result = new GridBagConstraints();

        result.fill = fill;
        result.gridx = col;
        result.gridy = row;
        result.weightx = weightx;
        result.weighty = weighty;

        return result;
    }
}
