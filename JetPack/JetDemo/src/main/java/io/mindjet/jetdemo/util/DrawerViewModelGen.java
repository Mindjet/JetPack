package io.mindjet.jetdemo.util;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerHeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerViewModel;

/**
 * DrawerViewModel 生成器
 * <p>
 * Created by Mindjet on 5/10/17.
 */

public class DrawerViewModelGen {

    public static DrawerViewModel create() {
        return new DrawerViewModel.Builder()
                .item(new DrawerHeaderViewModel.Builder()
                        .height(R.dimen.drawer_header_height)
                        .background("http://pic.qiantucdn.com/58pic/19/74/39/571093256279c_1024.jpg!/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center")
                        .content("Drawer Header Test")
                        .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                        .build())
                .item(new DrawerItemViewModel().content("Inbox").icon(R.drawable.ic_track_gray))
                .item(new DrawerItemViewModel().content("Starred").icon(R.drawable.ic_setting_gray))
                .item(new DrawerItemViewModel().content("Sent mails").icon(R.drawable.ic_favorite_gray))
                .item(new DrawerItemViewModel().content("Draft").icon(R.drawable.ic_face_gray))
                .background(R.color.white)
                .width(R.dimen.drawer_width_normal)
                .build();
    }

}
