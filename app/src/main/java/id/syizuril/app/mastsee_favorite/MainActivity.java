package id.syizuril.app.mastsee_favorite;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import id.syizuril.app.mastsee_favorite.models.MovieResult;
import id.syizuril.app.mastsee_favorite.provider.MovieContentProvider;

public class MainActivity extends AppCompatActivity {
    private MovieFavoriteViewModel movieFavoriteViewModel;
    private RecyclerView rvMovies;
    private TextView tvDeleteAll, tvNoData;
    private ImageView imgNoData;
    private MovieFavoriteAdapter mAdapter;

    private static final int LOADER_MOVIES = 1;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rvMovie);
        tvDeleteAll = findViewById(R.id.deleteAll);
        tvNoData = findViewById(R.id.tvNotFound);
        imgNoData = findViewById(R.id.ivNotFound);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvMovies.setLayoutManager(new GridLayoutManager(this, 5));
        } else {
            rvMovies.setLayoutManager(new GridLayoutManager(this, 3));
        }
        rvMovies.setHasFixedSize(true);
        mAdapter = new MovieFavoriteAdapter();
        rvMovies.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(LOADER_MOVIES, null, mLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            switch (id){
                case LOADER_MOVIES:
                    return new CursorLoader(getApplicationContext(), MovieContentProvider.URI_MOVIE, new String[]{MovieResult.COLUMN_TILE,MovieResult.COLUMN_BACKDROP,MovieResult.COLUMN_ORI_LANG,MovieResult.COLUMN_OVERVIEW,MovieResult.COLUMN_ORI_TITLE, MovieResult.COLUMN_POPULARITY,MovieResult.COLUMN_POSTER,MovieResult.COLUMN_RELEASE, MovieResult.COLUMN_VOTE_AVERAGE, MovieResult.COLUMN_VOTE_COUNT}, null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            switch (loader.getId()){
                case LOADER_MOVIES:
                    mAdapter.setMovies(data);
                    break;
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            switch (loader.getId()){
                case LOADER_MOVIES:
                    mAdapter.setMovies(null);
                    break;
            }
        }
    };
}
