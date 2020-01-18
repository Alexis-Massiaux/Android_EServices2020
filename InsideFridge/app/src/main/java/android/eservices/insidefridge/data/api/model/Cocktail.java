package android.eservices.insidefridge.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cocktail {

   // private String id;
    private CocktailInfo cocktailInfo;

    @SerializedName("idIngredient")
    @Expose
    private String idIngredient;
    @SerializedName("strIngredient")
    @Expose
    private String strIngredient;

    public String getName() {
        return strIngredient;
    }

    public String getId() { return idIngredient; }


    public CocktailInfo getCocktailInfo() {
        return cocktailInfo;
    }

    public String toString() {
        return "Mon cocktail : "+idIngredient+ " _ "+ strIngredient;
    }

}
