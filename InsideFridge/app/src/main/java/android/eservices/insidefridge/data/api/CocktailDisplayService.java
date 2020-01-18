package android.eservices.insidefridge.data.api;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CocktailDisplayService {

    @Headers({"x-rapidapi-host: the-cocktail-db.p.rapidapi.com", "x-rapidapi-key: 307a2dfe3cmshab5301ebabb5427p126057jsn155145a71bdb"})
    @GET("search.php")
    Single<CocktailSearchResponse> getCocktails(@Query("i") String keywords, @Query("key") String apiKey);
}
