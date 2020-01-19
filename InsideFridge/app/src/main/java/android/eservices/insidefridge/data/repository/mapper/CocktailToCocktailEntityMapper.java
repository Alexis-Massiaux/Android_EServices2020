package android.eservices.insidefridge.data.repository.mapper;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.entity.CocktailEntity;

public class CocktailToCocktailEntityMapper {

    public CocktailEntity map(Cocktail cocktail) {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(cocktail.getId());
        cocktailEntity.setTitle(cocktail.getName());
        cocktailEntity.setImageURL(cocktail.getImageURL());

        return cocktailEntity;
    }
}
