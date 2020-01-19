package android.eservices.insidefridge.data.db;

import android.eservices.insidefridge.data.entity.CocktailEntity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {CocktailEntity.class}, version = 1)
public abstract class CocktailDataBase extends RoomDatabase {
    public abstract CocktailDao cocktailDao();
}
