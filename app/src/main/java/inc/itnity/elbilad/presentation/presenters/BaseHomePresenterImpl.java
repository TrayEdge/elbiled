package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.domain.buses.OpenTabRxBus;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.domain.subscribers.BaseUseCaseSubscriber;
import inc.itnity.elbilad.domain.usecases.GetCategoriesUseCase;
import inc.itnity.elbilad.presentation.presenters.base.BasePresenter;
import inc.itnity.elbilad.presentation.presenters.base.ProgressConnectionPresenter;
import inc.itnity.elbilad.presentation.views.BaseHomeView;
import java.util.List;

/**
 * Created by MichaelCloud on 16.01.17.
 */

public class BaseHomePresenterImpl extends ProgressConnectionPresenter<BaseHomeView>
    implements BaseHomePresenter {

  private OpenTabRxBus openTabRxBus;
  private GetCategoriesUseCase getCategoriesUseCase;

  public BaseHomePresenterImpl(OpenTabRxBus openTabRxBus,
      GetCategoriesUseCase getCategoriesUseCase) {
    this.openTabRxBus = openTabRxBus;
    this.getCategoriesUseCase = getCategoriesUseCase;
  }

  @Override public void onCreate() {
    openTabRxBus.getOpenTabObservable().subscribe(openTabSubscriber());

    getCategoriesUseCase.setRefresh(false);
    getCategoriesUseCase.execute(categoriesSubscriber());
  }

  private BaseUseCaseSubscriber<List<Category>> categoriesSubscriber() {
    return new BaseUseCaseSubscriber<List<Category>>(){
      @Override public void onNext(List<Category> categories) {
        super.onNext(categories);

        try {
          checkViewBound();

          getView().showLoadedCategories(categories);
        } catch (ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }

  private BaseUseCaseSubscriber<Integer> openTabSubscriber() {
    return new BaseUseCaseSubscriber<Integer>() {
      @Override public void onNext(Integer integer) {
        super.onNext(integer);

        try {
          checkViewBound();

          getView().openTab(integer);
        } catch (BasePresenter.ViewNotBoundException e) {
          e.printStackTrace();
        }
      }
    };
  }
}
