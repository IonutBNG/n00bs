package game.entity;

import company.entity.CompanyEntity;
import genre.entity.GenreEntity;
import platform.entity.PlatformEntity;
import utils.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 */

@Entity
@Table(name = "games")
@NamedQueries(
        {
                @NamedQuery(name = GameEntity.GET_ALL_GAMES, query = "Select game from GameEntity game"),
                @NamedQuery(name = GameEntity.GET_ALL_GAMES_WISHLIST, query = "Select game from GameEntity game join WishlistEntity w on w.id_game = game.id WHERE w.id_user = :id "),
                @NamedQuery(name = GameEntity.GET_GAMES_BY_GENRES, query = "Select game from GameEntity game join GamesGenresEntity gg on gg.game_id = game.id WHERE gg.genre_id in :list "),
                @NamedQuery(name = GameEntity.GET_GAME_BY_ID, query = "Select game from GameEntity game where game.id = :" + GameEntity.ID)

        }
)
public class GameEntity extends BaseEntity<Long> {
    public static final String GET_ALL_GAMES = "GameEntity.getAllGames";
    public static final String GET_ALL_GAMES_WISHLIST = "GameEntity.getAllGamesWishlist";
    public static final String GET_GAMES_BY_GENRES = "GameEntity.getGamesByGenres";
    public static final String ID = "id";
    public static final String GET_GAME_BY_ID = "GameEntity.getGameById";

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private String release_date;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "cover_url", nullable = false)
    private String cover_url;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "games_companies",
            joinColumns = @JoinColumn(name="id_game", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_company",referencedColumnName = "id",nullable = false)
    )
    private List<CompanyEntity> companyEntityList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "games_genres",
            joinColumns = @JoinColumn(name="game_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="genre_id",referencedColumnName = "id",nullable = false)
    )
    private List<GenreEntity> genreEntityList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "games_platforms",
            joinColumns = @JoinColumn(name="game_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="platform_id",referencedColumnName = "id",nullable = false)
    )
    private List<PlatformEntity> platformEntityList = new ArrayList<>();



    public GameEntity() {
    }

    public GameEntity(Long id,String name, String release_date, String summary, Double rating, String cover_url) {
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.summary = summary;
        this.rating = rating;
        this.cover_url = cover_url;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<CompanyEntity> getCompanyEntityList() {
        return companyEntityList;
    }

    public void setCompanyEntityList(List<CompanyEntity> companyEntityList) {
        this.companyEntityList = companyEntityList;
    }

    public List<GenreEntity> getGenreEntityList() {
        return genreEntityList;
    }

    public void setGenreEntityList(List<GenreEntity> genreEntityList) {
        this.genreEntityList = genreEntityList;
    }

    public List<PlatformEntity> getPlatformEntityList() {
        return platformEntityList;
    }

    public void setPlatformEntityList(List<PlatformEntity> platformEntityList) {
        this.platformEntityList = platformEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEntity that = (GameEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(release_date, that.release_date) &&
                Objects.equals(id, that.id) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(cover_url, that.cover_url) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name, release_date, summary, rating, cover_url);
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", summary='" + summary + '\'' +
                ", rating=" + rating +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
