package eu.bquepab.popularmovies.ui.adapter;

import android.support.v7.util.DiffUtil;
import eu.bquepab.popularmovies.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieDiffCallback extends DiffUtil.Callback {

    List<Movie> oldMovies;
    List<Movie> newMovies;

    public MovieDiffCallback(final List<Movie> oldMovies, final List<Movie> newMovies) {
        this.oldMovies = null != oldMovies ? oldMovies : new ArrayList<>();
        this.newMovies = null != newMovies ? newMovies : new ArrayList<>();
    }

    @Override
    public int getOldListSize() {
        return oldMovies.size();
    }

    @Override
    public int getNewListSize() {
        return newMovies.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {
        return oldMovies.get(oldItemPosition).id() == newMovies.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {
        return oldMovies.get(oldItemPosition).equals(newMovies.get(newItemPosition));
    }
}
