package com.chs.androiddailytext.jetpack;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author chs
 * date：2019-04-19 11:03
 * des：
 */
@Database(entities = {User.class},version = 2,exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {
   public abstract UserDao userDao();

   public static UserRoomDatabase instance;

   public static UserRoomDatabase getInstance(Context context){
       if(instance == null){
           synchronized (UserRoomDatabase.class){
               if(instance == null){
                   instance = Room.databaseBuilder(context.getApplicationContext(),UserRoomDatabase.class
                   ,"user_database")
                           .addMigrations(MIGRATION_1_2)
                           .fallbackToDestructiveMigration()
                           .build();
               }
           }
       }
       return instance;
   }


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("alter table user add age INTEGER NOT NULL default 0");
        }
    };


}
