package nl.faanveldhuijsen.gliese2mas.config;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by faan1 on 24,Sep, 20.
 */

public class Table {

    public static final HashMap<String, Label> data_labels = new LinkedHashMap<>();

    public Table() {
        data_labels.put("Name",   new Label(0, 21));
        data_labels.put("f_Name", new Label(21, 22));
        data_labels.put("OName",  new Label(22, 35));
        data_labels.put("RAh",    new Label(36, 38, Float.class));
        data_labels.put("RAm",    new Label(39, 41, Float.class));
        data_labels.put("RAs",    new Label(42, 47, Float.class));

        data_labels.put("DEd",    new Label(48, 51, Float.class));
        data_labels.put("DEm",    new Label(52, 54, Float.class));
        data_labels.put("DEs",    new Label(55, 59, Float.class));
        data_labels.put("l_pmRA", new Label(60, 61));
        data_labels.put("pmRA",   new Label(61, 67, Float.class));
        data_labels.put("l_pmDE", new Label(68, 69));
        data_labels.put("pmDE",   new Label(69, 75, Float.class));
        data_labels.put("_2MASS",  new Label(76, 93));
        data_labels.put("Jmag",   new Label(94, 100, Float.class));
        data_labels.put("Hmag",   new Label(101, 107, Float.class));
        data_labels.put("Ksmag",  new Label(108, 114, Float.class));
        data_labels.put("Com",    new Label(115, 132));
    }

}
