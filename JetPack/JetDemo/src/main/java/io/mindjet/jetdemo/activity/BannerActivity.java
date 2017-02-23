package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import io.mindjet.jetgear.mvvm.viewmodel.BannerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelActivity;
import io.mindjet.jetutil.task.Task;

/**
 * Created by Jet on 2/23/17.
 */

public class BannerActivity extends ViewModelActivity<BannerViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, BannerActivity.class);
    }

    @Override
    public BannerViewModel giveMeViewModel() {
        return new BannerViewModel.Builder()
                .interval(4000)
                .dotSpace(io.mindjet.jetgear.R.dimen.view_pager_dot_space)
                .height(io.mindjet.jetgear.R.dimen.view_pager_small_height)
                .build();
    }

    @Override
    public void onViewAttached(final BannerViewModel viewModel) {
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=657304686,216901620&fm=21&gp=0.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450180&di=60101eddc5fc5e74a43e125c777f9040&imgtype=jpg&er=1&src=http%3A%2F%2Ffile.quweiwu.com%2Fnews%2F20151030102559902.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450197&di=a151ec971c05b732bd97e7a5d836a4e2&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.xiazaizhijia.com%2Fuploads%2Fallimg%2F150926%2F195-15092615352MT.jpg");
                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450208&di=13042111be2919023a3fc158f254edb3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg");
                list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg");
                viewModel.updateUrlList(list);
            }
        }, 2000);
    }

}
