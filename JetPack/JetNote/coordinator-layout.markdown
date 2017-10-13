## CoordinatorLayout 及其控件属性

### 基本结构

```xml
<CoordinatorLayout>
  
    <AppbarLayout>

        <CollapsingToolbarLayout>
  
            <!--可自定义的expendedView，一般用FrameLayout包裹-->
            <FrameLayout/>
            <Toolbar/>

        </CollapsingToolbarLayout>

    </AppbarLayout>

    <ScrollView/>
  
</CoordinatorLayout>
```

### layout_scrollFlags

`layout_scrollFlags` 控制 `AppBarLayout` 下子 View 的行为。

|          值           |           作用           |
| :------------------: | :--------------------: |
|        scroll        |          跟随滑动          |
|         snap         | 避免停留在中间态，即滑动结束后全部显示或隐藏 |
|     enterAlways      |        下拉时立即显示         |
| enterAlwaysCollapsed |   下拉时立即显示（只显示折叠后的高度）   |
|  exitUntilCollapsed  |     上拉的时候，跟随滑动直到折叠     |
|          默认          |          静态显示          |

一些需要注意的点：

* AppBarLayout 实际上是一个 `orientation:vertical` 的 `LinearLayout` 

* 若一个 View 静态显示，那么在其下方的 View 即使设置了 `scroll` 也只能停留在其下方而不能继续往上滑动，相当于一个静态显示的 View 污染了下方所有 View 使它们都是静态显示

* `enterAlways` 虽说是下拉时立即显示，但也仅限于其下方没有设置了 `scroll` 的 View 或者下方的第一个 View 是静态显示（第二个点说到的污染问题）

* `enterAlways`, `enterAlwaysCollapsed` 和 `exitUntilCollapsed` 都需要配合 `scroll` 使用，不然跟静态显示没有区别

  ​

### layout_collapseMode

`layout_collapseMode` 控制 `CollapsingToolbarLayout` 下 View 的行为。`CollapsingToolbarLayout` 一般放两个 View，一个 `Toolbar` 和一个 View。

|    值     |                    作用                    |
| :------: | :--------------------------------------: |
|   pin    | 固定且不会因为 layout_scrollFlags = scroll 而被隐藏 |
| parallax |                   视差滚动                   |

故 `Toolbar` 设置 `pin` 而 View 作为 expended view 设置 `parallax`。



### title

一些需要注意的点：

* `CollapsingToolbarLayout` 和 `Toolbar` 均有 `title` 属性，但若同时设置前者的会覆盖后者的
* 若要使 `Toolbar` 均有 `title` 属性生效，需要 `CollapsingToolbarLayout` 的 `titleEnabled` 设为 **false**
* `CollapsingToolbarLayout` 的 title 有收缩和位移特性（可定制），而 `Toolbar` 的则只会一直停留在 `Toolbar` 上
