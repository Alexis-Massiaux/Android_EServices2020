package android.eservices.insidefridge.data.repository.remote;

import android.eservices.insidefridge.CocktailApplication;
import android.eservices.insidefridge.data.api.CocktailDisplayService;
import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;

import io.reactivex.Single;

public class CocktailDisplayRemoteDataSource {

        private CocktailDisplayService cocktailDisplayService;

        public CocktailDisplayRemoteDataSource(CocktailDisplayService cocktailDisplayService) {
            this.cocktailDisplayService = cocktailDisplayService;
        }

        public Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords) {
            return this.cocktailDisplayService.getCocktails(keywords, CocktailApplication.API_KEY);
        }

        public Single<Cocktail> getCocktailFromId(String cocktailId) {
            return this.cocktailDisplayService.getCocktailFromId(cocktailId);
        }
}
