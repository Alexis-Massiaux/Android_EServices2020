package android.eservices.insidefridge.data.di;

import android.content.Context;
import android.eservices.insidefridge.data.api.CocktailDisplayService;
import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.db.CocktailDataBase;
import android.eservices.insidefridge.data.repository.CocktailDisplayDataRepository;
import android.eservices.insidefridge.data.repository.CocktailDisplayRepository;
import android.eservices.insidefridge.data.repository.remote.CocktailDisplayRemoteDataSource;

import androidx.room.Room;

import com.google.gson.Gson;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    private static Gson gson;
    private static Retrofit retrofit;
    private static Context applicationContext;
    private static CocktailDataBase cocktailDataBase;
    private static CocktailDisplayService cocktailDisplayService;
    private static CocktailDisplayRepository cocktailDisplayRepository;


    public static CocktailDisplayRepository getCocktailDisplayRepository() {
        if(cocktailDisplayRepository == null)
            cocktailDisplayRepository = new CocktailDisplayDataRepository
                    (new CocktailDisplayRemoteDataSource(getCocktailDisplayService())
                    //new BookDisplayLocalDataSource(getBookDatabase())
                    );
        return cocktailDisplayRepository;
    }

    public static CocktailDisplayService getCocktailDisplayService() {
        if(cocktailDisplayService == null) {
            cocktailDisplayService = getRetrofit().create(CocktailDisplayService.class);
        }
        return cocktailDisplayService;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://the-cocktail-db.p.rapidapi.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static CocktailDataBase getCocktailDatabase() {
        if (cocktailDataBase == null) {
            cocktailDataBase = Room.databaseBuilder(applicationContext,
                    CocktailDataBase.class, "cocktail-database").build();
        }
        return cocktailDataBase;
    }

}
