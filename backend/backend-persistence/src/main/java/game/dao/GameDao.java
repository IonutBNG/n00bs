package game.dao;

import game.entity.GameEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 */

@Stateless
public class GameDao {

    @PersistenceContext(unitName = "backend-persistence")
    private EntityManager entityManager;

    /**
     * Returns all games from the database
     * @return <list>GameEntity</list>
     */
    public List<GameEntity> getAllGames(){
        return this.entityManager
                .createNamedQuery(GameEntity.GET_ALL_GAMES, GameEntity.class)
                .getResultList();
    }
}
