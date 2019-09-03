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

import id.syizuril.app.mastsee_favorite.models.TvShowsResult;
import id.syizuril.app.mastsee_favorite.provider.TvContentProvider;

public class TvShowFavoriteFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvMovies;
    private TvFavoriteAdapter mAdapter;
    private ImageView ivNotFound;
    private TextView tvNotFound;

    private static final int LOADER_TV = 2;
    
    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rvMovie);
        ivNotFound = view.findViewById(R.id.ivNotFound);
        tvNotFound = view.findViewById(R.id.tvNotFound);

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL, false));
        mAdapter = new TvFavoriteAdapter();
        rvMovies.setAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_TV, null, mLoaderCallbacks);

        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(() -> refresh());
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            switch (id){
                case LOADER_TV:
                    return new CursorLoader(Objects.requireNonNull(getActivity()), TvContentProvider.URI_TV, new String[]{TvShowsResult.COLUMN_TILE,TvShowsResult.COLUMN_BACKDROP,TvShowsResult.COLUMN_ORI_LANG,TvShowsResult.COLUMN_OVERVIEW,TvShowsResult.COLUMN_ORI_TITLE, TvShowsResult.COLUMN_POPULARITY,TvShowsResult.COLUMN_POSTER,TvShowsResult.COLUMN_RELEASE, TvShowsResult.COLUMN_VOTE_AVERAGE, TvShowsResult.COLUMN_VOTE_COUNT}, null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            switch (loader.getId()){
                case LOADER_TV:
                    mAdapter.setTv(data);
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
                case LOADER_TV:
                    mAdapter.setTv(null);
                    break;
            }
        }
    };

    private void refresh(){
        //insert dummy string of current date/time
        getLoaderManager().restartLoader(LOADER_TV, null, mLoaderCallbacks);
        swipeRefreshLayout.setRefreshing(false);
    }
}
