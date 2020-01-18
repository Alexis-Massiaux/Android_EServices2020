package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.repository.remote.CocktailDisplayRemoteDataSource;

import io.reactivex.Single;

public class CocktailDisplayDataRepository implements  CocktailDisplayRepository{

    private CocktailDisplayRemoteDataSource cocktailDisplayRemoteDataSource;

    public CocktailDisplayDataRepository(CocktailDisplayRemoteDataSource cocktailDisplayRemoteDataSource) {
        this.cocktailDisplayRemoteDataSource = cocktailDisplayRemoteDataSource;
    }

    @Override
    public Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords) {

        return cocktailDisplayRemoteDataSource.getCocktailSearchResponse(keywords);
    }
}
