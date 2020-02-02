package com.chs.androiddailytext.jetpack.paging;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author：chs date：2020/2/2
 * des：
 */
public class ArticleResponse {
    /**
     * data : {"curPage":2,"datas":[{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":304,"chapterName":"基础源码","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11528,"link":"https://juejin.im/entry/5e1d72a4f265da3df245dc1d","niceDate":"2020-01-16 21:37","niceShareDate":"2020-01-16 08:51","origin":"","prefix":"","projectLink":"","publishTime":1579181855000,"selfVisible":0,"shareDate":1579135912000,"shareUser":"goweii","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Android进阶之路&mdash;&mdash;Serializable序列化","type":0,"userId":20382,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11518,"link":"https://juejin.im/post/5e1e6cf66fb9a0301828ca0a","niceDate":"2020-01-16 21:37","niceShareDate":"2020-01-15 13:05","origin":"","prefix":"","projectLink":"","publishTime":1579181822000,"selfVisible":0,"shareDate":1579064721000,"shareUser":"Lgxing","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"深入探索Android布局优化（下）","type":0,"userId":29390,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":296,"chapterName":"阅读","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11527,"link":"https://juejin.im/post/5e1d36955188252c4f2ba56f","niceDate":"2020-01-16 21:36","niceShareDate":"2020-01-16 08:47","origin":"","prefix":"","projectLink":"","publishTime":1579181772000,"selfVisible":0,"shareDate":1579135648000,"shareUser":"于慢慢家的吴蜀黍","superChapterId":202,"superChapterName":"延伸技术","tags":[],"title":"未来 Android 开发的从业方向","type":0,"userId":1260,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11532,"link":"https://juejin.im/post/5e1fb9386fb9a0300a4501a6","niceDate":"2020-01-16 13:01","niceShareDate":"2020-01-16 13:01","origin":"","prefix":"","projectLink":"","publishTime":1579150917000,"selfVisible":0,"shareDate":1579150917000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（二、深入理解Retrofit源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11530,"link":"https://juejin.im/post/5e1ef08e5188252c6d368633","niceDate":"2020-01-16 09:12","niceShareDate":"2020-01-16 09:12","origin":"","prefix":"","projectLink":"","publishTime":1579137135000,"selfVisible":0,"shareDate":1579137135000,"shareUser":"xuexiangjys","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"极光推送之Android客户端使用指南--基础篇","type":0,"userId":15137,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"承香墨影","canEdit":false,"chapterId":411,"chapterName":"承香墨影","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11582,"link":"https://mp.weixin.qq.com/s/ClAMGgMPQ1NNW5vdzxf91g","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 20:58","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438681000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"收拢图片，可以优化内存避免 OOM，但是收拢不是说说而已！(以Glide举例)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11590,"link":"https://mp.weixin.qq.com/s/vSLi1ePfo-FiqM_yxy5dAg","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438831000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 组件化工程结构以及项目具体实施方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11591,"link":"https://mp.weixin.qq.com/s/tZ_4eIsf6I7YqiRteB20Hg","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438850000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"精选！近 3 年常考的 Spring 面试题（附答案）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11524,"link":"https://blog.csdn.net/Rain_9155/article/details/103993278","niceDate":"2020-01-15 23:02","niceShareDate":"2020-01-15 17:34","origin":"","prefix":"","projectLink":"","publishTime":1579100554000,"selfVisible":0,"shareDate":1579080880000,"shareUser":"rain9155","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"RecyclerView之缓存设计","type":0,"userId":12884,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>这个问题其实不算一个太好的问题，但是也能考察事件的分发流程，搞清楚 Window,Activity,DecorView 在事件分发环节的调用流程。<\/p>","descMd":"","envelopePic":"","fresh":false,"id":11363,"link":"https://www.wanandroid.com/wenda/show/11363","niceDate":"2020-01-15 19:24","niceShareDate":"2020-01-04 20:53","origin":"","prefix":"","projectLink":"","publishTime":1579087495000,"selfVisible":0,"shareDate":1578142403000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  为什么 Dialog 默认弹出后 Activity 就无法响应用户事件了？","type":0,"userId":2,"visible":1,"zan":20},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11519,"link":"https://juejin.im/post/5e1eaa2b6fb9a0301461c2ef","niceDate":"2020-01-15 15:30","niceShareDate":"2020-01-15 14:16","origin":"","prefix":"","projectLink":"","publishTime":1579073402000,"selfVisible":0,"shareDate":1579068992000,"shareUser":"张风捷特烈","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"[ - OpenGLES3.0 - ] 第三集 主线 - shader着色器与图片特效","type":0,"userId":31634,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11505,"link":"https://www.jianshu.com/p/79f573bd0938","niceDate":"2020-01-15 00:39","niceShareDate":"2020-01-14 21:24","origin":"","prefix":"","projectLink":"","publishTime":1579019949000,"selfVisible":0,"shareDate":1579008285000,"shareUser":"残页","superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"源码分析 Android 9.0 http请求适配原理","type":0,"userId":12467,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":157,"chapterName":"获取设备唯一标识","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11507,"link":"https://www.jianshu.com/p/52e14654e842","niceDate":"2020-01-15 00:38","niceShareDate":"2020-01-15 00:24","origin":"","prefix":"","projectLink":"","publishTime":1579019910000,"selfVisible":0,"shareDate":1579019060000,"shareUser":"鸿洋","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"4-3-1-如何确定Android设备唯一识别码","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":432,"chapterName":"UI渲染","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11508,"link":"https://juejin.im/post/5e1bccc35188254db85ef852","niceDate":"2020-01-15 00:37","niceShareDate":"2020-01-15 00:36","origin":"","prefix":"","projectLink":"","publishTime":1579019821000,"selfVisible":0,"shareDate":1579019773000,"shareUser":"鸿洋","superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android VSYNC （Choreographer）与UI刷新原理分析","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11570,"link":"https://mp.weixin.qq.com/s/wmFBPYkwGh8ijQMODF27Fw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:51","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438288000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"布局还能异步加载？AsyncLayoutInflater一点小经验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11575,"link":"https://mp.weixin.qq.com/s/4Z_Do6kC8WC5u5qnGgZNKw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:53","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438383000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"2019年11月，分析Android原生开发的现状","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"互联网侦察","canEdit":false,"chapterId":421,"chapterName":"互联网侦察","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11579,"link":"https://mp.weixin.qq.com/s/THmKqvEhSFFufmpCct9k0Q","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:55","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438546000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/421/1"}],"title":"最近从 0 学习Git，详细分类总结了这份 Git 命令宝典","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11584,"link":"https://mp.weixin.qq.com/s/5NB6lmm65S-iwDz8RD75AQ","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:58","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438732000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"Flutter 开发必备 Dart 基础: Dart 快速入门 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11588,"link":"https://mp.weixin.qq.com/s/1s1PL6QeuZ7Zr5cOEyn3rw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438816000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Docker 的 6 年之痒！","type":0,"userId":-1,"visible":1,"zan":0}],"offset":20,"over":false,"pageCount":396,"size":20,"total":7916}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * curPage : 2
         * datas : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":304,"chapterName":"基础源码","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11528,"link":"https://juejin.im/entry/5e1d72a4f265da3df245dc1d","niceDate":"2020-01-16 21:37","niceShareDate":"2020-01-16 08:51","origin":"","prefix":"","projectLink":"","publishTime":1579181855000,"selfVisible":0,"shareDate":1579135912000,"shareUser":"goweii","superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"Android进阶之路&mdash;&mdash;Serializable序列化","type":0,"userId":20382,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":494,"chapterName":"广场","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11518,"link":"https://juejin.im/post/5e1e6cf66fb9a0301828ca0a","niceDate":"2020-01-16 21:37","niceShareDate":"2020-01-15 13:05","origin":"","prefix":"","projectLink":"","publishTime":1579181822000,"selfVisible":0,"shareDate":1579064721000,"shareUser":"Lgxing","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"深入探索Android布局优化（下）","type":0,"userId":29390,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":296,"chapterName":"阅读","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11527,"link":"https://juejin.im/post/5e1d36955188252c4f2ba56f","niceDate":"2020-01-16 21:36","niceShareDate":"2020-01-16 08:47","origin":"","prefix":"","projectLink":"","publishTime":1579181772000,"selfVisible":0,"shareDate":1579135648000,"shareUser":"于慢慢家的吴蜀黍","superChapterId":202,"superChapterName":"延伸技术","tags":[],"title":"未来 Android 开发的从业方向","type":0,"userId":1260,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11532,"link":"https://juejin.im/post/5e1fb9386fb9a0300a4501a6","niceDate":"2020-01-16 13:01","niceShareDate":"2020-01-16 13:01","origin":"","prefix":"","projectLink":"","publishTime":1579150917000,"selfVisible":0,"shareDate":1579150917000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"Android主流三方库源码分析（二、深入理解Retrofit源码）","type":0,"userId":611,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11530,"link":"https://juejin.im/post/5e1ef08e5188252c6d368633","niceDate":"2020-01-16 09:12","niceShareDate":"2020-01-16 09:12","origin":"","prefix":"","projectLink":"","publishTime":1579137135000,"selfVisible":0,"shareDate":1579137135000,"shareUser":"xuexiangjys","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"极光推送之Android客户端使用指南--基础篇","type":0,"userId":15137,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"承香墨影","canEdit":false,"chapterId":411,"chapterName":"承香墨影","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11582,"link":"https://mp.weixin.qq.com/s/ClAMGgMPQ1NNW5vdzxf91g","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 20:58","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438681000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"收拢图片，可以优化内存避免 OOM，但是收拢不是说说而已！(以Glide举例)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11590,"link":"https://mp.weixin.qq.com/s/vSLi1ePfo-FiqM_yxy5dAg","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438831000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 组件化工程结构以及项目具体实施方案","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11591,"link":"https://mp.weixin.qq.com/s/tZ_4eIsf6I7YqiRteB20Hg","niceDate":"2020-01-16 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579104000000,"selfVisible":0,"shareDate":1579438850000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"精选！近 3 年常考的 Spring 面试题（附答案）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11524,"link":"https://blog.csdn.net/Rain_9155/article/details/103993278","niceDate":"2020-01-15 23:02","niceShareDate":"2020-01-15 17:34","origin":"","prefix":"","projectLink":"","publishTime":1579100554000,"selfVisible":0,"shareDate":1579080880000,"shareUser":"rain9155","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"RecyclerView之缓存设计","type":0,"userId":12884,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>这个问题其实不算一个太好的问题，但是也能考察事件的分发流程，搞清楚 Window,Activity,DecorView 在事件分发环节的调用流程。<\/p>","descMd":"","envelopePic":"","fresh":false,"id":11363,"link":"https://www.wanandroid.com/wenda/show/11363","niceDate":"2020-01-15 19:24","niceShareDate":"2020-01-04 20:53","origin":"","prefix":"","projectLink":"","publishTime":1579087495000,"selfVisible":0,"shareDate":1578142403000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"问答","url":"/article/list/0?cid=440"}],"title":"每日一问  为什么 Dialog 默认弹出后 Activity 就无法响应用户事件了？","type":0,"userId":2,"visible":1,"zan":20},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11519,"link":"https://juejin.im/post/5e1eaa2b6fb9a0301461c2ef","niceDate":"2020-01-15 15:30","niceShareDate":"2020-01-15 14:16","origin":"","prefix":"","projectLink":"","publishTime":1579073402000,"selfVisible":0,"shareDate":1579068992000,"shareUser":"张风捷特烈","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"[ - OpenGLES3.0 - ] 第三集 主线 - shader着色器与图片特效","type":0,"userId":31634,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":200,"chapterName":"https","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11505,"link":"https://www.jianshu.com/p/79f573bd0938","niceDate":"2020-01-15 00:39","niceShareDate":"2020-01-14 21:24","origin":"","prefix":"","projectLink":"","publishTime":1579019949000,"selfVisible":0,"shareDate":1579008285000,"shareUser":"残页","superChapterId":98,"superChapterName":"网络访问","tags":[],"title":"源码分析 Android 9.0 http请求适配原理","type":0,"userId":12467,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":157,"chapterName":"获取设备唯一标识","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11507,"link":"https://www.jianshu.com/p/52e14654e842","niceDate":"2020-01-15 00:38","niceShareDate":"2020-01-15 00:24","origin":"","prefix":"","projectLink":"","publishTime":1579019910000,"selfVisible":0,"shareDate":1579019060000,"shareUser":"鸿洋","superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"4-3-1-如何确定Android设备唯一识别码","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":432,"chapterName":"UI渲染","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11508,"link":"https://juejin.im/post/5e1bccc35188254db85ef852","niceDate":"2020-01-15 00:37","niceShareDate":"2020-01-15 00:36","origin":"","prefix":"","projectLink":"","publishTime":1579019821000,"selfVisible":0,"shareDate":1579019773000,"shareUser":"鸿洋","superChapterId":153,"superChapterName":"framework","tags":[],"title":"Android VSYNC （Choreographer）与UI刷新原理分析","type":0,"userId":2,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"鸿洋","canEdit":false,"chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11570,"link":"https://mp.weixin.qq.com/s/wmFBPYkwGh8ijQMODF27Fw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:51","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438288000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"布局还能异步加载？AsyncLayoutInflater一点小经验","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"郭霖","canEdit":false,"chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11575,"link":"https://mp.weixin.qq.com/s/4Z_Do6kC8WC5u5qnGgZNKw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:53","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438383000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"2019年11月，分析Android原生开发的现状","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"互联网侦察","canEdit":false,"chapterId":421,"chapterName":"互联网侦察","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11579,"link":"https://mp.weixin.qq.com/s/THmKqvEhSFFufmpCct9k0Q","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:55","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438546000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/421/1"}],"title":"最近从 0 学习Git，详细分类总结了这份 Git 命令宝典","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"谷歌开发者","canEdit":false,"chapterId":415,"chapterName":"谷歌开发者","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11584,"link":"https://mp.weixin.qq.com/s/5NB6lmm65S-iwDz8RD75AQ","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 20:58","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438732000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/415/1"}],"title":"Flutter 开发必备 Dart 基础: Dart 快速入门 | 开发者说&middot;DTalk","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"code小生","canEdit":false,"chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":11588,"link":"https://mp.weixin.qq.com/s/1s1PL6QeuZ7Zr5cOEyn3rw","niceDate":"2020-01-15 00:00","niceShareDate":"2020-01-19 21:00","origin":"","prefix":"","projectLink":"","publishTime":1579017600000,"selfVisible":0,"shareDate":1579438816000,"shareUser":"","superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Docker 的 6 年之痒！","type":0,"userId":-1,"visible":1,"zan":0}]
         * offset : 20
         * over : false
         * pageCount : 396
         * size : 20
         * total : 7916
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * apkLink :
             * audit : 1
             * author :
             * canEdit : false
             * chapterId : 304
             * chapterName : 基础源码
             * collect : false
             * courseId : 13
             * desc :
             * descMd :
             * envelopePic :
             * fresh : false
             * id : 11528
             * link : https://juejin.im/entry/5e1d72a4f265da3df245dc1d
             * niceDate : 2020-01-16 21:37
             * niceShareDate : 2020-01-16 08:51
             * origin :
             * prefix :
             * projectLink :
             * publishTime : 1579181855000
             * selfVisible : 0
             * shareDate : 1579135912000
             * shareUser : goweii
             * superChapterId : 245
             * superChapterName : Java深入
             * tags : []
             * title : Android进阶之路&mdash;&mdash;Serializable序列化
             * type : 0
             * userId : 20382
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private int audit;
            private String author;
            private boolean canEdit;
            private int chapterId;
            private String chapterName;
            private boolean collect;
            private int courseId;
            private String desc;
            private String descMd;
            private String envelopePic;
            private boolean fresh;
            private int id;
            private String link;
            private String niceDate;
            private String niceShareDate;
            private String origin;
            private String prefix;
            private String projectLink;
            private long publishTime;
            private int selfVisible;
            private long shareDate;
            private String shareUser;
            private int superChapterId;
            private String superChapterName;
            private String title;
            private int type;
            private int userId;
            private int visible;
            private int zan;
            private List<?> tags;

            public String getApkLink() {
                return apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public boolean isCanEdit() {
                return canEdit;
            }

            public void setCanEdit(boolean canEdit) {
                this.canEdit = canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public void setDescMd(String descMd) {
                this.descMd = descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public void setNiceShareDate(String niceShareDate) {
                this.niceShareDate = niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public void setSelfVisible(int selfVisible) {
                this.selfVisible = selfVisible;
            }

            public long getShareDate() {
                return shareDate;
            }

            public void setShareDate(long shareDate) {
                this.shareDate = shareDate;
            }

            public String getShareUser() {
                return shareUser;
            }

            public void setShareUser(String shareUser) {
                this.shareUser = shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public void setSuperChapterId(int superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public int getZan() {
                return zan;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }

            @Override
            public boolean equals(@Nullable Object obj) {
                DatasBean cur = (DatasBean)obj;
                if(cur == null) return false;
                return cur.getId() == id&&cur.getPublishTime() == publishTime;
            }
        }
    }
}
