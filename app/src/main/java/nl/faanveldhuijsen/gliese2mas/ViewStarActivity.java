package nl.faanveldhuijsen.gliese2mas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import nl.faanveldhuijsen.gliese2mas.stars.Star;
import nl.faanveldhuijsen.gliese2mas.stars.Stars;

public class ViewStarActivity extends AppCompatActivity {

    private ArrayList<Star> stars;
    public Star star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stars = Stars.getInstance(getApplicationContext()).data;
        int starId = getIntent().getIntExtra(GlieseTableActivity.EXTRA_STAR_ID, 0);
        star = stars.get(starId);

        setContentView(R.layout.activity_view_star);

        addTextValues();
    }

    private void addTextValues() {
        TextView view = (TextView) findViewById(R.id.Name);
        view.setText(star.getName());

//        view = (TextView) findViewById(R.id.Name);
//        view.setText(star.getName());
//
//        view = (TextView) findViewById(R.id.Name);
//        view.setText(star.getName());
//
//        view = (TextView) findViewById(R.id.Name);
//        view.setText(star.getName());
//
//        view = (TextView) findViewById(R.id.Name);
//        view.setText(star.getName());

    }
}
