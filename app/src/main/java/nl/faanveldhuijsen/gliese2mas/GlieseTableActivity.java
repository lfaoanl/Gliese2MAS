package nl.faanveldhuijsen.gliese2mas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.faanveldhuijsen.gliese2mas.config.Table;

public class GlieseTableActivity extends AppCompatActivity {

    private Context context;

    public GlieseStar[] stars = new GlieseStar[5];
    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        table = new Table();
        context = getApplicationContext();

        parseDataFile();

        setContentView(R.layout.gliese_table);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.gliese_table);

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView text = new TextView(this);
        text.setText("Test");
        row.addView(text);

        tableLayout.addView(row, 0);
    }

    protected void parseDataFile() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.table1);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            int i = 0;
            while ((line = reader.readLine()) != null) {
                stars[i] = new GlieseStar(line);
                i++;
            }

        } catch (IOException e) {
            Log.e("File reading error", ":(");
            e.printStackTrace();
        }

        Log.d("Stars done", ":)");
        Log.d("Star ", stars[0].Name);
        Log.d("Star ", stars[1].Name);
        Log.d("Star ", stars[2].Name);
        Log.d("Star ", stars[3].Name);
    }
}
