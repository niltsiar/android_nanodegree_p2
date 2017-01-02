package eu.bquepab.popularmovies.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.squareup.picasso.Picasso;
import eu.bquepab.popularmovies.BuildConfig;
import eu.bquepab.popularmovies.PopularMoviesApplication;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Trailer;
import eu.bquepab.popularmovies.ui.adapter.TrailerArrayAdapter;
import javax.inject.Inject;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.trailer_image)
    ImageView trailerImage;
    @BindView(R.id.trailer_name)
    TextView trailerName;

    @Inject
    Picasso picasso;

    private Trailer trailer;
    private TrailerArrayAdapter.OnTrailerClickListener listener;

    public TrailerViewHolder(final View itemView) {
        super(itemView);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Trailer trailer, final TrailerArrayAdapter.OnTrailerClickListener listener) {
        this.trailer = trailer;
        this.listener = listener;
        trailerName.setText(trailer.name());
        final String youtube_thumb_url = String.format(BuildConfig.YOUTUBE_IMG_URL, trailer.key());
        picasso.load(youtube_thumb_url).placeholder(R.drawable.ic_play_arrow_black_24dp).into(trailerImage);
    }

    @OnClick(R.id.trailer_item)
    public void onClick() {
        listener.onItemClick(trailer);
    }
}
