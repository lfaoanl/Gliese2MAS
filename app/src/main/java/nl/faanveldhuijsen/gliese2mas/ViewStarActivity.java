package nl.faanveldhuijsen.gliese2mas;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().

        stars = Stars.getInstance(getApplicationContext()).data;
        int starId = getIntent().getIntExtra(GlieseTableActivity.EXTRA_STAR_ID, 0);
        star = stars.get(starId);

        setContentView(R.layout.activity_view_star);

        addTextValues();
    }

    private void addTextValues() {
        TextView view = (TextView) findViewById(R.id.Name);
        view.setText(star.getName());

        view = (TextView) findViewById(R.id._2MASS);
        view.setText(star.get2MASSName());

        view = (TextView) findViewById(R.id.RightAscension);
        view.setText(star.getRightAscension());

        view = (TextView) findViewById(R.id.Declination);
        view.setText(star.getDeclinaton());

        view = (TextView) findViewById(R.id.ProperMotionRA);
        view.setText(star.getProperMotionRA());

        view = (TextView) findViewById(R.id.ProperMotionDe);
        view.setText(star.getProperMotionDe());

        view = (TextView) findViewById(R.id.Jmag);
        view.setText(star.getJmag());

        view = (TextView) findViewById(R.id.Hmag);
        view.setText(star.getHmag());

        view = (TextView) findViewById(R.id.Ksmag);
        view.setText(star.getKsmag());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), GlieseTableActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
