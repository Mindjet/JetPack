package io.mindjet.jetdemo.listener;

import io.mindjet.jetdemo.viewmodel.GithubFollowerViewModel;

/**
 * Created by Jet on 3/9/17.
 */

public interface IFollowerListener {

    void onFollowerClick(int position, GithubFollowerViewModel item);

}
