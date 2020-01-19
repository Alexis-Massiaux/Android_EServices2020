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

        /**
         * Permet de récupérer une liste de cocktails selon un ingrédient
        * @param keywords - l'ingrédient
        * @return - une liste de cocktails
        */
        public Single<CocktailSearchResponse> getCocktailSearchResponse(String keywords) {
            return this.cocktailDisplayService.getCocktails(keywords, CocktailApplication.API_KEY);
        }

        /**
        * Permet de récupérer le détails d'un cocktail selon un id
        * @param cocktailId - l'id
        * @return - un cocktail
        */
        public Single<Cocktail> getCocktailFromId(String cocktailId) {
            return this.cocktailDisplayService.getCocktailFromId(cocktailId);
        }
}
