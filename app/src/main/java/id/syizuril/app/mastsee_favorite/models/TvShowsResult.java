package id.syizuril.app.mastsee_favorite.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.Date;

@Entity(tableName = TvShowsResult.TABLE_NAME)
public class TvShowsResult implements Parcelable {
    public static final String TABLE_NAME = "tvshows_table";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_VOTE_COUNT = "vote_count";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_TILE = "title";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_POSTER = "poster";
    public static final String COLUMN_ORI_LANG = "ori_lang";
    public static final String COLUMN_ORI_TITLE = "ori_title";
    public static final String COLUMN_BACKDROP = "backdrop";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE = "release_date";

    @ColumnInfo(name = COLUMN_ORI_TITLE)
    private String originalName;
    @ColumnInfo(name = COLUMN_TILE)
    private String name;
    @ColumnInfo(name = COLUMN_POPULARITY)
    private Double popularity;
    @ColumnInfo(name = COLUMN_VOTE_COUNT)
    private Integer voteCount;
    @ColumnInfo(name = COLUMN_RELEASE)
    private Date firstAirDate;
    @ColumnInfo(name = COLUMN_BACKDROP)
    private String backdropPath;
    @ColumnInfo(name = COLUMN_ORI_LANG)
    private String originalLanguage;
    @PrimaryKey
    @ColumnInfo(index = true, name = COLUMN_ID)
    private Long id;
    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    private Double voteAverage;
    @ColumnInfo(name = COLUMN_OVERVIEW)
    private String overview;
    @ColumnInfo(name = COLUMN_POSTER)
    private String posterPath;

    @Ignore
    public TvShowsResult() {

    }

    public static TvShowsResult fromContentValues(ContentValues values){
        final TvShowsResult tvResult = new TvShowsResult();
        if(values.containsKey(COLUMN_ID)){
            tvResult.id = values.getAsLong(COLUMN_ID);
        }
        if(values.containsKey(COLUMN_VOTE_COUNT)){
            tvResult.voteCount = values.getAsInteger(COLUMN_VOTE_COUNT);
        }
        if(values.containsKey(COLUMN_VOTE_AVERAGE)){
            tvResult.voteAverage = values.getAsDouble(COLUMN_VOTE_AVERAGE);
        }
        if(values.containsKey(COLUMN_TILE)){
            tvResult.name = values.getAsString(COLUMN_TILE);
        }
        if(values.containsKey(COLUMN_POPULARITY)){
            tvResult.popularity = values.getAsDouble(COLUMN_POPULARITY);
        }
        if(values.containsKey(COLUMN_POSTER)){
            tvResult.posterPath = values.getAsString(COLUMN_POSTER);
        }
        if(values.containsKey(COLUMN_ORI_LANG)){
            tvResult.originalLanguage = values.getAsString(COLUMN_ORI_LANG);
        }
        if(values.containsKey(COLUMN_ORI_TITLE)){
            tvResult.originalName = values.getAsString(COLUMN_ORI_TITLE);
        }
        if(values.containsKey(COLUMN_BACKDROP)){
            tvResult.backdropPath = values.getAsString(COLUMN_BACKDROP);
        }
        if(values.containsKey(COLUMN_OVERVIEW)){
            tvResult.overview = values.getAsString(COLUMN_OVERVIEW);
        }
        if(values.containsKey(COLUMN_RELEASE)){
            tvResult.firstAirDate = (Date) values.get(COLUMN_RELEASE);
        }
        return tvResult;
    }


    public String getOriginalName() {
        return originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Date getFirstAirDate() {
        return firstAirDate;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w533_and_h300_bestv2/"+backdropPath;
    }

    public String getBackdropPathAlt() {
        return backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2/"+posterPath;
    }

    public String getPosterPathAlt() {
        return posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeLong(this.firstAirDate != null ? this.firstAirDate.getTime() : -1);
        dest.writeString(this.backdropPath);
        dest.writeString(this.originalLanguage);
        dest.writeValue(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
    }

    public TvShowsResult(String originalName, String name, Double popularity, Integer voteCount, Date firstAirDate, String backdropPath, String originalLanguage, Long id, Double voteAverage, String overview, String posterPath) {
        this.originalName = originalName;
        this.name = name;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.id = id;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public TvShowsResult(Parcel in) {
        this.originalName = in.readString();
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpFirstAirDate = in.readLong();
        this.firstAirDate = tmpFirstAirDate == -1 ? null : new Date(tmpFirstAirDate);
        this.backdropPath = in.readString();
        this.originalLanguage = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Creator<TvShowsResult> CREATOR = new Creator<TvShowsResult>() {
        @Override
        public TvShowsResult createFromParcel(Parcel source) {
            return new TvShowsResult(source);
        }

        @Override
        public TvShowsResult[] newArray(int size) {
            return new TvShowsResult[size];
        }
    };
}
