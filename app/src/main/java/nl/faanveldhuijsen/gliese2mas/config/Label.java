package nl.faanveldhuijsen.gliese2mas.config;

import nl.faanveldhuijsen.gliese2mas.ByteRange;

/**
 * Created by faan1 on 24-Sep-20.
 */

public class Label {

    public ByteRange byteRange;
    public Class type;


    public Label(int byte0, int byte1, Class type) {
        this.type = type;
        this.byteRange = new ByteRange(byte0, byte1);
    }

    public Label(int byte0, int byte1) {
        this(byte0, byte1, String.class);
    }
}
