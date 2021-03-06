package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityBannerViewBinding;
import io.mindjet.jetdemo.view.activity.base.BaseDemoActivity;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.item.BannerItemViewModel;
import io.mindjet.jetutil.drawable.DrawableDyer;
import io.mindjet.jetwidget.banner.BannerView;
import io.mindjet.jetwidget.banner.indicator.IndicatorInterfaceImpl;
import io.mindjet.jetwidget.banner.indicator.IndicatorView;

/**
 * Created by Mindjet on 5/15/17.
 */

public class BannerViewActivity extends BaseDemoActivity {

    private ActivityBannerViewBinding mBinding;
    private BannerView mBannerView;
    private ViewPagerAdapter<BannerItemViewModel> adapter;

    public static Intent intentFor(Context context) {
        return new Intent(context, BannerViewActivity.class);
    }

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_banner_view);
        mBannerView = (BannerView) findViewById(R.id.banner_view);
        mBinding.setData(this);
    }

    @Override
    public void initView() {
        adapter = new ViewPagerAdapter<BannerItemViewModel>() {
            @Override
            protected Object initItem(ViewGroup container, BannerItemViewModel item, String title, int position) {
                return ViewModelBinder.bind(container, item).getRoot();
            }
        };
        adapter.add(new BannerItemViewModel("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=657304686,216901620&fm=21&gp=0.jpg"));
        adapter.add(new BannerItemViewModel("https://timgsa.baidu.com/timg?image&quality=80&num=b9999_10000&sec=1488450180&di=60101eddc5fc5e74a43e125c777f9040&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.quweiwu.com%2Fnews%2F20151030102559902.jpg"));
        adapter.add(new BannerItemViewModel("https://timgsa.baidu.com/timg?image&quality=80&num=b9999_10000&sec=1488450197&di=a151ec971c05b732bd97e7a5d836a4e2&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.xiazaizhijia.com%2Fuploads%2Fallimg%2F150926%2F195-15092615352MT.jpg"));
        adapter.add(new BannerItemViewModel("https://timgsa.baidu.com/timg?image&quality=80&num=b9999_10000&sec=1488450208&di=13042111be2919023a3fc158f254edb3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg"));
        adapter.add(new BannerItemViewModel("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg"));
        mBannerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    public void onOriginStyle() {
        mBannerView.setIndicatorStyle(new IndicatorInterfaceImpl(this));
    }

    public void onSquareStyle() {
        mBannerView.setIndicatorStyle(IndicatorView.create(this,
                getResources().getDrawable(R.drawable.shape_indicator_sqaure),
                DrawableDyer.dye(getResources().getDrawable(R.drawable.shape_indicator_sqaure), getResources().getColor(R.color.colorPrimary)),
                R.dimen.common_gap_small));
    }

    public void onBigStyle() {
        mBannerView.setIndicatorStyle(IndicatorView.create(this,
                getResources().getDrawable(R.drawable.shape_indicator_big),
                DrawableDyer.dye(getResources().getDrawable(R.drawable.shape_indicator_big), getResources().getColor(R.color.colorPrimary)),
                R.dimen.common_gap_medium));
    }

    public void onBigCursorStyle() {
        mBannerView.setIndicatorStyle(IndicatorView.create(this, R.drawable.shape_cursor_normal, R.drawable.shape_cursor_big, R.dimen.common_gap_medium));
    }

    public void onSmallCursorStyle() {
        mBannerView.setIndicatorStyle(IndicatorView.create(this, R.drawable.shape_indicator_big, R.drawable.shape_cursor_selected, R.dimen.common_gap_medium));
    }

}
