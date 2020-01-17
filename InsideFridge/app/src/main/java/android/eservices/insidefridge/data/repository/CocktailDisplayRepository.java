package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;

import io.reactivex.Single;

public interface CocktailDisplayRepository {

    Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords);
}
