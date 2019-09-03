package id.syizuril.app.mastsee_favorite;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.syizuril.app.mastsee_favorite.models.TvShowsResult;

public class TvFavoriteAdapter extends RecyclerView.Adapter<TvFavoriteAdapter.MovieHolder> {
    private Cursor mCursor;

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movies, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TvFavoriteAdapter.MovieHolder holder, int position) {
        if(mCursor.moveToPosition(position)){
            holder.tvTitle.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(TvShowsResult.COLUMN_TILE)));
            Glide.with(holder.itemView.getContext())
                    .load(mCursor.getString(mCursor.getColumnIndexOrThrow(TvShowsResult.COLUMN_BACKDROP)))
                    .apply(new RequestOptions().override(500,750))
                    .into(holder.imgBanner);
            double score = mCursor.getDouble(mCursor.getColumnIndexOrThrow(TvShowsResult.COLUMN_VOTE_AVERAGE))/2;
            holder.tvScore.setRating((float) score);
            String time = mCursor.getString(mCursor.getColumnIndexOrThrow(TvShowsResult.COLUMN_RELEASE));
            String date = DateFormat.format("MMM, dd yyyy", Long.parseLong(time)).toString();
            holder.tvDate.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    void setTv(Cursor cursor){
        mCursor = cursor;
        notifyDataSetChanged();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        TextView tvTitle,tvDate;
        RatingBar tvScore;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvScore = itemView.findViewById(R.id.rating_bar);
            imgBanner = itemView.findViewById(R.id.iv_banner);
        }
    }
}
