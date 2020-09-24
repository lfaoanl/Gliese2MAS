package nl.faanveldhuijsen.gliese2mas.stars;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import nl.faanveldhuijsen.gliese2mas.R;

/**
 * Created by faan1 on 24-Sep-20.
 */

public class Stars {
    private static Stars single_instance = null;

    public ArrayList<Star> data = new ArrayList<>();

    private Stars(Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.table1);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i > 5) {
                    break;
                }
                data.add(new Star(i, line));
                i++;
            }

        } catch (IOException e) {
            Log.e("File reading error", ":(");
            e.printStackTrace();
        }
    }

    public static Stars getInstance(Context context)
    {
        if (single_instance == null) {
            single_instance = new Stars(context);
        }
        return single_instance;
    }
}