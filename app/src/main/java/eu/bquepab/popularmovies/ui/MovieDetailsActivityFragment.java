package eu.bquepab.popularmovies.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import eu.bquepab.popularmovies.PopularMoviesApplication;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.data.LocalDataStore;
import eu.bquepab.popularmovies.model.Movie;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;
import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;
    @BindView(R.id.movie_user_rating)
    TextView movieUserRating;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.movie_favorite_icon)
    ImageView movieFavoriteIcon;
    @BindColor(R.color.colorPrimary)
    int primaryColor;
    @BindColor(R.color.whiteDark)
    int whiteDark;

    @Inject
    Picasso picasso;

    @Inject
    LocalDataStore localDataStore;

    private Movie movie;

    public MovieDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movie = getActivity().getIntent().getParcelableExtra(MovieDetailsActivity.MOVIE);

        picasso.load(movie.posterUrl()).into(moviePoster);
        movieTitle.setText(movie.title());
        movieReleaseDate.setText(movie.releaseDate());
        movieUserRating.setText(String.format(Locale.getDefault(), "%.2f", movie.userRating()));

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_movie_details:
                    showMovieDetailsFragment();
                    break;
                case R.id.action_movie_reviews:
                    showMovieReviewsFragment();
                    break;
                case R.id.action_movie_trailers:
                    showMovieTrailersFragment();
                    break;
            }
            return true;
        });

        localDataStore.isFavoriteMovie(movie)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(isFavorite -> {
                          if (isFavorite) {
                              DrawableCompat.setTint(movieFavoriteIcon.getDrawable(), primaryColor);
                          } else {
                              DrawableCompat.setTint(movieFavoriteIcon.getDrawable(), whiteDark);
                          }
                      });

        showMovieDetailsFragment();
    }

    @OnClick(R.id.movie_favorite_icon)
    public void onFavoriteMovie() {

    }

    private void showMovieDetailsFragment() {
        MovieDetailsSynopsisActivityFragment synopsisFragment = new MovieDetailsSynopsisActivityFragment();
        Bundle synosisFragmentArgs = new Bundle();
        synosisFragmentArgs.putString(MovieDetailsSynopsisActivityFragment.EXTRA_SYNOPSIS, movie.synopsis());
        synopsisFragment.setArguments(synosisFragmentArgs);
        showFragment(synopsisFragment);
    }

    private void showMovieReviewsFragment() {
        MovieDetailsReviewsActivityFragment reviewsFragment = new MovieDetailsReviewsActivityFragment();
        Bundle reviewsFragmentArgs = new Bundle();
        reviewsFragmentArgs.putInt(MovieDetailsReviewsActivityFragment.EXTRA_MOVIE, movie.id());
        reviewsFragment.setArguments(reviewsFragmentArgs);
        showFragment(reviewsFragment);
    }

    private void showMovieTrailersFragment() {
        MovieDetailsTrailersActivityFragment trailersFragment = new MovieDetailsTrailersActivityFragment();
        Bundle trailersFragmentArgs = new Bundle();
        trailersFragmentArgs.putInt(MovieDetailsTrailersActivityFragment.EXTRA_MOVIE, movie.id());
        trailersFragment.setArguments(trailersFragmentArgs);
        showFragment(trailersFragment);
    }

    private void showFragment(final Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_movie_details, fragment);
        transaction.commit();
    }
}
