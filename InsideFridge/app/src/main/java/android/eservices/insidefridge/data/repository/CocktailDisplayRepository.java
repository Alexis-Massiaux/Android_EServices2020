package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.entity.CocktailEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CocktailDisplayRepository {

    /**
     * Renvoi une liste de coktails selon un ingrédient
     * @param keywords - l'ingredient
     * @return - une liste de coktails
     */
    Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords);

    /**
     * Récupére tous les cocktails en favoris
     * @return - une liste de cocktails
     */
    Flowable<List<CocktailEntity>> getFavorites();

    /**
     * Enregistre un cocktail placer en favoris
     * @param cocktailID - l'id du cocktail
     * @return - une réponse de l'enregistrement
     */
    Completable addCocktailToFavorites(String cocktailID);

    /**
     * Supprime un cocktail retirer de la liste des favoris
     * @param cocktailID - id du cocktail supprimer
     * @return - une réponse de l'efffacement
     */
    Completable removeCocktailFromFavorites(String cocktailID);
}
