package eu.bquepab.popularmovies.ui;

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
import eu.bquepab.popularmovies.data.api.TmdbService;
import eu.bquepab.popularmovies.model.Review;
import eu.bquepab.popularmovies.ui.adapter.ReviewArrayAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import javax.inject.Inject;

public class MovieDetailsReviewsActivityFragment extends Fragment {

    public static final String EXTRA_MOVIE = "movieId";
    private static final String EXTRA_REVIEWS = "reviews";
    @Inject
    TmdbService tmdbService;
    @BindView(R.id.reviews_view)
    RecyclerView reviewsRecyclerView;
    private ReviewArrayAdapter reviewsArrayAdapter;
    private ArrayList<Review> reviews;

    public MovieDetailsReviewsActivityFragment() {
        reviews = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details_reviews, container, false);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        reviewsRecyclerView.addItemDecoration(dividerItemDecoration);
        reviewsArrayAdapter = new ReviewArrayAdapter(reviews);
        reviewsRecyclerView.setAdapter(reviewsArrayAdapter);

        if (null != savedInstanceState) {
            reviews = savedInstanceState.getParcelableArrayList(EXTRA_REVIEWS);
            reviewsArrayAdapter.setReviews(reviews);
        } else {
            Bundle args = getArguments();
            if (null != args && args.containsKey(EXTRA_MOVIE)) {
                int movieId = args.getInt(EXTRA_MOVIE);
                refreshReviews(movieId);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != reviews && !reviews.isEmpty()) {
            outState.putParcelableArrayList(EXTRA_REVIEWS, reviews);
        }
    }

    private void refreshReviews(final int movieId) {
        tmdbService.getReviews(movieId, BuildConfig.THE_MOVIE_DATABASE_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewsResponse -> {
                    reviews = new ArrayList<>(reviewsResponse.results());
                    reviewsArrayAdapter.setReviews(reviews);
                });
    }
}
