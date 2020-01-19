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

    @Query("SELECT * from cocktailentity")
    Flowable<List<CocktailEntity>> loadFavorites();

    @Insert
    public Completable addBookToFavorites(CocktailEntity cocktailkEntity);

    @Query("DELETE FROM cocktailentity WHERE id = :id")
    public Completable deleteBookFromFavorites(String id);

    @Query("SELECT id from cocktailentity")
    Single<List<String>> getFavoriteIdList();
}
