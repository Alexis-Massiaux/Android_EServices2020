package android.eservices.insidefridge.presentation.cocktaildisplay.favorite.mapper;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.entity.CocktailEntity;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class CocktailEntityToViewModelMapper {

    /**
     * Renvoi un objet CocktailItemViewModel selon un objet CocktailEntity
     * @param cocktail - le CocktailEntity qui sera transformé
     * @return - un CocktailItemViewModel
     */
    private CocktailItemViewModel map(CocktailEntity cocktail) {
        CocktailItemViewModel cocktailItemViewModel = new CocktailItemViewModel();

        cocktailItemViewModel.setId(cocktail.getId());
        cocktailItemViewModel.setName(cocktail.getName());
        cocktailItemViewModel.setImageURL(cocktail.getImageURL());

        return cocktailItemViewModel;
    }

    /**
     * Renvoi une liste de CocktailItemViewModel selon une liste de CocktailEntity
     * @param cocktailEntitiesList - la liste de CocktailEntity qui sera transformé
     * @return - la liste des CocktailItemViewModel sortant
     */
    public List<CocktailItemViewModel> map(List<CocktailEntity> cocktailEntitiesList) {
        List<CocktailItemViewModel> result = new ArrayList<>();

        for(CocktailEntity cocktailEntity : cocktailEntitiesList)
            result.add(map(cocktailEntity));

        return result;
    }
}
