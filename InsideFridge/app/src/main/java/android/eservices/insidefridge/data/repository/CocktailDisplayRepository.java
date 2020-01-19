package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.entity.CocktailEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CocktailDisplayRepository {

    Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords);

    Flowable<List<CocktailEntity>> getFavorites();

    Completable addCocktailToFavorites(String cocktailID);

    Completable removeCocktailFromFavorites(String cocktailID);
}
