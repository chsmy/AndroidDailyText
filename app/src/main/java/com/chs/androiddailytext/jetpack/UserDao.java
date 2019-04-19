package com.chs.androiddailytext.jetpack;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author chs
 * date：2019-04-19 10:46
 * des：
 */
@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("select * from user")
    LiveData<List<User>> getUserList();

    @Query("delete from user")
    void deleteAll();

    @Update
    void updateUsers(User... users);
}
