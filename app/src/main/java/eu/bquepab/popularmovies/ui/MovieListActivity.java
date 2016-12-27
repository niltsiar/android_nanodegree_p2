package eu.bquepab.popularmovies.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.R;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindString(R.string.pref_sort_order_key)
    String prefSortOrder;
    @BindString(R.string.pref_sort_order_popularity_value)
    String prefSortOrderByPopularity;
    @BindString(R.string.pref_sort_order_top_rated_value)
    String prefSortOrderByTopRated;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_sort_by_popularity) {
            saveSortOrderPreferences(prefSortOrderByPopularity);
            return true;
        } else if (id == R.id.action_sort_by_top_rated) {
            saveSortOrderPreferences(prefSortOrderByTopRated);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveSortOrderPreferences(final String sortOrder) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(prefSortOrder, sortOrder).apply();
    }
}
