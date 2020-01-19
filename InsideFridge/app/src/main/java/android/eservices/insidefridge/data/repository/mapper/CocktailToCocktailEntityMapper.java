package android.eservices.insidefridge.data.repository.mapper;

import android.eservices.insidefridge.data.api.model.Cocktail;
import android.eservices.insidefridge.data.entity.CocktailEntity;

public class CocktailToCocktailEntityMapper {

    /**
     * Renvoi un objet CocktailEntity depuis un un objet Cocktail
     * @param cocktail - le Coktail qui sera transformé
     * @return - un CocktailEntity
     */
    public CocktailEntity map(Cocktail cocktail) {
        CocktailEntity cocktailEntity = new CocktailEntity();
        cocktailEntity.setId(cocktail.getId());
        cocktailEntity.setName(cocktail.getName());
        cocktailEntity.setImageURL(cocktail.getImageURL());

        return cocktailEntity;
    }
}
