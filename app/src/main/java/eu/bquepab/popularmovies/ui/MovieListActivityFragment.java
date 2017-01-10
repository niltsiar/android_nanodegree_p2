package eu.bquepab.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindInt;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.PopularMoviesApplication;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.data.DataRepository;
import eu.bquepab.popularmovies.model.Movie;
import eu.bquepab.popularmovies.ui.adapter.MovieArrayAdapter;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieListActivityFragment extends Fragment implements MovieArrayAdapter.OnMovieClickListener {

    private static final String EXTRA_MOVIES = "movies";
    private static final String EXTRA_LAST_SORT = "last_sort_by";

    @Inject
    DataRepository localStorage;

    @BindView(R.id.movies_grid)
    RecyclerView moviesRecyclerView;
    @BindInt(R.integer.grid_columns)
    int columnsNumber;
    @BindString(R.string.pref_sort_order_key)
    String prefSortOrder;
    @BindString(R.string.pref_sort_order_popularity_value)
    String prefSortOrderByPopularity;
    @BindString(R.string.pref_sort_order_top_rated_value)
    String prefSortOrderByTopRated;
    @BindString(R.string.pref_sort_order_favorites_value)
    String prefSortOrderByFavorites;
    @BindString(R.string.network_error_message)
    String networkErrorMessage;

    private MovieArrayAdapter movieArrayAdapter;
    private ArrayList<Movie> movies;
    private String lastSortBy;

    public MovieListActivityFragment() {
        movies = new ArrayList<>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, final @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnsNumber));
        movieArrayAdapter = new MovieArrayAdapter(movies, this);
        moviesRecyclerView.setAdapter(movieArrayAdapter);

        if (null != savedInstanceState) {
            movies = savedInstanceState.getParcelableArrayList(EXTRA_MOVIES);
            movieArrayAdapter.setMovies(movies);
            lastSortBy = savedInstanceState.getString(EXTRA_LAST_SORT);
        } else {
            lastSortBy = getSortByFromSettings();
            refreshMovies(lastSortBy);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        final String sortByFromSettings = getSortByFromSettings();
        sortMovies(sortByFromSettings);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != movies && !movies.isEmpty()) {
            outState.putParcelableArrayList(EXTRA_MOVIES, movies);
            outState.putString(EXTRA_LAST_SORT, lastSortBy);
        }
    }

    public void sortMovies(final String sortBy) {
        if (!sortBy.equals(lastSortBy)) {
            lastSortBy = sortBy;
            refreshMovies(lastSortBy);
        }
    }

    private String getSortByFromSettings() {
        return PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(prefSortOrder, prefSortOrderByPopularity);
    }

    private void refreshMovies(final String sortBy) {
        Maybe<List<Movie>> moviesMaybe = Maybe.empty();
        if (prefSortOrderByTopRated.equals(sortBy)) {
            moviesMaybe = localStorage.getTopRatedMovies();
        } else if (prefSortOrderByPopularity.equals(sortBy)) {
            moviesMaybe = localStorage.getPopularMovies();
        } else if (prefSortOrderByFavorites.equals(sortBy)) {
            moviesMaybe = localStorage.getStarredMovies();
        }
        moviesMaybe.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(movies -> {
                       this.movies = new ArrayList<>(movies);
                       movieArrayAdapter.setMovies(this.movies);
                   }, exception -> {
                       Timber.e(exception);
                       View view = getView();
                       if (null != view) {
                           Snackbar.make(view, networkErrorMessage, Snackbar.LENGTH_LONG)
                                   .show();
                       }
                       movies.clear();
                       movieArrayAdapter.setMovies(movies);
                   });
    }

    @Override
    public void onItemClick(final Movie movie) {
        Intent movieDetailsIntent = new Intent(getActivity(), MovieDetailsActivity.class);
        movieDetailsIntent.putExtra(MovieDetailsActivity.MOVIE, movie);
        startActivity(movieDetailsIntent);
    }
}
