package com.chs.androiddailytext.jetpack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @author：chs date：2019/4/20
 * des：
 */
public class LiveDataBus {
    private final Map<String, BusMutableLiveData<Object>> mBus;

    private LiveDataBus() {
        mBus = new HashMap<>();
    }

    private static class SingletonHolder {
        private static final LiveDataBus DATA_BUS = new LiveDataBus();
    }

    public static LiveDataBus get() {
        return SingletonHolder.DATA_BUS;
    }

    public synchronized <T> MutableLiveData<T> with(String target, Class<T> type,boolean isFirstChange) {
        if (!mBus.containsKey(target)) {
            mBus.put(target, new BusMutableLiveData<>());
        }
        BusMutableLiveData liveData = mBus.get(target);
        liveData.isChangeData = isFirstChange;
        return (MutableLiveData<T>)liveData;
    }
    public synchronized <T> MutableLiveData<T> with(String target, Class<T> type) {
        return (MutableLiveData<T>) with(target,Object.class,false);
    }

    public MutableLiveData<Object> with(String target) {
        return with(target, Object.class);
    }


    private class BusMutableLiveData<T> extends MutableLiveData<T>{

        /**
         * 是否需要更新数据,当主动调用setValue或者postValue的时候才触发
         */
        private boolean isChangeData = false;

        @Override
        public void setValue(T value) {
            isChangeData = true;
            super.setValue(value);
        }

        @Override
        public void postValue(T value) {
            isChangeData = true;
            super.postValue(value);
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, new ObserverWrapper<T>(observer,this));
        }
    }

    private class ObserverWrapper<T> implements Observer<T>{

        private Observer<? super T> mObserver;
        private BusMutableLiveData<T> mLiveData;

        public ObserverWrapper(Observer<? super T> observer,BusMutableLiveData<T> liveData) {
            mObserver = observer;
            mLiveData = liveData;
        }

        @Override
        public void onChanged(T t) {
            if(mLiveData.isChangeData&&mObserver!=null){
                mObserver.onChanged(t);
            }
        }
    }

//    /**
//     * 重写MutableLiveData，实现它的observe方法，在其中反射改变mLastVersion的值
//     * @param <T>
//     */
//    private class BusMutableLiveData<T> extends MutableLiveData<T>{
//        @Override
//        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
//            super.observe(owner, observer);
//            //先调用super方法，将observer的包装对象放入map中在反射更改。
//            try {
//                hook(observer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        private void hook(Observer<? super T> observer) throws Exception {
//            //获取LiveData的class
//            Class<LiveData> liveDataClass = LiveData.class;
//            //反射回去LiveData的成员变量mObservers
//            Field fileObservers = liveDataClass.getDeclaredField("mObservers");
//            //设置该属性可更改
//            fileObservers.setAccessible(true);
//            //get方法获取的是当前对象的实例，这里就是mObservers这个Map集合
//            Object objectObservers  = fileObservers.get(this);
//            //获取map对象的类
//            Class<?> classObservers = objectObservers.getClass();
//            //获取集合的Map方法
//            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
//            //设置get方法可以被访问
//            methodGet.setAccessible(true);
//            //执行get方法拿出当前观察者对应的对象
//            Object objectWrapperEntry = methodGet.invoke(objectObservers,observer);
//            //定义一个空对象
//            Object objectWrapper =  null;
//            //判断objectWrapperEntry是否是Map.Entry类型
//            if(objectWrapperEntry instanceof Map.Entry){
//                //如果是拿出他的值,其实就是LifecycleBoundObserver
//                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
//            }
//            //如果是空抛个异常
//            if(objectWrapper == null){
//                throw new RuntimeException("objectWrapper is null");
//            }
//            //因为mLastVersion在LifecycleBoundObserver的父类ObserverWrapper中，所以拿到它的父类
//            Class<?> classObserverWrapper  = objectWrapper.getClass().getSuperclass();
//            //获取到mLastVersion字段
//            Field fieldLastVersion  = classObserverWrapper.getDeclaredField("mLastVersion");
//            //设置该字段可以更改
//            fieldLastVersion.setAccessible(true);
//
//            //获取LiveData中的mVersion值
//            Field fileVersion = liveDataClass.getDeclaredField("mVersion");
//            //设置该值可以被访问
//            fileVersion.setAccessible(true);
//            //获取mVersion的值
//            Object objVersion = fileVersion.get(this);
//            //给mLastVersion赋值
//            fieldLastVersion.set(objectWrapper,objVersion);
//        }
//    }

}
