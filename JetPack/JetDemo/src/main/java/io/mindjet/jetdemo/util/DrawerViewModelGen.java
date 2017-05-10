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
                        .background("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450208&di=13042111be2919023a3fc158f254edb3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg")
                        .content("Drawer Header Test")
                        .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                        .build())
                .item(new DrawerItemViewModel().content("Inbox").icon(R.drawable.ic_inbox_gray))
                .item(new DrawerItemViewModel().content("Starred").icon(R.drawable.ic_star_gray))
                .item(new DrawerItemViewModel().content("Sent mails").icon(R.drawable.ic_sent_gray))
                .item(new DrawerItemViewModel().content("Draft").icon(R.drawable.ic_draft_gray))
                .background(R.color.white)
                .width(R.dimen.drawer_width_normal)
                .build();
    }

}
