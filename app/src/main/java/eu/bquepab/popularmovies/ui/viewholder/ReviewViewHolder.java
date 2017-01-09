package eu.bquepab.popularmovies.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Review;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.author)
    TextView authorView;
    @BindView(R.id.review)
    TextView reviewView;

    private Review review;

    public ReviewViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Review review) {
        this.review = review;
        authorView.setText(review.author());
        reviewView.setText(review.content());
    }
}
