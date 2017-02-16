package io.mindjet.jetgear.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemProgressBinding;
import io.mindjet.jetgear.model.Follower;
import io.mindjet.jetgear.mvvm.base.BaseViewHolder;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.network.GithubService;
import io.mindjet.jetutil.logger.JLogger;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 2/16/17.
 */

public class LoadMoreAdapter<T extends BaseViewModel<V>, V extends ViewDataBinding> extends ListAdapter<T, V> implements LoadMoreListener {


    private JLogger jLogger = JLogger.get(getClass().getSimpleName());
    private boolean loadMore = true;
    private ItemProgressBinding progressBinding;

    private Retrofit retrofit;
    private GithubService service;
    private int page = 1;
    private int perPage = 3;

    public LoadMoreAdapter(Context context) {
        super(context);
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.github.com/")         //It is required but will be ignored if the call is given absolute path
                .build();
        service = retrofit.create(GithubService.class);
    }

    @Override
    public int getItemCount() {
        return loadMore ? size() + 1 : size();
    }

    @Override
    public BaseViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (loadMore && viewType == R.layout.item_progress) {
            progressBinding = ItemProgressBinding.inflate(getInflater(), parent, false);
            return new BaseViewHolder<V>(progressBinding);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindVH(BaseViewHolder<V> holder, int position) {
        if (loadMore && position == size()) {
            holder.getBinding().getRoot().setVisibility(View.VISIBLE);
            onLoadMore();
        } else {
            super.onBindVH(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (loadMore && position == getItemCount() - 1) {
            return R.layout.item_progress;
        }
        return super.getItemViewType(position);
    }

    public void enableLoadMore() {
        loadMore = true;
    }

    public void disableLoadMore() {
        loadMore = false;
    }

    @Override
    public void LoadMore() {
        onLoadMore();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBinding.getRoot().setVisibility(View.GONE);
            }
        }, 1000);
    }

    public void onLoadMore() {
        service.follower(page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Follower>>() {
                    @Override
                    public void call(List<Follower> followers) {
                        addAll((Collection<? extends T>) m2vm(followers));
                        page++;
                        notifyDataSetChanged();
                    }
                });
    }

    private List<ImageTextViewModel> m2vm(List<Follower> list) {
        List<ImageTextViewModel> vmlist = new ArrayList<>();
        for (Follower follower : list) {
            ImageTextViewModel vm = new ImageTextViewModel();
            vm.setImageUrl(follower.avatarUrl);
            vm.setContent(follower.htmlUrl);
            vm.setTitle(follower.name);
            vmlist.add(vm);
        }
        return vmlist;
    }

}
