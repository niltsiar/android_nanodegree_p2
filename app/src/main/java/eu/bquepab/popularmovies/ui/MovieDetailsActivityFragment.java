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
import eu.bquepab.popularmovies.data.DataRepository;
import eu.bquepab.popularmovies.model.Movie;
import eu.bquepab.popularmovies.model.Review;
import eu.bquepab.popularmovies.model.Trailer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
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
    DataRepository dataRepository;

    private Movie movie;
    private ArrayList<Review> reviews;
    private ArrayList<Trailer> trailers;

    public MovieDetailsActivityFragment() {
        reviews = new ArrayList<>(0);
        trailers = new ArrayList<>(0);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        PopularMoviesApplication.component()
                                .inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movie = getActivity().getIntent()
                             .getParcelableExtra(MovieDetailsActivity.MOVIE);

        picasso.load(movie.posterUrl())
               .into(moviePoster);
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

        dataRepository.isFavoriteMovie(movie)
                      .subscribeOn(Schedulers.io())
                      .subscribe(isFavorite -> {
                          dataRepository.getReviews(movie, isFavorite)
                                        .subscribe(dataReviews -> reviews = new ArrayList<>(dataReviews), exception -> {
                                        });
                          dataRepository.getTrailers(movie, isFavorite)
                                        .subscribe(dataTrailers -> trailers = new ArrayList<>(dataTrailers), exception -> {
                                        });
                      });

        showFavoriteMovieStatus();

        showMovieDetailsFragment();
    }

    private void showFavoriteMovieStatus() {
        dataRepository.isFavoriteMovie(movie)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(isFavorite -> {
                          if (isFavorite) {
                              DrawableCompat.setTint(movieFavoriteIcon.getDrawable(), primaryColor);
                          } else {
                              DrawableCompat.setTint(movieFavoriteIcon.getDrawable(), whiteDark);
                          }
                      });
    }

    @OnClick(R.id.movie_favorite_icon)
    public void onFavoriteMovie() {
        dataRepository.isFavoriteMovie(movie)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(isFavorite -> {
                          if (isFavorite) {
                              dataRepository.deleteMovie(movie);
                          } else {
                              dataRepository.saveMovie(movie, reviews, trailers);
                          }
                          showFavoriteMovieStatus();
                      });
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
        reviewsFragmentArgs.putParcelableArrayList(MovieDetailsReviewsActivityFragment.EXTRA_REVIEWS, reviews);
        reviewsFragment.setArguments(reviewsFragmentArgs);
        showFragment(reviewsFragment);
    }

    private void showMovieTrailersFragment() {
        MovieDetailsTrailersActivityFragment trailersFragment = new MovieDetailsTrailersActivityFragment();
        Bundle trailersFragmentArgs = new Bundle();
        trailersFragmentArgs.putParcelableArrayList(MovieDetailsTrailersActivityFragment.EXTRA_TRAILERS, trailers);
        trailersFragment.setArguments(trailersFragmentArgs);
        showFragment(trailersFragment);
    }

    private void showFragment(final Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_movie_details, fragment);
        transaction.commit();
    }
}
