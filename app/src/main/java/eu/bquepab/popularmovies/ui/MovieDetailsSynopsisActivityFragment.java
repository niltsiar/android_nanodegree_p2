package eu.bquepab.popularmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.R;

public class MovieDetailsSynopsisActivityFragment extends Fragment {

    public static final String SYNOPSIS = "eu.bquepab.popularmovies.ui.MovieDetailsSynopsisActivityFragment.SYNOPSIS";

    @BindView(R.id.movie_synopsis)
    TextView movieSynopsis;

    private String synopsis;

    public MovieDetailsSynopsisActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details_synopsis, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();

        if (null != savedInstanceState && savedInstanceState.containsKey(SYNOPSIS)) {
            synopsis = savedInstanceState.getString(SYNOPSIS);
            movieSynopsis.setText(synopsis);
        } else if (null != args && args.containsKey(SYNOPSIS)) {
            synopsis = args.getString(SYNOPSIS);
            movieSynopsis.setText(synopsis);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        outState.putString(SYNOPSIS, synopsis);
        super.onSaveInstanceState(outState);
    }
}
