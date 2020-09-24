package nl.faanveldhuijsen.gliese2mas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import nl.faanveldhuijsen.gliese2mas.config.Table;
import nl.faanveldhuijsen.gliese2mas.stars.Star;
import nl.faanveldhuijsen.gliese2mas.stars.Stars;

public class GlieseTableActivity extends AppCompatActivity {

    public static final String EXTRA_STAR_ID = "nl.faanveldhuijsen.gliese2mas.STAR_ID";

    private Context context;

    private ArrayList<Star> stars = new ArrayList<>();
    private Table table;

    private String selectedColumn = "";
    private boolean sortDesc = false;

    private boolean clicking = false;

    private LinkedHashMap<String, String> labels = new LinkedHashMap<>();

    public GlieseTableActivity() {
        super();

        labels.put("id", "ID");
        labels.put("name", "Name");

        table = new Table();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();
        stars = Stars.getInstance(context).data;

        setContentView(R.layout.gliese_table);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.gliese_table);
        tableLayout.setColumnStretchable(1, true);
        tableLayout.setHorizontalScrollBarEnabled(true);

        tableLayout.addView(tableHeader(), 0);

        for (Star star: stars) {
            tableLayout.addView(tableRow(star));
        }
    }

    private View tableRow(final Star star) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView textId = getTextView();
        textId.setText(String.valueOf(star.getId()));
        row.addView(textId);

        TextView textName = getTextView();
        textName.setText(star.getName());
        row.addView(textName);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewStarActivity.class);
                intent.putExtra(EXTRA_STAR_ID, star.id);
                startActivity(intent);
            }
        });

        return row;
    }

    private View tableHeader() {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        for (Map.Entry<String, String> entry: labels.entrySet()) {
            String label = entry.getValue();
            final String key = entry.getKey();

            TextView text = getTextView();
            text.setTypeface(null, Typeface.BOLD);
            text.setTag(key);
            text.setText(label);

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickedTableHeader(v, key);
                }
            });

            row.addView(text);
        }
        return row;
    }

    private void clickedTableHeader(View v, String key) {
        TableRow header = (TableRow) v.getParent();

        if (selectedColumn.equals(key)) {
            sortDesc = !sortDesc;
        }

        selectedColumn = key;
        for(int i =0; i< header.getChildCount(); i++){
            TextView headCol = (TextView) header.getChildAt(i);

            if (headCol.getTag().equals(key)) {
                int icon = R.drawable.ic_arrow_drop_down_black_24dp;
                if (sortDesc) {
                    icon = R.drawable.ic_arrow_drop_up_black_24dp;
                }

                headCol.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0);
            } else {
                headCol.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    public TextView getTextView() {
        TextView text = new TextView(this);
        text.setPadding(20, 20, 20, 20);
        text.setTextSize(14);

        return text;
    }
}
