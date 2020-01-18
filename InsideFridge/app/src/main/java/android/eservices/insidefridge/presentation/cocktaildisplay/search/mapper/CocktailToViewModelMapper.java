package android.eservices.insidefridge.presentation.cocktaildisplay.search.mapper;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class CocktailToViewModelMapper {

    private CocktailItemViewModel map(Cocktail cocktail) {
        CocktailItemViewModel cocktailItemViewModel = new CocktailItemViewModel();

        cocktailItemViewModel.setId(cocktail.getId());
        cocktailItemViewModel.setName(cocktail.getName());
        cocktailItemViewModel.setImageURL(cocktail.getImageURL());

        return cocktailItemViewModel;
    }

    public CocktailItemViewModel emptySearch() {
        CocktailItemViewModel cocktailItemViewModel = new CocktailItemViewModel();
        cocktailItemViewModel.setName("Aucun resultat");

        return cocktailItemViewModel;
    }

    public List<CocktailItemViewModel> map(List<Cocktail> cocktailList) {
        List<CocktailItemViewModel> result = new ArrayList<>();

        for(Cocktail cocktail : cocktailList)
            result.add(map(cocktail));

        return result;
    }
}
