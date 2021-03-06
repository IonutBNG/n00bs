package game.converter;

//import com.sun.org.apache.regexp.internal.RE;
import game.converter.dto.CompleteGameDto;
import game.converter.dto.ReviewDto;
import game.converter.dto.ViewGameDto;
import game.converter.dto.ViewReviewDto;
import game.entity.GameEntity;
import game.entity.ReviewEntity;

import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 */

@Stateless
public class GameConverter {

    /**
     * Converts the game entity to view dto
     * @param gameEntity user for the ViewGameDto
     * @return ViewGameDto
     */
    public ViewGameDto convertGameEntityToViewGameDto(GameEntity gameEntity){
        return new ViewGameDto(
                gameEntity.getId(),
                gameEntity.getName(),
                gameEntity.getRelease_date(),
                gameEntity.getSummary(),
                gameEntity.getRating(),
                gameEntity.getCover_url()
        );
    }

    public ReviewEntity convertReviewDtoToReviewEntity(ReviewDto reviewDto){
        return new ReviewEntity(
                reviewDto.getId_user(),
                reviewDto.getId_game(),
                reviewDto.getReview(),
                reviewDto.getRating()
        );
    }

    public CompleteGameDto convertGameEntityToCompleteGameDto(GameEntity gameEntity){
        return new CompleteGameDto(
                gameEntity.getId(),
                gameEntity.getName(),
                gameEntity.getRelease_date(),
                gameEntity.getSummary(),
                gameEntity.getRating(),
                gameEntity.getCover_url(),
                gameEntity.getCompanyEntityList(),
                gameEntity.getGenreEntityList(),
                gameEntity.getPlatformEntityList()
        );
    }

    public ReviewDto convertReviewEntityToReviewDto(ReviewEntity reviewEntity){
        return new ReviewDto(
                reviewEntity.getId_user(),
                reviewEntity.getId_game(),
                reviewEntity.getRating(),
                reviewEntity.getComment()
                );
    }
//    public ViewReviewDto convertReviewEntityToViewReviewDto(ReviewEntity reviewEntity){
//        return new ViewReviewDto(
//                reviewEntity.getId_user(),
//                reviewEntity.getUsername(),
//                reviewEntity.getId_game(),
//                reviewEntity.getRating(),
//                reviewEntity.getComment());
//    }
    //publ
    //public List<Long>

}
