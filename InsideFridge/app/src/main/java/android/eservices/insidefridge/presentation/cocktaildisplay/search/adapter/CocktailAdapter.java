package android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter;

import android.eservices.insidefridge.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter -> Faire le lien entre la vue RecyclerView & le contrôleur
 */
public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    /**
     * ViewHolder -> représentation de l'objet désérialisé du layout item_cocktail
     */
    public static class CocktailViewHolder extends RecyclerView.ViewHolder {

        private CocktailItemViewModel cocktailItemViewModel;
        private ImageView iconImageView;
        private TextView titleTextView;
        private Button favoriteButton;
        private Button detailsButton;
        private View view;

        public CocktailViewHolder(View view) {
            super(view);
            this.view = view;
            titleTextView = view.findViewById(R.id.cocktail_name_textview);
            iconImageView = view.findViewById(R.id.cocktail_icon_imageview);
            favoriteButton = view.findViewById(R.id.favorite_button);
            detailsButton = view.findViewById(R.id.details_button);
            setupListeners();
        }

        public void bind(CocktailItemViewModel cocktailItemViewModel) {
            this.cocktailItemViewModel = cocktailItemViewModel;
            titleTextView.setText(this.cocktailItemViewModel.getName());
            Glide.with(view)
                    .load(cocktailItemViewModel.getImageURL())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(iconImageView);
        }

        public void setupListeners() {
            favoriteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    System.out.println("Tron favorite click button");
                }
            });

            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Tron details click button");
                }
            });
        }
    }

    private List<CocktailItemViewModel> cocktailItemViewModelList;

    public CocktailAdapter() {
        cocktailItemViewModelList = new ArrayList<>();
    }

    /**
     * Utiliser pour indiquer à l'utilisateur que la recherche n'a renvoyée aucun résultat
     * @param cocktailItemViewModel - On se sert de l'attribut name de cet objet pour transmettre le message
     */
    public void bindViewModel(CocktailItemViewModel cocktailItemViewModel) {
        this.cocktailItemViewModelList.clear();
        this.cocktailItemViewModelList.add(cocktailItemViewModel);
        notifyDataSetChanged();
    }

    public void bindViewModels(List<CocktailItemViewModel> cocktailItemViewModelList) {
        this.cocktailItemViewModelList.clear();
        this.cocktailItemViewModelList.addAll(cocktailItemViewModelList);
        notifyDataSetChanged();
    }

     // Permet de créer un ViewHolder à partir du layout xml représentant chaque ligne de la RecyclerView
    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocktail, parent, false);
        CocktailViewHolder cocktailViewHolder = new CocktailViewHolder(v);
        return cocktailViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CocktailViewHolder holder, int position) {
        holder.bind(cocktailItemViewModelList.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cocktailItemViewModelList.size();
    }

}
