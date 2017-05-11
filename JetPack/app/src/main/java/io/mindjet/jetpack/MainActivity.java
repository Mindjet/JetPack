package io.mindjet.jetpack;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.mindjet.jetdemo.activity.BannerActivity;
import io.mindjet.jetdemo.activity.CircularRevealActivity;
import io.mindjet.jetdemo.activity.CollapseTabLayoutActivity;
import io.mindjet.jetdemo.activity.CollapseToolbarLayoutActivity;
import io.mindjet.jetdemo.activity.DrawerCollapseTabLayoutActivity;
import io.mindjet.jetdemo.activity.DrawerLayoutActivity;
import io.mindjet.jetdemo.activity.ImageLoaderActivity;
import io.mindjet.jetdemo.activity.ImagePickerActivity;
import io.mindjet.jetdemo.activity.ImageSaverActivity;
import io.mindjet.jetdemo.activity.NativeDrawerLayoutActivity;
import io.mindjet.jetdemo.activity.SwipeRecyclerDemoActivity;
import io.mindjet.jetgear.databinding.ItemButtonBinding;
import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
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
        ViewModelAdapter<ItemButtonBinding> adapter = recyclerViewModel.getAdapter();

        textResList.add(R.string.image_picker);
        textResList.add(R.string.image_saver);
        textResList.add(R.string.image_loader);
        textResList.add(R.string.pretty_banner);
        textResList.add(R.string.drawer_layout);
        textResList.add(R.string.native_drawer_layout);
        textResList.add(R.string.coordinator_layout);
        textResList.add(R.string.coordinator_collapse_layout);
        textResList.add(R.string.drawer_coordinator_layout);
        textResList.add(R.string.swipe_view);
        textResList.add(R.string.circular_reveal);

        intentList.add(ImagePickerActivity.intentFor(this));
        intentList.add(ImageSaverActivity.intentFor(this));
        intentList.add(ImageLoaderActivity.intentFor(this));
        intentList.add(BannerActivity.intentFor(this));
        intentList.add(DrawerLayoutActivity.intentFor(this));
        intentList.add(NativeDrawerLayoutActivity.intentFor(this));
        intentList.add(CollapseTabLayoutActivity.intentFor(this));
        intentList.add(CollapseToolbarLayoutActivity.intentFor(this));
        intentList.add(DrawerCollapseTabLayoutActivity.intentFor(this));
        intentList.add(SwipeRecyclerDemoActivity.intentFor(this));
        intentList.add(CircularRevealActivity.intentFor(this));

        for (int i = 0; i < textResList.size(); i++) {
            final Intent intent = intentList.get(i);
            adapter.add(new ButtonViewModel.Builder()
                    .simpleButton(textResList.get(i), new Action0() {
                        @Override
                        public void call() {
                            startActivity(intent);
                        }
                    }).build());
        }
        adapter.notifyItemRangeInserted(0, adapter.size());
    }

}
