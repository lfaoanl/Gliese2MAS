package nl.faanveldhuijsen.gliese2mas.stars;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.ArrayMap;
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
    public ArrayMap<Integer, String> common_names = new ArrayMap<>();

    private Stars(Context context) {
        loadCommonNames(context);
        loadStars(context);
    }

    private void loadCommonNames(Context context) {
        BufferedReader file = loadFile(context, R.raw.common_names);

        String line;
        try {
            while((line = file.readLine()) != null) {
                int hipNumber = Integer.parseInt(line.substring(0, 6).trim());
                String name = line.substring(7);
                common_names.put(hipNumber, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStars(Context context) {
        BufferedReader reader = loadFile(context, R.raw.table1);

        Log.d("My favo comm name", common_names.get(70890));

        try {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (i > 2475) {
                    break;
                }
                Star star = new Star(i, line);
                star.setCommonName(common_names);
                data.add(star);
                i++;
            }

        } catch (IOException e) {
            Log.e("File reading error", ":(");
            e.printStackTrace();
        }
    }

    @NonNull
    private BufferedReader loadFile(Context context, int table1) {
        InputStream inputStream = context.getResources().openRawResource(table1);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static Stars getInstance(Context context)
    {
        if (single_instance == null) {
            single_instance = new Stars(context);
        }
        return single_instance;
    }
}