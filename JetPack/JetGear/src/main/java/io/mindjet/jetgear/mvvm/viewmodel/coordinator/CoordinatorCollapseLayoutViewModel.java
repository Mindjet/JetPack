package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.content.res.ColorStateList;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetwidget.JToolBar;

/**
 * A view model based on {@link android.support.design.widget.CoordinatorLayout}, consisting of {@link CollapsingToolbarLayout} and {@link FloatingActionButton}.
 * <p>
 * Created by Mindjet on 3/2/17.
 */

public abstract class CoordinatorCollapseLayoutViewModel<V extends ViewInterface<IncludeCoordinatorCollapseLayoutBinding>> extends BaseViewModel<V> implements AppBarLayout.OnOffsetChangedListener, Toolbar.OnMenuItemClickListener {

    @Override
    public void onViewAttached(View view) {
        getSelfView().getBinding().appBarLayout.addOnOffsetChangedListener(this);
        afterViewAttached();
        setSupportActionBar(getSelfView().getBinding().toolbar);
        initListener();
        initCollapsingToolbar(getSelfView().getBinding().collapsingToolBar);
        initCollapsingContent(getSelfView().getBinding().collapsingContent);
        setNavIcon(getSelfView().getBinding().toolbar);
        initContent(getSelfView().getBinding().llyContainer);
        initFab(getSelfView().getBinding().fab);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_coordinator_collapse_layout;
    }

    protected abstract void afterViewAttached();

    /**
     * Here, you can to set navigation icon with {@link JToolBar#setNavIcon(int)}.
     */
    protected abstract void setNavIcon(JToolBar toolbar);

    /**
     * You are supposed to set the toolbar as the activity's actionbar. {@link android.support.v7.app.AppCompatActivity#setSupportActionBar(Toolbar)}
     */
    protected abstract void setSupportActionBar(JToolBar toolBar);

    /**
     * You can custom the {@link CollapsingToolbarLayout} here with:
     * <ul>
     * <li>set the title. ({@link CollapsingToolbarLayout#setTitle(CharSequence)})</li>
     * <li> set collapsed background({@link CollapsingToolbarLayout#setContentScrimResource(int)})</li>
     * <li>set the expanded title margins. ({@link CollapsingToolbarLayout#setExpandedTitleMargin(int, int, int, int)})</li>
     * <li>set collapsed/expanded title appearance({@link CollapsingToolbarLayout#setCollapsedTitleTextAppearance(int)}, {@link CollapsingToolbarLayout#setExpandedTitleTextAppearance(int)})</li>
     * </ul>
     */
    protected abstract void initCollapsingToolbar(CollapsingToolbarLayout layout);

    /**
     * You can attach any view to the collapsing container here.
     *
     * @param container the collapsing container.
     */
    protected abstract void initCollapsingContent(ViewGroup container);

    protected abstract void initContent(ViewGroup container);

    /**
     * You can custom the {@link FloatingActionButton} here with:
     * <ul>
     * <li>change the fab size ({@link FloatingActionButton#setSize(int)})</li>
     * <li>change the fab icon ({@link FloatingActionButton#setImageResource(int)})</li>
     * <li>change the fab background color ({@link FloatingActionButton#setBackgroundTintList(ColorStateList)} (int)})</li>
     * </ul>
     */
    protected abstract void initFab(FloatingActionButton fab);

    /**
     * Called when the icon on the {@link JToolBar} is clicked.
     */
    protected abstract void onNavIconClick();

    /**
     * Called when the {@link FloatingActionButton} is clicked.
     */
    protected abstract void onFabClick();

    private void initListener() {
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavIconClick();
            }
        });
        getToolbar().setOnMenuItemClickListener(this);
        getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabClick();
            }
        });
    }

    protected Toolbar getToolbar() {
        return getSelfView().getBinding().toolbar;
    }

    protected FloatingActionButton getFab() {
        return getSelfView().getBinding().fab;
    }

    protected CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return getSelfView().getBinding().collapsingToolBar;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

}
