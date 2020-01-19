package android.eservices.insidefridge.presentation.cocktaildisplay.favorite;

import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.List;

public interface CocktailFavoriteContract {

    interface View{
        /**
         * Permet l'affichage des cocktails favoris
         * @param cocktailItemViewModelList
         */
        void displayFavorites(List<CocktailItemViewModel> cocktailItemViewModelList);
    }

    interface Presenter {

        void attachView(View view);

        /**
         * Permet la récupération des favoris en base
         */
        void getFavorites();

        void detachView();
    }
}
