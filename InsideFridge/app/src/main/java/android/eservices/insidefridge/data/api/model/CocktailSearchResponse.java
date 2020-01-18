package android.eservices.insidefridge.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CocktailSearchResponse {

    @SerializedName("drinks")
    List<Cocktail> cocktailList;

    public List<Cocktail> getCocktailList() { return this.cocktailList; }
}
