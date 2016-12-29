package eu.bquepab.popularmovies.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import eu.bquepab.popularmovies.PopularMoviesApplication;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Trailer;
import javax.inject.Inject;

public class TrailerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.trailer_image)
    ImageView trailerImage;
    @BindView(R.id.trailer_name)
    TextView trailerName;

    @Inject
    Picasso picasso;

    private Trailer trailer;

    public TrailerViewHolder(final View itemView) {
        super(itemView);
        PopularMoviesApplication.component().inject(this);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final Trailer trailer) {
        this.trailer = trailer;
        trailerName.setText(trailer.name());
        String yt_thumbnail_url = "https://img.youtube.com/vi/" + trailer.key() + "/0.jpg";
        picasso.load(yt_thumbnail_url).placeholder(R.drawable.ic_play_arrow_black_24dp).into(trailerImage);
    }
}
