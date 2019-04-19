package com.chs.androiddailytext.jetpack;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * @author chs
 * date：2019-04-19 12:14
 * des：
 */
public class UserRepository {

    private UserDao mUserDao;

    private LiveData<List<User>> allUser;

    public UserRepository(Application application) {
        UserRoomDatabase db = UserRoomDatabase.getInstance(application);
        mUserDao = db.userDao();
        allUser = mUserDao.getUserList();
//        new InitThread(application).start();
    }

    public LiveData<List<User>> getAllUser() {
        return allUser;
    }

    public void update(User user){
        new UpdateAsyncTask(mUserDao).execute(user);
    }

    public void deleteAll(){
        new DeleteAsyncTask(mUserDao).execute();
    }

    public void insert(User user){
     new InsertAsyncTask(mUserDao).execute(user);
    }

    private  class InitThread extends Thread{
        Application application;
        InitThread(Application application){
            this.application = application;
        }
        @Override
        public void run() {
            UserRoomDatabase db = UserRoomDatabase.getInstance(application);
            mUserDao = db.userDao();
            allUser = mUserDao.getUserList();
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        UpdateAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.updateUsers(params[0]);
            return null;
        }
    }
    private static class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mAsyncTaskDao;

        InsertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao mAsyncTaskDao;
        DeleteAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
