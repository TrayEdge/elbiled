package inc.itnity.elbilad.presentation.presenters;

import inc.itnity.elbilad.presentation.presenters.base.IPresenter;
import inc.itnity.elbilad.presentation.views.MainMenuView;

/**
 * Created by MichaelCloud on 16.01.17.
 */

public interface MainMenuPresenter extends IPresenter<MainMenuView> {
  void onCreate();

  void openBaseFragmentTab(int position);
}
