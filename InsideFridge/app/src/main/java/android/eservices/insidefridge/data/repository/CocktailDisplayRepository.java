package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.entity.CocktailEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CocktailDisplayRepository {

    Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords);

    Completable addCocktailToFavorites(String cocktailID);

    Completable removeCocktailFromFavorites(String cocktailID);
}
