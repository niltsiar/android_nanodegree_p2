package eu.bquepab.popularmovies.ui.adapter;

import android.support.v7.util.DiffUtil;
import eu.bquepab.popularmovies.model.Movie;
import java.util.List;

public class MovieDiffCallback extends DiffUtil.Callback {

    List<Movie> oldMovies;
    List<Movie> newMovies;

    public MovieDiffCallback(List<Movie> oldMovies, List<Movie> newMovies) {
        this.oldMovies = oldMovies;
        this.newMovies = newMovies;
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
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovies.get(oldItemPosition).id() == newMovies.get(newItemPosition).id();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovies.get(oldItemPosition).equals(newMovies.get(newItemPosition));
    }
}
