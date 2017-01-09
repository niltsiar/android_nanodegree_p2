package eu.bquepab.popularmovies;

import dagger.Component;
import eu.bquepab.popularmovies.data.api.ApiModule;
import eu.bquepab.popularmovies.ui.MovieDetailsActivityFragment;
import eu.bquepab.popularmovies.ui.MovieDetailsReviewsActivityFragment;
import eu.bquepab.popularmovies.ui.MovieDetailsTrailersActivityFragment;
import eu.bquepab.popularmovies.ui.MovieListActivityFragment;
import eu.bquepab.popularmovies.ui.viewholder.MovieViewHolder;
import eu.bquepab.popularmovies.ui.viewholder.TrailerViewHolder;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(MovieListActivityFragment mainActivityFragment);

    void inject(MovieViewHolder movieViewHolder);

    void inject(MovieDetailsActivityFragment movieDetailsActivityFragment);

    void inject(MovieDetailsReviewsActivityFragment movieDetailsReviewsActivityFragment);

    void inject(MovieDetailsTrailersActivityFragment movieDetailsTrailersActivityFragment);

    void inject(TrailerViewHolder trailerViewHolder);

    final class Initializer {
        private Initializer() {
            //Avoid instances
        }

        public static ApplicationComponent init(final PopularMoviesApplication app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .apiModule(new ApiModule())
                    .build();
        }
    }
}
