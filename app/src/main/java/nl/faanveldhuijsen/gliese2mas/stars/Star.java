package nl.faanveldhuijsen.gliese2mas.stars;

import java.lang.reflect.Field;
import java.util.Map;

import nl.faanveldhuijsen.gliese2mas.config.ByteRange;
import nl.faanveldhuijsen.gliese2mas.config.Label;
import nl.faanveldhuijsen.gliese2mas.config.Table;

/**
 * Created by faan1 on 24-Sep-20.
 */

public class Star {

    public final int id;
    public String Name;
    public String f_Name;
    public String OName;
    public Float RAh;
    public Float RAm;
    public Float RAs;
    public Float DEd;
    public Float DEm;
    public Float DEs;
    public String l_pmRA;
    public Float pmRA;
    public String l_pmDE;
    public Float pmDE;
    public String _2MASS;
    public Float Jmag;
    public Float Hmag;
    public Float Ksmag;
    public String Com;
    public String src;
    public String Note;


    public Star(int id, String row) {
        this.id = id;

        for (Map.Entry<String, Label> entry: Table.data_labels.entrySet()) {
            String field = entry.getKey();
            Label label = entry.getValue();
            ByteRange byteRange = label.byteRange;

            String column = row.substring(byteRange.x, byteRange.y).trim();

            if (column.equals("---")) {
                column = "0";
            }

            if (label.type == Float.class) {
                Float column1 = Float.valueOf((String) column);
                set(field, column1);
            } else if (label.type == Integer.class) {
                int column1 = Integer.parseInt((String) column);
                set(field, column1);
            } else {
                set(field, column);
            }
        }
    }

    private void set(String fieldName, Object value) {
        try {

            Field field = this.getClass().getField(fieldName);
            field.set(this, value);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return String.valueOf(this.id + 1);
    }

    public String getName() {
        return String.format("%s (%s)", Name, OName);
    }
}