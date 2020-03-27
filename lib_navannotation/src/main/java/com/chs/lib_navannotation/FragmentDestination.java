package com.chs.lib_navannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * author：chs
 * date：2020/3/26
 * des：
 */
@Target(ElementType.TYPE)
public @interface FragmentDestination {

    /**
     * @return 页面路径
     */
    String pageUrl();

    /**
     *
     * @return 是否需要登录
     */
    boolean needLogin() default false;

    /**
     * @return 是否是启动页
     */
    boolean asStarter() default false;
}
