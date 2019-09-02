package id.syizuril.app.mastsee_favorite;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.syizuril.app.mastsee_favorite.models.MovieResult;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieHolder> {
    private Cursor mCursor;

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movies, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieFavoriteAdapter.MovieHolder holder, int position) {
        if(mCursor.moveToPosition(position)){
            holder.tvTitle.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieResult.COLUMN_TILE)));
            Glide.with(holder.itemView.getContext())
                    .load(mCursor.getString(mCursor.getColumnIndexOrThrow(MovieResult.COLUMN_POSTER)))
                    .apply(new RequestOptions().override(500,750))
                    .into(holder.imgCover);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    void setMovies(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView imgCover, imgBanner;
        TextView tvTitle,tvDate, tvScore, tvOverview, tvVoteCount, tvOriginalLanguage, tvOriginalTitle, tvPopularityPoint;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
//            tvScore = itemView.findViewById(R.id.tvScore);
//            tvOverview = itemView.findViewById(R.id.tvOverview);
//            tvVoteCount = itemView.findViewById(R.id.tvCount);
//            tvOriginalLanguage = itemView.findViewById(R.id.tvOriginalLanguage);
//            tvOriginalTitle = itemView.findViewById(R.id.tvOriginalTitle);
//            tvPopularityPoint = itemView.findViewById(R.id.tvPopularityPoint);
            imgCover = itemView.findViewById(R.id.imgCover);
//            imgBanner = itemView.findViewById(R.id.banner);
        }
    }
}
