# overview

如果图片看不到，可能是因为防盗链了，请直接看这里 http://www.jianshu.com/p/cba6663435c7


最近 ***Android-CleanArchitecture*** 闹得是沸沸扬扬，然而笔者也不甘寂寞，一直在研究这个东西，看过，不少的实现。

比如 android10  大神的实现 https://github.com/android10/Android-CleanArchitecture
比如 googlesample 的实现  https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean/
比如 dmilicic 大神的实现 https://github.com/dmilicic/Android-Clean-Boilerplate  

Boilerplate 其实就是 模板的意思了，相信大家也看过不少这个词了。

然而，架构方面的文章也很多，但是，究其源头，无非都是出自uncle-bob 叔叔的这篇 https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html。
至于大家为什么大谈特谈，那一定是有他的道理的。就好比，用了CleanArchitecture，你会得到以下好处。

>**代码复用性更高**
>**更易于测试**
>**耦合度更小**

下面这幅图，是googlesample下面的了。
![googlesamples](http://upload-images.jianshu.io/upload_images/1019822-cda363d399934d04.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

下面这幅图，是uncle-bob画的了。
![uncle-bob](http://upload-images.jianshu.io/upload_images/1019822-b2acfd9ed6182541.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

细心的你已经发现了，这两个图其实是一个意思。从大的方向上看，都是三层结构。

> **DataLayer**

最底层，完全不知道有`DomainLayer`，`PresentationLayer`的存在，听到这里，你还在怀疑这个架构的`可测试性`和`耦合度低`吗？那么`DataLayer`的主要职责是什么？
1、从网络获取数据，向网络提交数据，总之就是和网络打交道。
2、从本地DB，shareprefence等等，内存等，总之就是本地获取数据，缓存数据，总之就是和本地数据打交道的。
这也就是你为什么看到很多Android-CleanArchitecture 的 package里面有一个local ,和一个remote了，然而是否有必要分的这么细，个人习惯啊~，不强求。反正这一层如果出现了 anroid.os***,我就更你拼了，对不起，你已经偏离了Android-CleanArchitecture了。
> **DomainLayer**

中间层，他完全不知道有一个`PresentationLayer`存在，他只知道，有DataLayer，他可以基于这些数据，建立很多玩法，比如去网络拿一堆名人回来，然后将这些数据缓存到本地，在比如，他写了一篇黑某明星的文章，将文字发布到网上等等。因此他的主要职责是:
1、控制`DataLayer`对数据做***增删改查***，没错，就这么简单，然后就没有然后了。
2、真的没有了，不骗你，但是这一层如果出现了 anroid.os***,我就更你拼了，对不起，你已经偏离了Android-CleanArchitecture了。

> **PresentationLayer**

最上层，他知道`DomainLayer`，有人要问了，那么他知道`DataLayer`，回答，他知道你妹~ 他累不累啊，要知道这么多？
因此，它只知道`DomainLayer`，那么他的职责有哪些？
1、通知`DomainLayer`有活干了，根据`DomainLayer`反馈变化界面
2、通知`DomainLayer`有活干了，根据`DomainLayer`反馈变化界面
3、通知`DomainLayer`有活干了，根据`DomainLayer`反馈变化界面
这年头，重要的时间一定要说三遍，而且，就是这么任性~~

分析了每层之后，我们发现，依赖的关系是 **PresentationLayer** --> **DomainLayer**  --> **DataLayer** 的。
**DomainLayer**  --> **DataLayer** 不知道有android平台的存在。
因此，只要我们围绕这个原则去做架构，那么就称的上是Android-CleanArchitecture。

