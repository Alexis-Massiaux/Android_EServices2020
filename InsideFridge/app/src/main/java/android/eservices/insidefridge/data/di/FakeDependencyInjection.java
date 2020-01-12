package android.eservices.insidefridge.data.di;

import android.eservices.insidefridge.data.api.CocktailDisplayService;

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

    private static CocktailDisplayService cocktailDisplayService;

    public static CocktailDisplayService getCocktailDisplayService() {
        if(cocktailDisplayService == null) {
            //cocktailDisplayService =
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
                    .baseUrl("https://the-cocktail-db.p.rapidapi.com/filter.php")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

}
