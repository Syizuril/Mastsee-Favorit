package id.syizuril.app.mastsee_favorite.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "tvshows_table")
public class TvShowsResult implements Parcelable {

    private String originalName;
    private String name;
    private Double popularity;
    private Integer voteCount;
    private Date firstAirDate;
    private String backdropPath;
    private String originalLanguage;
    @PrimaryKey
    private Integer id;
    private Double voteAverage;
    private String overview;
    private String posterPath;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TvShowsResult(String originalName, String name, Double popularity, Integer voteCount, Date firstAirDate, String backdropPath, String originalLanguage, Integer id, Double voteAverage, String overview, String posterPath) {
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
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
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
