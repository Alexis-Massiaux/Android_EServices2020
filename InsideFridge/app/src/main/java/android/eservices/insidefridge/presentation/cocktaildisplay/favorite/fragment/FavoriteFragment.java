package android.eservices.insidefridge.presentation.cocktaildisplay.favorite.fragment;

import android.eservices.insidefridge.data.di.FakeDependencyInjection;
import android.eservices.insidefridge.presentation.cocktaildisplay.favorite.CocktailFavoriteContract;
import android.eservices.insidefridge.presentation.cocktaildisplay.favorite.CocktailFavoritePresenter;
import android.eservices.insidefridge.presentation.cocktaildisplay.favorite.mapper.CocktailEntityToViewModelMapper;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.CocktailSearchContract;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailActionInterface;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailAdapter;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.mapper.CocktailToViewModelMapper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.eservices.insidefridge.R;

import java.util.List;

public class FavoriteFragment extends Fragment implements CocktailFavoriteContract.View , CocktailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private RecyclerView recyclerView;
    private CocktailAdapter cocktailAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CocktailFavoritePresenter cocktailFavoritePresenter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        cocktailFavoritePresenter = new CocktailFavoritePresenter(FakeDependencyInjection.getCocktailDisplayRepository(getContext()),new CocktailEntityToViewModelMapper());
        cocktailFavoritePresenter.attachView(this);
        cocktailFavoritePresenter.getFavorites();
    }


    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.favorite_recycler_view);
        cocktailAdapter = new CocktailAdapter(this);
        recyclerView.setAdapter(cocktailAdapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setOnFavorite(String cocktailID) {

    }

    @Override
    public void displayFavorites(List<CocktailItemViewModel> cocktailItemViewModelList) {
        cocktailAdapter.bindViewModels(cocktailItemViewModelList);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        cocktailFavoritePresenter.detachView();
    }
}
