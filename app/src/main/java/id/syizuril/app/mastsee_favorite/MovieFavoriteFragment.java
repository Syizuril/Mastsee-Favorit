package id.syizuril.app.mastsee_favorite;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import id.syizuril.app.mastsee_favorite.models.MovieResult;
import id.syizuril.app.mastsee_favorite.provider.MovieContentProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvMovies;
    private MovieFavoriteAdapter mAdapter;
    private ImageView ivNotFound;
    private TextView tvNotFound;

    private static final int LOADER_MOVIES = 1;
    public static String RESUME = "resume";

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rvMovie);
        ivNotFound = view.findViewById(R.id.ivNotFound);
        tvNotFound = view.findViewById(R.id.tvNotFound);

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL, false));
        mAdapter = new MovieFavoriteAdapter();
        rvMovies.setAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_MOVIES, null, mLoaderCallbacks);

        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(() -> refresh());
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            switch (id){
                case LOADER_MOVIES:
                    return new CursorLoader(Objects.requireNonNull(getActivity()), MovieContentProvider.URI_MOVIE, new String[]{MovieResult.COLUMN_TILE,MovieResult.COLUMN_BACKDROP,MovieResult.COLUMN_ORI_LANG,MovieResult.COLUMN_OVERVIEW,MovieResult.COLUMN_ORI_TITLE, MovieResult.COLUMN_POPULARITY,MovieResult.COLUMN_POSTER,MovieResult.COLUMN_RELEASE, MovieResult.COLUMN_VOTE_AVERAGE, MovieResult.COLUMN_VOTE_COUNT}, null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            switch (loader.getId()){
                case LOADER_MOVIES:
                    mAdapter.setMovies(data);
                    if(data.getCount()==0){
                        tvNotFound.setVisibility(View.VISIBLE);
                        ivNotFound.setVisibility(View.VISIBLE);
                    }else if(data.getCount()>0){
                        tvNotFound.setVisibility(View.GONE);
                        ivNotFound.setVisibility(View.GONE);
                    }
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

    private void refresh(){
        //insert dummy string of current date/time
        getLoaderManager().restartLoader(LOADER_MOVIES, null, mLoaderCallbacks);
        swipeRefreshLayout.setRefreshing(false);
    }
}
