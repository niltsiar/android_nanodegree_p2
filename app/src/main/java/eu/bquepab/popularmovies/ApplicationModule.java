package eu.bquepab.popularmovies;

import android.content.Context;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
public class ApplicationModule {

    private final PopularMoviesApplication application;

    public ApplicationModule(final PopularMoviesApplication app) {
        this.application = app;
    }

    @Provides
    @Singleton
    static OkHttpClient providesOkHttpClient() {
        final String OKHTTP_TAG = "OkHttp";
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

                @Override
                public void log(String message) {
                    Timber.tag(OKHTTP_TAG).d(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor).build();
        }
        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    static Picasso providesPicasso(final Context context, final OkHttpClient okHttpClient) {
        final OkHttpClient cachedOkHttpClient = okHttpClient.newBuilder()
                .cache(OkHttp3Downloader.createDefaultCache(context))
                .build();

        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(cachedOkHttpClient))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }
}
