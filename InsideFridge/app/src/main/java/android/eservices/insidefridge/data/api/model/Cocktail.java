package android.eservices.insidefridge.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cocktail {

    @SerializedName("idDrink")
    @Expose
    private String idIngredient;
    @SerializedName("strDrink")
    @Expose
    private String name;

    public String getId() { return idIngredient; }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Mon cocktail : "+getId()+ " / "+ getName();
    }
}
