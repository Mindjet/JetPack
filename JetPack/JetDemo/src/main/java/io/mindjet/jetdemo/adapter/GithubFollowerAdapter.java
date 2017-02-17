package io.mindjet.jetdemo.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.adapter.LoadMoreAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetutil.logger.JLogger;
import io.mindjet.jetutil.toast.Toaster;

/**
 * Created by Jet on 2/17/17.
 */

public class GithubFollowerAdapter extends LoadMoreAdapter<ImageTextViewModel, ItemImageTextBinding> {

    private static JLogger jLogger = JLogger.get(GithubFollowerAdapter.class.getSimpleName());

    private GithubService service;
    private int page = 1;
    private int perPage = 5;

    public GithubFollowerAdapter(Context context) {
        super(context);
        initService();
    }

    private void initService() {
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    public void onLoadMore() {
        if (page <= 9) {
            add(new ImageTextViewModel());
            page++;
        } else {
            finishLoadMore(false);
        }
//        service.follower("JakeWharton", page, perPage)
//                .throttleLast(500, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<List<Follower>>() {
//                    @Override
//                    public void call(List<Follower> followers) {
//                        if (followers.size() == 0) {
//                            finishLoadMore(false);
//                        } else {
//                            List<ImageTextViewModel> vmList = new ArrayList<>();
//                            for (Follower follower : followers) {
//                                ImageTextViewModel vm = new ImageTextViewModel();
//                                vm.setImageUrl(follower.avatarUrl);
//                                vm.setTitle(follower.name);
//                                vm.setContent(follower.getHtmlUrl());
//                                vmList.add(vm);
//                            }
//                            page++;
//                            addAll(vmList);
//                            notifyDataSetChanged();
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        jLogger.e(throwable.getMessage());
//                        finishLoadMore(false);
//                    }
//                });
    }

    @Override
    public void onItemClick(ViewDataBinding binding, int position) {
        Toaster.toast(binding.getRoot().getContext(), get(position).getTitle());
    }
}
