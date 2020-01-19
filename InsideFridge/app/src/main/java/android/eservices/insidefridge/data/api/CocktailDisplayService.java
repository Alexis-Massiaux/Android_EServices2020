package android.eservices.insidefridge.data.api;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CocktailDisplayService {

    /**
     * Permet de récupérer une liste de cocktails selon un ingrédient
     * @param keywords - l'ingrédient
     * @return - une liste de cocktails
     */
    @Headers({"x-rapidapi-host: the-cocktail-db.p.rapidapi.com", "x-rapidapi-key: 307a2dfe3cmshab5301ebabb5427p126057jsn155145a71bdb"})
    @GET("filter.php")
    Single<CocktailSearchResponse> getCocktails(@Query("i") String keywords, @Query("key") String apiKey);

    /**
     * Permet de récupérer le détails d'un cocktail selon un id
     * @param cocktailID - l'id
     * @return - un cocktail
     */
    @Headers({"x-rapidapi-host: the-cocktail-db.p.rapidapi.com", "x-rapidapi-key: 307a2dfe3cmshab5301ebabb5427p126057jsn155145a71bdb"})
    @GET("lookup.php")
    Single<Cocktail> getCocktailFromId(@Query("i") String cocktailID);
}
