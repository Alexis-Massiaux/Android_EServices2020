package android.eservices.insidefridge.presentation.cocktaildisplay.search.fragment;

import android.eservices.insidefridge.data.di.FakeDependencyInjection;
import android.eservices.insidefridge.presentation.cocktaildisplay.fridge.fragment.FridgeFragment;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.CocktailSearchContract;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.CocktailSearchPresenter;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailActionInterface;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailAdapter;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.mapper.CocktailToViewModelMapper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.eservices.insidefridge.R;
import android.widget.Button;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragment used to search an ingredient or a cocktail
 */
public class SearchFragment extends Fragment implements CocktailSearchContract.View, CocktailActionInterface {

    public static final String TAB_NAME = "Search";
    private View rootView;
    private Button gridListButton;
    private Button detailsCocktailsButton;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CocktailSearchContract.Presenter presenter;
    private RecyclerView.LayoutManager layoutManager;
    private CocktailAdapter cocktailAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDetailsButton();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        gridListButton = rootView.findViewById(R.id.button);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        setupSearchView();

        presenter = new CocktailSearchPresenter(FakeDependencyInjection.getCocktailDisplayRepository(getContext()),new CocktailToViewModelMapper());
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if(s.length() == 0) {
                    presenter.cancelSubscription();
                }else{
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            presenter.searchCocktails(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }

    @Override
    public void displayCocktails(List<CocktailItemViewModel> cocktailItemViewModelList) {
        cocktailAdapter.bindViewModels(cocktailItemViewModelList);
    }

    @Override
    public void displayEmptySearch(CocktailItemViewModel cocktailItemViewModel) {
        cocktailAdapter.bindViewModel(cocktailItemViewModel);
    }

    @Override
    public void onCocktailAddedToFavorites() {
        //Do nothing here
    }

    @Override
    public void onCocktailRemovedToFavorites() {
        //Do nothing here
    }

    /**
     * Initialisation d'un recycler view où on lui partage un adapter
     * Donne l'action au bouton qui permet de passer d'une vue liste à une vue grille
     */
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        cocktailAdapter = new CocktailAdapter(this);
        recyclerView.setAdapter(cocktailAdapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        gridListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutManager instanceof GridLayoutManager) {
                    layoutManager = new LinearLayoutManager(getContext());
                } else {
                    layoutManager = new GridLayoutManager(getContext(), 2);
                }
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }

    /**
     * Donne l'action au bouton qui devrait afficher une nouvelle vue pour le détails d'un coktail
     * (à supprimer, il est dans l'adapter / trouvé un moyen d'effectuer le replaceFragment depuis l'adapter)
     */
    private void setupDetailsButton() {
        View search = getLayoutInflater().inflate(R.layout.item_cocktail, null);
        detailsCocktailsButton = search.findViewById(R.id.details_button);
        detailsCocktailsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("TRON clickbuttton");
                replaceFragment(new FridgeFragment());
            }
        });
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setOnFavorite(String cocktailID) {
        presenter.addCocktailToFavorite(cocktailID);
    }
}
