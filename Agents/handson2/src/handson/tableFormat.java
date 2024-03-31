package handson;

import java.util.ArrayList;
import java.util.Map;

public class tableFormat {

    private final ArrayList<double[]> values;
    private int maxLong = 0;

    public tableFormat(Map<Integer, Object> params) {
        values = new ArrayList<>();
        for (int i = 0; i < params.size(); i++) {
            values.add((double[]) params.get(i));
            if (maxLong < values.get(i).length) {
                maxLong = values.get(i).length;
            }
        }
    }

    public String[][] getDataTable() {
        String[][] table = new String[maxLong][values.size() + 1];
        for (int columns = 0; columns < values.size(); columns++) {
            for (int rows = 0; rows < maxLong; rows++) {
                if (table[rows][columns] == null) {
                    table[rows][columns] = String.valueOf(rows + 1);
                    table[rows][columns + 1] = String.valueOf(values.get(columns)[rows]);
                } else {
                    table[rows][columns + 1] = String.valueOf(values.get(columns)[rows]);
                }

            }
        }
        return table;
    }
}
