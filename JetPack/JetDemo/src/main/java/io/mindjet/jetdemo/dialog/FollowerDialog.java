package io.mindjet.jetdemo.dialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.DialogFollowerBinding;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.base.BaseDialog;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetgear.reactivex.rxbus.RxBus;
import io.mindjet.jetutil.anim.RevealUtil;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 3/11/17.
 */

public class FollowerDialog extends BaseDialog {

    private DialogFollowerBinding binding;
    private String userName;

    private GithubService service;
    private Subscription userDetailSub;
    public ObservableField<Follower> _follower = new ObservableField<>(null);

    public FollowerDialog(@NonNull Context context, String userName) {
        super(context);
        this.userName = userName;
        this.service = ServiceGen.create(GithubService.class);
    }

    @Override
    protected void beforeInitView() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_follower, null, true);
        setContentView(binding.getRoot());
        binding.setData(this);
        RevealUtil.revealDialog(this, 800);
    }

    @Override
    protected void initView() {
        binding.tvName.setText(userName);
    }

    @Override
    protected void initData() {
        userDetailSub = service.getUserDetail(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Follower>() {
                    @Override
                    public void call(Follower follower) {
                        _follower.set(follower);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        jLogger.e(throwable);
                    }
                });
    }

    @Override
    public void onDetachedFromWindow() {
        RxBus.unSubscribe(userDetailSub);
    }
}
