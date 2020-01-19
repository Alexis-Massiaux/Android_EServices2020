package android.eservices.insidefridge.presentation.cocktaildisplay.favorite;

import android.eservices.insidefridge.data.entity.CocktailEntity;
import android.eservices.insidefridge.data.repository.CocktailDisplayRepository;
import android.eservices.insidefridge.presentation.cocktaildisplay.favorite.mapper.CocktailEntityToViewModelMapper;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.mapper.CocktailToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class CocktailFavoritePresenter implements CocktailFavoriteContract.Presenter {

    private CocktailFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private CocktailDisplayRepository cocktailDisplayRepository;
    private CocktailEntityToViewModelMapper cocktailEntityToViewModelMapper;

    public CocktailFavoritePresenter(CocktailDisplayRepository cocktailDisplayRepository, CocktailEntityToViewModelMapper cocktailToViewModelMapper) {
        this.compositeDisposable = new CompositeDisposable();
        this.cocktailDisplayRepository = cocktailDisplayRepository;
        this.cocktailEntityToViewModelMapper = cocktailToViewModelMapper;
    }

    @Override
    public void attachView(CocktailFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavorites() {
        compositeDisposable.add(cocktailDisplayRepository.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new ResourceSubscriber<List<CocktailEntity>>() {

                @Override
                public void onNext(List<CocktailEntity> cocktailEntities) {
                    view.displayFavorites(cocktailEntityToViewModelMapper.map(cocktailEntities));
                    System.out.println("tron get favorites !");
                }

                @Override
                public void onError(Throwable t) {
                    t.printStackTrace();
                }

                @Override
                public void onComplete() {

                }
            }));

    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        this.view = null;
    }
}
