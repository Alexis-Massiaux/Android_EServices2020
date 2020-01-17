package android.eservices.insidefridge.presentation.cocktaildisplay.search.fragment;

import android.eservices.insidefridge.presentation.cocktaildisplay.search.CocktailSearchContract;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.CocktailSearchPresenter;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailAdapter;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.adapter.CocktailItemViewModel;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.eservices.insidefridge.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Fragment used to search an ingredient or a cocktail
 */
public class SearchFragment extends Fragment implements CocktailSearchContract.View {

    public static final String TAB_NAME = "Search";
    private View rootView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CocktailSearchContract.Presenter presenter;
    private CocktailAdapter cocktailAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        setupSearchView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        return rootView;
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

    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        cocktailAdapter = new CocktailAdapter();
        recyclerView.setAdapter(cocktailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
