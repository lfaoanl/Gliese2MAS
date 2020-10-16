package nl.faanveldhuijsen.gliese2mas.stars;

import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String CommonName;

    public Star(int id, String row) {
        this.id = id;
        Log.d("Line", row);
        for (Map.Entry<String, Label> entry: Table.data_labels.entrySet()) {
            String field = entry.getKey();
            Label label = entry.getValue();
            ByteRange byteRange = label.byteRange;
            if (byteRange.y > row.length()) {
                byteRange.y = row.length() - 1;
            }

            String column = row.substring(byteRange.x, byteRange.y).trim();

            if (column.equals("---")) {
                column = "0";
            }

            if (label.type == Float.class) {
                if (column == null || column.length() <= 0){
                    column = "0";
                }
                Float column1 = Float.valueOf(column);
                set(field, column1);
            } else if (label.type == Integer.class) {
                int column1 = Integer.parseInt(column);
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

    public void setCommonName(ArrayMap<Integer, String> list) {
        CommonName = list.get(getHipNumber());
        Log.d("Common Name", String.valueOf(getHipNumber()));
    }

    public String getId() {
        return String.valueOf(this.id + 1);
    }

    public String getName() {
        return getName(false);
    }

    public String getName(boolean small) {
        ArrayList<String> args = new ArrayList<>();
        String format = "%s";

        args.add(Name);

        if (small && !OName.equals("")) {
            args.add(OName);
            format += " (%s)";
        }

        return String.format(format, args.toArray());
    }

    public String get2MASSName() {
        return _2MASS;
    }

    public String getRightAscension() {
        return String.format(Locale.ENGLISH, "%s:%s:%s", formatTime(RAh), formatTime(RAm), formatTime(RAs));
    }

    public String getDeclinaton() {
        String format = "%s°";
        ArrayList<String> args = new ArrayList<>();
        args.add(formatFloat(DEd));

        if (DEm > 0) {
            format += " %s'";
            args.add(formatFloat(DEm));

            if (DEs > 0) {
                format += " %s\"";
                args.add(formatFloat(DEs));
            }
        }

        return String.format(Locale.ENGLISH, format, args.toArray());
    }

    private int getHipNumber() {
        if (OName.length() > 0) {
            Pattern pattern = Pattern.compile("HIP (\\d+) ?[A-Z]*");
            Matcher matcher = pattern.matcher(OName);

            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            }
        }
        return 0;
    }

    public String getProperMotionRA() {
        return String.format(Locale.ENGLISH, "%s arcsec/year", formatFloat(pmRA));
    }

    public String getProperMotionDe() {
        return String.format(Locale.ENGLISH, "%s arcsec/year", formatFloat(pmDE));
    }

    public String getJmag() {
        return formatFloat(Jmag);
    }

    public String getHmag() {
        return formatFloat(Hmag);
    }

    public String getKsmag() {
        return formatFloat(Ksmag);
    }

    private String formatTime(float f)
    {
        String s = formatFloat(f);

        if (String.valueOf((long) f).length() == 1) {
            return "0" + s;
        }

        return s;
    }

    private String formatFloat(float f)
    {
        if(f == (long) f) {
            return String.format(Locale.ENGLISH, "%d", (long) f);
        } else {
            return String.format("%s", f);
        }
    }
}
