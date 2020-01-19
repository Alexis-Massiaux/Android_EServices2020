package android.eservices.insidefridge.data.db;

import android.eservices.insidefridge.data.entity.CocktailEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CocktailEntity.class}, version = 1, exportSchema = false)
public abstract class CocktailDataBase extends RoomDatabase {
    public abstract CocktailDao cocktailDao();
}
