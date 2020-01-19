package android.eservices.insidefridge.presentation.cocktaildisplay.favorite;

import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.List;

public interface CocktailFavoriteContract {

    interface View{
        void displayFavorites(List<CocktailItemViewModel> cocktailItemViewModelList);
    }

    interface Presenter {
        void attachView(View view);

        void getFavorites();

        void detachView();
    }
}
