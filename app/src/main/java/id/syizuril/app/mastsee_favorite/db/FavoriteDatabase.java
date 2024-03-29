package id.syizuril.app.mastsee_favorite.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import id.syizuril.app.mastsee_favorite.models.MovieResult;
import id.syizuril.app.mastsee_favorite.models.TvShowsResult;

@Database(entities = {MovieResult.class, TvShowsResult.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    @SuppressWarnings("WeakerAccess")
    public abstract MovieFavoriteDao movieFavoriteDao();
    @SuppressWarnings("WeakerAccess")
    public abstract TvShowFavoriteDao tvShowFavoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
