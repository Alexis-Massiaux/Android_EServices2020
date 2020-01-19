package android.eservices.insidefridge.data.repository;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.entity.CocktailEntity;
import android.eservices.insidefridge.data.repository.local.CocktailLocalDataSource;
import android.eservices.insidefridge.data.repository.mapper.CocktailToCocktailEntityMapper;
import android.eservices.insidefridge.data.repository.remote.CocktailDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class CocktailDisplayDataRepository implements CocktailDisplayRepository{

    private CocktailDisplayRemoteDataSource cocktailDisplayRemoteDataSource;
    private CocktailToCocktailEntityMapper cocktailToCocktailEntityMapper;
    private CocktailLocalDataSource cocktailLocalDataSource;

    public CocktailDisplayDataRepository(CocktailDisplayRemoteDataSource cocktailDisplayRemoteDataSource, CocktailToCocktailEntityMapper cocktailToCocktailEntityMapper, CocktailLocalDataSource cocktailLocalDataSource) {
        this.cocktailDisplayRemoteDataSource = cocktailDisplayRemoteDataSource;
        this.cocktailToCocktailEntityMapper = cocktailToCocktailEntityMapper;
        this.cocktailLocalDataSource = cocktailLocalDataSource;
    }

    @Override
    public Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords) {

        return cocktailDisplayRemoteDataSource.getCocktailSearchResponse(keywords);
    }

    @Override
    public Flowable<List<CocktailEntity>> getFavorites() {
        return cocktailLocalDataSource.loadFavorites();
    }

    @Override
    public Completable addCocktailToFavorites(String cocktailID) {
        return cocktailDisplayRemoteDataSource.getCocktailFromId(cocktailID)
                .map(new Function<Cocktail, CocktailEntity>() {
                    @Override
                    public CocktailEntity apply(Cocktail cocktail) throws Exception {
                        return cocktailToCocktailEntityMapper.map(cocktail);
                    }
                })
                .flatMapCompletable(new Function<CocktailEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(CocktailEntity cocktailEntity) throws Exception {
                        return cocktailLocalDataSource.addCocktailToFavorites(cocktailEntity);
                    }
                });
    }

    @Override
    public Completable removeCocktailFromFavorites(String cocktailID) {
        return cocktailLocalDataSource.removeCocktailsFromFavorites(cocktailID);
    }
}
