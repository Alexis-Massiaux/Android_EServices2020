package android.eservices.insidefridge.presentation.cocktaildisplay.search;

import android.eservices.insidefridge.data.api.model.CocktailSearchResponse;
import android.eservices.insidefridge.data.repository.CocktailDisplayRepository;
import android.eservices.insidefridge.presentation.cocktaildisplay.search.mapper.CocktailToViewModelMapper;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CocktailSearchPresenter implements CocktailSearchContract.Presenter {

    private CompositeDisposable compositeDisposable;
    private CocktailDisplayRepository cocktailDisplayRepository;
    private CocktailSearchContract.View view;
    private CocktailToViewModelMapper cocktailToViewModelMapper;

    public CocktailSearchPresenter(CocktailDisplayRepository cocktailDisplayRepository, CocktailToViewModelMapper cocktailToViewModelMapper) {
        this.compositeDisposable = new CompositeDisposable();
        this.cocktailDisplayRepository = cocktailDisplayRepository;
        this.cocktailToViewModelMapper = cocktailToViewModelMapper;
    }

    @Override
    public void searchCocktails(String keywords) {
        //stop tous les Disposables
        compositeDisposable.clear();
        compositeDisposable.add(cocktailDisplayRepository.getCocktailSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CocktailSearchResponse>() {

                    @Override
                    public void onSuccess(CocktailSearchResponse cocktailSearchResponse) {
                        if(cocktailSearchResponse.getCocktailList() != null)
                            view.displayCocktails(cocktailToViewModelMapper.map(cocktailSearchResponse.getCocktailList()));
                        else
                            view.displayEmptySearch(cocktailToViewModelMapper.emptySearch());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }
                }));
    }

    @Override
    public void addCocktailToFavorite(String cocktailID) {
        compositeDisposable.add(cocktailDisplayRepository.addCocktailToFavorites(cocktailID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onCocktailAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeCocktailToFavorite(String cocktailID) {
        compositeDisposable.add(cocktailDisplayRepository.removeCocktailFromFavorites(cocktailID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onCocktailRemovedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(CocktailSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }


}
