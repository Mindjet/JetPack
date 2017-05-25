package io.mindjet.jetpack;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import io.mindjet.jetdemo.view.activity.BannerViewActivity;
import io.mindjet.jetdemo.view.activity.BehaviorViewActivity;
import io.mindjet.jetdemo.view.activity.CircularRevealActivity;
import io.mindjet.jetdemo.view.activity.CollapseTabLayoutActivity;
import io.mindjet.jetdemo.view.activity.CollapseToolbarLayoutActivity;
import io.mindjet.jetdemo.view.activity.CornerLinearLayoutActivity;
import io.mindjet.jetdemo.view.activity.CuteCheckBoxActivity;
import io.mindjet.jetdemo.view.activity.CuteLoadingDialogActivity;
import io.mindjet.jetdemo.view.activity.CuteLoadingViewActivity;
import io.mindjet.jetdemo.view.activity.DrawableDyerActivity;
import io.mindjet.jetdemo.view.activity.DrawerCollapseTabLayoutActivity;
import io.mindjet.jetdemo.view.activity.DrawerLayoutActivity;
import io.mindjet.jetdemo.view.activity.ImageLoaderActivity;
import io.mindjet.jetdemo.view.activity.ImagePickerActivity;
import io.mindjet.jetdemo.view.activity.ImageSaverActivity;
import io.mindjet.jetdemo.view.activity.NativeDrawerLayoutActivity;
import io.mindjet.jetdemo.view.activity.SwipeRecyclerDemoActivity;
import io.mindjet.jetdemo.viewmodel.item.EntryItemViewModel;
import io.mindjet.jetgear.databinding.ItemButtonBinding;
import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetpack.databinding.ActivityMainBinding;
import rx.functions.Action0;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Intent> intentList = new ArrayList<>();

    private List<Integer> textResList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    private void initData() {
        RecyclerViewModel<ItemButtonBinding> recyclerViewModel = new RecyclerViewModel<>();
        ViewModelBinder.bind(binding.llyContainer, recyclerViewModel);
        recyclerViewModel.getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ViewModelAdapter<ItemButtonBinding> adapter = recyclerViewModel.getAdapter();

        textResList.add(io.mindjet.jetdemo.R.string.image_picker);
        textResList.add(io.mindjet.jetdemo.R.string.image_saver);
        textResList.add(io.mindjet.jetdemo.R.string.image_loader);
        textResList.add(io.mindjet.jetdemo.R.string.pretty_banner);
        textResList.add(io.mindjet.jetdemo.R.string.circular_reveal);
        textResList.add(io.mindjet.jetdemo.R.string.drawer_layout);
        textResList.add(io.mindjet.jetdemo.R.string.native_drawer_layout);
        textResList.add(io.mindjet.jetdemo.R.string.collapse_tab_layout);
        textResList.add(io.mindjet.jetdemo.R.string.collapse_toolbar_layout);
        textResList.add(io.mindjet.jetdemo.R.string.drawer_collapse_tab_layout);
        textResList.add(io.mindjet.jetdemo.R.string.refresh_loadmore);
        textResList.add(io.mindjet.jetdemo.R.string.drawable_dyer);
        textResList.add(io.mindjet.jetdemo.R.string.cute_check_box);
        textResList.add(R.string.cute_loading_view);
        textResList.add(R.string.cute_loading_dialog);
        textResList.add(R.string.corner_linear_layout);
        textResList.add(io.mindjet.jetdemo.R.string.behavior_view);

        intentList.add(ImagePickerActivity.intentFor(this));
        intentList.add(ImageSaverActivity.intentFor(this));
        intentList.add(ImageLoaderActivity.intentFor(this));
        intentList.add(BannerViewActivity.intentFor(this));
        intentList.add(CircularRevealActivity.intentFor(this));
        intentList.add(DrawerLayoutActivity.intentFor(this));
        intentList.add(NativeDrawerLayoutActivity.intentFor(this));
        intentList.add(CollapseTabLayoutActivity.intentFor(this));
        intentList.add(CollapseToolbarLayoutActivity.intentFor(this));
        intentList.add(DrawerCollapseTabLayoutActivity.intentFor(this));
        intentList.add(SwipeRecyclerDemoActivity.intentFor(this));
        intentList.add(DrawableDyerActivity.intentFor(this));
        intentList.add(CuteCheckBoxActivity.intentFor(this));
        intentList.add(CuteLoadingViewActivity.intentFor(this));
        intentList.add(CuteLoadingDialogActivity.intentFor(this));
        intentList.add(CornerLinearLayoutActivity.intentFor(this));
        intentList.add(BehaviorViewActivity.intentFor(this));

        for (int i = 0; i < textResList.size(); i++) {
            final Intent intent = intentList.get(i);
            adapter.add(new EntryItemViewModel(textResList.get(i), new Action0() {
                @Override
                public void call() {
                    startActivity(intent);
                }
            }));
        }
        adapter.notifyItemRangeInserted(0, adapter.size());
    }

}
