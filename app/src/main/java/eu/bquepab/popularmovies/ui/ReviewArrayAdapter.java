package eu.bquepab.popularmovies.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewArrayAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<Review> reviews;

    public ReviewArrayAdapter(final List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ReviewViewHolder holder, final int position) {
        holder.bind(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(final List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
        notifyDataSetChanged();
    }
}
