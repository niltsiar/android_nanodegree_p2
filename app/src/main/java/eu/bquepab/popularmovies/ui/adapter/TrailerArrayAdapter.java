package eu.bquepab.popularmovies.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eu.bquepab.popularmovies.R;
import eu.bquepab.popularmovies.model.Trailer;
import eu.bquepab.popularmovies.ui.viewholder.TrailerViewHolder;
import java.util.ArrayList;
import java.util.List;

public class TrailerArrayAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private List<Trailer> trailers;

    public TrailerArrayAdapter(final List<Trailer> movies) {
        this.trailers = new ArrayList<>(movies);
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new TrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TrailerViewHolder holder, final int position) {
        holder.bind(trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public void setTrailers(final List<Trailer> trailers) {
        this.trailers = new ArrayList<>(trailers);
        notifyDataSetChanged();
    }
}
