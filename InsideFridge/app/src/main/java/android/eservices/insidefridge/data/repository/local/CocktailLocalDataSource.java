package android.eservices.insidefridge.data.repository.local;

import android.eservices.insidefridge.data.db.CocktailDataBase;
import android.eservices.insidefridge.data.entity.CocktailEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class CocktailLocalDataSource {

    private CocktailDataBase cocktailDataBase;

    public CocktailLocalDataSource (CocktailDataBase cocktailDataBase) {
        this.cocktailDataBase = cocktailDataBase;
    }

    /**
     * Récupére tous les cocktails en favoris
     * @return - une liste de cocktails
     */
    public Flowable<List<CocktailEntity>> loadFavorites(){
        return cocktailDataBase.cocktailDao().loadFavorites();
    }

    /**
     * Enregistre un cocktail placer en favoris
     * @param cocktailkEntity - le cocktail
     * @return - une réponse de l'enregistrement
     */
    public Completable addCocktailToFavorites(CocktailEntity cocktailkEntity) {
        return cocktailDataBase.cocktailDao().addCocktailToFavorites(cocktailkEntity);
    }

    /**
     * Supprime un cocktail retirer de la liste des favoris
     * @param id - id du cocktail supprimer
     * @return - une réponse de l'efffacement
     */
    public Completable removeCocktailsFromFavorites(String id) {
        return cocktailDataBase.cocktailDao().deleteCocktailFromFavorites(id);
    }

    /**
     * Retourne une liste d'id des cocktails enregistrés localement
     * @return - une liste d'id
     */
    public Single<List<String>> getFavoriteIdList() {
        return cocktailDataBase.cocktailDao().getFavoriteIdList();
    }
}
