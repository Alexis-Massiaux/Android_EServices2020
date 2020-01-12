package android.eservices.insidefridge.data.api;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailDisplayService {

    @GET("cocktails")
    Single<CocktailSearchResponse> getCocktails(@Query("q") String keywords, @Query("key") String apiKey);
}
