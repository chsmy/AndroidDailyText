package com.chs.androiddailytext.dagger;

import dagger.Component;

/**
 * 作者：chs
 * 时间：2018-05-04 16:37
 * 描述：
 */
@Component
public interface UserComponent {
    void inject(User user);
}
