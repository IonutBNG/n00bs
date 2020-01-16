package game.facade;

import game.control.GameController;
import game.converter.dto.ReviewDto;
import game.converter.dto.ViewGameDto;
import genre.converter.dto.GenreIdsListDto;
import user.converter.dto.UserIdDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 */

@Stateless
public class GameFacade {

    @EJB
    private GameController gameController;

    public List<ViewGameDto> getAllGames(){
        return this.gameController.getAllGames();
    }

    public List<ViewGameDto> getAllGamesWishlist(UserIdDto userIdDto){
        return this.gameController.getAllGamesWishlist(userIdDto.getId());
    }

    public List<ViewGameDto> getGamesByGenres(GenreIdsListDto genreIdsListDto){
        return this.gameController.getGamesByGenres(genreIdsListDto);
    }

    public void addReview(ReviewDto reviewDto){
        this.gameController.addReview(reviewDto);
    }

}
