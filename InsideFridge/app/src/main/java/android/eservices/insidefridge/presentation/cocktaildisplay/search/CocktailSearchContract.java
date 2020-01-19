package android.eservices.insidefridge.presentation.cocktaildisplay.search;

import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.List;

public interface CocktailSearchContract {

    interface View {
        /**
         * Permet l'affichage des cocktails retournés par l'API
         * @param cocktailItemViewModelList
         */
        void displayCocktails(List<CocktailItemViewModel> cocktailItemViewModelList);

        void displayEmptySearch(CocktailItemViewModel cocktailItemEmpty);

        void onCocktailAddedToFavorites();

        void onCocktailRemovedToFavorites();

    }

    interface Presenter {
        void attachView(View view);

        void cancelSubscription();

        void detachView();

        /**
         * Permet la récupération de cocktails selon un ingrédient
         * @param keywords - l'ingrédient
         */
        void searchCocktails(String keywords);

        void addCocktailToFavorite(String cocktailID);

        void removeCocktailToFavorite(String cocktailID);
    }
}
