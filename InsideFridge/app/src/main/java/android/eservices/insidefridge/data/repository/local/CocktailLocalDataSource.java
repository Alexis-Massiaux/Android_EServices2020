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

    public Flowable<List<CocktailEntity>> loadFavorites(){
        return cocktailDataBase.cocktailDao().loadFavorites();
    }

    public Completable addCocktailToFavorites(CocktailEntity cocktailkEntity) {
        return cocktailDataBase.cocktailDao().addCocktailToFavorites(cocktailkEntity);
    }

    public Completable removeCocktailsFromFavorites(String id) {
        return cocktailDataBase.cocktailDao().deleteCocktailFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return cocktailDataBase.cocktailDao().getFavoriteIdList();
    }
}
