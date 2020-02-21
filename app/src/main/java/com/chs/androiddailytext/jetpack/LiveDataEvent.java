package com.chs.androiddailytext.jetpack;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author：chs
 * date：2020/2/21
 * des：
 */
public class LiveDataEvent {

    private LiveDataEvent(){}

    private static class SingletonHolder{
        private static final LiveDataEvent sLiveDataEvent = new LiveDataEvent();
    }

    public static LiveDataEvent get(){
        return SingletonHolder.sLiveDataEvent;
    }

    private ConcurrentHashMap<String, BusLiveData<Object>> mMap = new ConcurrentHashMap<>();

    public  BusLiveData<Object> with(String key){
        BusLiveData<Object> busLiveData = mMap.get(key);
        if(busLiveData == null){
            busLiveData = new BusLiveData<>(key);
            mMap.put(key,busLiveData);
        }
        return busLiveData;
    }

     public class BusLiveData<T> extends LiveData<T>{

        private int mCurrentVersion;
        private String mEventName;

        BusLiveData(String eventName) {
            mEventName = eventName;
        }

        @Override
        protected void setValue(T value) {
            mCurrentVersion++;
            super.setValue(value);
        }

        @Override
        protected void postValue(T value) {
            mCurrentVersion++;
            super.postValue(value);
        }

        public void setStickyData(T stickyData) {
            setValue(stickyData);
        }

        public void postStickyData(T stickyData){
            postValue(stickyData);
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            observe(owner,observer,false);
        }

         public void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer){
             observe(owner,observer,true);
         }

        private void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer,boolean isSticky){
            super.observe(owner, new ObserverWrapper<T>(this,isSticky,observer));
            owner.getLifecycle().addObserver((LifecycleEventObserver) (source, event) -> {
                //当页面销毁的时候，移除掉当前事件
                if(event == Lifecycle.Event.ON_DESTROY){
                    mMap.remove(mEventName);
                }
            });
        }

    }

    private class ObserverWrapper<T> implements Observer<T>{

        private int mLastVersion;
        private BusLiveData mBusLiveData;
        /**
         * 是否是黏性事件
         */
        private boolean isSticky;
        private Observer<? super T> mObserver;

        ObserverWrapper(BusLiveData busLiveData, boolean isSticky, Observer<? super T> observer) {
            mBusLiveData = busLiveData;
            this.isSticky = isSticky;
            mObserver = observer;
            //当前刚创建的观察者的version 赋值为BusLiveData中的version，这样就不会立即收到之前的信息。
            //当BusLiveData中postValue ,setValue 的时候，busLiveData.mCurrentVersion会增大,当前新建的
            //observer就能收到之后的信息了
            mLastVersion = busLiveData.mCurrentVersion;
        }

        @Override
        public void onChanged(T t) {
            //到这里的时候，执行了onChanged方法，此时mBusLiveData.mCurrentVersion肯定执行了++操作。
            if(mLastVersion<mBusLiveData.mCurrentVersion){
                mLastVersion =  mBusLiveData.mCurrentVersion;
                mObserver.onChanged(t);
            }else {
                //mLastVersion<=mBusLiveData.mCurrentVersion 首次创建的时候会走到这里
                if(isSticky&&t!=null){
                   //如多是黏性事件，那么就应该马上收到消息
                    mObserver.onChanged(t);
                }
            }
        }
    }
}
