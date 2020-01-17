package android.eservices.insidefridge.presentation.cocktaildisplay.search;

import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.List;

public interface CocktailSearchContract {

    interface View {
        void displayCocktails(List<CocktailItemViewModel> cocktailItemViewModelList);
    }

    interface Presenter {
        void attachView(View view);

        void cancelSubscription();

        void detachView();

        void searchCocktails(String keywords);
    }
}
