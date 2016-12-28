package eu.bquepab.popularmovies.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.R;

public class MovieDetailsSynopsisActivityFragment extends Fragment {

    public static final String EXTRA_SYNOPSIS = "SYNOPSIS";

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
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        if (null != savedInstanceState && savedInstanceState.containsKey(EXTRA_SYNOPSIS)) {
            synopsis = savedInstanceState.getString(EXTRA_SYNOPSIS);
            movieSynopsis.setText(synopsis);
        } else if (null != args && args.containsKey(EXTRA_SYNOPSIS)) {
            synopsis = args.getString(EXTRA_SYNOPSIS);
            movieSynopsis.setText(synopsis);
        }
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        outState.putString(EXTRA_SYNOPSIS, synopsis);
        super.onSaveInstanceState(outState);
    }
}
