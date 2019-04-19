package com.chs.androiddailytext.jetpack;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author chs
 * date：2019-04-19 10:00
 * des：
 */
@Entity
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_")
    public int id;

    public String name;
    public String password;
    public String school;
    public int age;

}
