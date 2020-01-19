package android.eservices.insidefridge.data.db;

import android.eservices.insidefridge.data.entity.CocktailEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CocktailDao {

    /**
     * Récupére tous les cocktails en favoris
     * @return - une liste de cocktails
     */
    @Query("SELECT * from cocktailentity")
    Flowable<List<CocktailEntity>> loadFavorites();

    /**
     * Enregistre un cocktail placer en favoris
     * @param cocktailkEntity - le cocktail
     * @return - une réponse de l'enregistrement
     */
    @Insert
    public Completable addCocktailToFavorites(CocktailEntity cocktailkEntity);

    /**
     * Supprime un cocktail retirer de la liste des favoris
     * @param id - id du cocktail supprimer
     * @return - une réponse de l'efffacement
     */
    @Query("DELETE FROM cocktailentity WHERE id = :id")
    public Completable deleteCocktailFromFavorites(String id);

    /**
     * Retourne une liste d'id des cocktails enregistrés localement
     * @return - une liste d'id
     */
    @Query("SELECT id from cocktailentity")
    Single<List<String>> getFavoriteIdList();
}
