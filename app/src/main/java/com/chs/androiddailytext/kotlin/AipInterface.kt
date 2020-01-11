package com.chs.androiddailytext.kotlin

import retrofit2.Call
import retrofit2.http.GET


/**
 *  @author chs
 *  date: 2020-01-08 15:21
 *  des:
 */
interface AipInterface {

    @GET("article/list/1/json")
    fun getHomeList() : Call<WanBaseResponse<Data>>

//    @GET("article/list/1/json")
//    suspend  fun getHomeList() : WanBaseResponse<Data>


//    // ================com/chs/androiddailytext/kotlin/AipInterface.class =================
//       // class version 50.0 (50)
//      // access flags 0x601
//        public abstract interface com/chs/androiddailytext/kotlin/AipInterface {
//
//
//        // access flags 0x401
//        // signature (Lkotlin/coroutines/Continuation<-Lcom/chs/androiddailytext/kotlin/WanBaseResponse<Lcom/chs/androiddailytext/kotlin/Data;>;>;)Ljava/lang/Object;
//        // declaration:  getHomeList(kotlin.coroutines.Continuation<? super com.chs.androiddailytext.kotlin.WanBaseResponse<com.chs.androiddailytext.kotlin.Data>>)
//        public abstract getHomeList(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
//        @Lretrofit2/http/GET;(value="article/list/1/json")
//        @Lorg/jetbrains/annotations/Nullable;() // invisible
//        // annotable parameter count: 1 (visible)
//        // annotable parameter count: 1 (invisible)
//        @Lorg/jetbrains/annotations/NotNull;() // invisible, parameter 0
//        LOCALVARIABLE this Lcom/chs/androiddailytext/kotlin/AipInterface; L0 L1 0
//
//        @Lkotlin/Metadata;(mv={1, 1, 16}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0008f\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u0008\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\u0008\u0019\u00a8\u0006\u0006"}, d2={"Lcom/chs/androiddailytext/kotlin/AipInterface;", "", "getHomeList", "Lcom/chs/androiddailytext/kotlin/WanBaseResponse;", "Lcom/chs/androiddailytext/kotlin/Data;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
//        // compiled from: AipInterface.kt
//    }
}