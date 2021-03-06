package eu.bquepab.popularmovies.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.BuildConfig;
import eu.bquepab.popularmovies.PopularMoviesApplication;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Trailer;
import eu.bquepab.popularmovies.ui.adapter.TrailerArrayAdapter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_VIEW;

public class MovieDetailsTrailersActivityFragment extends Fragment implements TrailerArrayAdapter.OnTrailerClickListener {

    public static final String EXTRA_TRAILERS = "trailers";

    @BindView(R.id.trailers_view)
    RecyclerView trailersRecyclerView;
    private TrailerArrayAdapter trailersArrayAdapter;
    private ArrayList<Trailer> trailers;

    public MovieDetailsTrailersActivityFragment() {
        trailers = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details_trailers, container, false);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trailersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        trailersRecyclerView.addItemDecoration(dividerItemDecoration);
        trailersArrayAdapter = new TrailerArrayAdapter(trailers, this);
        trailersRecyclerView.setAdapter(trailersArrayAdapter);

        if (null != savedInstanceState) {
            refreshTrailers(savedInstanceState.getParcelableArrayList(EXTRA_TRAILERS));
        } else {
            Bundle args = getArguments();
            if (null != args && args.containsKey(EXTRA_TRAILERS)) {
                refreshTrailers(args.getParcelableArrayList(EXTRA_TRAILERS));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != trailers) {
            outState.putParcelableArrayList(EXTRA_TRAILERS, trailers);
        }
    }

    private void refreshTrailers(final List<Trailer> trailers) {
        this.trailers = new ArrayList<>(trailers);
        trailersArrayAdapter.setTrailers(this.trailers);
    }

    @Override
    public void onItemClick(final Trailer trailer) {
        Intent intent = new Intent();
        intent.setAction(ACTION_VIEW);
        intent.setData(Uri.parse(String.format(BuildConfig.YOUTUBE_URL, trailer.key())));
        startActivity(intent);
    }
}
