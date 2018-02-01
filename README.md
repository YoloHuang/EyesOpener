 EyesOpener
 -----
 EyesOpener，一款开眼高仿APP，
 基于 `MVP+rxjava2+Retrofit+Dagger2+Glide+Realm+GSYVideoPlayer`    
 出于学习的目的使用开眼API做的这款开源视频播放APP，基本涵盖了当前Android主流开源框架，是一个非常适合小白学习的项目。废话不多说，先上截图：
 
 #### tips
 * 项目还是测试阶段，如果大家有发现BUG或者什么好的建议，可直接提[issue](https://github.com/zhijianhuang/EyesOpener/issues)
 * 遇到`please select SDK`的情况，是因为项目需要API27支持，请下载最新SDK
 * 项目仅供学习使用，API数据属于原公司所有，请不要用于其他用途
 
 Point
 -----
 * 使用`MVP`对整个应用进行架构，分为`model`，`UI`，`presenter`三个部分。使用`contract`将`View`和`presenter`联系起来
 * 使用`Dagger2`将M层注入到P层，将P层注入到V层。可直接调用，无需`new`。
 * 使用`ButterKnife`对`view`进行注入
 * 使用`retrofit`和`rxjava2`进行网络请求
 * 使用`okhttp3`对网络返回数据进行缓存，配置相关日志，头部消息，超时连接
 * 使用`DataManager`对所有`model`层进行汇总整理，统一管理。
 * 使用`rxjava2`其他操作符进行延时，轮转操作  
 * 使用`RxPresenter`管理所有订阅的生命周期
 * 使用`realm`进行数据库增、删、改、查
 * 使用`GsonFormat`进行`JSON`解析
 * 使用`recyclerview`完成大部分界面，实现与`viewpager`结合，下拉刷新，上划加载更多
 * 使用`viewpager`与多个`fragment`结合完成主界面，自定义`TabLayout.Tab`实现开眼同等效果
 * 使用`GSYVideoPlayer`框架完成视频播放部分
 * 自定义`JumpShowTextView`实现字体跳转加载
 * 使用glide完成图片加载，圆形头像加载，圆角矩形加载
 * 使用`FlowLayout`实现搜索热门词流式布局
 * 使用`BlockCanary`,`LeakCanary`监测是否存在内存泄漏和过度绘制
 

Version
----
#### V 1.0.0
上传第一版

#### 下一步计划
 * 自定义播放界面，实现开眼播放界面效果
 * 在界面布局上面做新的尝试
 * 将`JumpShowTextView`集成为框架，完善`JumpShowTextView`
 * 将toast等公用操作进行集合整理
 
遇到的问题
----
 * 使用`diffutil`来做数据更新会出现新数据不会显示出来的问题
>   问题出现的原因是，`fragment`是直接将数据传递给`adapter`的，`adapter`中的`data`的引用，`跟fragment`中`data`引用一样。当更新`fragment`中的`data`，并将其与`adapter`中对比时，`adapter`中`data`已经产生变化，两者相同，所以`diffutil`无法起作用。解决办法是，不要使用相同引用。

 * 在`recyclerview`中添加`item`时，会出现宽高失效的问题
>  因为是添加的自定义`view`，自定义`framelayout`中已经设置了宽高，然后让`TextView`显示在`center`。结果是`TextView`永远显示在开头。排除了布局问题之后，先查找资料。发现[RecyclerView Item布局宽高无效问题探究](https://www.jianshu.com/p/9a6db88b8ad3)这篇文章。感觉问题原因应该跟这个差不多。这篇文章讲的是`inflate`方法使用错误导致的问题。但是我这里是使用自定义`view`加载的布局，问题同样是在于，`RecyclerView`在加载子`View`的时候，使用了默认布局的缘故。解决办法是，在加载子`View`的时候，先给它设置`LayoutParams`为`MATCH_PARENT`。

* `Topviewpager`切换时，由于调用了`JumpShowTextView`，会概率性出现`image`无法加载，`fragment`中`textview`也无法正常加载的问题
>  问题出现的原因是，我为顶部的`Topviewpager`设置了每隔六秒自动换页。而换页会调用`JumpShowTextView`。当整个`fragment`切换时，`Topviewpager`并不会停止换页，则会继续调用`JumpShowTextView`。从而阻塞了主界面的`UI`绘制，导致剩下的`UI`无法完成正常绘制。解决办法是，当fragment完成左右切换时，并不会调用`onPause`,`onStart`方法。我们需要找到一个当`fragment`不在界面显示时会调用的方法，并在其中关掉`Topviewpager`轮播功能。最终我们找到，当`fragment`不在界面显示时，会调用`setUserVisibleHint`方法，根据方法中`isVisibleToUser`参数，选择让`Topviewpager`关闭或继续轮播。

* 当`JumpShowTextView`在继续显示时，滑动`recyclerView`使其不显示。同样会出现`image`无法加载的问题。
> 问题出现的原因与上一条一致，解决办法是，在`recyclerView`的`setOnScrollListener`方法中进行判断，当`Topviewpager`不显示时，暂停其轮播。
 * `JumpShowTextView`中遇到的问题
> 我是采用`rxjava`中`interval`操作符来进行逐字显示的，所以会出现很多问题。比如当逐字显示到一半时，当前`View`不在主界面显示了。比如当逐字显示到一半时，调用了新的`setText`。这个时候会导致一系列问题。我选择，在`setText`中加入`withAnimation`和`isRun`两个参数进行判断。第一个参数用来判断是否调用逐字显示动画，第二个参数用来判断是否正在进行逐字显示。通过这两个参数可以有效的避免前面所说的问题，从而完成功能。但是可能还会存在些低概率事件没有测试到，这个会在接下来将`JumpShowTextView`制作成开源框架的过程中解决。


Thanks
----
#### API
没有选择自己抓API，选择直接使用了 [开眼API](https://github.com/jokermonn/-Api/blob/master/Eyepetizer.md)

#### RES
反编译了开眼APP，获取了其中的RES资源

#### LIB:

##### 主流框架
 * [Rxjava2](https://github.com/ReactiveX/RxJava)
 * [Dagger2](https://github.com/google/dagger)
 * [ButterKnife](https://github.com/JakeWharton/butterknife)
 
##### 网络 
 * [Retrofit](https://github.com/square/retrofit)
 * [Okhttp3](https://github.com/square/okhttp)
 * [Gson](https://github.com/google/gson)

##### UI
 * [Glide](https://github.com/bumptech/glide)
 * [Glide-transformations](https://github.com/wasabeef/glide-transformations)
 * [FlowLayout](https://github.com/hongyangAndroid/FlowLayout)
 * [GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer)
 
##### Other
 * [Realm](https://github.com/realm/realm-java)
 * [BlockCanary](https://github.com/markzhai/AndroidPerformanceMonitor)
 * [LeakCanary](https://github.com/square/leakcanary)

License
----
Copyright (c) 2018  EyesOpener

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 