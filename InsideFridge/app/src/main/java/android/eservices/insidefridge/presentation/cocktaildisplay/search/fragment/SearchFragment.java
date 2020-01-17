package android.eservices.insidefridge.presentation.cocktaildisplay.search.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.eservices.insidefridge.R;

import java.util.List;
/**
 * Fragment used to search an ingredient or a cocktail
 */
public class SearchFragment extends Fragment implements CocktailSearchContract.View {

    private View rootView;
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
    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        cocktailAdapter = new CocktailAdapter();
        recyclerView.setAdapter(cocktailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
